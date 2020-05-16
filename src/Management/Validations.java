package Management;

import BasicsClasses.Orders.Order;
import BasicsClasses.Orders.OrderLine;

import java.awt.font.FontRenderContext;
import java.util.ArrayList;
import java.util.Scanner;

public class Validations {

    /**
     * @return
     */

    public boolean wantContinue(){
        char wantContinue;
        Scanner SC = new Scanner(System.in);
        do {
            System.out.print("Insert S if want continue or N if wantn't continue: ");
            wantContinue = Character.toUpperCase(SC.next().charAt(0));
        }while(wantContinue != 'S' && wantContinue != 'N');

        return wantContinue == 'S';
    }

    /**
     * @param username
     * @return
     */

    public boolean checkUsername(String username){
        boolean userValid = false, checkIntPart = true;

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

    private char calculateCharUser(int cadena){
        char character = ' ';
        int resultado;

        resultado = cadena % 23;

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
     * @return
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

    /**
     * @return
     */

    public String readAndValidatePassword(){
        Scanner sc = new Scanner(System.in);
        EmployeeManagement EM = new EmployeeManagement();
        String password;

        do {
            System.out.print("Insert the password: ");
            if ((password = sc.next()).length() < 8){
                System.out.println("The password must be longer than 7 characters");
            }
        }while (password.length() < 8);

        password = EM.encriptPassword(password);
        return password;
    }

    /**
     * @return
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
     * @return
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
     * @return
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
     * @return
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
     * @param ordersNotShipped
     * @return
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
     * @return
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
     * @return
     */

    public OrderLine readAndValidateNewOrderLine(){
        FilesManagement FM = new FilesManagement();
        return new OrderLine(FM.readAndSearchProduct(),readAndValidateQuantityOfProduct());
    }

    /**
     * @param validIDs
     * @return
     */

    public int readAndValidateIDProductOfOrder(ArrayList<Integer> validIDs){
        int IDChoosed = 0;
        boolean chooseCorrect = false;
        Scanner SC = new Scanner(System.in);
        do {
            System.out.print("Insert ID of the product: ");
            IDChoosed = SC.nextInt();
            for (int i = 0; i < validIDs.size() && !chooseCorrect; i++){
                chooseCorrect = (validIDs.get(i).equals(IDChoosed));
            }
        }while (!chooseCorrect);
        return IDChoosed;
    }


    /**
     * @param maxDecrease
     * @return
     */

    public int readAndValidateQuantityToDecrease(int maxDecrease){
        Scanner SC = new Scanner(System.in);
        int quantityToDecrease;
        do {
            System.out.println("Max quantity is "+maxDecrease);
            System.out.print("Insert total quantity to decrease: ");
            quantityToDecrease = SC.nextInt();
        }while (quantityToDecrease < 0 || quantityToDecrease > maxDecrease);
        return quantityToDecrease;
    }


    /**
     * @return
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

}