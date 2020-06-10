package JUnitTest.foodStuffDrinks;

import BasicsClasses.FoodstuffDrinks.Enums.EnumAllergies;
import BasicsClasses.FoodstuffDrinks.Enums.EnumType;
import BasicsClasses.FoodstuffDrinks.Food;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FoodTest {

    ArrayList<EnumAllergies> enumsAllergies = new ArrayList<>();
    Food f = new Food("CocaCola", "Soda", 1.20,enumsAllergies, EnumType.Spaguetti);

    @Test
    void getType() {
        EnumType type = f.getType();
        assertEquals(type, EnumType.Spaguetti);
    }

    @Test
    void testToString() {
    }

    @Test
    void testClone() {
    }
}