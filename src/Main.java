
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
                        break;

                    case "FloorManager":
                        //optionOfFloorManager
                        break;

                    case "Staff":
                        //optionOfStaff
                        break;
                }
            }else{
                System.out.println("Error to LogIn, please retry");
            }

        }while (permisons == null || VD.wantContinue());

    }

}
