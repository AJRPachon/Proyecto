package basicsClasses.employee;

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
 *           * integer getSDHour()
 *           * integer getSDMinute()
 *           * integer getSDDayOfSchedule()
 *           * integer getSDMonthOfSchedule()
 *           * integer getSDYearOfSchedule()
 *
 *           * integer getEDHour()
 *           * integer getEDMinute()
 *           * integer getEDDayOfSchedule()
 *           * integer getEDMonthOfSchedule()
 *           * integer getEDYearOfSchedule()
 *
 *       - none setDate(dateType date);
 *           * none setSDDayOfSchedule(integer day)
 *           * none setSDMonthOfSchedule(integer month)
 *           * none setSDYearOfSchedule(integer year)
 *           * none setSDHour(integer hour)
 *           * none setSDMinute(integer minute)
 *
 *           * none setEDDayOfSchedule(integer day)
 *           * none setEDMonthOfSchedule(integer month)
 *           * none setEDYearOfSchedule(integer year)
 *           * none setEDHour(integer hour)
 *           * none setEDMinute(integer minute)
 *
 *
 *
 *   ADDED METHODS:
 *      toString()
 *
 *
 */


import basicsClasses.employee.Enums.EnumWeekDays;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Schedule {

    private EnumWeekDays weekDay;
    private GregorianCalendar startDate;
    private GregorianCalendar endDate;
    private Employee employee;

    public Schedule(){ //Constructor without parameters

        this.weekDay = EnumWeekDays.Spaguetti;
        this.startDate = new GregorianCalendar();
        this.endDate = new GregorianCalendar();
        this.employee = new Employee();
    }

    public Schedule(EnumWeekDays weekDay, GregorianCalendar startDate, GregorianCalendar endDate, Employee employee){  //Constructor with parameters

        this.weekDay = weekDay;
        this.startDate = startDate;
        this.endDate = endDate;
        this.employee = employee;

    }

    public Schedule(EnumWeekDays weekDay, GregorianCalendar startDate, GregorianCalendar endDate){  //Constructor with parameters

        this.weekDay = weekDay;
        this.startDate = startDate;
        this.endDate = endDate;
        this.employee = new Employee();

    }


/////// BASIC METHODS ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public EnumWeekDays getWeekDay(){
        return this.weekDay;
    }

    public void setWeekDay(EnumWeekDays weekDay){
        this.weekDay = weekDay;
    }



    public GregorianCalendar getStartDate(){
        return this.startDate;
    }


    public void setStartDate(GregorianCalendar startDate){
        this.startDate = startDate;
    }




    public GregorianCalendar getEndDate(){
        return this.endDate;
    }

    public void setEndDate(GregorianCalendar endDate){
        this.endDate = endDate;
    }


    public int getSDHour(){ return this.startDate.get(Calendar.HOUR_OF_DAY); }

    public void setSDHour(int hour){ this.startDate.set(Calendar.HOUR_OF_DAY, hour); }



    public int getSDMinutes(){ return this.startDate.get(Calendar.MINUTE); }

    public void setSDMinutes(int minute){ this.startDate.set(Calendar.MINUTE, minute); }



    public int getSDDayOfSchedule(){
        return this.startDate.get(Calendar.DAY_OF_MONTH);
    }

    public void setSDDayOfSchedule(int day){
        this.startDate.set(Calendar.DAY_OF_MONTH, day);
    }



    public int getSDMonthOfSchedule(){
        return this.startDate.get(Calendar.MONTH);
    }

    public void setSDMonthOfSchedule(int month){
        this.startDate.set(Calendar.MONTH, month);
    }



    public int getSDYearOfSchedule(){
        return this.startDate.get(Calendar.YEAR);
    }

    public void setSDYearOfSchedule(int year){
        this.startDate.set(Calendar.YEAR, year);
    }


    //END DATE

    public int getEDHour(){ return this.endDate.get(Calendar.HOUR_OF_DAY); }

    public void setEDHour(int hour){ this.endDate.set(Calendar.HOUR_OF_DAY, hour); }



    public int getEDMinutes(){ return this.endDate.get(Calendar.MINUTE); }

    public void setEDMinutes(int minute){ this.endDate.set(Calendar.MINUTE, minute); }



    public int getEDDayOfSchedule(){
        return this.endDate.get(Calendar.DAY_OF_MONTH);
    }

    public void setEDDayOfSchedule(int day){
        this.endDate.set(Calendar.DAY_OF_MONTH, day);
    }



    public int getEDMonthOfSchedule(){
        return this.endDate.get(Calendar.MONTH);
    }

    public void setEDMonthOfSchedule(int month){
        this.endDate.set(Calendar.MONTH, month);
    }



    public int getEDYearOfSchedule(){
        return this.endDate.get(Calendar.YEAR);
    }

    public void setEDYearOfSchedule(int year){
        this.endDate.set(Calendar.YEAR, year);
    }



/////// ADDED METHODS ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public String toString(){

        return employee.getDNI()+"#"+weekDay.toString()+"#"+getSDDayOfSchedule()+"/"+getSDMonthOfSchedule()+"/"+getSDYearOfSchedule()+"#"+getSDHour()+":"+getSDMinutes()+"#"+
                getEDDayOfSchedule()+"/"+getEDMonthOfSchedule()+"/"+getEDYearOfSchedule()+"#"+getEDHour()+":"+getEDMinutes();

    }

}
