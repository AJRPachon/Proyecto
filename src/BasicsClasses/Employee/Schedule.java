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

    public Schedule(){ //Constructor sin parámetros

        this.weekDay = EnumWeekDays.Spaguetti;
        this.time = new GregorianCalendar();
    }

    public Schedule(EnumWeekDays weekDay, GregorianCalendar time){  //Constructor con parametros

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
        SIGNATURE: void consultSchedule()

        COMENTARY: Shows the time, shift and day of the week

        INPUTS: Not one

        OUTPUTS: Not one

        INTPUT/OUTPUT: Not one

        PRECONDITION: Attribute values must be !null or >= 0

        POSTCONDITION: Values printed per screen

     */


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
