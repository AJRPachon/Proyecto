package Management;

import BasicsClasses.Orders.Order;
import BasicsClasses.Orders.OrderLine;
import utils.Utils;

import java.util.ArrayList;
import java.util.Scanner;

public class Validations {

    /**
     * @return true / false if you want to continue
     */

    public boolean wantContinue(){
        char wantContinue;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print("Insert S if want continue or N if wantn't continue: ");
            wantContinue = Character.toUpperCase(sc.next().charAt(0));
        }while(wantContinue != 'S' && wantContinue != 'N');

        return wantContinue == 'S';
    }

    /**
     * Validates if a DNI / Username is correct
     * @param username DNI / Username that we want to check
     * @return true / false if the username is valid or not
     */

    public boolean checkUsername(String username){
        boolean userValid = false;

        if (username.length() == 9){
            String[] numbersToEvaluate = username.substring(0,8).split("");
            String numbers = "";
            char character = Character.toUpperCase(username.charAt(8));

            for (int i = 0; i < numbersToEvaluate.length; i++){
                if (numbersToEvaluate[i].charAt(0) >= 48 && numbersToEvaluate[i].charAt(0) <= 57){
                    numbers +=numbersToEvaluate[i];
                }
            }

            if (numbers.length() == 8){
                userValid = (character == calculateCharUser(Integer.parseInt(numbers)));
            }

        }

        return userValid;
    }


    /**
     * Calculate the DNI letter
     * @param string DNI
     * @return caracter of the DNI
     */

    private char calculateCharUser(int string){
        char character = ' ';
        int resultado;

        resultado = string % 23;

        switch (resultado) {
            case 0 -> character = 'T';
            case 1 -> character = 'R';
            case 2 -> character = 'W';
            case 3 -> character = 'A';
            case 4 -> character = 'G';
            case 5 -> character = 'M';
            case 6 -> character = 'Y';
            case 7 -> character = 'F';
            case 8 -> character = 'P';
            case 9 -> character = 'D';
            case 10 -> character = 'X';
            case 11 -> character = 'B';
            case 12 -> character = 'N';
            case 13 -> character = 'J';
            case 14 -> character = 'Z';
            case 15 -> character = 'S';
            case 16 -> character = 'Q';
            case 17 -> character = 'V';
            case 18 -> character = 'H';
            case 19 -> character = 'L';
            case 20 -> character = 'C';
            case 21 -> character = 'K';
            case 22 -> character = 'E';
        }

        return character;
    }


    /**
     * Read and Validate a Username
     * @return validate Username
     */

    public String readAndValidateUsername(){
        Scanner sc = new Scanner(System.in);
        boolean userNotValid;
        String username;

        do {
            System.out.print("Insert the username: ");
            if (!(userNotValid = checkUsername(username = sc.next()))){
                System.out.println("The username aren`t correct. The syntax is: 00000000T");
            }
        }while (!userNotValid);

        return username;
    }



    /** Read and Validate a Password
     * @return Validate password
     */

    public String readAndValidatePassword(){
        Scanner sc = new Scanner(System.in);
        Utils u = new Utils();
        String password;

        do {
            System.out.print("Insert the password: ");
            if ((password = sc.next()).length() < 8){
                System.out.println("The password must be longer than 7 characters");
            }
        }while (password.length() < 8);

        password = u.encriptPassword(password);
        return password;
    }


    /**
     * Read and validate the options that administrator can choose
     * @return Option
     */

    public int readAndValidateOptionsAdministrator(){
        int option;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("0.- Sign off");
            System.out.println("1.- Register employee");
            System.out.println("2.- Dismiss an employee");
            System.out.println("3.- Assign schedule to employee");
            System.out.println("4.- Modify employee schedule");
            System.out.println("5.- Modify employee salary");
            System.out.println("6.- See personal data of the one employee");
            System.out.println("7.- Add new order");
            System.out.println("8.- Modify order");
            System.out.println("9.- See my personal data");
            System.out.println("10.- Check your schedule");
            System.out.print("Insert option: ");
            option = sc.nextInt();
            if (option < 0 || option > 10){
                System.out.println("Insert a valid option");
            }
        }while (option < 0 || option > 10);
        return option;
    }



    /**
     * Read and validate the options that Floor Manager can choose
     * @return Option
     */

    public int readAndValidateOptionsFloorManager(){
        int option;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("0.- Sign off");
            System.out.println("1.- See personal data of the one employee");
            System.out.println("2.- Assign schedule to employee");
            System.out.println("3.- Modify employee schedule");
            System.out.println("4.- See my personal data");
            System.out.println("5.- Check your schedule");
            System.out.print("Insert option: ");
            option = sc.nextInt();
            if (option < 0 || option > 5){
                System.out.println("Insert a valid option");
            }
        }while (option < 0 || option > 5);
        return option;
    }



    /**
     * Read and validate the options that Staff can choose
     * @return Option
     */

    public int readAndValidateOptionsStaff(){
        int option;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("0.- Sign off");
            System.out.println("1.- See my personal data");
            System.out.println("2.- Check your schedule");
            System.out.print("Insert option: ");
            option = sc.nextInt();
            if (option < 0 || option > 2){
                System.out.println("Insert a valid option");
            }
        }while (option < 0 || option > 2);
        return option;
    }


    /**
     * Read and validate product quantity
     * @return quantity
     */

    public int readAndValidateQuantityOfProduct(){
        int quantity;
        Scanner sc = new Scanner(System.in);

        //Validate quantity product
        do {
            System.out.print("Insert quantity of product: ");
            quantity = sc.nextInt();
        }while (quantity < 1);
        System.out.println();

        return quantity;
    }


    /**
     * Select an order by ID
     * @param ordersNotShipped orders not shipped
     * @return Order choosed
     */

    public Order chooseOrderByID(ArrayList<Order> ordersNotShipped){
        Order orderChoosed = null;
        Scanner SC = new Scanner(System.in);
        int IDInsert;
        boolean find = false;

        do {
            for (Order o : ordersNotShipped){
                System.out.println("ID: "+o.getID()+"  Date of order: "+o.getStringDateOrder()+"  Sent: "+o.getSent()+"  Cancel: "+o.getCancel());
            }

            System.out.print("Insert ID of the Order to want choose: ");
            IDInsert = SC.nextInt();
            for (int i = 0; i < ordersNotShipped.size() && !find; i++){
                if (ordersNotShipped.get(i).getID() == IDInsert){
                    find = true;
                    orderChoosed = ordersNotShipped.get(i).clone();
                }

            }
        }while (!find);
        return orderChoosed;
    }


    /**
     * Read and validate options (menu order)
     * @return option choosed
     */

    public int readAndValidateOptionsOrder(){
        int option;
        Scanner SC = new Scanner(System.in);
            do {
                System.out.println("Options to modify the order:");
                System.out.println("0.- Exit");
                System.out.println("1.- Add order line");
                System.out.println("2.- Remove order line");
                System.out.println("3.- Decrease quantity of one product");
                System.out.println("4.- Increase quantity of one product");
                System.out.println("5.- Show all orders lines");
                System.out.println("6.- Cancel order");
                System.out.print("Insert the option want execute: ");
                option = SC.nextInt();
            }while (option < 0 || option > 5);
        return option;
    }


    /**
     * Read and validate new Order Line
     * @return New Order Line
     */

    public OrderLine readAndValidateNewOrderLine(){
        Utils u = new Utils();
        return new OrderLine(u.readAndSearchProduct(),readAndValidateQuantityOfProduct());
    }

    
    /**
     * Read and validate IDProduct from Order
     * @param validIDs ID valids
     * @return ID Choosed
     */

    public int readAndValidateIDProductOfOrder(ArrayList<Integer> validIDs){
        int IDChoosed;
        boolean chooseCorrect = false;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print("Insert ID of the product: ");
            IDChoosed = sc.nextInt();
            for (int i = 0; i < validIDs.size() && !chooseCorrect; i++){
                chooseCorrect = (validIDs.get(i).equals(IDChoosed));
            }
        }while (!chooseCorrect);
        return IDChoosed;
    }


    /**
     * Read and validate the amount of product we want to decrease
     * @param maxDecrease .
     * @return Amount to decrease
     */

    public int readAndValidateQuantityToDecrease(int maxDecrease){
        Scanner sc = new Scanner(System.in);
        int quantityToDecrease;
        do {
            System.out.println("Max quantity is "+maxDecrease);
            System.out.print("Insert total quantity to decrease: ");
            quantityToDecrease = sc.nextInt();
        }while (quantityToDecrease < 0 || quantityToDecrease > maxDecrease);
        return quantityToDecrease;
    }


    /**
     * Read and validate the amount of product we want to increase
     * @return Amount to increase
     */

    public int readAndValidateQuantityToIncrease(){
        Scanner SC = new Scanner(System.in);
        int quantityToDecrease;
        do {
            System.out.print("Insert total quantity to increase: ");
            quantityToDecrease = SC.nextInt();
        }while (quantityToDecrease < 0);
        return quantityToDecrease;
    }


    /**
     * Reads and validate a keyboard read salary
     * @return validate salary
     */

    public double readAndValidateSalary(){

        Scanner sc =  new Scanner(System.in);

        double salary;

        System.out.println("Inserte salario para el empleado seleccionado");
        salary = sc.nextDouble();

        while(salary <= 0){
            System.out.println("El salario tiene que ser mayor que 0");
            salary = sc.nextDouble();
        }

        return salary;

    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * This method reads and validates the time
     *
     * @return Hour
     */

    public int readAndValidateHour() {

        Scanner sc = new Scanner(System.in);

        int hour;

        hour = sc.nextInt();

        //Validation
        while (hour < 0 || hour > 24) {
            System.out.println("The time must be between 0 and 24");
            hour = sc.nextInt();
        }

        return hour;

    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * This method reads and validates the minutes
     *
     * @return Minute
     */

    public int readAndValidateMinute(){

        Scanner sc = new Scanner(System.in);

        int minute;

        minute = sc.nextInt();

        //Validation
        while (minute < 0 || minute > 59) {
            System.out.println("The minutes must be between 0 and 59");
            minute = sc.nextInt();
        }

        return minute;

    }


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Read and validate day of the week
     * @return day
     */

    public int readAndValidateDay(){

        Scanner sc = new Scanner(System.in);

        int day;

        day = sc.nextInt();

        //Validation
        while (day < 1 || day > 31) {
            System.out.println("The day must be between 1 and 31");
            day = sc.nextInt();
        }

        return day;

    }


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Read and validate month
     * @return validate month
     */

    public int readAndValidateMonth(){

        Scanner sc = new Scanner(System.in);

        int month;

        month = sc.nextInt();

        //Validation
        while (month < 1 || month > 12) {
            System.out.println("The month must be between 1 and 12");
            month = sc.nextInt();
        }

        return month;

    }




}