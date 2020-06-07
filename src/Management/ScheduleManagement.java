package Management;

import BasicsClasses.Employee.Enums.EnumWeekDays;
import BasicsClasses.Employee.Schedule;

import java.util.GregorianCalendar;

public class ScheduleManagement {


///////// ASSIGN SCHEDULE //////////////////////////////////////////////////////////////////////////////////////////////


    //TODO Verificar esto
    /**
     * Este metodo asigna a un empleado un horario
     *
     */

    public Schedule[] setScheduleData(){

        Schedule[] schedule = new Schedule[7];
        Validations va = new Validations();

        int sDHour, sDMinute, eDHour, eDMinute;
        int sDDay, sDMonth, eDDay, eDMonth;

        for(int cont = 0; cont < schedule.length; cont++) {

            schedule[cont] = new Schedule();

            System.out.println("Introduzca día de entrada");
            sDDay = va.readDay();
            schedule[cont].setSDDayOfSchedule(sDDay);

            System.out.println("Introduzca mes de entrada");
            sDMonth = va.readMonth();
            schedule[cont].setSDMonthOfSchedule(sDMonth);

            System.out.println("Introduzca hora de entrada");
            sDHour = va.readHour();
            schedule[cont].setSDHour(sDHour);

            System.out.println("Introduzca minutos de entrada");
            sDMinute = va.readMinute();
            schedule[cont].setSDMinutes(sDMinute);

            System.out.println("Introduzca día de salida");
            eDDay = va.readDay();
            schedule[cont].setEDDayOfSchedule(eDDay);

            System.out.println("Introduzca mes de salida");
            eDMonth = va.readMonth();
            schedule[cont].setEDMonthOfSchedule(eDMonth);

            System.out.println("Introduzca hora de salida");
            eDHour = va.readHour();
            schedule[cont].setEDHour(eDHour);

            System.out.println("Introduzca minutos de salida");
            eDMinute = va.readMinute();
            schedule[cont].setEDMinutes(eDMinute);


            schedule[cont].setWeekDay(EnumWeekDays.values()[cont]); //Introduce Lunes, Martes... en la posicion del contador
            schedule[cont].setSDYearOfSchedule(GregorianCalendar.YEAR);
            schedule[cont].setEDYearOfSchedule(GregorianCalendar.YEAR);

        }

        return schedule;
    }



}
