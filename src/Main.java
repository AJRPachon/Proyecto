
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

import BasicsClasses.Orders.Order;
import Management.EmployeeManagement;
import Management.FilesManagement;
import Management.OrderManagement;
import Management.Validations;


public class Main {

    public static void main(String[] args) {

        FilesManagement FM = new FilesManagement();
        EmployeeManagement EM = new EmployeeManagement();
        Validations VD = new Validations();
        OrderManagement OM = new OrderManagement();

        FM.checkFiles();
        String username, password, permisons, DNIToConsultData;
        String pathFileEmployee = ".\\src\\Files\\Employees", pathFileOrdersTemp = ".\\src\\Files\\tmp\\OrdersTemp", pathFileOrders = ".\\src\\Files\\Orders";
        Order newOrder, orderChoosed;
        int optionPermisons, optionModifyOrder, IDProductToDelete, IDProductToDecrease, amountToDecrease, IDProductToIncrease, amountToIncrease;

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
                                case 1:
                                    System.out.println("Dar de alta a empleado");
                                    break;
                                case 2:
                                    System.out.println("Dar de baja a empleado");
                                    break;
                                case 3:
                                    System.out.println("Asignar horario a empleado");
                                    break;
                                case 4:
                                    System.out.println("Modificar horario de empleado");
                                    break;
                                case 5:
                                    System.out.println("Modificar sueldo empleado");
                                    break;
                                case 6:
                                    System.out.println();
                                    DNIToConsultData = VD.readAndValidateUsername();
                                    FM.printPersonalData(DNIToConsultData,pathFileEmployee);
                                    System.out.println();
                                    break;
                                case 7:
                                    newOrder = OM.readAndValidateNewOrder();
                                    if (!FM.insertObjectInFile(newOrder,pathFileOrdersTemp)) {
                                        System.out.println("The new order wasn't added");
                                    }
                                    break;
                                case 8:
                                    //mostrar y elegir order por ID
                                    orderChoosed = VD.chooseOrderByID(FM.getOrdersNotShipped(pathFileOrders));
                                    //Repetir
                                    do {
                                        //leer y mostrar opciones modificar pedido
                                        optionModifyOrder = VD.readAndValidateOptionsOrder();
                                        //segun (opcion del pedido)
                                        switch (optionModifyOrder){
                                            //caso 1 (añadir producto)
                                            case 1:
                                                orderChoosed.addOrderLine(VD.readAndValidateNewOrderLine());
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
                                    //Añadir al fichero las modificaciones del pedido
                                    break;
                                case 9:
                                    System.out.println();
                                    DNIToConsultData = username;
                                    FM.printPersonalData(DNIToConsultData,pathFileEmployee);
                                    System.out.println();
                                    break;
                                case 10:
                                    System.out.println("Consultar sus horarios");
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
                                System.out.println("Ver datos personales del empleado");
                            break;
                            case 2:
                                System.out.println("Asignar horario a empleado");
                            break;
                            case 3:
                                System.out.println("Modificar horario de empleado");
                            break;
                            case 4:
                                System.out.println("Ver datos personales floor manager");
                            break;
                            case 5:
                                System.out.println("Consultar sus horarios");
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
                            break;
                            case 2:
                                System.out.println("Consultar sus horarios");
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
