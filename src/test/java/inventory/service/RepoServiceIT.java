package inventory.service;

import inventory.model.Part;
import inventory.repository.InventoryRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class RepoServiceIT {

    private InventoryService inventoryService;

    @Mock
    private Part part;

    @BeforeEach
    void setUp() {
        InventoryRepository repo = new InventoryRepository();
        inventoryService = new InventoryService(repo);

        MockitoAnnotations.initMocks(this);
        Mockito.when(part.toString()).thenReturn("");
        repo.addPart(part);
    }

    @AfterEach
    void tearDown() {
        inventoryService.deletePart(inventoryService.getAllParts().get(inventoryService.getAllParts().size()-1));
    }

    @Test
    void shouldFindPart() {
        Mockito.when(part.getName()).thenReturn("Part 1");

        Part result = inventoryService.lookupPart("Part 1");

        assertEquals(result, part);
    }

    @Test
    void shouldNullWhenNotFound() {
        Mockito.when(part.getName()).thenReturn("Part 1");

        Part result = inventoryService.lookupPart("abcdef");

        assertNull(result);
    }

}