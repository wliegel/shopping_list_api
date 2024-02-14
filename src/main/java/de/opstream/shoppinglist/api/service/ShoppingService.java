package de.opstream.shoppinglist.api.service;

import de.opstream.shoppinglist.api.entity.ShoppingItemEntity;
import de.opstream.shoppinglist.api.exception.ShoppingItemNotFoundException;
import de.opstream.shoppinglist.api.jpa.ShoppingRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class ShoppingService {
    private final ShoppingRepository shoppingRepository;

    public ShoppingService(ShoppingRepository shoppingRepository) {
        this.shoppingRepository = shoppingRepository;
    }

    public List<ShoppingItemEntity> findAll() {
        return this.shoppingRepository.findAll();
    }

    public ShoppingItemEntity findById(long id) {
        return this.shoppingRepository.findById(id).orElseThrow(ShoppingItemNotFoundException::new);
    }

    public ShoppingItemEntity createShoppingItem(ShoppingItemEntity shoppingItemEntity) {
        Assert.notNull(shoppingItemEntity, "Shopping Item must not be null");
        Assert.isTrue(shoppingItemEntity.getId() == null || shoppingItemEntity.getId() == 0,
                "Shopping Item id must not be set when creating a new Item");
        return this.shoppingRepository.saveAndFlush(shoppingItemEntity);
    }

    public ShoppingItemEntity updateShoppingItem(Long id, ShoppingItemEntity shoppingItemEntity) {
        Assert.isTrue(id != null && id > 0, "Shopping Item ID must be set.");
        ShoppingItemEntity shoppingItem = this.shoppingRepository.findById(id).orElseThrow(ShoppingItemNotFoundException::new);
        shoppingItem.setName(shoppingItemEntity.getName());
        shoppingItem.setCategory(shoppingItemEntity.getCategory());
        shoppingItem.setDescription(shoppingItemEntity.getDescription());
        shoppingItem.setActive(shoppingItemEntity.isActive());
        return this.shoppingRepository.saveAndFlush(shoppingItem);
    }

    public void deleteShoppingItem(Long id) {
        Assert.isTrue(id != null && id > 0, "Shopping Item ID must be set.");
        ShoppingItemEntity shoppingItem = this.shoppingRepository.findById(id).orElseThrow(ShoppingItemNotFoundException::new);
        this.shoppingRepository.delete(shoppingItem);
    }
}
