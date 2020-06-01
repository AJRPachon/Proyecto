
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

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        FilesManagement FM = new FilesManagement();
        EmployeeManagement EM = new EmployeeManagement();
        Validations VD = new Validations();
        OrderManagement OM = new OrderManagement();

        FM.checkFiles();
        String username, password;
        String permisons;
        Order newOrder;
        int optionPermisons;

        do {

            username = VD.readAndValidateUsername();
            password = VD.readAndValidatePassword();

            permisons = EM.getPermisons(username, password);

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
                                    String path = " ";
                                    FM.insertObjectInFile(EM.collectEmployeeData(), path);

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
                                    if (!OM.insertOrder(newOrder)) {
                                        System.out.println("The new order wasn't added");
                                    }
                                    break;
                                case 9:
                                    System.out.println("Modificar pedido");
                                    break;
                                case 10:
                                    System.out.println("Cancelar pedido");
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

                        optionPermisons = VD.readAndValidateOptionsFloorManager();
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
