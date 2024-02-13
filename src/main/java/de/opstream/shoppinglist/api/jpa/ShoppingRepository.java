package de.opstream.shoppinglist.api.jpa;

import de.opstream.shoppinglist.api.entity.ShoppingItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingRepository extends JpaRepository<ShoppingItemEntity, Long> {
}
