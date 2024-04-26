package inventory.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class InventoryUnitTest {

    private Inventory inventory;

    @Mock
    private Part part;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        inventory = new Inventory();
        inventory.addPart(part);
    }

    @ParameterizedTest
    @DisplayName("should return product when found")
    @ValueSource(strings = {"Part 1"})
    void shouldGetProductByName(String searchItem) {
        Mockito.when(part.getName()).thenReturn("Part 1");

        Part result = inventory.lookupPart(searchItem);

        assertEquals(part, result);
    }

    @Test
    void shouldReturnEmptyProductWhenNotFound() {
        Mockito.when(part.getName()).thenReturn("Part 1");

        Part result = inventory.lookupPart("abcdef");

        assertNull(result);
    }


}