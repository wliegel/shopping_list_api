# Stage 1: Build Application
# Verwenden Sie ein offizielles OpenJDK 17-Image als Basisimage zum Kompilieren der Anwendung
FROM maven:3.8.4-openjdk-17 AS builder

# Setzen Sie das Arbeitsverzeichnis im Container
WORKDIR /app

# Kopieren Sie das pom.xml, um Abhängigkeiten herunterzuladen
COPY pom.xml .

# Kopieren Sie die restlichen Quelldateien
COPY src ./src

# Führen Sie den Build-Prozess durch
RUN mvn clean package -DskipTests -Pprod


# Stage 2: Run Application
# Verwenden Sie ein offizielles OpenJDK 17-Image als Basisimage für die Ausführung der Anwendung
FROM openjdk:17-alpine

# Setzen Sie das Arbeitsverzeichnis im Container
WORKDIR /app

# Kopieren Sie das JAR-Paket aus dem Build-Stage-Container in das Arbeitsverzeichnis im Ausführungs-Container
COPY --from=builder /app/target/ShoppingListAPI-0.0.1-SNAPSHOT.jar /app/ShoppingListAPI-0.0.1-SNAPSHOT.jar

# Befehl zum Ausführen der Spring-Boot-Anwendung beim Starten des Containers
CMD ["java", "-jar", "ShoppingListAPI-0.0.1-SNAPSHOT.jar", "--spring.profiles.active=prod"]
