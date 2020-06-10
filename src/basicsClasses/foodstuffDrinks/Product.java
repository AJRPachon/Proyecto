package basicsClasses.foodstuffDrinks;

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
        clone()
        equals(Object obj)



 */

public class Product implements Cloneable{

    private int IDProduct;
    private static int increaseID = 0;
    private String name;
    private String characteristics;
    private double price;


    public Product(){ //Constructor without parameters

        this.IDProduct = ++increaseID;
        this.name = " ";
        this.characteristics = " ";
        this.price = 0.0;

    }

    public Product(String name, String characteristics, double price){  //Constructor with parameters

        this.IDProduct = ++increaseID;
        this.name = name;
        this.characteristics = characteristics;
        this.price = price;

    }

    public Product(int ID, String name, String characteristics, double price){  //Constructor with parameters

        this.IDProduct = ID;
        this.name = name;
        this.characteristics = characteristics;
        this.price = price;

    }


///////// BASICS METHODS ////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public int getIDProduct(){
        return this.IDProduct;
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

    public static Product stringToProduct(String line){
        Product newProduct = null;
        String[] partsOfProduct = line.split("#");
        newProduct = new Product();
        newProduct.IDProduct = Integer.parseInt(partsOfProduct[0]);
        newProduct.name = partsOfProduct[1];
        newProduct.characteristics = partsOfProduct[2];
        newProduct.price = Double.parseDouble(partsOfProduct[3]);
        return newProduct;
    }


///////// ADDED METHODS ////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public String toString(){

        return IDProduct +"#"+name+"#"+characteristics+"#"+price;

    }


    @Override
    public Product clone() {
        Product newProduct = null;

        try {
            newProduct = (Product)super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return newProduct;
    }


    @Override
    public boolean equals(Object obj) {

        boolean isEquals = false;

        if (this == obj){
            isEquals = true;

        }else{

            if (obj != null && obj instanceof Product){
                Product objProduct = (Product)obj;

                if (this.IDProduct == objProduct.getIDProduct()
                        && this.price == objProduct.getPrice()
                        && this.name.equals(objProduct.getName())
                        && this.characteristics.equals(objProduct.characteristics)){
                    isEquals = true;
                }
            }
        }

        return isEquals;
    }


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
