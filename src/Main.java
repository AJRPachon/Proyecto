
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

import java.lang.reflect.Array;
import java.util.ArrayList;


public class Main {

    public static void main(String[] args) {

        FilesManagement FM = new FilesManagement();
        EmployeeManagement EM = new EmployeeManagement();
        Validations VD = new Validations();
        OrderManagement OM = new OrderManagement();

        FM.checkFiles();
        String username, password, permisons;
        String pathFileEmployee = ".\\src\\Files\\Employees", pathFileOrdersTemp = ".\\src\\Files\\tmp\\OrdersTemp";
        Order newOrder;
        ArrayList<Order> ordersNotShipped;
        int optionPermisons;

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
                                    System.out.println("Ver datos personales del empleado");
                                    break;
                                case 7:
                                    System.out.println("Ver datos personales del floor manager");
                                    break;
                                case 8:
                                    newOrder = OM.readAndValidateNewOrder();
                                    if (!FM.insertObjectInFile(newOrder,pathFileOrdersTemp)) {
                                        System.out.println("The new order wasn't added");
                                    }
                                    break;
                                case 9:
                                    //mostrar pedidos sin enviar
                                    OM.printArrayListOrders(ordersNotShipped = FM.getOrdersNotShipped(".\\src\\Files\\Orders"));
                                    //leer y validar el ID del pedido introducido
                                    
                                    //Mostrar opciones modificar pedido
                                    //Repetir
                                        //segun (opcion del pedido)
                                            //caso 1 (añadir producto)
                                                //leer y validar linea de pedido
                                                //añadir linea
                                            //caso 2 (eliminar producto)
                                                //mostrar productos en la lista
                                                //eliminar linea de pedido
                                            //caso 3 (disminuir cantidad producto)
                                                //mostrar productos en la lista
                                                //leer y validar cantidad a disminuir
                                                //dismiuir cantidad
                                            //caso 4 (disminuir cantidad producto)
                                                //mostrar productos en la lista
                                                //leer y validar cantidad a aumentar
                                                //aumentar cantidad
                                        //finSegun
                                        //Mostrar opciones modificar pedido
                                    //mientras (opcion modificar distinta de 0)
                                    break;
                                case 10:
                                    System.out.println("Cancelar pedido");
                                    //mostrar pedidos sin enviar
                                    //leer y validar el ID del pedido introducido
                                    //cancelar pedido
                                    break;
                                case 11:
                                    System.out.println("Ver datos personales administrador");
                                    break;
                                case 12:
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
