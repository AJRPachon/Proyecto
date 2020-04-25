package Interfaces;

/*
 * Properties:
 *  - Basics:
 *      > IDProduct: integer, Consulting, Modifiable
 *      > amountProduct: integer, Consulting, Modifiable
 *      > dateOrder: date, Consulting, Modifiable
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
 *      > none setAmountProduct(integer amountProduct)
 *
 *      > date getDateOrder()
 *      > none setDateOrder(date dateOrder)
 *
 *  - Added:
 *      > None
 *
 */

import java.util.GregorianCalendar;

public interface IOrder {

    public int getIDProduct();

    public void setIDProduct(int IDProduct);

    public int getAmountProduct();

    public void setAmountProduct(int amountProduct);

    public GregorianCalendar getDateOrder();

    public void setDateOrder(GregorianCalendar dateOrder);

}


