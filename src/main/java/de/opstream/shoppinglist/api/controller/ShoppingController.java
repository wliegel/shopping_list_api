package de.opstream.shoppinglist.api.controller;

import de.opstream.shoppinglist.api.service.ShoppingService;
import de.opstream.shoppinglist.api.entity.ShoppingItemEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequestMapping("/api/v1/shoppingitems")
@RestController
public class ShoppingController {

    private final ShoppingService shoppingService;

    public ShoppingController(ShoppingService shoppingService) {
        this.shoppingService = shoppingService;
    }

    @GetMapping
    public List<ShoppingItemEntity> getShoppingItems() {
        log.debug("Get all shopping items.");

        return shoppingService.findAll();
    }
}
