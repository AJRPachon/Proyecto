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
        boolean userValid = false;
        String numbers = username.substring(0,8);
        char character = username.charAt(8);

        return userValid;
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

}