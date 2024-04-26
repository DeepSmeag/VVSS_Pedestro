package inventory.model;

import org.junit.jupiter.api.AfterEach;
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

public class InventoryUnitTest {

    private Inventory inventory;

    @Mock
    private Part part;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        inventory = new Inventory();
        inventory.addPart(part);
    }
    @AfterEach
    public void tearDown() {
        inventory.deletePart(inventory.getAllParts().get(inventory.getAllParts().size()-1));
    }

    @Test
    public void shouldGetProductByName() {
        Mockito.when(part.getName()).thenReturn("Part 1");

        Part result = inventory.lookupPart("Part 1");

        assertEquals(part, result);
    }

    @Test
    public void shouldReturnEmptyProductWhenNotFound() {
        Mockito.when(part.getName()).thenReturn("Part 1");

        Part result = inventory.lookupPart("abcdef");

        assertNull(result);
    }


}