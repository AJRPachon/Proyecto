package jUnitTest.orders;

import basicsClasses.orders.OrderLine;
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
        assertNotEquals(name, "McFlurry");
    }

    @Test
    void getCharacteristicsProduct() {
        String characteristics = ol.getCharacteristicsProduct();
        assertNotEquals(characteristics, "not default");
    }

    @Test
    void getPriceProduct() {
        double price = ol.getPriceProduct();
        assertEquals(price, 0.0);
    }

    @Test
    void getProductQuantity() {
        int productQuantity = ol.getProductQuantity();
        assertNotEquals(productQuantity, -1);
    }

    @Test
    void setProductQuantity() {
        ol.setProductQuantity(40);
        int productQuantity = ol.getProductQuantity();
        assertEquals(productQuantity, 40);
    }

    @Test
    void increaseQuantity() {
        ol.increaseQuantity(20);
        int incQuantity = ol.getProductQuantity();
        assertNotEquals(incQuantity, 0);
    }

    @Test
    void decreaseQuantity() {
        ol.decreaseQuantity(1);
        int decQuantity = ol.getProductQuantity();
        assertNotEquals(decQuantity, -1);
    }

    @Test
    void getTotalPrice() {
        double totalPrice = ol.getTotalPrice();
        assertNotEquals(totalPrice, -1);
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