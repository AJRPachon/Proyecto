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
import Management.ScheduleManagement;

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

    public Employee getEmployee() {
        return this.employee;
    }


/////// ADDED METHODS ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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

    public void consultSchedule(){ //TODO

        //Day of the week
        for (int cont = 0; cont < 7; cont++){

            System.out.print(EnumWeekDays.getWeekDay(cont)+"  |  ");

        }

        System.out.println();

        for (int cont = 0; cont < 7; cont++){

            System.out.print(getSDHour()+":"+getSDMinutes()+ "\n"+getEDHour()+":"+getEDMinutes());

        }

    }


///////// ASSIGN SCHEDULE //////////////////////////////////////////////////////////////////////////////////////////////


    //TODO Verificar esto
    /**
     * Este metodo asigna a un empleado un horario
     * @param employee
     */

    public void assignSchedule(Employee employee){

        ScheduleManagement SM = new ScheduleManagement();

        Schedule[] schedule;

        schedule = employee.getSchedule();

        int sDHour, sDMinute, eDHour, eDMinute;
        int sDDay, sDMonth, eDDay, eDMonth;

        for(int cont = 0; cont < schedule.length; cont++) {

            System.out.println("Introduzca día de entrada");
            sDDay = SM.readDay();
            schedule[cont].setSDDayOfSchedule(sDDay);

            System.out.println("Introduzca mes de entrada");
            sDMonth = SM.readMonth();
            schedule[cont].setSDMonthOfSchedule(sDMonth);

            System.out.println("Introduzca hora de entrada");
            sDHour = SM.readHour();
            schedule[cont].setSDHour(sDHour);

            System.out.println("Introduzca minutos de entrada");
            sDMinute = SM.readMinute();
            schedule[cont].setSDMinutes(sDMinute);

            System.out.println("Introduzca día de salida");
            eDDay = SM.readDay();
            schedule[cont].setSDDayOfSchedule(eDDay);

            System.out.println("Introduzca mes de salida");
            eDMonth = SM.readMonth();
            schedule[cont].setSDMonthOfSchedule(eDMonth);

            System.out.println("Introduzca hora de salida");
            eDHour = SM.readHour();
            schedule[cont].setEDHour(eDHour);

            System.out.println("Introduzca minutos de salida");
            eDMinute = SM.readMinute();
            schedule[cont].setEDMinutes(eDMinute);


            schedule[cont].setWeekDay(EnumWeekDays.values()[cont]); //Introduce Lunes, Martes... en la posicion del contador

            employee.setSchedule(schedule[cont], cont);

        }
    }


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public String toString(){

        return weekDay.toString()+"#"+getSDDayOfSchedule()+"/"+getSDMonthOfSchedule()+"/"+getSDYearOfSchedule()+" "+getSDHour()+":"+getSDMinutes()+"#"+
                getEDDayOfSchedule()+"/"+getEDMonthOfSchedule()+"/"+getEDYearOfSchedule()+" "+getEDHour()+":"+getEDMinutes();

    }

}
