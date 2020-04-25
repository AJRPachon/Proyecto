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
 *      > date getDateOrder();
 *          > integer getDayOfDateOrder();
 *          > integer getMonthOfDateOrder();
 *          > integer getYearOfDateOrder();
 *      > none setDateOrder(date dateOrder);
 *          > none setDayOfDateOrder(integer day);
 *          > none setMonthOfDateOrder(integer month);
 *          > none setYearOfDateOrder(integer year);
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
        public int getDayOfDateOrder();
        public int getMonthOfDateOrder();
        public int getYearOfDateOrder();
    public void setDateOrder(GregorianCalendar dateOrder);
        public void setDayOfDateOrder(int day);
        public void setMonthOfDateOrder(int month);
        public void setYearOfDateOrder(int year);

}


