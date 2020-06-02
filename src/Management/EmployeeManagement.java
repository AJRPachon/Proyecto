package Management;

import BasicsClasses.Employee.Employee;
import BasicsClasses.Employee.Enums.EnumCategory;
import BasicsClasses.Employee.Enums.EnumPosition;
import BasicsClasses.Employee.Payslip;
import BasicsClasses.Employee.Schedule;
import BasicsClasses.Orders.Order;

import javax.sound.midi.Soundbank;
import java.io.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLOutput;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class EmployeeManagement {

    /**
     * This method check if the username and the password passed by parameter exist and return the permisons of this employee.
     * If the username not exist or the password are wrong, return null.
     * @param username Username of employee
     * @param password Password of employee
     * @return Return the permisons of this employee. If the username not exist or the password are wrong, return null.
     */

    public String getPermisons(String username, String password, String path){

        String category = null;
        BufferedReader BR = null;
        String line;
        String[] lineDivide;

        try {
            BR = new BufferedReader(new FileReader(path));
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


////////// COLLECT EMPLOYEE DATA ///////////////////////////////////////////////////////////////////////////////////////


    public Employee collectEmployeeData(){

        Scanner sc = new Scanner(System.in);
        Validations VA = new Validations();


        String name, surname, dNI, nAF, bankAccountN, password, position, category;
        GregorianCalendar birthday = new GregorianCalendar();
        int day, month, year;

        System.out.println("Employee Name");
        name = sc.nextLine();

        System.out.println("Employee Surname");
        surname = sc.nextLine();

        System.out.println("Employee DNI");
        dNI = VA.readAndValidateUsername();

        System.out.println("Employee NAF");
        nAF = sc.nextLine();

        System.out.println("Employee birthday-day");
        day = sc.nextInt();
        while (day < 1 || day > 31){
            System.out.println("Employee birthday-day wasn´t correct, insert it again");
            day = sc.nextInt();
        }

        System.out.println("Employee birthday-month");
        month = sc.nextInt();
        while(month < 1 || month > 12){
            System.out.println("Employee birthday-month wasn´t correct, insert it again");
            month = sc.nextInt();
        }

        System.out.println("Employee birthday-year");
        year = sc.nextInt();
        while (year < 1900 || year > 2002){
            System.out.println("Employee birthday-year wasn´t correct, insert it again");
            year = sc.nextInt();
        }

        //TODO Create enum with all months?
        birthday.set(day, month,year);

        System.out.println("Employee position");
        position = sc.nextLine();
        EnumPosition.valueOf(position);

        System.out.println("Employee category");
        category = sc.nextLine();
        EnumCategory.valueOf(category);

        System.out.println("Employee bankAccoutN");
        bankAccountN = sc.nextLine();

        System.out.println("Employee password");
        password = VA.readAndValidatePassword(); //Validamos la contraseña


        return new Employee(name, surname,dNI,nAF,birthday, EnumPosition.valueOf(position), EnumCategory.valueOf(category),bankAccountN,password);

    }




//////////// TERMINATE AN EMPLOYEE /////////////////////////////////////////////////////////////////////////////////////

    /**
     * Seleccionamos el empleado que deseamos dar de baja
     */

    public void terminateAnEmployee() {

        Scanner sc = new Scanner(System.in);

        FilesManagement fm = new FilesManagement();
        String path = ".\\src\\files\\Employee";
        String tempPath = ".\\src\\files\\temp\\EmployeeTemp";
        String employeeDNI;

        //Mostrar todos los empleados registrados
        fm.showFileData(path);

        //Elegir empleado al que deseamos dar de baja
        System.out.println("Indique el empleado al que quiera dar de baja (DNI)");
        employeeDNI = sc.nextLine();
        fm.terminateAnEmployee(path, employeeDNI,tempPath);



    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////









}
