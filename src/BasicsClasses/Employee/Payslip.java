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


    ADDED METHODS:


 */


public class Payslip {

    private double salary;

    public Payslip(){ //Constructor without parameters

        this.salary = 0;

    }

    public Payslip(double salary){ //Constructor with parameters

        this.salary = salary;

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
