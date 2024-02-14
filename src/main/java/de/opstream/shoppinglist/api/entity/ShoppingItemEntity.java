package de.opstream.shoppinglist.api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "SHOPPING_ITEM")
public class ShoppingItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @NotBlank
    public String name;
    @NotBlank
    public String category;
    public String description;
    public boolean active = false;
}
