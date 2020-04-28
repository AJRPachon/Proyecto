package Interfaces;

/*
 * Properties:
 *  - Basics:
 *      > product: Product, Consulting
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
 *      > integer getIDProduct();
 *      > integer getNameProduct();
 *      > string getCharacteristicsProduct();
 *      > realNumber getPriceProduct();
 *
 *      > integer getProductQuantity()
 *      > none setProductQuantity(integer productQuantity)
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

}
