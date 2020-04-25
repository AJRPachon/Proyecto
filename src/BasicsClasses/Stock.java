package BasicsClasses;

/*
 * Properties:
 *  - Basics:
 *      > IDProduct: integer, Consulting, Modifiable
 *      > amountProduct: integer, Consulting, Modifiable
 *
 *  - Derivatives:
 *      > None
 *
 *  - Shared:
 *      > None
 *
 * Methods:
 *  - Basics:
 *      > integer getIDProduct()
 *      > none setIDProduct(integer IDProduct)
 *
 *      > integer getAmountProduct()
 *      > none setAmountProduct(integer newAmount)
 *
 *      > none increaseAmountProduct(integer amountToIncrease)
 *
 *  - Added:
 *      > None
 *
 */


import Interfaces.IStock;

public class Stock implements IStock,Cloneable,Comparable {

    private int IDProduct;
    private int amountProduct;

    public Stock(){
        IDProduct = -1;
        amountProduct = -1;
    }

    public Stock(int IDProduct, int amountProduct){
        this.IDProduct = IDProduct;
        this.amountProduct = amountProduct;
    }

    public Stock(Stock other){
        IDProduct = other.IDProduct;
        amountProduct = other.amountProduct;
    }

    public int getIDProduct() {
        return IDProduct;
    }

    public void setIDProduct(int IDProduct) {
        this.IDProduct = IDProduct;
    }

    public int getAmountProduct() {
        return amountProduct;
    }

    public void setAmountProduct(int amountProduct) {
        this.amountProduct = amountProduct;
    }

    /**
     * This method increase the total amount stock.
     * @param amountToIncrease Total to increase
     */

    public void increaseAmountProduct(int amountToIncrease){
        amountProduct += amountToIncrease;
    }

    @Override
    public String toString(){
        return IDProduct+"|"+amountProduct;
    }

    @Override
    public int compareTo(Object ob){
        int result = -1;

        Stock otherStock = (Stock)ob;

        if (this.IDProduct == otherStock.IDProduct){
            result = 0;
        }else{
            if (this.IDProduct > otherStock.IDProduct){
                result = 1;
            }
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        boolean isEquals = false;
        if (this == obj){
            isEquals = true;
        }else{
            if (obj != null && obj instanceof Stock){
                Stock newStock = (Stock)obj;
                if (this.IDProduct == newStock.getIDProduct()
                    && this.amountProduct == newStock.getAmountProduct()){
                    isEquals = true;
                }
            }
        }
        return isEquals;
    }

    @Override
    public Stock clone() {
        Stock newStock = null;

        try {
            newStock = (Stock)super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return newStock;
    }

}
