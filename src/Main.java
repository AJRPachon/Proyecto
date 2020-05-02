
/*
 *
 * PG0
 * INIT
 *  checkFiles*
 *  readUser
 *  readPassword
 *  do
 *      switch()
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
 *  while(checkLogIn fail and user want continue)
 *  checkLogIn*
 * END
 *
 */

import Management.FilesManagement;

public class Main {

    public static void main(String[] args) {

        FilesManagement FM = new FilesManagement();

        FM.checkFiles();

    }

}
