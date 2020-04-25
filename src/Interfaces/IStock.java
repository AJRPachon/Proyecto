package Interfaces;

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

public interface IStock {

    public int getIDProduct();

    public void setIDProduct(int IDProduct);

    public int getAmountProduct();

    public void setAmountProduct(int newAmount);

    public void increaseAmountProduct(int newAmount);

}
