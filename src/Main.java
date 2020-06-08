
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


public class Main {

    public static void main(String[] args) {

        FilesManagement FM = new FilesManagement();
        EmployeeManagement EM = new EmployeeManagement();
        Validations VD = new Validations();
        OrderManagement OM = new OrderManagement();
        ScheduleManagement SM = new ScheduleManagement();

        FM.checkFiles();
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

        do {

            //username = VD.readAndValidateUsername();
            //password = VD.readAndValidatePassword();

            username = "00000000T";
            password = "e807f1fcf82d132f9bb018ca6738a19f";

            permisons = EM.getPermisons(username, password, pathFileEmployee);

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


                                case 1: //Hire an employee
                                    System.out.println("Hire an employee");
                                    employee = EM.collectEmployeeData(); //We collect details of the new employee
                                    FM.insertObjectInFile(employee, pathFileEmployee); //We register a new employee in the file



                                    break;


                                case 2:
                                    System.out.println("Dismiss an employee");
                                    FM.showFileData(pathFileEmployee);
                                    employeeDNI = VD.readAndValidateUsername(); //We select the id of the employee that we want to unsubscribe
                                    employee = FM.getSelectedEmployee(pathFileEmployee, employeeDNI); //We get the object used
                                    FM.insertObjectDeletedInFile(employee, pathFileEmployeeTemp); //We mark the object as deleted and insert it in the temporary file
                                    break;


                                case 3:
                                    System.out.println("Assign schedule to an employee");
                                    FM.showFileData(pathFileEmployee); //Show file data
                                    System.out.println("Enter the DNI of the employee to whom you want to assign a schedule");
                                    employeeDNI = VD.readAndValidateUsername(); //Select the ID of the employee to whom we want to change the schedule
                                    schedule = SM.setScheduleData(); //Create a new employee
                                    FM.insertScheduleOnFile(pathSchedule, employeeDNI, parthScheduleTemp, schedule); //Insert the new modified data in the temporary file
                                    
                                    break;


                                case 4:
                                    System.out.println("Modify employee schedule");
                                    FM.showFileData(pathFileEmployee); //Show all employees
                                    System.out.println("Enter the DNI of the employee to whom you want to assign a schedule");
                                    employeeDNI = VD.readAndValidateUsername(); //Select the ID of the employee to whom we want to change the schedule
                                    schedule = SM.setScheduleData(); //Create a new schedule
                                    FM.insertScheduleOnFile(pathSchedule, employeeDNI, parthScheduleTemp, schedule); //Insert the new modified data in the temporary file

                                    break;


                                case 5: //Modify salary
                                    FM.showFileData(pathPaySlips);
                                    employeeDNI = VD.readAndValidateUsername(); //Read employee DNI
                                    salary = VD.readAndValidateSalary();  //Assign new salary
                                    payslip = FM.insertSalary(pathPaySlips, employeeDNI, salary); //Create a payslip object with the new salary
                                    FM.insertObjectModifiedInFile(payslip, pathPaySlipsTemp ); //Add the new modified object to the temporary file and then add it to the master file

                                    break;


                                case 6:
                                    System.out.println();
                                    DNIToConsultData = VD.readAndValidateUsername();
                                    FM.printEmployeePersonalData(DNIToConsultData,pathFileEmployee);
                                    System.out.println();
                                    break;

                                case 7:
                                    newOrder = OM.readAndValidateNewOrder();
                                    if (!FM.insertObjectInFile(newOrder,pathFileOrdersTemp)) {
                                        System.out.println("The new order wasn't added");
                                    }
                                    break;
                                case 8:
                                    //Show and chooshe order ID
                                    orderChoosed = VD.chooseOrderByID(FM.getOrdersNotShipped(pathFileOrders));
                                    //Repeat
                                    do {
                                        //Read and show options: modify order
                                        optionModifyOrder = VD.readAndValidateOptionsOrder();

                                        switch (optionModifyOrder){
                                            //case 1 (Add product)
                                            case 1:
                                                orderChoosed.addOrderLine(VD.readAndValidateNewOrderLine());
                                                break;

                                            //case 2 (Delete product)
                                            case 2:
                                                //Show product from list
                                                orderChoosed.printOrdersLines();
                                                //Select Product ID to delete
                                                IDProductToDelete = VD.readAndValidateIDProductOfOrder(orderChoosed.getIDProducts());
                                                //Delete OrderLine
                                                orderChoosed.removeOrderLine(IDProductToDelete);
                                                break;

                                            //case 3 (decrease product quantity)
                                            case 3:
                                                //Show products in the list
                                                orderChoosed.printOrdersLines();
                                                //Choose ID
                                                IDProductToDecrease = VD.readAndValidateIDProductOfOrder(orderChoosed.getIDProducts());
                                                //Read and validate quantity
                                                amountToDecrease = VD.readAndValidateQuantityToDecrease(orderChoosed.quantityOfAProduct(IDProductToDecrease));
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
                                                IDProductToIncrease = VD.readAndValidateIDProductOfOrder(orderChoosed.getIDProducts());
                                                //Read and validate quantity
                                                amountToIncrease = VD.readAndValidateQuantityToIncrease();
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
                                    FM.insertObjectModifiedInFile(orderChoosed,pathFileOrdersTemp);
                                    //Añadir al fichero las modificaciones del pedido
                                    break;


                                case 9:
                                    System.out.println("Show administrator personal data");
                                    FM.printEmployeePersonalData(username,pathFileEmployee);

                                    break;


                                case 10:
                                    System.out.println("Check schedule");
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
                                System.out.println("Show employee personal data");
                            break;


                            case 2:
                                System.out.println("Assign schedule to an employee");
                                FM.showFileData(pathFileEmployee);
                                System.out.println("Enter the DNI of the employee to whom you want to assign a schedule");
                                employeeDNI = VD.readAndValidateUsername();
                                schedule = SM.setScheduleData();
                                FM.insertScheduleOnFile(pathSchedule, employeeDNI, parthScheduleTemp, schedule);
                            break;


                            case 3:
                                System.out.println("Modificar horario de empleado");
                                FM.showFileData(pathFileEmployee);
                                System.out.println("Introduzca el DNI del empleado al que desea asignar un horario");
                                employeeDNI = VD.readAndValidateUsername();
                                schedule = SM.setScheduleData();
                                FM.insertScheduleOnFile(pathSchedule, employeeDNI, parthScheduleTemp, schedule);


                            break;


                            case 4:
                                System.out.println("Ver datos personales floor manager");
                                FM.printEmployeePersonalData(username, pathFileEmployee);
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
                                System.out.println("Ver datos personales del empleado");
                                FM.printEmployeePersonalData(username,pathFileEmployee);

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
