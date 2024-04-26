package inventory.service;

import inventory.model.OutsourcedPart;
import inventory.model.Part;
import inventory.repository.InventoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class InventoryServiceUnitTest {

    private InventoryService inventoryService;

    @Mock
    private InventoryRepository repo;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        inventoryService = new InventoryService(repo);
    }

    @Test
    void shouldFindPart() {
        Mockito.when(repo.lookupPart("Part 1")).thenReturn(new OutsourcedPart(1, "Part 1", 1.0, 1, 0, 10, "CompanySRL"));

        Part result = inventoryService.lookupPart("Part 1");

        assertNotNull(result);
        assertEquals("Part 1", result.getName());
        assertEquals(1.0, result.getPrice());
        assertEquals(1, result.getInStock());
        assertEquals(0, result.getMin());
        assertEquals(10, result.getMax());
    }


    @Test
    void shouldNullWhenNotFound() {
        Mockito.when(repo.lookupPart("Part 1")).thenReturn(null);

        Part result = inventoryService.lookupPart("Part 1");

        assertNull(result);
    }

}