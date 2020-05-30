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
 *           * integer getHour()
 *           * integer getMinute()
 *           * integer getDayOfSchedule()
 *           * integer getMonthOfSchedule()
 *           * integer getYearOfSchedule()
 *       - none setDate(dateType date);
 *           * none setDayOfSchedule(integer day)
 *           * none setMonthOfSchedule(integer month)
 *           * none setYearOfSchedule(integer year)
 *           * none setHour(integer hour)
 *           * none setMinute(integer minute)
 *
 *       - getShift(); TODO
 *
 *
 *   ADDED METHODS:
 *       - consultShedule() ( Displays the time, day and shift on the screen )
 *       - assignShedule() TODO
 *
 */


import BasicsClasses.Employee.Enums.EnumWeekDays;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

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
/////////// ASSIGN SCHEDULE //////////////////////////////////////////////////////////////////////////////////////////////////////////////

   /*
        SIGNATURE:
            public void assignSchedule(GregorianCalendar startDate, GregorianCalendar endDate)

        COMENTARY:
            - Assign a schedule to an employee

        INPUTS:
            - Not one

        OUTPUTS:
            - Not one

        INTPUT/OUTPUT:
            - Not one

        PRECONDITION:
            - Not one

        POSTCONDITION:
            - The schedule must be assigned to the employee after the method is executed

    */

    //TODO PONER LOS COMENTARIOS EN INGLÉS Y VALIDAR
    //TODO PREGUNTAR ENUMS
/*
    public void assignSchedule(){

        Scanner sc = new Scanner(System.in);

        int SDHour, SDMinute, EDHour, EDMinute;
        String SDWeekDay, EDWeekDay;

        //START DATE
        System.out.println("Introduzca hora de inicio de jornada");
        SDHour = sc.nextInt();
        setSDHour(SDHour);

        System.out.println("Introduzca minutos de inicio de jornada");
        SDMinute = sc.nextInt();
        setSDMinutes(SDMinute);

        System.out.println("Introduzca dia de inicio de jornada");
        SDWeekDay = sc.nextLine();
        setWeekDay(SDWeekDay);


        //END DATE
        System.out.println("Introduzca hora de fin de jornada");
        EDHour = sc.nextInt();
        setEDHour(EDHour);

        System.out.println("Introduzca minutos de fin de jornada");
        EDMinute = sc.nextInt();
        setEDMinutes(EDMinute);

        System.out.println("Introduzca dia de fin de jornada");
        EDWeekDay = sc.nextInt();
        setEDDayOfSchedule(EDWeekDay);


    }

*/

/////// CONSULT SHEDULE //////////////////////////////////////////////////////////////////////////////////////////////////////////////////

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

        System.out.println();

        for (int cont = 0; cont < 7; cont++){

            System.out.print(getSDHour()+":"+getSDMinutes()+ "\n"+getEDHour()+":"+getEDMinutes());

        }

    }



//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public String toString(){

        return weekDay.toString()+"#"+getSDDayOfSchedule()+"/"+getSDMonthOfSchedule()+"/"+getSDYearOfSchedule()+" "+getSDHour()+":"+getSDMinutes()+"#"+
                getEDDayOfSchedule()+"/"+getEDMonthOfSchedule()+"/"+getEDYearOfSchedule()+" "+getEDHour()+":"+getEDMinutes();

    }

}
