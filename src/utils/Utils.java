package utils;

import BasicsClasses.FoodstuffDrinks.Product;
import Management.FilesManagement;

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
     * Convertimos una fecha String a enteros y se la asignamos a una variable de tipo GregorianCalendar
     *
     * @param separaciones
     * @return
     */

    public GregorianCalendar createVariableGregorianCalendar(String[] separaciones){

        String[] fechaString;
        GregorianCalendar birthdate = new GregorianCalendar();
        int[] fecha = new int[3];

        //Separamos el String de la fecha en sus distintos numeros
        fechaString = separaciones[4].split("/");

        //Recorremos el array y hacemos un parseInt para obtener los enteros
        for(int cont = 0; cont < fechaString.length; cont++){
            fecha[cont] = Integer.parseInt(fechaString[cont]);
        }

        //Metemos la fecha de cumpleaños en nuestra variable tipo fecha y ya la podemos usar en nuestro constructor
        birthdate.set(Calendar.DAY_OF_MONTH, fecha[0]);
        birthdate.set(Calendar.MONTH, fecha[1]);
        birthdate.set(Calendar.YEAR, fecha[2]);

        return birthdate;

    }

///////// ENCRIPT PASSWORD /////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * @param password
     * @return
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
     * @return
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


}
