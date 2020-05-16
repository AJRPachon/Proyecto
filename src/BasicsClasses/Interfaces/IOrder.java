package BasicsClasses.Interfaces;

/*
 * Properties:
 *  - Basics:
 *      > OrdersLines: listOfOrdersLines, Consulting, Modifiable
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
 *      > listOfOrdersLines getOrdersLines();
 *          > OrderLine getOrderLine(int IDProduct);
 *          > OrderLine getOrderLineIndex(int index);
 *      > void setOrdersLines(listOfOrdersLines OrdersLines);
 *          > void setOrderLineIndex(int index, OrderLine orderLine);
 *          > void addOrderLine(OrderLine orderLine);
 *          > void increaseAmountProduct(int IDProduct, int amountToIncrease);
 *          > void decreaseAmountProduct(int IDProduct, int amountToDecrease);
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

import BasicsClasses.Orders.OrderLine;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public interface IOrder {

    public ArrayList<OrderLine> getOrdersLines();
    public OrderLine getOrderLine(int IDProduct);

    public OrderLine getOrderLineIndex(int index);
    public void setOrderLineIndex(int index, OrderLine orderLine);
    public void addOrderLine(OrderLine orderLine);
    public boolean increaseAmountProduct(int IDProduct, int amountToIncrease);
    public boolean decreaseAmountProduct(int IDProduct, int amountToDecrease);

    public GregorianCalendar getDateOrder();
        public int getDayOfDateOrder();
        public int getMonthOfDateOrder();
        public int getYearOfDateOrder();
    public void setDateOrder(GregorianCalendar dateOrder);
        public void setDayOfDateOrder(int day);
        public void setMonthOfDateOrder(int month);
        public void setYearOfDateOrder(int year);

}


