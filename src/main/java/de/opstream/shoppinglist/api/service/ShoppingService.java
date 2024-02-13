package de.opstream.shoppinglist.api.service;

import de.opstream.shoppinglist.api.entity.ShoppingItemEntity;
import de.opstream.shoppinglist.api.jpa.ShoppingRepository;
import org.springframework.stereotype.Service;

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
}
