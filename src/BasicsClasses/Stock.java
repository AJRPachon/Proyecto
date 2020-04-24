package BasicsClasses;

public class Stock implements Cloneable {

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

    @Override
    public String toString(){
        return IDProduct+"|"+amountProduct;
    }

    @Override
    public boolean equals(Object obj) {
        boolean isEquals = false;
        if (this == obj){
            isEquals = true;
        }else{
            if (obj != null && obj instanceof Stock){
                Stock newBoat = (Stock)obj;
                if (IDProduct == newBoat.getIDProduct()
                    && amountProduct == newBoat.getAmountProduct()){
                    isEquals = true;
                }
            }
        }
        return isEquals;
    }

    @Override
    public Object clone() {
        Stock newStock = null;

        try {
            newStock = (Stock)super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return newStock;
    }

}
