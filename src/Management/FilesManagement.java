package Management;

import BasicsClasses.Employee.Employee;
import BasicsClasses.Employee.Enums.EnumCategory;
import BasicsClasses.Employee.Enums.EnumPosition;
import java.io.*;
import BasicsClasses.FoodstuffDrinks.Product;
import BasicsClasses.Orders.Order;
import BasicsClasses.Orders.OrderLine;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;
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
        file = new File(".\\src\\Files\\Products");
        checkFile(file);

        file = new File(".\\src\\Files\\Employees");
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
        file = new File(".\\src\\Files\\tmp\\ProductsTemp");
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

                BW = new BufferedWriter(new FileWriter(file));
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
     * @param object
     * @param path
     * @param <T>
     * @return
     */

    public <T> boolean insertObjectInFile(T object, String path){

        boolean objectInserted = false;
        FileWriter FW = null;

        try {
            FW = new FileWriter(path,true);
            FW.write(object.toString()+"\n");
            FW.flush();
            objectInserted = true;

        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                FW.close();
            }catch (IOException|NullPointerException error){
                error.printStackTrace();
            }
        }
        return objectInserted;
    }

    /**
     * @param object
     * @param path
     * @param <T>
     * @return
     */

    public <T> boolean insertObjectDeletedInFile(T object, String path){
        boolean objectInserted = false;
        FileWriter FW = null;
        try {
            FW = new FileWriter(path,true);
            FW.write(object.toString()+"#D"+"\n");
            FW.flush();
            objectInserted = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                FW.close();
            }catch (IOException|NullPointerException error){
                error.printStackTrace();
            }
        }
        return objectInserted;
    }

    /**
     * @param object
     * @param path
     * @param <T>
     * @return
     */

    public <T> boolean insertObjectModifiedInFile(T object, String path){
        boolean objectInserted = false;
        FileWriter FW = null;
        try {
            FW = new FileWriter(path,true);
            FW.write(object.toString()+"#M"+"\n");
            FW.flush();
            objectInserted = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                FW.close();
            }catch (IOException|NullPointerException error){
                error.printStackTrace();
            }
        }
        return objectInserted;
    }

    /**
     * @param path
     * @return
     */

    public ArrayList<Order> getOrdersNotShipped(String path){
        ArrayList<Order> ordersNotShipped = new ArrayList<>();
        BufferedReader BR = null;
        String line;
        Order orderRead;
        try {
            BR = new BufferedReader(new FileReader(path));
            line = BR.readLine();
            while (line != null){
                orderRead = Order.stringToOrder(line);
                if (!orderRead.getSent() && !orderRead.getCancel()){
                    ordersNotShipped.add(Order.stringToOrder(line));
                }
                line = BR.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                BR.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ordersNotShipped;
    }

    /**
     * @return
     */

    public Product readAndSearchProduct(){
        Scanner sc = new Scanner(System.in);

        int ID;
        String pathProductFile = ".\\src\\Files\\Products";
        Product productGet;

        //Validate Product
        do {
            System.out.print("Insert IDProduct: ");
            ID = sc.nextInt();
            productGet = getProductFromFile(ID,pathProductFile);
            if (productGet == null){
                System.out.println("This product don't exist. Please insert a product existing");
            }
        }while (productGet == null);

        return productGet;

    }


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


    /**
     * @param DNIEmployee
     * @param path
     */


    public void printPersonalData(String DNIEmployee, String path) {
        BufferedReader BR;
        String[] lineParted = null;
        String line;
        boolean employeeFind = false;

        try {
            BR = new BufferedReader(new FileReader(path));
            line = BR.readLine();
            while (line != null && !employeeFind) {
                lineParted = line.split("#");
                employeeFind = (lineParted[2].equals(DNIEmployee));
                line = BR.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        if (employeeFind) {
            System.out.println("Name of employee: " + lineParted[0]);
            System.out.println("Suname of employee: " + lineParted[1]);
            System.out.println("DNI of employee: " + lineParted[2]);
            System.out.println("Number of SS of employee: " + lineParted[3]);
            System.out.println("Birthday of employee: " + lineParted[4]);
            System.out.println("Position of employee: " + lineParted[5]);
            System.out.println("Category of employee: " + lineParted[6]);
            System.out.println("Number of bank account of employee: " + lineParted[7]);
        } else {
            System.out.println("This employee wasn`t found");
        }
    }



}
