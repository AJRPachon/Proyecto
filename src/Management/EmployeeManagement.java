package Management;

import java.io.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EmployeeManagement {

    /**
     * This method check if the username and the password passed by parameter exist and return the permisons of this employee.
     * If the username not exist or the password are wrong, return null.
     * @param username Username of employee
     * @param password Password of employee
     * @return Return the permisons of this employee. If the username not exist or the password are wrong, return null.
     */

    public String getPermisons(String username, String password){

        String category = null;
        BufferedReader BR = null;
        String line;
        String[] lineDivide;

        try {
            BR = new BufferedReader(new FileReader(".\\src\\Files\\Employees"));
            line = BR.readLine();
            while (line != null && category == null){
                lineDivide = line.split("#");
                if (lineDivide[2].equals(username) && lineDivide[8].equals(password)){
                    category = lineDivide[6];
                }
                line = BR.readLine();
            }
        }catch (FileNotFoundException e) {
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
        return category;
    }

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


}
