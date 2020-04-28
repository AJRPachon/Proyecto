package BasicsClasses;

/*
 * Properties:
 *  - Basics:
 *      > IDProduct: integer, Consulting, Modifiable
 *      > productQuantity: integer, Consulting, Modifiable
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
 *      > integer getProductQuantity()
 *      > none setProductQuantity(integer productQuantity)
 *
 *  - Added:
 *      > None
 *
 */


import Interfaces.IOrderLine;

public class OrderLine implements IOrderLine, Cloneable, Comparable {

    private int IDProduct;
    private int productQuantity;

    public OrderLine(){
        IDProduct = -1;
        productQuantity = -1;
    }

    public OrderLine(int IDProduct, int productQuantity) {
        this.IDProduct = IDProduct;
        this.productQuantity = productQuantity;
    }

    public OrderLine(OrderLine otherOrderLine) {
        this.IDProduct = otherOrderLine.IDProduct;
        this.productQuantity = otherOrderLine.productQuantity;
    }

    public int getIDProduct() {
        return IDProduct;
    }

    public void setIDProduct(int IDProduct) {
        this.IDProduct = IDProduct;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    @Override
    public String toString(){
        return IDProduct+"|"+productQuantity;
    }

    @Override
    public int compareTo(Object ob){
        int result = -1;
        OrderLine otherOrderLine = (OrderLine)ob;
        if (this.IDProduct == otherOrderLine.IDProduct){
            result = 0;
        }else{
            if (this.IDProduct > otherOrderLine.IDProduct){
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
            if (obj != null && obj instanceof OrderLine){
                OrderLine newOrderLine = (OrderLine)obj;
                if (this.IDProduct == newOrderLine.IDProduct
                    && this.productQuantity == newOrderLine.productQuantity){
                    isEquals = true;
                }
            }
        }
        return isEquals;
    }

    @Override
    protected Object clone(){
        OrderLine newOrderLine = null;
        try {
            newOrderLine = (OrderLine)super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return newOrderLine;
    }
}
