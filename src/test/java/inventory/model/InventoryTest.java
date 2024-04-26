package inventory.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class InventoryTest {

    private Inventory inventory;


    @BeforeEach
    public void setUp() {
        inventory = new Inventory();
        inventory.addProduct(new Product(1, "Product", 10.0, 10, 1, 100, null));
        inventory.addProduct(new Product(2, "Product 2", 10.0, 10, 1, 100, null));
    }

    @Test
    public void shouldGetProductByName2() {
        Product expectedProduct = new Product(2, "Product 2", 10.0, 10, 1, 100, null);
//
        Product product = inventory.lookupProduct("Product 2");

        assertEquals(expectedProduct.getProductId(), product.getProductId());
        assertEquals(expectedProduct.getName(), product.getName());
        assertEquals(expectedProduct.getPrice(), product.getPrice());
        assertEquals(expectedProduct.getInStock(), product.getInStock());
        assertEquals(expectedProduct.getMin(), product.getMin());
        assertEquals(expectedProduct.getMax(), product.getMax());
    }

    @Test
    public void shouldGetProductByName() {
        Product expectedProduct = new Product(1, "Product", 10.0, 10, 1, 100, null);
//
        Product product = inventory.lookupProduct("Product");

        assertEquals(expectedProduct.getProductId(), product.getProductId());
        assertEquals(expectedProduct.getName(), product.getName());
        assertEquals(expectedProduct.getPrice(), product.getPrice());
        assertEquals(expectedProduct.getInStock(), product.getInStock());
        assertEquals(expectedProduct.getMin(), product.getMin());
        assertEquals(expectedProduct.getMax(), product.getMax());
    }

    @Test
    public void shouldReturnEmptyProductWhenNotFound() {
        Product expectedProduct = new Product(0, null, 0.0, 0, 0, 0, null);

        Product product = inventory.lookupProduct("abcdef");

        assertEquals(expectedProduct.getProductId(), product.getProductId());
        assertEquals(expectedProduct.getName(), product.getName());
        assertEquals(expectedProduct.getPrice(), product.getPrice());
        assertEquals(expectedProduct.getInStock(), product.getInStock());
        assertEquals(expectedProduct.getMin(), product.getMin());
        assertEquals(expectedProduct.getMax(), product.getMax());
    }

    @Test
    public void shouldNotFoundEmptyList(){
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
    public void shouldThrowNullPointerException(){

        assertThrows(NullPointerException.class, () -> {
            inventory.lookupProduct(null);
        });
    }
}