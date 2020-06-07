package Management;

import BasicsClasses.Employee.Employee;

import BasicsClasses.Employee.Enums.EnumCategory;
import BasicsClasses.Employee.Enums.EnumPosition;


import java.io.*;

import java.util.GregorianCalendar;
import java.util.Scanner;

import java.sql.*;


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
        BufferedReader br = null;
        String line;
        String[] lineDivide;

        try {
            br = new BufferedReader(new FileReader(path));
            line = br.readLine();
            while (line != null && category == null){
                lineDivide = line.split("#");
                if (lineDivide[2].equals(username) && lineDivide[8].equals(password)){
                    category = lineDivide[6];
                }
                line = br.readLine();
            }
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return category;
    }


////////// COLLECT EMPLOYEE DATA ///////////////////////////////////////////////////////////////////////////////////////


    public Employee collectEmployeeData(){

        Scanner sc = new Scanner(System.in);
        Validations VA = new Validations();


        String name, surname, dNI, nAF, bankAccountN, password, position, category;
        GregorianCalendar birthday = new GregorianCalendar();
        int day, month, year;

        System.out.println("Employee Name");
        name = sc.next();

        System.out.println("Employee Surname");
        surname = sc.next();

        System.out.println("Employee DNI");
        dNI = VA.readAndValidateUsername();

        System.out.println("Employee NAF");
        nAF = sc.next();

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

        birthday.set(day, month,year);

        System.out.println("Employee position");
        position = sc.next();
        EnumPosition.valueOf(position);

        System.out.println("Employee category");
        category = sc.next();
        EnumCategory.valueOf(category);

        System.out.println("Employee bankAccoutN");
        bankAccountN = sc.next();

        System.out.println("Employee password");
        password = VA.readAndValidatePassword(); //Validamos la contraseña


        return new Employee(name, surname,dNI,nAF,birthday, EnumPosition.valueOf(position), EnumCategory.valueOf(category),bankAccountN,password);

    }






////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////





}
