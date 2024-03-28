package inventory.service;

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

class InventoryServiceTest {

    private InventoryService inventoryService;


    @BeforeEach
    void setUp() {
        InventoryRepository repo = new InventoryRepository();
        inventoryService = new InventoryService(repo);
    }

    @AfterEach
    void tearDown() {
        inventoryService.deletePart(inventoryService.getAllParts().get(inventoryService.getAllParts().size()-1));
    }

    @DisplayName("should create part for valid inStock")
    @ParameterizedTest
    @ValueSource(ints = {0, 1})
    void shouldCreateValidPart(int value) {
        int initialSize = inventoryService.getAllParts().size();
        String name = "Part";
        double price = 1.0;
        int min = 0;
        int max = 10;
        String companyName = "CompanySRL";

        inventoryService.addOutsourcePart(name, price, value, min, max, companyName);

        assertEquals(initialSize + 1, inventoryService.getAllParts().size());
        assertEquals(name, inventoryService.getAllParts().get(initialSize).getName());
        assertEquals(price, inventoryService.getAllParts().get(initialSize).getPrice());
        assertEquals(value, inventoryService.getAllParts().get(initialSize).getInStock());
        assertEquals(min, inventoryService.getAllParts().get(initialSize).getMin());
        assertEquals(max, inventoryService.getAllParts().get(initialSize).getMax());
    }

    @DisplayName("should throw exception when creating part with negative stock")
    @Test
    void shouldThrowExceptionWithNegativeStock() {
        String name = "Part";
        double price = 1.0;
        int inStock = -1;
        int min = 0;
        int max = 10;
        String companyName = "CompanySRL";

        assertThrows(Exception.class, () -> inventoryService.addOutsourcePart(name, price, inStock, min, max, companyName));
    }

    @DisplayName("should create part for valid name")
    @ParameterizedTest
    @MethodSource("provideStringsForValidName")
    void shouldCreateValidPartWithValidName(String name){
        int initialSize = inventoryService.getAllParts().size();
        double price = 1.0;
        int value = 1;
        int min = 0;
        int max = 10;
        String companyName = "CompanySRL";

        inventoryService.addOutsourcePart(name, price, value, min, max, companyName);

        assertEquals(initialSize + 1, inventoryService.getAllParts().size());
        assertEquals(name, inventoryService.getAllParts().get(initialSize).getName());
        assertEquals(price, inventoryService.getAllParts().get(initialSize).getPrice());
        assertEquals(value, inventoryService.getAllParts().get(initialSize).getInStock());
        assertEquals(min, inventoryService.getAllParts().get(initialSize).getMin());
        assertEquals(max, inventoryService.getAllParts().get(initialSize).getMax());
    }

    @DisplayName("should throw exception when creating part with empty name")
    @ParameterizedTest
    @EmptySource
    void shouldThrowExceptionWithEmptyName(String name) {
        double price = 1.0;
        int inStock = 1;
        int min = 0;
        int max = 10;
        String companyName = "CompanySRL";

        assertThrows(Exception.class, () -> inventoryService.addOutsourcePart(name, price, inStock, min, max, companyName));
    }

    private static Stream<Arguments> provideStringsForValidName() {
        return Stream.of(
                Arguments.of("a")
        );
    }
}