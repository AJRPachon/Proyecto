package utils;

import basicsClasses.employee.Employee;
import basicsClasses.employee.Enums.EnumCategory;
import basicsClasses.employee.Enums.EnumPosition;
import basicsClasses.employee.Schedule;
import basicsClasses.foodstuffDrinks.Consumable;
import basicsClasses.foodstuffDrinks.Drink;
import basicsClasses.foodstuffDrinks.Food;
import basicsClasses.foodstuffDrinks.Product;
import basicsClasses.foodstuffDrinks.enums.EnumAllergies;
import basicsClasses.foodstuffDrinks.enums.EnumType;
import basicsClasses.orders.Order;
import management.FilesManagement;

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
import java.util.ArrayList;
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


/////////// INSERT FOODS //////////////////////////////////////////////////////////////////////////////////////////////

    public void insertFoods(){

        FileWriter fw = null;
        BufferedWriter bw = null;

        ArrayList<EnumAllergies> allergies = new ArrayList<>();
        allergies.add(EnumAllergies.Glutenfree);
        allergies.add(EnumAllergies.Dairy);

        Food con1 = new Food("Beef parmentier","FRENCH BEEF AND POTATO CASSEROLE", 12.5, allergies, EnumType.Spaguetti );
        Food con2 = new Food("Omelette","French omelette", 8.5, allergies, EnumType.Spaguetti);
        Food con3 = new Food("French Fries","French Fries", 2.5, allergies, EnumType.Spaguetti);
        Food con4 = new Food("Poached eggs"," ------ ", 2, allergies, EnumType.Spaguetti);
        Food con5 = new Food("Avocado toast"," ------ ", 5, allergies, EnumType.Spaguetti);
        Food con6 = new Food("Cesar Salad"," --------- ", 11.20, allergies, EnumType.Spaguetti);


        try{

            fw = new FileWriter(".\\src\\files\\Foods", true);
            bw = new BufferedWriter(fw);

            bw.write(con1.toString());
            bw.newLine();
            bw.write(con2.toString());
            bw.newLine();
            bw.write(con3.toString());
            bw.newLine();
            bw.write(con4.toString());
            bw.newLine();
            bw.write(con5.toString());
            bw.newLine();
            bw.write(con6.toString());
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


/////////// INSERT DRINKS //////////////////////////////////////////////////////////////////////////////////////////////

    public void insertDrinks(){

        FileWriter fw = null;
        BufferedWriter bw = null;

        ArrayList<EnumAllergies> allergies = new ArrayList<>();
        allergies.add(EnumAllergies.Glutenfree);
        allergies.add(EnumAllergies.Dairy);

        Drink dri1 = new Drink("Cola-Cola"," --------- ", 1.5, allergies, 0.0, true);
        Drink dri2 = new Drink("Nestea"," --------- ", 1.5, allergies, 0.0, false);
        Drink dri3 = new Drink("Diet Coke"," --------- ", 1.5, allergies, 0.0, true);
        Drink dri4 = new Drink("Water"," --------- ", 1, allergies, 0.0, false);
        Drink dri5 = new Drink("Soda"," --------- ", 1.2, allergies, 0.0, true);
        Drink dri6 = new Drink("Seven UP"," --------- ", 1.5, allergies, 0.0, true);


        try{

            fw = new FileWriter(".\\src\\files\\Drinks", true);
            bw = new BufferedWriter(fw);

            bw.write(dri1.toString());
            bw.newLine();
            bw.write(dri2.toString());
            bw.newLine();
            bw.write(dri3.toString());
            bw.newLine();
            bw.write(dri4.toString());
            bw.newLine();
            bw.write(dri5.toString());
            bw.newLine();
            bw.write(dri6.toString());
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


/////////// INSERT ORDERS //////////////////////////////////////////////////////////////////////////////////////////////

    public void insertOrders(){

        FileWriter fw = null;
        BufferedWriter bw = null;

        Order or1 = new Order();
        Order or2 = new Order();
        Order or3 = new Order();
        Order or4 = new Order();
        Order or5 = new Order();
        Order or6 = new Order();


        try{

            fw = new FileWriter(".\\src\\files\\Orders", true);
            bw = new BufferedWriter(fw);

            bw.write(or1.toString());
            bw.newLine();
            bw.write(or2.toString());
            bw.newLine();
            bw.write(or3.toString());
            bw.newLine();
            bw.write(or4.toString());
            bw.newLine();
            bw.write(or5.toString());
            bw.newLine();
            bw.write(or6.toString());
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



/////////// INSERT PRODUCTS //////////////////////////////////////////////////////////////////////////////////////////////

    public void insertProducts(){

        FileWriter fw = null;
        BufferedWriter bw = null;

        Product pr1 = new Product("Carrot","----", 0.69);
        Product pr2 = new Product("Potato","----", 1.55);
        Product pr3 = new Product("Tomato","----", 3.98);
        Product pr4 = new Product("Strawberry","----", 4.70);
        Product pr5 = new Product("Avocado","----", 3.98);
        Product pr6 = new Product("Pinaple","----", 2.12);


        try{

            fw = new FileWriter(".\\src\\files\\Products", true);
            bw = new BufferedWriter(fw);

            bw.write(pr1.toString());
            bw.newLine();
            bw.write(pr2.toString());
            bw.newLine();
            bw.write(pr3.toString());
            bw.newLine();
            bw.write(pr4.toString());
            bw.newLine();
            bw.write(pr5.toString());
            bw.newLine();
            bw.write(pr6.toString());
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

}
