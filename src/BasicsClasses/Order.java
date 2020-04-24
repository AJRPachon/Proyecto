package BasicsClasses;

import java.util.GregorianCalendar;

public class Order {

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
        return new GregorianCalendar(dateOrder.DAY_OF_MONTH,dateOrder.MONTH,dateOrder.YEAR);
    }

    @Override
    public String toString(){
        return IDProduct+"|"+amountProduct+"|"+dateOrder.DAY_OF_MONTH+"|"+(dateOrder.MONTH+1)+"|"+dateOrder.YEAR;
    }


}
