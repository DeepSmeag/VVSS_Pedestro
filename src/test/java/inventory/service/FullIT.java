package inventory.service;

import inventory.model.Part;
import inventory.repository.InventoryRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class FullIT {

    private InventoryService inventoryService;

    private Part part = new Part(1, "Part 1", 1.0, 1, 0, 10);


    @BeforeEach
    void setUp() {
        InventoryRepository repo = new InventoryRepository();
        inventoryService = new InventoryService(repo);
        repo.addPart(part);
    }

    @AfterEach
    void tearDown() {
        inventoryService.deletePart(inventoryService.getAllParts().get(inventoryService.getAllParts().size()-1));
    }

    @Test
    void shouldFindPart() {
        Part result = inventoryService.lookupPart("Part 1");

        assertEquals(result, part);
    }

    @Test
    void shouldNullWhenNotFound() {
        Part result = inventoryService.lookupPart("abcdef");

        assertNull(result);
    }

}