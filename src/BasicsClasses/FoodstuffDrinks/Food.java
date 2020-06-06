package BasicsClasses.FoodstuffDrinks;

/*


    ANALYSIS:
        This class has all the information regarding the food used in the restaurant and purchased


    BASIC PROPERTIES:
        type enum consulting


    DERIVATE PROPERTIES:
        Not one


    INTERFACE
    BASICS METHODS:
        getType()


    ADDED METHODS:
        toString()



 */

import BasicsClasses.FoodstuffDrinks.Enums.EnumAllergies;
import BasicsClasses.FoodstuffDrinks.Enums.EnumType;

import java.util.ArrayList;

public class Food extends Consumable {

    private EnumType type;


    public Food(){  //Constructor without parameters

        super();
        this.type = EnumType.Spaguetti;

    }


    public Food(String name, String description, double price, ArrayList<EnumAllergies> allergies, double proof, EnumType type){

        super(name,description,price,allergies);
        this.type = type;

    }


//////// BASICS METHODS /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public EnumType getType(){
        return this.type;
    }


//////// ADDED METHODS /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public String toString(){
        return type+"#";
    }


    @Override
    public Food clone() {
        Food newFood = null;
        newFood = (Food) super.clone();
        return newFood;
    }


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


}
