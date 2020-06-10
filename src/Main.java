
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

import basicsClasses.employee.Employee;
import basicsClasses.employee.Payslip;
import basicsClasses.employee.Schedule;
import basicsClasses.orders.Order;
import management.*;
import utils.Utils;


public class Main {

    public static void main(String[] args) {

        FilesManagement fm = new FilesManagement();
        EmployeeManagement em = new EmployeeManagement();
        Validations vd = new Validations();
        OrderManagement om = new OrderManagement();
        ScheduleManagement sm = new ScheduleManagement();
        Utils u = new Utils();

        fm.checkFiles();
        String username, password, permisons, DNIToConsultData;
        String pathFileEmployee = ".\\src\\Files\\Employees", pathFileEmployeeTemp = ".\\src\\files\\temp\\EmployeesTemp";
        String pathFileOrdersTemp = ".\\src\\Files\\tmp\\OrdersTemp", pathFileOrders = ".\\src\\Files\\Orders";
        String pathPaySlips = ".\\src\\files\\Payslips", pathPaySlipsTemp = ".\\src\\files\\temp\\PayslipsTemp";
        String pathSchedule = ".\\src\\files\\Schedule", parthScheduleTemp = ".\\src\\files\\temp\\ScheduleTemp";
        Order newOrder, orderChoosed;
        int optionPermisons, optionModifyOrder, IDProductToDelete, IDProductToDecrease, amountToDecrease, IDProductToIncrease, amountToIncrease;
        String employeeDNI;
        double salary;

        Payslip payslip;
        Employee employee;
        Schedule[] schedule;

        //u.insertEmployees();
        //u.insertPaySlips();
        //u.insertSchedules();
        //u.insertFoods();
        //u.insertDrinks();
        //u.insertOrders();
        //u.insertProducts();


        do {

            //username = vd.readAndValidateUsername();
            //password = vd.readAndValidatePassword();

            username = "00000000T";
            password = "e807f1fcf82d132f9bb018ca6738a19f";

            permisons = em.getPermisons(username, password, pathFileEmployee);

            if (permisons != null){
                switch (permisons) {
                    case "Administrator":
                        //optionOfAdministrator

                        do {
                            optionPermisons = vd.readAndValidateOptionsAdministrator();

                            switch (optionPermisons) {
                                case 0:
                                    System.out.println("Closed session.");
                                    break;


                                case 1: //Hire an employee
                                    System.out.println("Hire an employee");
                                    employee = em.collectEmployeeData(); //We collect details of the new employee
                                    fm.insertObjectInFile(employee, pathFileEmployee); //We register a new employee in the file



                                    break;


                                case 2:
                                    System.out.println("Dismiss an employee");
                                    fm.showFileData(pathFileEmployee);
                                    employeeDNI = vd.readAndValidateUsername(); //We select the id of the employee that we want to unsubscribe
                                    employee = fm.getSelectedEmployee(pathFileEmployee, employeeDNI); //We get the object used
                                    fm.insertObjectDeletedInFile(employee, pathFileEmployeeTemp); //We mark the object as deleted and insert it in the temporary file
                                    break;


                                case 3:
                                    System.out.println("Assign schedule to an employee");
                                    fm.showFileData(pathFileEmployee); //Show file data
                                    System.out.println("Enter the DNI of the employee to whom you want to assign a schedule");
                                    employeeDNI = vd.readAndValidateUsername(); //Select the ID of the employee to whom we want to change the schedule
                                    schedule = sm.setScheduleData(); //Create a new employee
                                    fm.insertScheduleOnFile(pathSchedule, employeeDNI, parthScheduleTemp, schedule); //Insert the new modified data in the temporary file
                                    
                                    break;


                                case 4:
                                    System.out.println("Modify employee schedule");
                                    fm.showFileData(pathFileEmployee); //Show all employees
                                    System.out.println("Enter the DNI of the employee to whom you want to assign a schedule");
                                    employeeDNI = vd.readAndValidateUsername(); //Select the ID of the employee to whom we want to change the schedule
                                    schedule = sm.setScheduleData(); //Create a new schedule
                                    fm.insertScheduleOnFile(pathSchedule, employeeDNI, parthScheduleTemp, schedule); //Insert the new modified data in the temporary file

                                    break;


                                case 5: //Modify salary
                                    fm.showFileData(pathPaySlips);
                                    employeeDNI = vd.readAndValidateUsername(); //Read employee DNI
                                    salary = vd.readAndValidateSalary();  //Assign new salary
                                    payslip = fm.insertSalary(pathPaySlips, employeeDNI, salary); //Create a payslip object with the new salary
                                    fm.insertObjectModifiedInFile(payslip, pathPaySlipsTemp ); //Add the new modified object to the temporary file and then add it to the master file

                                    break;


                                case 6:
                                    System.out.println();
                                    DNIToConsultData = vd.readAndValidateUsername();
                                    fm.printEmployeePersonalData(DNIToConsultData,pathFileEmployee);
                                    System.out.println();
                                    break;

                                case 7:
                                    newOrder = om.readAndValidateNewOrder(pathFileOrders);
                                    if (!fm.insertObjectInFile(newOrder,pathFileOrdersTemp)) {
                                        System.out.println("The new order wasn't added");
                                    }
                                    break;
                                case 8:
                                    //Show and chooshe order ID
                                    orderChoosed = vd.chooseOrderByID(fm.getOrdersNotShipped(pathFileOrders));
                                    //Repeat
                                    do {
                                        //Read and show options: modify order
                                        optionModifyOrder = vd.readAndValidateOptionsOrder();

                                        switch (optionModifyOrder){
                                            //case 1 (Add product)
                                            case 1:
                                                orderChoosed.addOrderLine(vd.readAndValidateNewOrderLine(pathFileOrders));
                                                break;

                                            //case 2 (Delete product)
                                            case 2:
                                                //Show product from list
                                                orderChoosed.printOrdersLines();
                                                //Select Product ID to delete
                                                IDProductToDelete = vd.readAndValidateIDProductOfOrder(orderChoosed.getIDProducts());
                                                //Delete OrderLine
                                                orderChoosed.removeOrderLine(IDProductToDelete);
                                                break;

                                            //case 3 (decrease product quantity)
                                            case 3:
                                                //Show products in the list
                                                orderChoosed.printOrdersLines();
                                                //Choose ID
                                                IDProductToDecrease = vd.readAndValidateIDProductOfOrder(orderChoosed.getIDProducts());
                                                //Read and validate quantity
                                                amountToDecrease = vd.readAndValidateQuantityToDecrease(orderChoosed.quantityOfAProduct(IDProductToDecrease));
                                                //drecrease amount
                                                orderChoosed.decreaseAmountProduct(IDProductToDecrease,amountToDecrease);
                                                //Show final result
                                                orderChoosed.printOrderLine(IDProductToDecrease);
                                                break;

                                            //case 4 (Increase product quantity)
                                            case 4:
                                                //Show products in the list
                                                orderChoosed.printOrdersLines();
                                                //Choose ID
                                                IDProductToIncrease = vd.readAndValidateIDProductOfOrder(orderChoosed.getIDProducts());
                                                //Read and validate quantity
                                                amountToIncrease = vd.readAndValidateQuantityToIncrease();
                                                //Increase amount
                                                orderChoosed.increaseAmountProduct(IDProductToIncrease,amountToIncrease);
                                                //Show final result
                                                orderChoosed.printOrderLine(IDProductToIncrease);
                                                break;

                                            //case 5 (Show all orders lines)
                                            case 5:
                                                orderChoosed.printOrdersLines();
                                                break;


                                            //case 6 (cancel order)
                                            case 6:
                                                orderChoosed.markCancel();
                                                System.out.println("The order with ID: "+orderChoosed.getID()+" was canceled.");
                                                //cancelar pedido
                                                break;
                                            //finSegun
                                        }
                                    }while (optionModifyOrder != 0 && !orderChoosed.getCancel());
                                    fm.insertObjectModifiedInFile(orderChoosed,pathFileOrdersTemp);
                                    //Añadir al fichero las modificaciones del pedido
                                    break;


                                case 9:
                                    System.out.println("Show administrator personal data");
                                    fm.printEmployeePersonalData(username,pathFileEmployee);

                                    break;


                                case 10:
                                    System.out.println("Check schedule");
                                    fm.printScheduleFromFile(pathSchedule,username);
                                    break;
                            }

                        }while (optionPermisons != 0) ;

                    break;

                    case "FloorManager":
                        //optionOfFloorManager

                        optionPermisons = vd.readAndValidateOptionsFloorManager();

                        switch (optionPermisons){
                            case 0:
                                System.out.println("Session closed.");
                            break;

                            case 1:
                                System.out.println("Show employee personal data");
                            break;


                            case 2:
                                System.out.println("Assign schedule to an employee");
                                fm.showFileData(pathFileEmployee);
                                System.out.println("Enter the DNI of the employee to whom you want to assign a schedule");
                                employeeDNI = vd.readAndValidateUsername();
                                schedule = sm.setScheduleData();
                                fm.insertScheduleOnFile(pathSchedule, employeeDNI, parthScheduleTemp, schedule);
                            break;


                            case 3:
                                System.out.println("Modify employee schedule");
                                fm.showFileData(pathFileEmployee);
                                System.out.println("Enter the DNI of the employee to whom you want to assign a schedule");
                                employeeDNI = vd.readAndValidateUsername();
                                schedule = sm.setScheduleData();
                                fm.insertScheduleOnFile(pathSchedule, employeeDNI, parthScheduleTemp, schedule);


                            break;


                            case 4:
                                System.out.println("Show floor manager personal data");
                                fm.printEmployeePersonalData(username, pathFileEmployee);
                            break;


                            case 5:
                                System.out.println("Check schedule");
                                fm.printScheduleFromFile(pathSchedule,username);
                            break;
                        }

                    break;

                    case "Staff":
                        //optionOfStaff

                        optionPermisons = vd.readAndValidateOptionsStaff();
                        switch (optionPermisons){
                            case 0:
                                System.out.println("Session closed.");
                            break;

                            case 1:
                                System.out.println("Show employee personal data");
                                fm.printEmployeePersonalData(username,pathFileEmployee);

                            break;


                            case 2:
                                System.out.println("Check schedule");
                                fm.printScheduleFromFile(pathSchedule,username);
                            break;
                        }

                    break;
                }
            }else{
                System.out.println("Error to LogIn, please retry");
            }

        }while (permisons == null || vd.wantContinue());

    }

}
