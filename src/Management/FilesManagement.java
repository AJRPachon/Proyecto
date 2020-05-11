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

        file = new File(".\\src\\Files\\ConsumablesTemp");
        checkFile(file);
        file = new File(".\\src\\Files\\DrinksTemp");
        checkFile(file);
        file = new File(".\\src\\Files\\FoodsTemp");
        checkFile(file);
        file = new File(".\\src\\Files\\OrdersTemp");
        checkFile(file);
        file = new File(".\\src\\Files\\PayslipsTemp");
        checkFile(file);
        file = new File(".\\src\\Files\\ReceiptsTemp");
        checkFile(file);
        file = new File(".\\src\\Files\\SchedulesTemp");
        checkFile(file);

        file = new File(".\\src\\Files\\EmployeesTemp");
        checkFileEmployee(file);

        file = new File(".\\src\\Files\\tmp\\ConsumablesTemp");
        checkFile(file);
        file = new File(".\\src\\Files\\tmp\\DrinksTemp");
        checkFile(file);
        file = new File(".\\src\\Files\\tmp\\FoodsTemp");
        checkFile(file);
        file = new File(".\\src\\Files\\tmp\\OrdersTemp");
        checkFile(file);
        file = new File(".\\src\\Files\\tmp\\PayslipsTemp");
        checkFile(file);
        file = new File(".\\src\\Files\\tmp\\ReceiptsTemp");
        checkFile(file);
        file = new File(".\\src\\Files\\tmp\\SchedulesTemp");
        checkFile(file);
        file = new File(".\\src\\Files\\tmp\\EmployeesTemp");
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


    /**

    /**
     * @param ID
     * @param path
     * @return
     */


    public Product getProductFromFile(int ID, String path){
        Product newProduct = null;
        BufferedReader BR;
        String[] lineParted;
        String line;

        try {
            BR = new BufferedReader(new FileReader(path));
            line = BR.readLine();
            while (line != null && newProduct == null){
                lineParted = line.split("#");
                if (Integer.parseInt(lineParted[0]) == ID){
                    newProduct = new Product(Integer.parseInt(lineParted[0]),lineParted[1],lineParted[2],Double.parseDouble(lineParted[3]));
                }
                line = BR.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return newProduct;
    }



}
