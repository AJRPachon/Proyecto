package JUnitTest.management;

import Management.OrderManagement;

class OrderManagementTest {

    public static void main(String [] args) {

        OrderManagement or = new OrderManagement();
        String pathOrder = ".\\src\\files\\Products";

        System.out.println("or.readAndValidateNewOrder: "+or.readAndValidateNewOrder(pathOrder));

        System.out.println("\n-----------------------------------------------------------------------\n");


    }
}