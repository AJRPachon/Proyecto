package BasicsClasses.Orders;

/*
 * Properties:
 *  - Basics:
 *      > orderID: integer, Consultable
 *      > OrdersLines: listOfOrdersLines, Consulting, Modifiable
 *      > dateOrder: date, Consulting, Modifiable
 *      > sent: boolean, Consulting, Modifiable
 *      > cancel: boolean, Consulting, Modifiable
 *
 *  - Derivatives:
 *      > totalIDOrders: integer
 *
 *  - Shared:
 *      > None
 *
 * Methods:
 *  - Basics:
 *      > integer getOrderID();
 *
 *      > listOfOrdersLines getOrdersLines();
 *          > OrderLine getOrderLine(int IDProduct);
 *          > OrderLine getOrderLineIndex(int index);
 *      > void setOrdersLines(listOfOrdersLines OrdersLines);
 *          > none setOrderLineIndex(int index, OrderLine orderLine);
 *          > none addOrderLine(OrderLine orderLine);
 *          > none increaseAmountProduct(int IDProduct, int amountToIncrease);
 *          > none decreaseAmountProduct(int IDProduct, int amountToDecrease);
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
 *      > none markSent();
 *      > none markNotSent();
 *
 *      > boolean getSent();
 *
 *      > none markCancel();
 *      > none markNotCancel();
 *
 *      > boolean getCancel();
 *
 *  - Added:
 *      > boolean insertInitID(String pathFile);
 *
 */

import BasicsClasses.FoodstuffDrinks.Product;
import BasicsClasses.Interfaces.IOrder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Order implements IOrder,Cloneable,Comparable {

    private static int totalIDOrders = 0;
    private int orderID;
    private ArrayList<OrderLine> ordersLines;
    private GregorianCalendar dateOrder;
    private boolean sent;
    private boolean cancel;

    public Order(){
        this.orderID = ++totalIDOrders;
        ordersLines = new ArrayList<>();
        dateOrder = new GregorianCalendar();
    }

    public Order(String pathFile){
        insertInitID(pathFile);
        this.orderID = ++totalIDOrders;
        ordersLines = new ArrayList<>();
        dateOrder = new GregorianCalendar();
    }

    public Order(ArrayList<OrderLine> ordersLines, GregorianCalendar dateOrder) {
        this.orderID = ++totalIDOrders;
        this.ordersLines = (ArrayList<OrderLine>)ordersLines.clone();
        this.dateOrder = (GregorianCalendar)dateOrder.clone();
    }

    public Order(GregorianCalendar dateOrder) {
        this.orderID = ++totalIDOrders;
        ordersLines = new ArrayList<>();
        this.dateOrder = (GregorianCalendar)dateOrder.clone();
    }

    public Order(Order other) {
        this.orderID = ++totalIDOrders;
        ordersLines = (ArrayList<OrderLine>)other.ordersLines.clone();
        this.dateOrder = (GregorianCalendar)other.dateOrder.clone();
    }

    public int getID(){
        return this.orderID;
    }

    public ArrayList<OrderLine> getOrdersLines() {
        return (ArrayList<OrderLine>)ordersLines.clone();
    }

    public OrderLine getOrderLine(int IDProduct) {
        OrderLine orderLine = null;

        for (int i = 0; i<ordersLines.size();i++){
            if (ordersLines.get(i).getIDProduct() == IDProduct){
                orderLine = ordersLines.get(i);
            }
        }

        return orderLine;
    }

    public OrderLine getOrderLineIndex(int index) {
        return ordersLines.get(index).clone();
    }

    public void setOrderLineIndex(int index, OrderLine orderLine) {
        ordersLines.set(index, orderLine.clone());
    }

    public void addOrderLine(OrderLine orderLine) {     //TODO Comprobar que no exista ningun producto añadido del tipo de la linea a añadir
        ordersLines.add(orderLine.clone());             //TODO Si hubiera un producto añadido de ese tipo, aumentar la cantidad de esa linea
    }

    public void increaseAmountProduct(int IDProduct, int amountToIncrease) {
        for (int i = 0; i<ordersLines.size();i++){
            if (ordersLines.get(i).getIDProduct() == IDProduct){
                ordersLines.get(i).increaseQuantity(amountToIncrease);
            }
        }
    }

    public void decreaseAmountProduct(int IDProduct, int amountToDecrease) {
        for (int i = 0; i<ordersLines.size();i++){
            if (ordersLines.get(i).getIDProduct() == IDProduct){
                ordersLines.get(i).decreaseQuantity(amountToDecrease);
            }
        }
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

    public void markSent(){
        this.sent = true;
    }

    public void markNotSent(){
        this.sent = false;
    }

    public boolean getSent() {
        return this.sent;
    }

    public void markCancel(){
        this.cancel = true;
    }

    public void markNotCancel(){
        this.cancel = false;
    }

    public boolean getCancel() {
        return this.cancel;
    }

    private static boolean insertInitID(String pathFile){
        boolean valueChanged = false;
        int oldID;
        if (totalIDOrders == 0){
            BufferedReader BR = null;
            try {
                BR = new BufferedReader(new FileReader(pathFile));
                oldID = Integer.parseInt(BR.readLine());
                totalIDOrders = oldID;
                valueChanged = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    BR.close();
                }catch (IOException|NullPointerException error){
                    error.printStackTrace();
                }
            }
        }
        return valueChanged;
    }

    /**
     * @param line
     * @return
     */

    public static Order stringToOrder(String line){
        Order newOrder = null;
        String IDOrder = line.split("%")[0];
        String[] ordersLines = line.split("%")[1].split("\\$");
        String[] partsOfOrder = line.split("%")[2].split("#");

        if (partsOfOrder.length == 3 && ordersLines.length > 0){

            newOrder = new Order();

            try {
                newOrder.orderID = Integer.parseInt(IDOrder);

                for (String s : ordersLines) {
                    newOrder.addOrderLine(new OrderLine(Product.stringToProduct(s), Integer.parseInt(s.split("#")[4])));
                }

            }catch (NumberFormatException e){
                e.printStackTrace();
            }

            newOrder.sent = Boolean.parseBoolean(partsOfOrder[partsOfOrder.length-2]);
            newOrder.cancel = Boolean.parseBoolean(partsOfOrder[partsOfOrder.length-1]);

        }


        return newOrder;
    }


    @Override
    public String toString(){
        String string = orderID+"%";

        for (int i = 0;i < this.ordersLines.size();i++){

            if (i+1 == this.ordersLines.size()){
                string+=this.ordersLines.get(i).toString();
            }else{
                string+=this.ordersLines.get(i).toString()+"$";
            }

        }
        string+="%"+dateOrder.get(Calendar.DAY_OF_MONTH)+"/"+(dateOrder.get(Calendar.MONTH)+1)+"/"+dateOrder.get(Calendar.YEAR)+"#"+sent+"#"+cancel;
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
