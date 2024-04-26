package inventory.service;

import inventory.model.Part;
import inventory.repository.InventoryRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class InventoryServiceTest {

    private InventoryService inventoryService;


    @BeforeEach
    public void setUp() {
        InventoryRepository repo = new InventoryRepository();
        inventoryService = new InventoryService(repo);
    }

    @AfterEach
    public void tearDown() {
        Part part = inventoryService.getAllParts().get(inventoryService.getAllParts().size()-1);
        if(part.getName().equals("Part") || part.getName().equals("a"))
            inventoryService.deletePart(part);
    }


    @Test
    public void shouldCreateValidPart() {
        int initialSize = inventoryService.getAllParts().size();
        String name = "Part";
        double price = 1.0;
        int min = 0;
        int max = 10;
        String companyName = "CompanySRL";

        inventoryService.addOutsourcePart(name, price, 0, min, max, companyName);

        assertEquals(initialSize + 1, inventoryService.getAllParts().size());
        assertEquals(name, inventoryService.getAllParts().get(initialSize).getName());
        assertEquals(price, inventoryService.getAllParts().get(initialSize).getPrice());
        assertEquals(0, inventoryService.getAllParts().get(initialSize).getInStock());
        assertEquals(min, inventoryService.getAllParts().get(initialSize).getMin());
        assertEquals(max, inventoryService.getAllParts().get(initialSize).getMax());
    }

    @Test
    public void shouldThrowExceptionWithNegativeStock() {
        String name = "Part";
        double price = 1.0;
        int inStock = -1;
        int min = 0;
        int max = 10;
        String companyName = "CompanySRL";

        assertThrows(Exception.class, () -> inventoryService.addOutsourcePart(name, price, inStock, min, max, companyName));
    }

    @Test
    public void shouldCreateValidPartWithValidName(){
        int initialSize = inventoryService.getAllParts().size();
        double price = 1.0;
        int value = 1;
        int min = 0;
        int max = 10;
        String companyName = "CompanySRL";

        inventoryService.addOutsourcePart("a", price, value, min, max, companyName);

        assertEquals(initialSize + 1, inventoryService.getAllParts().size());
        assertEquals("a", inventoryService.getAllParts().get(initialSize).getName());
        assertEquals(price, inventoryService.getAllParts().get(initialSize).getPrice());
        assertEquals(value, inventoryService.getAllParts().get(initialSize).getInStock());
        assertEquals(min, inventoryService.getAllParts().get(initialSize).getMin());
        assertEquals(max, inventoryService.getAllParts().get(initialSize).getMax());
    }

    @Test
    public void shouldThrowExceptionWithEmptyName() {
        double price = 1.0;
        int inStock = 1;
        int min = 0;
        int max = 10;
        String companyName = "CompanySRL";

        assertThrows(Exception.class, () -> inventoryService.addOutsourcePart("", price, inStock, min, max, companyName));
    }

}