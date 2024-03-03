## Build and run Docker image
<pre>
docker compose up -d --build
</pre>

## Shutdown Docker image
<pre>
docker compose down
</pre>

## Clean up Docker image and resources
<pre>
docker image rm shoppinglistapi:latest
docker image prune
docker network prune
</pre>
