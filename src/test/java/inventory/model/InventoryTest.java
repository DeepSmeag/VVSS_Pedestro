package inventory.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class InventoryTest {

    private Inventory inventory;


    @BeforeEach
    void setUp() {
        inventory = new Inventory();
        inventory.addProduct(new Product(1, "Product", 10.0, 10, 1, 100, null));
        inventory.addProduct(new Product(2, "Product 2", 10.0, 10, 1, 100, null));
    }

    @ParameterizedTest
    @DisplayName("should return product when found 2")
    @ValueSource(strings = {"Product 2", "2"})
    void shouldGetProductByName2(String searchItem) {
        Product expectedProduct = new Product(2, "Product 2", 10.0, 10, 1, 100, null);
//
        Product product = inventory.lookupProduct(searchItem);

        assertEquals(expectedProduct.getProductId(), product.getProductId());
        assertEquals(expectedProduct.getName(), product.getName());
        assertEquals(expectedProduct.getPrice(), product.getPrice());
        assertEquals(expectedProduct.getInStock(), product.getInStock());
        assertEquals(expectedProduct.getMin(), product.getMin());
        assertEquals(expectedProduct.getMax(), product.getMax());
    }
    @ParameterizedTest
    @DisplayName("should return product when found")
    @ValueSource(strings = {"Product", "1"})
    void shouldGetProductByName(String searchItem) {
        Product expectedProduct = new Product(1, "Product", 10.0, 10, 1, 100, null);
//
        Product product = inventory.lookupProduct(searchItem);

        assertEquals(expectedProduct.getProductId(), product.getProductId());
        assertEquals(expectedProduct.getName(), product.getName());
        assertEquals(expectedProduct.getPrice(), product.getPrice());
        assertEquals(expectedProduct.getInStock(), product.getInStock());
        assertEquals(expectedProduct.getMin(), product.getMin());
        assertEquals(expectedProduct.getMax(), product.getMax());
    }

    @ParameterizedTest
    @DisplayName("should return empty product when not found")
    @ValueSource(strings = {"abcdef"})
    void shouldReturnEmptyProductWhenNotFound(String searchItem) {
        Product expectedProduct = new Product(0, null, 0.0, 0, 0, 0, null);

        Product product = inventory.lookupProduct(searchItem);

        assertEquals(expectedProduct.getProductId(), product.getProductId());
        assertEquals(expectedProduct.getName(), product.getName());
        assertEquals(expectedProduct.getPrice(), product.getPrice());
        assertEquals(expectedProduct.getInStock(), product.getInStock());
        assertEquals(expectedProduct.getMin(), product.getMin());
        assertEquals(expectedProduct.getMax(), product.getMax());
    }

    @Test
    @DisplayName("should return product with id 0")
    void shouldNotFoundEmptyList(){
        Product expectedProduct = new Product(0, null, 0.0, 0, 0, 0, null);
        inventory = new Inventory();

        Product product = inventory.lookupProduct("abcdef");


        assertEquals(expectedProduct.getProductId(), product.getProductId());
        assertEquals(expectedProduct.getName(), product.getName());
        assertEquals(expectedProduct.getPrice(), product.getPrice());
        assertEquals(expectedProduct.getInStock(), product.getInStock());
        assertEquals(expectedProduct.getMin(), product.getMin());
        assertEquals(expectedProduct.getMax(), product.getMax());

    }
    @Test
    @DisplayName("should throw error NullPointerException")
    void shouldThrowNullPointerException(){

        assertThrows(NullPointerException.class, () -> {
            inventory.lookupProduct(null);
        });
    }
}