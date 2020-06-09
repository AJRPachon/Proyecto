package JUnitTest.orders;

import BasicsClasses.Orders.OrderLine;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderLineTest {

    OrderLine ol = new OrderLine();

    @Test
    void getIDProduct() {
        int productID = ol.getIDProduct();
        assertNotEquals(productID, -1);
    }

    @Test
    void getNameProduct() {
        String name = ol.getNameProduct();
        assertNotEquals(name, "anchoas");
    }

    @Test
    void getCharacteristicsProduct() {
    }

    @Test
    void getPriceProduct() {
    }

    @Test
    void getProductQuantity() {
    }

    @Test
    void setProductQuantity() {
    }

    @Test
    void increaseQuantity() {
    }

    @Test
    void decreaseQuantity() {
    }

    @Test
    void getTotalPrice() {
    }

    @Test
    void testToString() {
    }

    @Test
    void compareTo() {
    }

    @Test
    void testEquals() {
    }

    @Test
    void testClone() {
    }
}