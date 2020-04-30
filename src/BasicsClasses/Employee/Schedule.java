package BasicsClasses.Employee;

/*
 *
 *   ANALYSIS:
 *       - Collect the schedule of each employee ( shift, day of the week and time at which the service must be performed )
 *
 *   BASIC PROPERTIES:
 *       - weekDay: enum, Consulting, Modifiable
 *       - time: GregorianCalendar, Consulting, Modifiable
 *
 *   DERIVATE PROPERTIES:
 *       - shift: String, Consulting ( Depends on the time )
 *
 *
 *   INTERFACE
 *   BASIC METHODS:
 *       - EnumWeekDays getWeekDay();
 *       - none setWeekDay(enum);
 *
 *       - date getDate();
 *           * integer getDayOfSchedule()
 *           * integer getMonthOfSchedule()
 *           * integer getYearOfSchedule()
 *       - none setDate(dateType date);
 *           * none setDayOfSchedule(integer day)
 *           * none setMonthOfSchedule(integer month)
 *           * none setYearOfSchedule(integer year)
 *
 *       - getShift();
 *
 *
 *   ADDED METHODS:
 *       - consultShedule() ( Displays the time, day and shift on the screen )
 *
 */


import BasicsClasses.Employee.Enums.EnumWeekDays;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Schedule {

    private EnumWeekDays weekDay;
    private GregorianCalendar date;

    public Schedule(){ //Constructor without parameters

        this.weekDay = EnumWeekDays.Spaguetti;
        this.date = new GregorianCalendar();
    }

    public Schedule(EnumWeekDays weekDay, GregorianCalendar date){  //Constructor with parameters

        this.weekDay = weekDay;
        this.date = date;

    }


/////// BASIC METHODS ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public EnumWeekDays getWeekDay(){
        return this.weekDay;
    }

    public void setWeekDay(EnumWeekDays weekDay){
        this.weekDay = weekDay;
    }


    public GregorianCalendar getDate(){
        return this.date;
    }

    public void setDate(GregorianCalendar date){
        this.date = date;
    }


    /*      - date getDate();
 *           * integer getDayOfSchedule()
 *           * integer getMonthOfSchedule()
 *           * integer getYearOfSchedule()
 *       - none setDate(dateType date);
 *           * none setDayOfSchedule(integer day)
 *           * none setMonthOfSchedule(integer month)
 *           * none setYearOfSchedule(integer year)*/

    public int getDayOfSchedule(){
        return this.date.get(Calendar.DAY_OF_MONTH);
    }

    public void setDayOfSchedule(int day){
        this.date.set(Calendar.DAY_OF_MONTH, day);
    }



    public int getMonthOfSchedule(){
        return this.date.get(Calendar.MONTH);
    }

    public void setMonthOfSchedule(int month){
        this.date.set(Calendar.MONTH, month);
    }



    public int getYearOfSchedule(){
        return this.date.get(Calendar.YEAR);
    }

    public void setYearOfSchedule(int year){
        this.date.set(Calendar.YEAR, year);
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

            System.out.print(EnumWeekDays.getWeekDay(cont)+"  |  ");

        }


        //


    }


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
