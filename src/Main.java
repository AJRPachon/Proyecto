
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

import Management.EmployeeManagement;
import Management.FilesManagement;
import Management.Validations;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        FilesManagement FM = new FilesManagement();
        EmployeeManagement EM = new EmployeeManagement();
        Validations VD = new Validations();

        FM.checkFiles();
        String username, password;
        String permisons = null;

        do {

            username = VD.readAndValidateUsername();
            password = VD.readAndValidatePassword();

            permisons = EM.getPermisons(username, password);

            if (permisons != null){
                switch (permisons) {
                    case "Administrator":
                        //optionOfAdministrator
                        System.out.println("Dar de alta a empleado");
                        System.out.println("Dar de baja a empleado");
                        System.out.println("Asignar horario a empleado");
                        System.out.println("Modificar horario de empleado");
                        System.out.println("Modificar sueldo empleado");
                        System.out.println("Ver datos personales del empleado");
                        System.out.println("Ver datos personales del floor manager");
                        System.out.println("Realizar pedidos");
                        System.out.println("Modificar pedido");
                        System.out.println("Cancelar pedido");
                        System.out.println("Ver datos personales administrador");
                        System.out.println("Consultar sus horarios");
                        System.out.println("Cerrar sesion");
                    break;

                    case "FloorManager":
                        //optionOfFloorManager
                        System.out.println("Ver datos personales del empleado");
                        System.out.println("Asignar horario a empleado");
                        System.out.println("Modificar horario de empleado");
                        System.out.println("Ver datos personales floor manager");
                        System.out.println("Consultar sus horarios");
                        System.out.println("Cerrar sesion");
                    break;

                    case "Staff":
                        //optionOfStaff
                        System.out.println("Ver datos personales del empleado");
                        System.out.println("Consultar sus horarios");
                        System.out.println("Cerrar sesion");
                    break;
                }
            }else{
                System.out.println("Error to LogIn, please retry");
            }

        }while (permisons == null || VD.wantContinue());

    }

}
