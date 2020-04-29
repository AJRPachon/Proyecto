package Interfaces;

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
 *
 *      > realNumber getTotalPrice();
 *
 *  - Added:
 *      > None
 *
 */

public interface IOrderLine {

    public int getIDProduct();
    public String getNameProduct();
    public String getCharacteristicsProduct();
    public double getPriceProduct();

    public int getProductQuantity();
    public void setProductQuantity(int productQuantity);
    public void increaseQuantity(int quantityToIncrease);
    public void decreaseQuantity(int quantityToDecrease);

    public double getTotalPrice();

}
