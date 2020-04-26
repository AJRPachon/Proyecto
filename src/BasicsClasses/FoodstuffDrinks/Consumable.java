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

public class Consumable {

    private String name;
    private String description;
    private double price;
    private EnumAllergies allergies;

    public Consumable(){  //Constructor withour parameters

        this.name = " ";
        this.description = " ";
        this.price = 0.0;
        this.allergies = EnumAllergies.Spaguetti;

    }

    public Consumable(String name, String description, double price, EnumAllergies allergies){

        this.name = name;
        this.description = description;
        this.price = price;
        this.allergies = allergies;

    }


///////// BASICS METHODS ////////////////////////////////////////////////////////////////////////////////////////////////////

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


    public EnumAllergies allergies(){
        return this.allergies;
    }


///////// ADDED METHODS ////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public String toString(){

        return name+"|"+description+"|"+price+"|"+allergies;

    }


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
