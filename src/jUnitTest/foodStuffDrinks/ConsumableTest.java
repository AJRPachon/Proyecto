package jUnitTest.foodStuffDrinks;

import basicsClasses.foodstuffDrinks.Consumable;
import basicsClasses.foodstuffDrinks.enums.EnumAllergies;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ConsumableTest {

    ArrayList<EnumAllergies> enumsAllergies = new ArrayList<>() ;
    Consumable consumable = new Consumable("CocaCola", "Soda", 1.20, enumsAllergies);

    @Test
    void getIDConsumable() {
        int id = consumable.getIDConsumable();
        assertEquals(id, 1);
    }

    @Test
    void getName() {
        String name = consumable.getName();
        assertEquals(name, "CocaCola");
    }

    @Test
    void getDescription() {
        String desciption = consumable.getDescription();
        assertEquals(desciption, "Soda");
    }

    @Test
    void getPrice() {
        double price = consumable.getPrice();
        assertEquals(price, 1.20);
    }

    @Test
    void setPrice() {
        consumable.setPrice(1.80);
        double price = consumable.getPrice();
        assertEquals(price, 1.80);
    }

    @Test
    void getAllergies() {

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