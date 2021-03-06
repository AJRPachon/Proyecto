package management;

import basicsClasses.employee.Employee;

import basicsClasses.employee.Enums.EnumCategory;
import basicsClasses.employee.Enums.EnumPosition;


import java.io.*;

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

    /**
     *  Read and validate the necessary data to create a new employee
     * @return object employee
     */

    public Employee collectEmployeeData(){

        Scanner sc = new Scanner(System.in);
        Validations va = new Validations();


        String name, surname, dNI, nAF, bankAccountN, password, position, category;
        GregorianCalendar birthday = new GregorianCalendar();
        int day, month, year;

        System.out.println("Employee Name");
        name = sc.next();

        System.out.println("Employee Surname");
        surname = sc.next();

        System.out.println("Employee DNI");
        dNI = va.readAndValidateUsername();

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


        System.out.println("Employee position: CommisChef, HeadChef, ChefThePartie, SousChef, Cook, Waiter, Waitress, Busser, Manager, Host, Bartender, Spaguetti");
        position = va.readAndValidatePosition();;

        System.out.println("Employee category");
        category = va.readAndValidateCategory();

        System.out.println("Employee bankAccoutN");
        bankAccountN = sc.next();

        System.out.println("Employee password");
        password = va.readAndValidatePassword();

        return new Employee(name, surname,dNI,nAF,birthday, EnumPosition.valueOf(position), EnumCategory.valueOf(category), bankAccountN,password);

    }


    public void printEmployeePersonalData(Employee employee) {
        System.out.println("Name of employee: " + employee.getName());
        System.out.println("Suname of employee: " + employee.getSurname());
        System.out.println("DNI of employee: " + employee.getDNI());
        System.out.println("Number of SS of employee: " + employee.getnAF());
        System.out.println("Birthday of employee: " + employee.getBirthday());
        System.out.println("Position of employee: " + employee.getPosition());
        System.out.println("Category of employee: " + employee.getCategory());
        System.out.println("Number of bank account of employee: " + employee.getBankAccountN());
    }





////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////





}
