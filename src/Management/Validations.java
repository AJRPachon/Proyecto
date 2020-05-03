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

}