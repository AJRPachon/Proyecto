package BasicsClasses;

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

import Interfaces.IOrder;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Order implements IOrder,Cloneable,Comparable {

    private ArrayList<OrderLine> ordersLines;
    private GregorianCalendar dateOrder;

    public Order(){
        ordersLines = new ArrayList<>();
        dateOrder = new GregorianCalendar();
    }

    public Order(ArrayList<OrderLine> ordersLines, GregorianCalendar dateOrder) {
        this.ordersLines = (ArrayList<OrderLine>)ordersLines.clone();
        this.dateOrder = (GregorianCalendar)dateOrder.clone();
    }

    public Order(GregorianCalendar dateOrder) {
        ordersLines = new ArrayList<>();
        this.dateOrder = (GregorianCalendar)dateOrder.clone();
    }

    public Order(Order other) {
        ordersLines = (ArrayList<OrderLine>)other.ordersLines.clone();
        this.dateOrder = (GregorianCalendar)other.dateOrder.clone();
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

    public GregorianCalendar getDateOrder() {
        return new GregorianCalendar(dateOrder.get(Calendar.DAY_OF_MONTH),dateOrder.get(Calendar.MONTH),dateOrder.get(Calendar.YEAR));
    }

    public int getDayOfDateOrder(){
        return dateOrder.get(Calendar.DAY_OF_MONTH);
    }

    public int getMonthOfDateOrder(){
        return dateOrder.get(Calendar.MONTH);
    }

    public int getYearOfDateOrder() {
        return dateOrder.get(Calendar.YEAR);
    }

    public void setDateOrder(GregorianCalendar dateOrder){
        this.dateOrder = new GregorianCalendar(dateOrder.get(Calendar.DAY_OF_MONTH),dateOrder.get(Calendar.MONTH),dateOrder.get(Calendar.YEAR));
    }

    public void setDayOfDateOrder(int day){
        dateOrder.set(Calendar.DAY_OF_MONTH,day);
    }

    public void setMonthOfDateOrder(int month){
        dateOrder.set(Calendar.MONTH,month);
    }

    public void setYearOfDateOrder(int year){
        dateOrder.set(Calendar.YEAR,year);
    }

    @Override
    public String toString(){
        String string = "";
        for (OrderLine orderLine : this.ordersLines){
            string+=orderLine.toString()+",";
        }
        string+="|"+dateOrder.get(Calendar.DAY_OF_MONTH)+"|"+(dateOrder.get(Calendar.MONTH)+1)+"|"+dateOrder.get(Calendar.YEAR);
        return string;
    }

    @Override
    public int compareTo(Object ob){
        int result = -1;
        Order otherOrder = (Order)ob;
        if (this.dateOrder.compareTo(otherOrder.dateOrder) == 0){
            result = 0;
        }else{
            if (this.dateOrder.compareTo(otherOrder.dateOrder) > 0){
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
            if (obj != null && obj instanceof Order){
                Order newOrder = (Order)obj;
                if (this.ordersLines.equals(newOrder.ordersLines)
                    && this.dateOrder.equals(newOrder.dateOrder)){
                    isEquals = true;
                }
            }
        }
        return isEquals;
    }

    @Override
    public Order clone() {
        Order newOrder = new Order();
        newOrder.ordersLines= (ArrayList<OrderLine>)ordersLines.clone();
        newOrder.dateOrder = (GregorianCalendar) dateOrder.clone();
        return newOrder;
    }


}
