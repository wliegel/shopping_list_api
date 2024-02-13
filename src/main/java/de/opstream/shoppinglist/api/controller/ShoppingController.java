package de.opstream.shoppinglist.api.controller;

import de.opstream.shoppinglist.api.exception.ShoppingItemNotFoundException;
import de.opstream.shoppinglist.api.service.ShoppingService;
import de.opstream.shoppinglist.api.entity.ShoppingItemEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Operation(summary = "Get a shopping item by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the item.", content = {
                    @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ShoppingItemEntity.class)
                    )
            }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Shopping item not found.", content = @Content)
    })
    @GetMapping("/{id}")
    public ShoppingItemEntity getShoppingItem(@Parameter(description = "id of the shopping item to be returned.") @PathVariable long id) {
        log.debug("Get a shopping item for id {}", id);
        return shoppingService.findById(id).orElseThrow(ShoppingItemNotFoundException::new);
    }
}
