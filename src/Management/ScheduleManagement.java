package Management;

import BasicsClasses.Employee.Enums.EnumWeekDays;
import BasicsClasses.Employee.Schedule;

import java.util.GregorianCalendar;

public class ScheduleManagement {


///////// SET SCHEDULE DATA //////////////////////////////////////////////////////////////////////////////////////////////


    /**
     *
     * This method assigns an employee a schedule
     *
     */

    public Schedule[] setScheduleData(){

        Schedule[] schedule = new Schedule[7];
        Validations va = new Validations();

        int sDHour, sDMinute, eDHour, eDMinute;
        int sDDay, sDMonth, eDDay, eDMonth;

        for(int cont = 0; cont < schedule.length; cont++) {

            System.out.println("Enter check in day");
            sDDay = va.readDay();
            schedule[cont].setSDDayOfSchedule(sDDay);

            System.out.println("Enter check in month");
            sDMonth = va.readMonth();
            schedule[cont].setSDMonthOfSchedule(sDMonth);

            System.out.println("Enter check in time");
            sDHour = va.readHour();
            schedule[cont].setSDHour(sDHour);

            System.out.println("Enter check in minutes");
            sDMinute = va.readMinute();
            schedule[cont].setSDMinutes(sDMinute);

            System.out.println("Enter check out day");
            eDDay = va.readDay();
            schedule[cont].setEDDayOfSchedule(eDDay);

            System.out.println("Enter check out month");
            eDMonth = va.readMonth();
            schedule[cont].setEDMonthOfSchedule(eDMonth);

            System.out.println("Enter check out time");
            eDHour = va.readHour();
            schedule[cont].setEDHour(eDHour);

            System.out.println("Enter check out minutes");
            eDMinute = va.readMinute();
            schedule[cont].setEDMinutes(eDMinute);


            schedule[cont].setWeekDay(EnumWeekDays.values()[cont]);
            schedule[cont].setSDYearOfSchedule(GregorianCalendar.YEAR);
            schedule[cont].setEDYearOfSchedule(GregorianCalendar.YEAR);

        }

        return schedule;
    }



}
