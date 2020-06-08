
/*
 *
 * PG0
 * INIT
 * checkFiles*
 * do
 *  readUser
 *  readPassword
 *  getPermisons*
 *      switch(permisons)
 *          case Administrator
 *              //optionOfAdministrator
 *          endCase
 *
 *          case FloorManager
 *              //optionOfFloorManager
 *          endCase
 *
 *          case Staff
 *              //optionOfStaff
 *          endCase
 *      EndSwitch
 *      askWantContinue*
 * while(checkLogIn fail and user want continue)
 * END
 *
 */

import BasicsClasses.Employee.Employee;
import BasicsClasses.Employee.Payslip;
import BasicsClasses.Employee.Schedule;
import BasicsClasses.Orders.Order;
import BasicsClasses.Orders.OrderLine;
import Management.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;


public class MainBBDD {

    public static void main(String[] args) {

        String sourceURL = "jdbc:sqlserver://localhost:1433;database=Restaurant";
        String usernameBBDD = "UserJava";
        String passwordBBDD = "newPassUser";
        Connection connectionDataBase = null;

        try {
            connectionDataBase = DriverManager.getConnection(sourceURL, usernameBBDD, passwordBBDD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        ArrayList<Order> ordersNotShipped;

        String username = "", password = "", permisons = null, DNIToConsultData;
        Order newOrder, orderChoosed;
        int optionPermisons, optionModifyOrder, IDProductToDelete, IDProductToDecrease, amountToDecrease, IDProductToIncrease, amountToIncrease;
        String employeeDNI;
        double salary;
        OrderLine newOrderLine;

        DatabaseManagement dbManag = new DatabaseManagement();

        Employee employee;
        Schedule[] schedule;

        EmployeeManagement emplManag = new EmployeeManagement();
        Validations valid = new Validations();
        OrderManagement orderManag = new OrderManagement();
        ScheduleManagement scheManag = new ScheduleManagement();

        do {

            if (connectionDataBase != null){
                username = valid.readAndValidateUsername();
                password = valid.readAndValidatePassword();
                permisons = dbManag.getPermisons(username, password, connectionDataBase);
            }else{
                System.out.println("Error in connection");
            }

            if (permisons != null){
                switch (permisons) {
                    case "Administrator":
                        //optionOfAdministrator

                        do {
                            optionPermisons = valid.readAndValidateOptionsAdministrator();

                            switch (optionPermisons) {
                                case 0:
                                    System.out.println("Closed session.");
                                    break;

                                case 1: //Dar de alta a un empleado
                                    System.out.println("Dar de alta a un empleado");
                                    employee = emplManag.collectEmployeeData();
                                    if (!dbManag.insertEmployee(employee, connectionDataBase)){
                                        System.out.println("Could not insert employee");
                                    }else{
                                        System.out.println("Employee inserted");
                                    }
                                    break;

                                case 2:
                                    System.out.println("Dar de baja a empleado");
                                    employeeDNI = valid.readAndValidateUsername(connectionDataBase);
                                    employee = dbManag.getEmployeeByDNI(employeeDNI, connectionDataBase);
                                    if (employee != null){
                                        if (!dbManag.deleteEmployee(employee, connectionDataBase)){
                                            System.out.println("Could not delete employee");
                                        }else{
                                            System.out.println("Employee deleted");
                                        }
                                    }
                                    break;


                                case 3:
                                    System.out.println("Asignar horario a empleado");
                                    System.out.println("Enter the DNI of the employee to whom you want to assign a schedule");
                                    employeeDNI = valid.readAndValidateUsername(connectionDataBase); //Seleccionamos el dni del empleado al cual queremos cambiarle el horario
                                    schedule = scheManag.setScheduleData(); //Creamos un nuevo horario
                                    if (!dbManag.insertSchedules(schedule,employeeDNI,connectionDataBase)){
                                        System.out.println("Could not assign schedule");
                                    }else{
                                        System.out.println("Schedule assigned");
                                    }
                                    break;


                                case 4: //Modificar sueldo a empleado
                                    employeeDNI = valid.readAndValidateUsername(connectionDataBase); //Lee el dni del empleado
                                    salary = valid.readAndValidateSalary();  //Asignamos el nuevo salario
                                    if (!dbManag.modifySalary(new Payslip(salary, new Employee(employeeDNI)), connectionDataBase)){
                                        System.out.println("Could not modify the salary");
                                    }else{
                                        System.out.println("Salary modified");
                                    }
                                    break;


                                case 5:
                                    System.out.println();
                                    DNIToConsultData = valid.readAndValidateUsername(connectionDataBase);
                                    emplManag.printEmployeePersonalData(dbManag.getEmployeeByDNI(DNIToConsultData,connectionDataBase));
                                    System.out.println();
                                    break;

                                case 6:
                                    newOrder = orderManag.readAndValidateNewOrder(connectionDataBase);
                                    if (!dbManag.insertNewOrder(username, newOrder, connectionDataBase)) {
                                        System.out.println("The new order wasn't added");
                                    }else{
                                        System.out.println("Order added");
                                    }
                                    break;
                                case 7:
                                    ordersNotShipped = dbManag.getOrdersNotShipped(connectionDataBase);
                                    if (ordersNotShipped.size() > 0){
                                        orderChoosed = valid.chooseOrderByID(ordersNotShipped);
                                        //Repetir
                                        do {
                                            //leer y mostrar opciones modificar pedido
                                            optionModifyOrder = valid.readAndValidateOptionsOrder();
                                            //segun (opcion del pedido)
                                            switch (optionModifyOrder){
                                                //caso 1 (añadir producto)
                                                case 1:
                                                    newOrderLine = valid.readAndValidateNewOrderLine(connectionDataBase);
                                                    orderChoosed.addOrderLine(newOrderLine);
                                                    if (!dbManag.insertNewOrderLine(orderChoosed ,newOrderLine ,connectionDataBase)) {
                                                        System.out.println("The new order line wasn't added");
                                                    }else{
                                                        System.out.println("New order line added");
                                                    }
                                                    break;

                                                //caso 2 (eliminar producto)
                                                case 2:
                                                    //mostrar productos en la lista
                                                    orderChoosed.printOrdersLines();
                                                    //elegir ID del pedido a eliminar
                                                    IDProductToDelete = valid.readAndValidateIDProductOfOrder(orderChoosed.getIDProducts());
                                                    //eliminar linea de pedido
                                                    orderChoosed.removeOrderLine(IDProductToDelete);
                                                    if (!(dbManag.deleteOrderLine(orderChoosed, IDProductToDelete, connectionDataBase))) {
                                                        System.out.println("The order line wasn't deleted");
                                                    }else{
                                                        System.out.println("The order line was deleted");
                                                    }
                                                    break;

                                                //caso 3 (disminuir cantidad producto)
                                                case 3:
                                                    //mostrar productos en la lista
                                                    orderChoosed.printOrdersLines();
                                                    //elegir ID del pedido a disminuir cantidas
                                                    IDProductToDecrease = valid.readAndValidateIDProductOfOrder(orderChoosed.getIDProducts());
                                                    //leer y validar cantidad a disminuir
                                                    amountToDecrease = valid.readAndValidateQuantityToDecrease(orderChoosed.quantityOfAProduct(IDProductToDecrease));
                                                    //dismiuir cantidad
                                                    orderChoosed.decreaseAmountProduct(IDProductToDecrease,amountToDecrease);
                                                    if (!dbManag.decreaseAmountProductOrderLine(orderChoosed,IDProductToDecrease,amountToDecrease,connectionDataBase)){
                                                        System.out.println("Quantity of order line wasn't decrease");
                                                    }else{
                                                        System.out.println("Quantity of order line was decrease");
                                                    }
                                                    //mostrar resultado final
                                                    orderChoosed.printOrderLine(IDProductToDecrease);
                                                    break;

                                                //caso 4 (aumentar cantidad producto)
                                                case 4:
                                                    //mostrar productos en la lista
                                                    orderChoosed.printOrdersLines();
                                                    //elegir ID del pedido a aumentar cantidad
                                                    IDProductToIncrease = valid.readAndValidateIDProductOfOrder(orderChoosed.getIDProducts());
                                                    //leer y validar cantidad a aumentar
                                                    amountToIncrease = valid.readAndValidateQuantityToIncrease();
                                                    //aumentar cantidad
                                                    orderChoosed.increaseAmountProduct(IDProductToIncrease,amountToIncrease);
                                                    if (!dbManag.increaseAmountProductOrderLine(orderChoosed,IDProductToIncrease,amountToIncrease,connectionDataBase)){
                                                        System.out.println("Quantity of order line wasn't increase");
                                                    }else{
                                                        System.out.println("Quantity of order line was increase");
                                                    }
                                                    //mostrar resultado final
                                                    orderChoosed.printOrderLine(IDProductToIncrease);
                                                    break;

                                                //caso 5 (mostrar todas las orders lines)
                                                case 5:
                                                    orderChoosed.printOrdersLines();
                                                    break;
                                                //finSegun

                                                //caso 6 (cancelar pedido)
                                                case 6:
                                                    orderChoosed.markCancel();
                                                    if (!dbManag.markCancelProduct(orderChoosed,connectionDataBase)){
                                                        System.out.println("Order wasn't marked cancel");
                                                    }else{
                                                        System.out.println("Order was marked cancel");
                                                    }
                                                    //cancelar pedido
                                                    break;
                                                //finSegun
                                            }
                                        }while (optionModifyOrder != 0 && !orderChoosed.getCancel());
                                    }else{
                                        System.out.println("Not exist orders without sent or not cancel");
                                    }
                                    break;

                                case 8:
                                    System.out.println();
                                    emplManag.printEmployeePersonalData(dbManag.getEmployeeByDNI(username,connectionDataBase));
                                    System.out.println();
                                    break;

                                case 9:
                                    System.out.println("Consultar sus horarios");
                                    scheManag.printSchedules(dbManag.getEmployeeSchedules(username,connectionDataBase));
                                    break;
                            }

                        }while (optionPermisons != 0) ;

                        break;

                    case "FloorManager":
                        //optionOfFloorManager

                        do {

                            optionPermisons = valid.readAndValidateOptionsFloorManager();
                            switch (optionPermisons){
                                case 0:
                                    System.out.println("Session closed.");
                                    break;
                                case 1:
                                    System.out.println();
                                    DNIToConsultData = valid.readAndValidateUsername(connectionDataBase);
                                    emplManag.printEmployeePersonalData(dbManag.getEmployeeByDNI(DNIToConsultData,connectionDataBase));
                                    System.out.println();
                                    break;


                                case 2:
                                    System.out.println("Asignar horario a empleado");
                                    System.out.println("Enter the DNI of the employee to whom you want to assign a schedule");
                                    employeeDNI = valid.readAndValidateUsername(connectionDataBase); //Seleccionamos el dni del empleado al cual queremos cambiarle el horario
                                    schedule = scheManag.setScheduleData(); //Creamos un nuevo horario
                                    if (!dbManag.insertSchedules(schedule,employeeDNI,connectionDataBase)){
                                        System.out.println("Could not assign schedule");
                                    }else{
                                        System.out.println("Schedule assigned");
                                    }
                                    break;


                                case 3:
                                    System.out.println();
                                    emplManag.printEmployeePersonalData(dbManag.getEmployeeByDNI(username,connectionDataBase));
                                    System.out.println();
                                    break;


                                case 4:
                                    System.out.println("Consultar sus horarios");
                                    scheManag.printSchedules(dbManag.getEmployeeSchedules(username,connectionDataBase));
                                    break;
                            }

                        }while (optionPermisons != 0) ;

                    break;

                    case "Staff":
                        //optionOfStaff

                        do {

                            optionPermisons = valid.readAndValidateOptionsStaff();
                            switch (optionPermisons){
                                case 0:
                                    System.out.println("Session closed.");
                                    break;

                                case 1:
                                    System.out.println();
                                    emplManag.printEmployeePersonalData(dbManag.getEmployeeByDNI(username,connectionDataBase));
                                    System.out.println();
                                    break;


                                case 2:
                                    System.out.println("Consultar sus horarios");
                                    scheManag.printSchedules(dbManag.getEmployeeSchedules(username,connectionDataBase));
                                    break;
                            }

                        }while (optionPermisons != 0) ;

                    break;

                }
            }else{
                System.out.println("Error to LogIn, please retry");
            }

        }while (permisons == null || valid.wantContinue());

    }

}
