package jUnitTest.orders;

import basicsClasses.orders.Order;
import basicsClasses.orders.OrderLine;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    Order o = new Order();

    @Test
    void getID() {
        int id = o.getID();
        assertNotEquals(id, 0);
    }

    @Test
    void getOrdersLines() {
    }

    @Test
    void getOrderLine() {
        OrderLine ol = o.getOrderLine(0);
        assertNotNull(ol);
    }

    @Test
    void getOrderLineIndex() {
    }

    @Test
    void setOrderLineIndex() {
    }

    @Test
    void addOrderLine() {
    }

 /*   @Test
    void removeOrderLine() {
        boolean r = o.removeOrderLine(0);
        assertTrue(r);
    }

    @Test
    void increaseAmountProduct() {
        boolean r = o.increaseAmountProduct(0, 2);
        assertTrue(r);
    }
*/
    @Test
    void decreaseAmountProduct() {
        boolean r = o.decreaseAmountProduct(0, 2);
        assertTrue(r);
    }

    @Test
    void getIDProducts() {
    }

    @Test
    void quantityOfAProduct() {
        int quan = o.quantityOfAProduct(0);
        assertNotEquals(quan, -1);
    }

    @Test
    void getDateOrder() {
    }

    @Test
    void getDayOfDateOrder() {
    }

    @Test
    void getMonthOfDateOrder() {
    }

    @Test
    void getYearOfDateOrder() {
    }

    @Test
    void getStringDateOrder() {
    }

    @Test
    void setDateOrder() {
    }

    @Test
    void setDayOfDateOrder() {
    }

    @Test
    void setMonthOfDateOrder() {
    }

    @Test
    void setYearOfDateOrder() {
    }

    @Test
    void markSent() {
    }

    @Test
    void markNotSent() {
    }

    @Test
    void getSent() {
    }

    @Test
    void markCancel() {
    }

    @Test
    void markNotCancel() {
    }

    @Test
    void getCancel() {
    }

    @Test
    void printOrdersLines() {
    }

    @Test
    void printOrderLine() {
    }

    @Test
    void stringToOrder() {
    }

    @Test
    void testToString() {
    }

    @Test
    void compareTo() {
    }

    @Test
    void testEquals() {
    }

    @Test
    void testClone() {
    }
}