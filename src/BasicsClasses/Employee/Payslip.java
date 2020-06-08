package BasicsClasses.Employee;

/*

    ANALYSIS:
        Contains the employee's payslip, salary


    BASIC PROPERTIES:
        salary real consulting and modifiable


    DERIVATE PROPERTIES:



    INTERFACE
    BASIC METHODS:
        getSalary()
        setSalary(real salary)

        getEmployee()
        setEmployee(Employee employee)


    ADDED METHODS:
        toString()


 */


public class Payslip {

    private double salary;
    private Employee employee;

    public Payslip(){ //Constructor without parameters

        this.salary = 0;
        this.employee = new Employee();

    }

    public Payslip(double salary){

        this.salary = salary;
        this.employee = new Employee();

    }

    public Payslip(double salary, Employee employee){ //Constructor with parameters

        this.salary = salary;
        this.employee = employee;

    }

/////// BASIC METHODS ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public double getSalary(){
        return this.salary;
    }

    public void setSalary(double salary){
        this.salary = salary;
    }


    public Employee getEmployee() {
        return this.employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }


/////// ADDED METHODS /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


/////// TO STRING ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public String toString() {
  
        return employee.getDNI()+"#"+this.salary+"#";


    }


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
