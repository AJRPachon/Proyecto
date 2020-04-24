package Interfaces;

/*
 * Properties:
 *  - Basics:
 *      > IDProduct: integer, Consulting
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
 *      > integer getAmountProduct()
 *      > none setAmountProduct(integer newAmount)
 *
 *  - Added:
 *      > none increaseAmountProduct(integer amountToIncrease)
 *
 */

public interface IStock {

    public int getIDProduct();

    public int getAmountProduct();

    public void setAmountProduct(int newAmount);

}
