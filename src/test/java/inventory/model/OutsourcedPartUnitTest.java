package inventory.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OutsourcedPartUnitTest {

    @Test
    public void shouldConvertToString() {
        OutsourcedPart part = new OutsourcedPart(1, "Part", 1.0, 1, 0, 10, "CompanySRL");

        assertEquals("O,1,Part,1.0,1,0,10,CompanySRL", part.toString());
    }

    @Test
    public void shouldGetCompanyName() {
        OutsourcedPart part = new OutsourcedPart(1, "Part", 1.0, 1, 0, 10, "CompanySRL");

        assertEquals("CompanySRL", part.getCompanyName());
    }

}