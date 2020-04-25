package BasicsClasses;

/*
 * Properties:
 *  - Basics:
 *      > username: integer, Consulting
 *      > password: integer, Consulting
 *
 *  - Derivatives:
 *      > None
 *
 *  - Shared:
 *      > None
 *
 * Methods:
 *  - Basics:
 *      > string getUsername()
 *
 *      > string getPassword()
 *
 *  - Added:
 *      > None
 *
 */

import Interfaces.ILogin;

public class Login implements ILogin,Cloneable,Comparable {

    private String username;
    private String password;

    public Login() {
        this.username = "Default";
        this.password = "Default";
    }

    public Login(String DNIEmployee, String password) {
        this.username = DNIEmployee;
        this.password = password;
    }

    public Login(Login other) {
        this.username = other.username;
        this.password = other.password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return username+"|"+password;
    }

    @Override
    public int compareTo(Object ob){
        int result = -1;

        Login otherLogin = (Login)ob;

        if (this.username.compareTo(otherLogin.username) == 0){
            result = 0;
        }else{
            if (this.username.compareTo(otherLogin.username) > 0){
                result = 1;
            }
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        boolean isEquals = false;
        if (this == obj){
            isEquals = true;
        }else{
            if (obj != null && obj instanceof Login){
                Login newLogin = (Login)obj;
                if (this.username.equals(newLogin.username)
                    && this.password.equals(newLogin.password)){
                    isEquals = true;
                }
            }
        }
        return isEquals;
    }

    @Override
    public Login clone() {
        Login newLogin = null;

        try {
            newLogin = (Login)super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return newLogin;
    }

}
