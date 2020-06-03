package Management;

import BasicsClasses.Employee.Employee;
import BasicsClasses.Employee.Enums.EnumWeekDays;
import BasicsClasses.Employee.Schedule;

import java.util.GregorianCalendar;
import java.util.Scanner;


public class ScheduleManagement {


    /**
     * This method reads and validates the time
     *
     * @return Hour
     */

    public int readHour() {

        Scanner sc = new Scanner(System.in);

        int hour;

        hour = sc.nextInt();

        //Validation
        while (hour < 0 || hour > 24) {
            System.out.println("The time must be between 0 and 24");
            hour = sc.nextInt();
        }

        return hour;

    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * This method reads and validates the minutes
     *
     * @return Minute
     */

    public int readMinute(){

        Scanner sc = new Scanner(System.in);

        int minute;

        minute = sc.nextInt();

        //Validation
        while (minute < 0 || minute > 59) {
            System.out.println("The minutes must be between 0 and 59");
            minute = sc.nextInt();
        }

        return minute;

    }


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public int readDay(){

        Scanner sc = new Scanner(System.in);

        int day;

        day = sc.nextInt();

        //Validation
        while (day < 1 || day > 31) {
            System.out.println("The day must be between 1 and 31");
            day = sc.nextInt();
        }

        return day;

    }


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public int readMonth(){

        Scanner sc = new Scanner(System.in);

        int month;

        month = sc.nextInt();

        //Validation
        while (month < 1 || month > 12) {
            System.out.println("The month must be between 1 and 12");
            month = sc.nextInt();
        }

        return month;

    }


///////// ASSIGN SCHEDULE //////////////////////////////////////////////////////////////////////////////////////////////


    //TODO Verificar esto
    /**
     * Este metodo asigna a un empleado un horario
     *
     */

    public Schedule[] obtenerDatosHorario(){

        Schedule[] schedule = new Schedule[7];

        int sDHour, sDMinute, eDHour, eDMinute;
        int sDDay, sDMonth, eDDay, eDMonth;

        for(int cont = 0; cont < schedule.length; cont++) {

            System.out.println("Introduzca día de entrada");
            sDDay = readDay();
            schedule[cont].setSDDayOfSchedule(sDDay);

            System.out.println("Introduzca mes de entrada");
            sDMonth = readMonth();
            schedule[cont].setSDMonthOfSchedule(sDMonth);

            System.out.println("Introduzca hora de entrada");
            sDHour = readHour();
            schedule[cont].setSDHour(sDHour);

            System.out.println("Introduzca minutos de entrada");
            sDMinute = readMinute();
            schedule[cont].setSDMinutes(sDMinute);

            System.out.println("Introduzca día de salida");
            eDDay = readDay();
            schedule[cont].setEDDayOfSchedule(eDDay);

            System.out.println("Introduzca mes de salida");
            eDMonth = readMonth();
            schedule[cont].setEDMonthOfSchedule(eDMonth);

            System.out.println("Introduzca hora de salida");
            eDHour = readHour();
            schedule[cont].setEDHour(eDHour);

            System.out.println("Introduzca minutos de salida");
            eDMinute = readMinute();
            schedule[cont].setEDMinutes(eDMinute);


            schedule[cont].setWeekDay(EnumWeekDays.values()[cont]); //Introduce Lunes, Martes... en la posicion del contador
            schedule[cont].setSDYearOfSchedule(GregorianCalendar.YEAR);
            schedule[cont].setEDYearOfSchedule(GregorianCalendar.YEAR);

        }

        return schedule;
    }
    


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

 */
}
