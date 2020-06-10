package JUnitTest.foodStuffDrinks;

import BasicsClasses.FoodstuffDrinks.Drink;
import BasicsClasses.FoodstuffDrinks.Enums.EnumAllergies;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DrinkTest {

    ArrayList<EnumAllergies> enumsAllergies = new ArrayList<>();
    Drink d = new Drink("CocaCola", "Soda", 1.20, enumsAllergies, 1.20, true);

    @Test
    void getProof() {
        double proof = d.getProof();
        assertEquals(proof, 1.20);
    }

    @Test
    void getSparkling() {
        boolean sparkling = d.getSparkling();
        assertTrue(sparkling);
    }

    @Test
    void testToString() {
    }

    @Test
    void testClone() {
    }
}