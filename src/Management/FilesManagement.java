package Management;

import BasicsClasses.Employee.Employee;
import BasicsClasses.Employee.Enums.EnumCategory;
import BasicsClasses.Employee.Enums.EnumPosition;
import java.io.*;
import BasicsClasses.Employee.Enums.EnumWeekDays;
import java.util.GregorianCalendar;

public class FilesManagement {

    /**
     * This method verifies that all the files necessary for the program to work are correct.
     */

    public void checkFiles(){

        File file;

        file = new File(".\\src\\Files\\Consumables");
        checkFile(file);
        file = new File(".\\src\\Files\\Drinks");
        checkFile(file);
        file = new File(".\\src\\Files\\Foods");
        checkFile(file);
        file = new File(".\\src\\Files\\Orders");
        checkFile(file);
        file = new File(".\\src\\Files\\Payslips");
        checkFile(file);
        file = new File(".\\src\\Files\\Receipts");
        checkFile(file);
        file = new File(".\\src\\Files\\Schedules");
        checkFile(file);

        file = new File(".\\src\\Files\\Employees");
        checkFileEmployee(file);

        file = new File(".\\src\\Files\\tmp\\Consumables");
        checkFile(file);
        file = new File(".\\src\\Files\\tmp\\Drinks");
        checkFile(file);
        file = new File(".\\src\\Files\\tmp\\Foods");
        checkFile(file);
        file = new File(".\\src\\Files\\tmp\\Orders");
        checkFile(file);
        file = new File(".\\src\\Files\\tmp\\Payslips");
        checkFile(file);
        file = new File(".\\src\\Files\\tmp\\Receipts");
        checkFile(file);
        file = new File(".\\src\\Files\\tmp\\Schedules");
        checkFile(file);
        file = new File(".\\src\\Files\\tmp\\Employees");
        checkFile(file);

    }

    /**
     * This method check if the file passed by parameter exist. If not exist this file, this method create it.
     * @param file File to check
     */

    public void checkFile(File file){
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * This method checks if an employee file exists and it contains at least one employee so that the program can start
     * @param file File to check
     */

    private void checkFileEmployee(File file){
        if (!(file.length() > 0)){

            BufferedWriter BW = null;
            try {
                file.createNewFile();
                BW = new BufferedWriter(new FileWriter(file));  //TODO Encript password
                BW.write(new Employee("Administrator","Administrator","00000000T","281234567840",new GregorianCalendar(),EnumPosition.Manager,EnumCategory.Administrator,"ES3231906288456991923866","e807f1fcf82d132f9bb018ca6738a19f").toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    BW.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }



}
