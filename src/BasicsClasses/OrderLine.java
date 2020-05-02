package BasicsClasses;

/*
 * Properties:
 *  - Basics:
 *      > product: Product, Consulting
 *      > productQuantity: integer, Consulting, Modifiable
 *
 *  - Derivatives:
 *      > totalPrice: realNumber, Consulting
 *
 *  - Shared:
 *      > None
 *
 * Methods:
 *  - Basics:
 *      > integer getIDProduct();
 *      > integer getNameProduct();
 *      > string getCharacteristicsProduct();
 *      > realNumber getPriceProduct();
 *
 *      > integer getProductQuantity();
 *      > none setProductQuantity(integer productQuantity);
 *      > none increaseQuantity(int quantityToIncrease);
 *      > none decreaseQuantity(int quantityToDecrease);
 *
 *      > realNumber getTotalPrice();
 *
 *  - Added:
 *      > None
 *
 */

import BasicsClasses.FoodstuffDrinks.Product;
import Interfaces.IOrderLine;

public class OrderLine implements IOrderLine, Cloneable, Comparable {

    private Product product;
    private int productQuantity;

    public OrderLine(){
        this.product = new Product();
        productQuantity = 0;
    }

    public OrderLine(Product product, int productQuantity){
        this.product = product.clone();
        this.productQuantity = productQuantity;
    }

    public OrderLine(OrderLine otherOrderLine) {
        this.product = otherOrderLine.product;
        this.productQuantity = otherOrderLine.productQuantity;
    }

    public int getIDProduct() {
        return product.getIDProduct();
    }

    public String getNameProduct() {
        return product.getName();
    }

    public String getCharacteristicsProduct(){
        return product.getCharacteristics();
    }

    public double getPriceProduct(){
        return product.getPrice();
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public void increaseQuantity(int quantityToIncrease) {
        productQuantity+=quantityToIncrease;
    }

    public void decreaseQuantity(int quantityToDecrease) {
        productQuantity-=quantityToDecrease;
    }

    public double getTotalPrice(){
        return (product.getPrice()*productQuantity);
    }

    @Override
    public String toString(){
        return product.getIDProduct()+"|"+product.getName()+"|"+product.getCharacteristics()+"|"+product.getPrice()+"|"+productQuantity;
    }

    @Override
    public int compareTo(Object ob){
        int result = -1;
        OrderLine otherOrderLine = (OrderLine)ob;
        if (this.getTotalPrice() == otherOrderLine.getTotalPrice()){
            result = 0;
        }else{
            if (this.getTotalPrice() > otherOrderLine.getTotalPrice()){
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
                if (this.product.equals(newOrderLine)
                    && this.productQuantity == newOrderLine.productQuantity){
                    isEquals = true;
                }
            }
        }
        return isEquals;
    }

    @Override
    public OrderLine clone(){
        OrderLine newOrderLine = null;
        try {
            newOrderLine = (OrderLine)super.clone();
            newOrderLine.product = this.product.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return newOrderLine;
    }
}
