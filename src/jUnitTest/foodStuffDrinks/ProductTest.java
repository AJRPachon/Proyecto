package jUnitTest.foodStuffDrinks;

import basicsClasses.foodstuffDrinks.Product;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    Product p = new Product("Papas Bravas", "Pican", 4.5);

    @Test
    void getIDProduct() {
        int id = p.getIDProduct();
        assertEquals(id, 1);
    }

    @Test
    void getName() {
        String nombre = p.getName();
        assertEquals(nombre, "Papas Bravas");
    }

    @Test
    void getCharacteristics() {
        String chara = p.getCharacteristics();
        assertEquals(chara, "Pican");
    }

    @Test
    void getPrice() {
        double price = p.getPrice();
        assertEquals(price, 4.5);
    }

    @Test
    void setPrice() {
        p.setPrice(5.1);
        double price = p.getPrice();
        assertEquals(price, 5.1);
    }

    @Test
    void stringToProduct() {
    }

    @Test
    void testToString() {
    }

    @Test
    void testClone() {
    }

    @Test
    void testEquals() {
    }
}