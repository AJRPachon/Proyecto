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

    public Payslip(){ //Constructor sin parámetros

        this.salary = 0;

    }

    public Payslip(double salary){ //Constructor con parámetros

        this.salary = salary;

    }

/////// BASIC METHODS ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public double getSalary(){
        return this.salary;
    }

    public void setSalary(double salary){
        this.salary = salary;
    }

/////// TO STRING ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public String toString() {
        return "El salario es: "+this.salary+"€";
    }


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
