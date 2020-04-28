package BasicsClasses.Employee;

/*

    ANALYSIS:
        Contains information from the employee of our restaurant (name, surname, DNI, etc)


    BASIC PROPERTIES:
        - name String consulting
        - surname String consulting
        - dNI String consulting
        - nAF String consulting
        - birthdate GregorianCalendar consulting
        - position enum consulting and modifiable
        - category enum consulting and modifiable
        - bankAccountN String consulting and modifiable
        - password String consulting and modifiable


    DERIVATE PROPERTIES:
        - Not one

    SHARED PROPERTIES:
        - Not one


    INTERFACE
    BASIC METHODS:
        String getName()
        getSurname()
        getDNI()
        getNAF()
        getBirthdate()

        getPosition()
        setPosition(enum position)

        getCategory()
        setCategory(enum category)

        getBankAccountN()
        setBankAccountN(String bankAccountN)

        getPassword()
        setPassword( String password )


           //Delegation pattern

               //PaySlip
               getSalaryPaySlip()
               setSalaryPaySlip(real salary)


               //Shedule
               getWeekDaySchedule()
               setWeekDaySchedule(enum)

               getTimeSchedule()
               setTimeSchedule(dateType hour)

               getShiftSchedule()



    ADDED METHODS:
        toString()



 */


import BasicsClasses.Employee.Enums.EnumCategory;
import BasicsClasses.Employee.Enums.EnumPosition;

import java.util.GregorianCalendar;

public class Employee {


    private String name;
    private String surname;
    private String dNI;
    private String nAF;
    private GregorianCalendar birthday;
    private EnumPosition position;
    private EnumCategory category;
    private String bankAccountN;
    private String password;


    public Employee(){ //Constructor without parameters

        this.name = "default";
        this.surname = "default";
        this.dNI = "00000000-X";
        this.nAF = "default";
        this.birthday = new GregorianCalendar();
        this.position = EnumPosition.Spaguetti;
        this.category = EnumCategory.Spaguetti;
        this.bankAccountN = "default";
        this.password = "default";

    }

    public Employee(String name, String surname, String dNI, String nAF, GregorianCalendar birthday, EnumPosition position, EnumCategory category, String bankAccountN, String password){ //Constructor with parameters

        this.name = name;
        this.surname = surname;
        this.dNI = dNI;
        this.nAF = nAF;
        this.birthday = birthday;
        this.position = position;
        this.category = category;
        this.bankAccountN = bankAccountN;
        this.password = password;


    }

/////// BASIC METHODS ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.surname;
    }

    public String getdNI() {
        return this.dNI;
    }

    public String getnAF() {
        return this.nAF;
    }

    public GregorianCalendar getBirthday() {
        return this.birthday;
    }



    public EnumPosition getPosition() {
        return this.position;
    }

    public void setPosition(EnumPosition position) {
        this.position = position;
    }



    public EnumCategory getCategory(){
        return this.category;
    }

    public void setCategory(EnumCategory category) {
        this.category = category;
    }



    public String getBankAccountN(){
        return this.bankAccountN;
    }

    public void setBankAccountN(String bankAccountN) {
        this.bankAccountN = bankAccountN;
    }



    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password){
        this.password = password;
    }


/////// ADDED METHODS ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public String toString(){

        return name+"|"+surname+"|"+dNI+"|"+nAF+"|"+birthday+"|"+position+"|"+category+"|"+bankAccountN+"|"+password;

    }

/*
    @Override
    public int compareTo(Object obj){

    }

    @Override
    public boolean equals(Object obj) {

    }
*/
    @Override
    public Employee clone() {
        Employee objEmployee = null;

        try {
            objEmployee = (Employee) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return objEmployee;
    }


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}






