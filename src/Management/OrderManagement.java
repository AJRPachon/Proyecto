package Management;

import BasicsClasses.FoodstuffDrinks.Product;
import BasicsClasses.Orders.Order;
import BasicsClasses.Orders.OrderLine;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class OrderManagement {

    /**
     * Read and validate the data to create an order object.
     * There must be at least 1 product in file product.
     * @return Return object Order valided
     */

    public Order readAndValidateNewOrder(){
        Scanner sc = new Scanner(System.in);
        FilesManagement FM = new FilesManagement();
        Validations Val = new Validations();

        int quantity;
        char character;
        Order newOrder = new Order(new GregorianCalendar());

        Product productGet;

        do {

            productGet = FM.readAndSearchProduct();
            quantity = Val.readAndValidateQuantityOfProduct();
            newOrder.addOrderLine(new OrderLine(productGet,quantity));

            //Read to want insert more line product
            do {
                System.out.print("Insert more products S or N: ");
                character = Character.toUpperCase(sc.next().charAt(0));
            }while (character != 'N' && character != 'S');

        }while (character == 'S');

        return newOrder;
    }


    public void printArrayListOrders(ArrayList<Order> orders){
        for (Order o : orders){
            System.out.println("ID: "+o.getID()+"  Date of order: "+o.getStringDateOrder()+"  Sent: "+o.getSent()+"  Cancel: "+o.getCancel());
        }
    }


}
