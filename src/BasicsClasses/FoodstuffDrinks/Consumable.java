package BasicsClasses.FoodstuffDrinks;

/*


    ANALYSIS:
        This class has all the information regarding the consumables that the restaurant has


    BASIC PROPERTIES:
        name String consulting
        description String consulting
        price double consulting and modifiable
        allergies enum consulting

    DERIVATE PROPERTIES:
        Not one


    INTERFACE
    BASICS METHODS:
        getName()
        getDescription()

        getPrice()
        setPrice(real price)

        getAllergies()

    ADDED METHODS:
        toString()


 */

import BasicsClasses.FoodstuffDrinks.Enums.EnumAllergies;

import java.util.ArrayList;

public class Consumable {

    private final int IDConsumable;
    private static int increaseID = 0;
    private final String name;
    private final String description;
    private double price;
    private final ArrayList<EnumAllergies> allergies;

    public Consumable(){  //Constructor withour parameters

        this.IDConsumable = ++increaseID;
        this.name = " ";
        this.description = " ";
        this.price = 0.0;
        this.allergies = new ArrayList<>();

    }

    public Consumable(String name, String description, double price, ArrayList<EnumAllergies> allergies){

        this.IDConsumable = ++increaseID;
        this.name = name;
        this.description = description;
        this.price = price;
        this.allergies = allergies;

    }


///////// BASICS METHODS ////////////////////////////////////////////////////////////////////////////////////////////////////

    public int getIDConsumable(){ return this.IDConsumable; }

    public String getName(){
        return this.name;
    }

    public String getDescription(){
        return this.description;
    }


    public double getPrice(){
        return this.price;
    }

    public void setPrice(double price){
        this.price = price;
    }


    public ArrayList<EnumAllergies> getAllergies(){

        return (ArrayList<EnumAllergies>)(this.allergies.clone());

    }


///////// ADDED METHODS ////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public String toString(){

        return name+"|"+description+"|"+price+"|"+allergies;

    }

    @Override
    public Consumable clone() {
        Consumable newConsumable = null;

        try {
            newConsumable = (Consumable)super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return newConsumable;
    }


    @Override
    public boolean equals(Object obj) {

        boolean isEquals = false;

        if (this == obj){
            isEquals = true;

        }else{

            if (obj != null && obj instanceof Consumable){
                Consumable objConsumable = (Consumable)obj;

                if (this.IDConsumable == objConsumable.getIDConsumable()
                        && this.price == objConsumable.getPrice()
                        && this.name.equals(objConsumable.getName())
                        && this.description.equals(objConsumable.getDescription())
                        && this.allergies.equals(objConsumable.getAllergies())){
                    isEquals = true;
                }
            }
        }

        return isEquals;
    }


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
