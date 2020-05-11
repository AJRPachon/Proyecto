package Management;

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
            System.out.println("6.- See personal data of the employee");
            System.out.println("7.- See personal data of the floor manager");
            System.out.println("8.- Add new order");
            System.out.println("9.- Modify order");
            System.out.println("10.- Cancel order");
            System.out.println("11.- View personal data administrator");
            System.out.println("12.- Check your hours");
            System.out.print("Insert option: ");
            option = sc.nextInt();
            if (option < 0 || option > 12){
                System.out.println("Insert a valid option");
            }
        }while (option < 0 || option > 12);
        return option;
    }

    public int readAndValidateOptionsFloorManager(){
        int option = 0;
        return option;
    }

}