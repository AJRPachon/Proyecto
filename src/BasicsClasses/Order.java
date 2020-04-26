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

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Order implements IOrder,Cloneable,Comparable {

    private int IDProduct;
    private int amountProduct;
    private GregorianCalendar dateOrder;

    public Order(){
        IDProduct = -1;
        amountProduct = -1;
        dateOrder = new GregorianCalendar();
    }

    public Order(int IDProduct, int amountProduct) {
        this.IDProduct = IDProduct;
        this.amountProduct = amountProduct;
        this.dateOrder = new GregorianCalendar();
    }

    public Order(int IDProduct, int amountProduct, GregorianCalendar dateOrder) {
        this.IDProduct = IDProduct;
        this.amountProduct = amountProduct;
        this.dateOrder = dateOrder;
    }

    public Order(Order other) {
        this.IDProduct = other.IDProduct;
        this.amountProduct = other.amountProduct;
        this.dateOrder = other.dateOrder;
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
        return IDProduct+"|"+amountProduct+"|"+dateOrder.get(Calendar.DAY_OF_MONTH)+"|"+(dateOrder.get(Calendar.MONTH)+1)+"|"+dateOrder.get(Calendar.YEAR);
    }

    @Override
    public int compareTo(Object ob){
        int result = -1;

        Order otherOrder = (Order)ob;

        if (this.IDProduct == otherOrder.IDProduct){
            result = 0;
        }else{
            if (this.IDProduct > otherOrder.IDProduct){
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
                if (this.IDProduct == newOrder.getIDProduct()
                    && this.amountProduct == newOrder.getAmountProduct()
                    && this.dateOrder.equals(newOrder.dateOrder)){
                    isEquals = true;
                }
            }
        }
        return isEquals;
    }

    @Override
    public Order clone() {
        Order newOrder = null;

        try {
            newOrder = (Order)super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return newOrder;
    }


}
