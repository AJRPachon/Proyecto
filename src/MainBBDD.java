
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
import Management.*;
import utils.Utils;

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

        String username, password, permisons, DNIToConsultData;
        Order newOrder, orderChoosed;
        int optionPermisons, optionModifyOrder, IDProductToDelete, IDProductToDecrease, amountToDecrease, IDProductToIncrease, amountToIncrease;
        String employeeDNI;
        double salary;

        EmployeeManagement em = new EmployeeManagement();
        DatabaseManagement db = new DatabaseManagement();

        Payslip payslip;
        Employee employee;
        Schedule[] schedule;

        FilesManagement FM = new FilesManagement();
        EmployeeManagement EM = new EmployeeManagement();
        Validations VD = new Validations();
        OrderManagement OM = new OrderManagement();
        ScheduleManagement SM = new ScheduleManagement();
        Utils U = new Utils();

        FM.checkFiles();

        String pathFileEmployee = ".\\src\\Files\\Employees", pathFileEmployeeTemp = ".\\src\\files\\temp\\EmployeesTemp";
        String pathFileOrdersTemp = ".\\src\\Files\\tmp\\OrdersTemp", pathFileOrders = ".\\src\\Files\\Orders";
        String pathPaySlips = ".\\src\\files\\Payslips", pathPaySlipsTemp = ".\\src\\files\\temp\\PayslipsTemp";
        String pathSchedule = ".\\src\\files\\Schedule", parthScheduleTemp = ".\\src\\files\\temp\\ScheduleTemp";

        do {

            //username = VD.readAndValidateUsername();
            //password = VD.readAndValidatePassword();

            username = "00000000T";
            password = "e807f1fcf82d132f9bb018ca6738a19f";

            permisons = db.getPermisons(username, password, connectionDataBase);

            if (permisons != null){
                switch (permisons) {
                    case "Administrator":
                        //optionOfAdministrator

                        do {
                            optionPermisons = VD.readAndValidateOptionsAdministrator();

                            switch (optionPermisons) {
                                case 0:
                                    System.out.println("Closed session.");
                                    break;

                                case 1: //Dar de alta a un empleado
                                    System.out.println("Dar de alta a un empleado");
                                    employee = EM.collectEmployeeData();
                                    if (!db.insertEmployee(employee, connectionDataBase)){
                                        System.out.println("Could not insert employee");
                                    }else{
                                        System.out.println("Employee inserted");
                                    }
                                    break;

                                case 2:
                                    System.out.println("Dar de baja a empleado");
                                    employeeDNI = VD.readAndValidateUsername(connectionDataBase);
                                    employee = db.getEmployeeByDNI(employeeDNI, connectionDataBase);
                                    if (employee != null){
                                        if (!db.deleteEmployee(employee, connectionDataBase)){
                                            System.out.println("Could not delete employee");
                                        }else{
                                            System.out.println("Employee deleted");
                                        }
                                    }
                                    break;


                                case 3:
                                    System.out.println("Asignar horario a empleado");
                                    System.out.println("Enter the DNI of the employee to whom you want to assign a schedule");
                                    employeeDNI = VD.readAndValidateUsername(connectionDataBase); //Seleccionamos el dni del empleado al cual queremos cambiarle el horario
                                    schedule = SM.setScheduleData(); //Creamos un nuevo horario
                                    if (!db.insertSchedules(schedule,employeeDNI,connectionDataBase)){
                                        System.out.println("Could not assign schedule");
                                    }else{
                                        System.out.println("Schedule assigned");
                                    }
                                    break;


                                case 4:
                                    //TODO Implementar bbdd
                                    System.out.println("Modificar horario de empleado");
                                    //TODO Se hace igual que asignar horario a empleado pero tendría que poderse cambiar sólo X días seleccionados
                                    FM.showFileData(pathFileEmployee); //Mostramos todos los empleados
                                    System.out.println("Introduzca el DNI del empleado al que desea asignar un horario");
                                    employeeDNI = VD.readAndValidateUsername(); //Seleccionamos el dni del empleado al cual queremos cambiarle el horario
                                    schedule = SM.setScheduleData(); //Creamos un nuevo horario
                                    FM.insertEmployeeOnSchedule(pathSchedule, employeeDNI, parthScheduleTemp, schedule); //Insertamos los nuevos datos modificados en el fichero temporal
                                    break;


                                case 5: //Modificar sueldo a empleado
                                    employeeDNI = VD.readAndValidateUsername(connectionDataBase); //Lee el dni del empleado
                                    salary = VD.readAndValidateSalary();  //Asignamos el nuevo salario
                                    if (!db.modifySalary(new Payslip(salary, new Employee(employeeDNI)), connectionDataBase)){
                                        System.out.println("Could not modify the salary");
                                    }else{
                                        System.out.println("Salary modified");
                                    }
                                    break;


                                case 6:
                                    System.out.println();
                                    DNIToConsultData = VD.readAndValidateUsername(connectionDataBase);
                                    EM.printEmployeePersonalData(db.getEmployeeByDNI(DNIToConsultData,connectionDataBase));
                                    System.out.println();
                                    break;

                                case 7:
                                    newOrder = OM.readAndValidateNewOrder(pathFileOrders);
                                    if (!FM.insertObjectInFile(newOrder,pathFileOrdersTemp)) {
                                        System.out.println("The new order wasn't added");
                                    }else{
                                        System.out.println("Order added");
                                    }
                                    break;
                                case 8:
                                    ordersNotShipped = db.getOrdersNotShipped(connectionDataBase);
                                    if (ordersNotShipped.size() > 0){
                                        orderChoosed = VD.chooseOrderByID(ordersNotShipped);
                                        //Repetir
                                        do {
                                            //leer y mostrar opciones modificar pedido
                                            optionModifyOrder = VD.readAndValidateOptionsOrder();
                                            //segun (opcion del pedido)
                                            switch (optionModifyOrder){
                                                //caso 1 (añadir producto)
                                                case 1:
                                                    if (!db.insertNewOrderLine(orderChoosed, VD.readAndValidateNewOrderLine(connectionDataBase) ,connectionDataBase)) {
                                                        System.out.println("The new order line wasn't added");
                                                    }
                                                    break;

                                                //caso 2 (eliminar producto)
                                                case 2:
                                                    //mostrar productos en la lista
                                                    orderChoosed.printOrdersLines();
                                                    //elegir ID del pedido a eliminar
                                                    IDProductToDelete = VD.readAndValidateIDProductOfOrder(orderChoosed.getIDProducts());
                                                    //eliminar linea de pedido
                                                    orderChoosed.removeOrderLine(IDProductToDelete);
                                                    break;

                                                //caso 3 (disminuir cantidad producto)
                                                case 3:
                                                    //mostrar productos en la lista
                                                    orderChoosed.printOrdersLines();
                                                    //elegir ID del pedido a disminuir cantidas
                                                    IDProductToDecrease = VD.readAndValidateIDProductOfOrder(orderChoosed.getIDProducts());
                                                    //leer y validar cantidad a disminuir
                                                    amountToDecrease = VD.readAndValidateQuantityToDecrease(orderChoosed.quantityOfAProduct(IDProductToDecrease));
                                                    //dismiuir cantidad
                                                    orderChoosed.decreaseAmountProduct(IDProductToDecrease,amountToDecrease);
                                                    //mostrar resultado final
                                                    orderChoosed.printOrderLine(IDProductToDecrease);
                                                    break;

                                                //caso 4 (disminuir cantidad producto)
                                                case 4:
                                                    //mostrar productos en la lista
                                                    orderChoosed.printOrdersLines();
                                                    //elegir ID del pedido a aumentar cantidad
                                                    IDProductToIncrease = VD.readAndValidateIDProductOfOrder(orderChoosed.getIDProducts());
                                                    //leer y validar cantidad a aumentar
                                                    amountToIncrease = VD.readAndValidateQuantityToIncrease();
                                                    //aumentar cantidad
                                                    orderChoosed.increaseAmountProduct(IDProductToIncrease,amountToIncrease);
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
                                                    System.out.println("El pedido con ID: "+orderChoosed.getID()+" fue cancelado.");
                                                    //cancelar pedido
                                                    break;
                                                //finSegun
                                            }
                                        }while (optionModifyOrder != 0 && !orderChoosed.getCancel());
                                        FM.insertObjectModifiedInFile(orderChoosed,pathFileOrdersTemp);
                                    }else{
                                        System.out.println("Not exist orders without sent or not cancel");
                                    }
                                    break;


                                case 9:
                                    System.out.println();
                                    EM.printEmployeePersonalData(db.getEmployeeByDNI(username,connectionDataBase));
                                    System.out.println();
                                    break;


                                case 10:
                                    System.out.println("Consultar sus horarios");
                                    FM.printScheduleFromFile(pathSchedule,username);
                                    break;
                            }

                        }while (optionPermisons != 0) ;

                        break;

                    case "FloorManager":
                        //optionOfFloorManager

                        optionPermisons = VD.readAndValidateOptionsFloorManager();
                        switch (optionPermisons){
                            case 0:
                                System.out.println("Session closed.");
                                break;
                            case 1:
                                System.out.println();
                                DNIToConsultData = VD.readAndValidateUsername(connectionDataBase);
                                EM.printEmployeePersonalData(db.getEmployeeByDNI(DNIToConsultData,connectionDataBase));
                                System.out.println();
                                break;


                            case 2:
                                System.out.println("Asignar horario a empleado");
                                System.out.println("Enter the DNI of the employee to whom you want to assign a schedule");
                                employeeDNI = VD.readAndValidateUsername(connectionDataBase); //Seleccionamos el dni del empleado al cual queremos cambiarle el horario
                                schedule = SM.setScheduleData(); //Creamos un nuevo horario
                                if (!db.insertSchedules(schedule,employeeDNI,connectionDataBase)){
                                    System.out.println("Could not assign schedule");
                                }else{
                                    System.out.println("Schedule assigned");
                                }
                                break;

                            case 3:
                                System.out.println("Modificar horario de empleado");
                                //TODO Se hace igual que asignar horario a empleado pero tendría que poderse cambiar sólo X días seleccionados
                                FM.showFileData(pathFileEmployee); //Mostramos todos los empleados
                                System.out.println("Introduzca el DNI del empleado al que desea asignar un horario");
                                employeeDNI = VD.readAndValidateUsername(); //Seleccionamos el dni del empleado al cual queremos cambiarle el horario
                                schedule = SM.setScheduleData(); //Creamos un nuevo horario
                                FM.insertEmployeeOnSchedule(pathSchedule, employeeDNI, parthScheduleTemp, schedule); //Insertamos los nuevos datos modificados en el fichero temporal
                                break;


                            case 4:
                                System.out.println();
                                EM.printEmployeePersonalData(db.getEmployeeByDNI(username,connectionDataBase));
                                System.out.println();
                                break;


                            case 5:
                                System.out.println("Consultar sus horarios");
                                FM.printScheduleFromFile(pathSchedule,username);
                                break;
                        }

                        break;

                    case "Staff":
                        //optionOfStaff

                        optionPermisons = VD.readAndValidateOptionsStaff();
                        switch (optionPermisons){
                            case 0:
                                System.out.println("Session closed.");
                                break;

                            case 1:
                                System.out.println();
                                EM.printEmployeePersonalData(db.getEmployeeByDNI(username,connectionDataBase));
                                System.out.println();
                                break;


                            case 2:
                                System.out.println("Consultar sus horarios");
                                FM.printScheduleFromFile(pathSchedule,username);
                                break;
                        }

                        break;
                }
            }else{
                System.out.println("Error to LogIn, please retry");
            }

        }while (permisons == null || VD.wantContinue());

    }

}
