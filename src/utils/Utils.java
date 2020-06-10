package utils;

import BasicsClasses.Employee.Employee;
import BasicsClasses.Employee.Enums.EnumCategory;
import BasicsClasses.Employee.Enums.EnumPosition;
import BasicsClasses.Employee.Enums.EnumWeekDays;
import BasicsClasses.Employee.Payslip;
import BasicsClasses.Employee.Schedule;
import BasicsClasses.FoodstuffDrinks.Product;
import Management.FilesManagement;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Utils {

/////////// CREATE VARIABLE GREGORIAN CALENDAR //////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Convert a String date to integer and assign it to a variable of type GregorianCalendar
     *
     * @param separations String date separated by "/"
     * @return birthdate (GregorianCalendar)
     */

    public GregorianCalendar createVariableGregorianCalendar(String separations){

        String[] dateString;
        GregorianCalendar birthdate = new GregorianCalendar();
        int[] fecha = new int[3];

        //We separate the String from the date in its different numbers
        dateString = separations.split("/");

        //We go through the array and do a parseInt to get the integers
        for(int cont = 0; cont < dateString.length; cont++){
            fecha[cont] = Integer.parseInt(dateString[cont]);
        }

        //We put the birthday date in our date type variable and we can already use it in our constructor
        birthdate.set(Calendar.DAY_OF_MONTH, fecha[0]);
        birthdate.set(Calendar.MONTH, fecha[1]);
        birthdate.set(Calendar.YEAR, fecha[2]);

        return birthdate;

    }

///////// ENCRIPT PASSWORD /////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * A password passed by parameters is encrypted
     * @param password password to encrypt
     * @return password encypted
     */


    public String encriptPassword(String password){
        String passEncripted = null;

        try {
            byte[] bytesOfMessage = password.getBytes(StandardCharsets.UTF_8);
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] thedigest = md.digest(bytesOfMessage);
            BigInteger bigInt = new BigInteger(1,thedigest);
            passEncripted = bigInt.toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return passEncripted;
    }

/////////// READ AND SEARCH PRODUCT //////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Read and shearch a product
     * @return product that we want to get
     */

    public Product readAndSearchProduct(String pathProductFile){
        FilesManagement fm = new FilesManagement();
        Scanner sc = new Scanner(System.in);

        int ID;
        Product productGet;

        //Validate Product
        do {
            System.out.print("Insert IDProduct: ");
            ID = sc.nextInt();
            productGet = fm.getProductFromFile(ID,pathProductFile);
            if (productGet == null){
                System.out.println("This product don't exist. Please insert a product existing");
            }
        }while (productGet == null);

        return productGet;

    }

    public Product readAndSearchProduct(Connection connection){

        Scanner sc = new Scanner(System.in);
        Statement sentence = null;
        ResultSet product = null;
        String select;

        int ID;
        Product productGet = null;

        //Validate Product
        do {
            System.out.print("Insert IDProduct: ");
            ID = sc.nextInt();

            select = "SELECT ID, [Name],[Characteristics],Price " +
                    "FROM Products " +
                    "WHERE ID = "+ID;

            try {
                sentence = connection.createStatement();
                product = sentence.executeQuery(select);
                while (product.next()){
                    productGet = new Product(product.getInt(ID), product.getString("Name"), product.getString("Characteristics"),product.getDouble("Price"));
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            finally {
                try {
                    sentence.close();
                    product.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

            if (productGet == null){
                System.out.println("This product don't exist. Please insert a product existing");
            }
        }while (productGet == null);

        return productGet;

    }


/////////// INSERT EMPLOYEES //////////////////////////////////////////////////////////////////////////////////////////////

    public void insertEmployees(){

        FileWriter fw = null;
        BufferedWriter bw = null;

        Schedule[] schedules = new Schedule[7];
        for (int cont = 0; cont < schedules.length; cont++){
            schedules[cont] = new Schedule();
        }

        Employee emp1 = new Employee("Antonio","Ramirez","53350963W","89DF76G98D7F", new GregorianCalendar(), EnumPosition.Manager, EnumCategory.Administrator, "ES54654968468478664","123asd123", schedules);
        Employee emp2 = new Employee("Josefa","Pavón","55428963H","85646FGH98D7F", new GregorianCalendar(), EnumPosition.Bartender, EnumCategory.Staff, "E64564564565464545664","123asd123", schedules);
        Employee emp3 = new Employee("Francisco","Avilés","53254480T","68576G98D7F", new GregorianCalendar(), EnumPosition.Busser, EnumCategory.Staff, "ES5465486425624848278664","123248fgdsh623", schedules);
        Employee emp4 = new Employee("Rafael","Ortuño","24157643W","89DF5F46GH5F7F", new GregorianCalendar(), EnumPosition.ChefThePartie, EnumCategory.Staff, "ES5426482648264","12246dhfg8283", schedules);
        Employee emp5 = new Employee("Ana","Mora","46720943S","89D45DFGD457F", new GregorianCalendar(), EnumPosition.HeadChef, EnumCategory.Administrator, "ES5448268426482664","1248hdfgh648223", schedules);
        Employee emp6 = new Employee("Celso","Hernandez","16792543T","89D465F8GG7F", new GregorianCalendar(), EnumPosition.SousChef, EnumCategory.Staff, "ES546548268248246664","12152dfghdfg65138263", schedules);

        try{

            fw = new FileWriter(".\\src\\files\\Employees", true);
            bw = new BufferedWriter(fw);

            bw.write(emp1.toString());
            bw.newLine();
            bw.write(emp2.toString());
            bw.newLine();
            bw.write(emp3.toString());
            bw.newLine();
            bw.write(emp4.toString());
            bw.newLine();
            bw.write(emp5.toString());
            bw.newLine();
            bw.write(emp6.toString());
            bw.newLine();
            bw.flush();



        }catch (IOException e){
            e.printStackTrace();
        }
        finally {
            try {
                if (fw != null){
                    fw.close();
                }
                if (bw != null){
                    bw.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }


/////////// INSERT PAYSLIPS //////////////////////////////////////////////////////////////////////////////////////////////


    public void insertPaySlips(){

        FileWriter fw = null;
        BufferedWriter bw = null;

        Schedule[] schedules = new Schedule[7];
        for (int cont = 0; cont < schedules.length; cont++){
            schedules[cont] = new Schedule();
        }

        Employee emp1 = new Employee("Antonio","Ramirez","53350963W","89DF76G98D7F", new GregorianCalendar(), EnumPosition.Manager, EnumCategory.Administrator, "ES54654968468478664","123asd123", schedules);
        Employee emp2 = new Employee("Josefa","Pavón","55428963H","85646FGH98D7F", new GregorianCalendar(), EnumPosition.Bartender, EnumCategory.Staff, "E64564564565464545664","123asd123", schedules);
        Employee emp3 = new Employee("Francisco","Avilés","53254480T","68576G98D7F", new GregorianCalendar(), EnumPosition.Busser, EnumCategory.Staff, "ES5465486425624848278664","123248fgdsh623", schedules);
        Employee emp4 = new Employee("Rafael","Ortuño","24157643W","89DF5F46GH5F7F", new GregorianCalendar(), EnumPosition.ChefThePartie, EnumCategory.Staff, "ES5426482648264","12246dhfg8283", schedules);
        Employee emp5 = new Employee("Ana","Mora","46720943S","89D45DFGD457F", new GregorianCalendar(), EnumPosition.HeadChef, EnumCategory.Administrator, "ES5448268426482664","1248hdfgh648223", schedules);
        Employee emp6 = new Employee("Celso","Hernandez","16792543T","89D465F8GG7F", new GregorianCalendar(), EnumPosition.SousChef, EnumCategory.Staff, "ES546548268248246664","12152dfghdfg65138263", schedules);

        Payslip ps1 = new Payslip(3500,emp1);
        Payslip ps2 = new Payslip(1650,emp2);
        Payslip ps3 = new Payslip(1650,emp3);
        Payslip ps4 = new Payslip(2500,emp4);
        Payslip ps5 = new Payslip(3000,emp5);
        Payslip ps6 = new Payslip(2800,emp6);


        try{

            fw = new FileWriter(".\\src\\files\\PaySlip", true);
            bw = new BufferedWriter(fw);
            bw.write(ps1.toString());
            bw.newLine();
            bw.write(ps2.toString());
            bw.newLine();
            bw.write(ps3.toString());
            bw.newLine();
            bw.write(ps4.toString());
            bw.newLine();
            bw.write(ps5.toString());
            bw.newLine();
            bw.write(ps6.toString());
            bw.newLine();
            bw.flush();


        }catch (IOException e){
            e.printStackTrace();
        }
        finally {
            try {
                if (fw != null){
                    fw.close();
                }
                if (bw != null){
                    bw.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

/////////// INSERT SCHEDULES //////////////////////////////////////////////////////////////////////////////////////////////


    public void insertSchedules(){

        FileWriter fw = null;
        BufferedWriter bw = null;

        Employee emp1 = new Employee("Antonio","Ramirez","53350963W","89DF76G98D7F", new GregorianCalendar(), EnumPosition.Manager, EnumCategory.Administrator, "ES54654968468478664","123asd123", schedules);
        Employee emp2 = new Employee("Josefa","Pavón","55428963H","85646FGH98D7F", new GregorianCalendar(), EnumPosition.Bartender, EnumCategory.Staff, "E64564564565464545664","123asd123", schedules);
        Employee emp3 = new Employee("Francisco","Avilés","53254480T","68576G98D7F", new GregorianCalendar(), EnumPosition.Busser, EnumCategory.Staff, "ES5465486425624848278664","123248fgdsh623", schedules);
        Employee emp4 = new Employee("Rafael","Ortuño","24157643W","89DF5F46GH5F7F", new GregorianCalendar(), EnumPosition.ChefThePartie, EnumCategory.Staff, "ES5426482648264","12246dhfg8283", schedules);
        Employee emp5 = new Employee("Ana","Mora","46720943S","89D45DFGD457F", new GregorianCalendar(), EnumPosition.HeadChef, EnumCategory.Administrator, "ES5448268426482664","1248hdfgh648223", schedules);
        Employee emp6 = new Employee("Celso","Hernandez","16792543T","89D465F8GG7F", new GregorianCalendar(), EnumPosition.SousChef, EnumCategory.Staff, "ES546548268248246664","12152dfghdfg65138263", schedules);

        Schedule[] schedules1 = new Schedule[7];

        try{

            fw = new FileWriter(".\\src\\files\\Schedule", true);
            bw = new BufferedWriter(fw);

            for (int cont = 0; cont < schedules1.length; cont++){
                schedules1[cont] = new Schedule(EnumWeekDays.values()[cont], new GregorianCalendar(), new GregorianCalendar(), emp1);
                bw.write(schedules1[cont].toString());
                bw.newLine();

            }

            for (int cont = 0; cont < schedules1.length; cont++){
                schedules1[cont] = new Schedule(EnumWeekDays.values()[cont], new GregorianCalendar(), new GregorianCalendar(), emp2);
                bw.write(schedules1[cont].toString());
                bw.newLine();

            }

            for (int cont = 0; cont < schedules1.length; cont++){
                schedules1[cont] = new Schedule(EnumWeekDays.values()[cont], new GregorianCalendar(), new GregorianCalendar(), emp3);
                bw.write(schedules1[cont].toString());
                bw.newLine();

            }

            for (int cont = 0; cont < schedules1.length; cont++){
                schedules1[cont] = new Schedule(EnumWeekDays.values()[cont], new GregorianCalendar(), new GregorianCalendar(), emp4);
                bw.write(schedules1[cont].toString());
                bw.newLine();

            }

            for (int cont = 0; cont < schedules1.length; cont++){
                schedules1[cont] = new Schedule(EnumWeekDays.values()[cont], new GregorianCalendar(), new GregorianCalendar(), emp5);
                bw.write(schedules1[cont].toString());
                bw.newLine();

            }

            for (int cont = 0; cont < schedules1.length; cont++){
                schedules1[cont] = new Schedule(EnumWeekDays.values()[cont], new GregorianCalendar(), new GregorianCalendar(), emp6);
                bw.write(schedules1[cont].toString());
                bw.newLine();

            }

            bw.flush();


        }catch (IOException e){
            e.printStackTrace();
        }
        finally {
            try {
                if (fw != null){
                    fw.close();
                }
                if (bw != null){
                    bw.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }



}
