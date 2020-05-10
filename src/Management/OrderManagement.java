package Management;

import BasicsClasses.Orders.Order;

import java.io.FileWriter;
import java.io.IOException;

public class OrderManagement {

    /**
     * @param order
     * @return
     */

    public boolean insertOrder(Order order){    //TODO Colsuntar si esto esta bien
        boolean orderInserted = false;
        FileWriter FW = null;
        try {
            FW = new FileWriter(".\\src\\Files\\tmp\\Orders",true);
            FW.write(order.toString());
            FW.flush();
            orderInserted = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                FW.close();
            }catch (IOException|NullPointerException error){
                error.printStackTrace();
            }
        }
        return orderInserted;
    }


    public Order readAndValidateNewOrder(){
        Order newOrder = null;
        return newOrder;
    }


}
