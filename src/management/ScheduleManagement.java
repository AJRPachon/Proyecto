package management;

import basicsClasses.employee.Enums.EnumWeekDays;
import basicsClasses.employee.Schedule;

import java.util.ArrayList;
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

            schedule[cont] = new Schedule();

            System.out.println("Enter check in day");
            sDDay = va.readAndValidateDay();
            schedule[cont].setSDDayOfSchedule(sDDay);

            System.out.println("Enter check in month");
            sDMonth = va.readAndValidateMonth();
            schedule[cont].setSDMonthOfSchedule(sDMonth);

            System.out.println("Enter check in time");
            sDHour = va.readAndValidateHour();
            schedule[cont].setSDHour(sDHour);

            System.out.println("Enter check in minutes");
            sDMinute = va.readAndValidateMinute();
            schedule[cont].setSDMinutes(sDMinute);

            System.out.println("Enter check out day");
            eDDay = va.readAndValidateDay();
            schedule[cont].setEDDayOfSchedule(eDDay);

            System.out.println("Enter check out month");
            eDMonth = va.readAndValidateMonth();
            schedule[cont].setEDMonthOfSchedule(eDMonth);

            System.out.println("Enter check out time");
            eDHour = va.readAndValidateHour();
            schedule[cont].setEDHour(eDHour);

            System.out.println("Enter check out minutes");
            eDMinute = va.readAndValidateMinute();
            schedule[cont].setEDMinutes(eDMinute);


            schedule[cont].setWeekDay(EnumWeekDays.values()[cont]);
            schedule[cont].setSDYearOfSchedule(GregorianCalendar.YEAR);
            schedule[cont].setEDYearOfSchedule(GregorianCalendar.YEAR);

        }

        return schedule;
    }


    public void printSchedules(ArrayList<Schedule> schedules){

        for (Schedule schedule:schedules) {
            System.out.println(schedule.toString());
        }

    }

}
