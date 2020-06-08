package utils;

import BasicsClasses.FoodstuffDrinks.Product;
import Management.FilesManagement;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

    public GregorianCalendar createVariableGregorianCalendar(String[] separations){

        String[] dateString;
        GregorianCalendar birthdate = new GregorianCalendar();
        int[] fecha = new int[3];

        //We separate the String from the date in its different numbers
        dateString = separations[4].split("/");

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

    public Product readAndSearchProduct(){
        FilesManagement fm = new FilesManagement();
        Scanner sc = new Scanner(System.in);

        int ID;
        String pathProductFile = ".\\src\\Files\\Products";
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


}
