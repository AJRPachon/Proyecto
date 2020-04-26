package BasicsClasses.FoodstuffDrinks;

/*


    ANALYSIS:
        This class has all the information regarding the products used in the restaurant and purchased


    BASIC PROPERTIES:
        productID int consulting
        Name String consulting
        characteristics String consulting
        price double consulting and modifiable


    DERIVATE PROPERTIES:
        Not one


    INTERFACE
    BASICS METHODS:
        getProductID()
        getName()
        getCharacteristics()

        getPrice()
        setPrice(real price)


    ADDED METHODS:
        toString()



 */

public class Product {

    private int productID;
    private String name;
    private String characteristics;
    private double price;


    public Product(){ //Constructor without parameters

        this.productID = 0;
        this.name = " ";
        this.characteristics = " ";
        this.price = 0.0;

    }


    public Product(int productID, String name, String characteristics, double price){  //Constructor with parameters

        this.productID = productID;
        this.name = name;
        this.characteristics = characteristics;
        this.price = price;

    }


///////// BASICS METHODS ////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public int getProductID(){
        return this.productID;
    }

    public String getName(){
        return this.name;
    }

    public String getCharacteristics(){
        return this.characteristics;
    }


    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price){
        this.price = price;
    }


///////// ADDED METHODS ////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public String toString(){

        return productID+"|"+name+"|"+characteristics+"|"+price;

    }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
