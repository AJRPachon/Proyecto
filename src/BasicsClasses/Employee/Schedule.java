package BasicsClasses.Employee;

/*

    ANALYSIS:
        Collect the schedule of each employee ( shift, day of the week and time at which the service must be performed )

    BASIC PROPERTIES:
        weekDay enum consulting and modifiable
        time GregorianCalendar consulting and modifiable

    DERIVATE PROPERTIES:
        shift String consulting ( Depends on the time )


    INTERFACE
    BASIC METHODS:
        getWeekDay();
        setWeekDay(enum);

        getTime();
        setTime(dateType time);

        getShift();


    ADDED METHODS:
        consultShedule() ( Displays the time, day and shift on the screen )

 */


import java.util.GregorianCalendar;

public class Schedule {

    private EnumWeekDays weekDay;
    private GregorianCalendar time;

    public Schedule(){ //Constructor without parameters

        this.weekDay = EnumWeekDays.Spaguetti;
        this.time = new GregorianCalendar();
    }

    public Schedule(EnumWeekDays weekDay, GregorianCalendar time){  //Constructor with parameters

        this.weekDay = weekDay;
        this.time = time;

    }


/////// BASIC METHODS ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public EnumWeekDays getWeekDay(){
        return this.weekDay;
    }

    public void setWeekDay(EnumWeekDays weekDay){
        this.weekDay = weekDay;
    }


    public GregorianCalendar getTime(){
        return this.time;
    }

    public void setTime(GregorianCalendar time){
        this.time = time;
    }



/////// ADDED METHODS ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////// CONSULT SHEDULE ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /*
        SIGNATURE:
            public void consultSchedule()

        COMENTARY:
            - Displays the employee's schedule on screen

        INPUTS:
            - Not one

        OUTPUTS:
            - Not one (On-screen display)

        INTPUT/OUTPUT:
            - Not one

        PRECONDITION:
            - Not one

        POSTCONDITION:
            - Printed schedule must be displayed

     */

    public void consultSchedule(){

        //Day of the week
        for (int cont = 0; cont < 7; cont++){

            System.out.print(EnumWeekDays.getWeekDay(cont));

        }


    }


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
