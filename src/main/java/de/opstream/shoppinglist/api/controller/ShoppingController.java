package de.opstream.shoppinglist.api.controller;

import de.opstream.shoppinglist.api.entity.ShoppingItemEntity;
import de.opstream.shoppinglist.api.service.ShoppingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/api/v1/shoppingitems")
@RestController
public class ShoppingController {

    private final ShoppingService shoppingService;

    public ShoppingController(ShoppingService shoppingService) {
        this.shoppingService = shoppingService;
    }

    /**
     * Get all Shopping items from Database
     * GET Method
     * @return a list of Shopping Items
     */
    @Operation(summary = "Get the shopping list.", description = "Get the full shopping list from the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The shopping list will be returned.", content = {
                    @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = ShoppingItemEntity.class))
                    )
            })
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ShoppingItemEntity>> getShoppingItems() {
        log.debug("Get all shopping items.");
        List<ShoppingItemEntity> shoppingItemEntities = shoppingService.findAll();
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(shoppingItemEntities);
    }

    /**
     * Get a single Shopping Item from Database
     * GET Method
     * @param id ID of the Shopping Item
     * @return the Shopping Item for the requested ID
     */
    @Operation(summary = "Get a shopping item by its id.", description = "Retrieve a shopping item identified by its id.")
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
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ShoppingItemEntity> getShoppingItem(@Parameter(description = "id of the shopping item to be returned.", required = true) @PathVariable Long id) {
        log.debug("Get a shopping item for id {}", id);
        ShoppingItemEntity shoppingItemEntity = shoppingService.findById(id);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(shoppingItemEntity);
    }

    /**
     * Create a new Shopping Item
     * POST Method
     * @param shoppingItemEntity the new Shopping Item data
     * @return the created Shopping Item
     */
    @Operation(summary = "Create a new shopping item.", description = "Creates a new shopping item in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created the shopping item.", content = {
                    @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ShoppingItemEntity.class)
                    )
            }),
            @ApiResponse(responseCode = "400", description = "Invalid data supplied.", content = @Content)
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ShoppingItemEntity> createNewShoppingItem(@Valid @RequestBody ShoppingItemEntity shoppingItemEntity) {
        log.debug("Create a new Shopping Item. Name: {}, Category: {}, Description: {}",
                shoppingItemEntity.getName(), shoppingItemEntity.getCategory(), shoppingItemEntity.getDescription());
        ShoppingItemEntity newShoppingItem = shoppingService.createShoppingItem(shoppingItemEntity);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(newShoppingItem);
    }

    /**
     * Update an existing Shopping Item
     * PUT Method
     * @param id the ID of the Shopping Item that should be updated.
     * @param shoppingItemEntity The new Shopping Item data.
     * @return the updated Shopping Item
     */
    @Operation(summary = "Update a shopping item.", description = "Updates an existing shopping item in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated the shopping item.", content = {
                    @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ShoppingItemEntity.class)
                    )
            }),
            @ApiResponse(responseCode = "400", description = "Invalid data supplied.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Shopping item not found.", content = @Content)
    })
    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ShoppingItemEntity> updateShoppingItem(
            @Parameter(description = "id of the shopping item to be returned.", required = true) @PathVariable Long id,
            @Valid @RequestBody ShoppingItemEntity shoppingItemEntity) {
        log.debug("Update an existing Shopping Item with ID {}. Name: {}, Category: {}, Description: {}",
                shoppingItemEntity.getId(),
                shoppingItemEntity.getName(),
                shoppingItemEntity.getCategory(),
                shoppingItemEntity.getDescription());
        ShoppingItemEntity newShoppingItem = shoppingService.updateShoppingItem(id, shoppingItemEntity);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(newShoppingItem);
    }


    /**
     * Delete an existing Shopping Item
     * DELETE Method
     * @param id the ID of the Shopping Item that should be updated.
     */
    @Operation(summary = "Delete a shopping item.", description = "Deletes an existing shopping item from the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted the shopping item.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Shopping item not found.", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShoppingItem(
            @Parameter(description = "id of the shopping item to be deleted", required = true) @PathVariable("id") Long id) {
        log.debug("delete an existing shopping Item with ID {}.", id);
        this.shoppingService.deleteShoppingItem(id);
        return ResponseEntity.noContent().build();
    }
}
