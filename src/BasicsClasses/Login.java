package BasicsClasses;

public class Login {

    private String DNIEmployee;
    private String password;

    public Login() {
        this.DNIEmployee = "Default";
        this.password = "Default";
    }

    public Login(String DNIEmployee, String password) {
        this.DNIEmployee = DNIEmployee;
        this.password = password;
    }

    public Login(Login other) {
        this.DNIEmployee = other.DNIEmployee;
        this.password = other.password;
    }

    public String getDNIEmployee() {
        return DNIEmployee;
    }

    public void setDNIEmployee(String DNIEmployee) {
        this.DNIEmployee = DNIEmployee;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return DNIEmployee+","+password;
    }
}
