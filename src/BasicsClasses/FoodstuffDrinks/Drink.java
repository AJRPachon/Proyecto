package BasicsClasses.FoodstuffDrinks;

/*

    ANALYSIS:
        This class has all the information regarding the drinks that the restaurant has


    BASIC PROPERTIES:
        proof real consulting
        sparkling boolean consulting

    DERIVATE PROPERTIES:
        Not one


    INTERFACE
    BASICS METHODS:
        getProof()
        getSparkling()

    ADDED METHODS:
        toString()


 */

import BasicsClasses.FoodstuffDrinks.Enums.EnumAllergies;

import java.util.ArrayList;

public class Drink extends Consumable {

    private double proof;
    private boolean sparkling;

    public Drink(){  //Constructor without parameters

        super();
        this.proof = 0.0;
        this.sparkling = false;

    }


    public Drink(String name, String description, double price, ArrayList<EnumAllergies> allergies, double proof, boolean sparkling){  //Constructor with parameters

        super(name,description,price,allergies);
        this.proof = proof;
        this.sparkling = sparkling;

    }

//////// BASICS METHODS /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public double getProof(){
        return this.proof;
    }

    public boolean getSparkling(){
        return this.sparkling;
    }


//////// ADDED METHODS /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public String toString(){

        return proof+"#"+sparkling+"#"+super.toString();
   
    }


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
