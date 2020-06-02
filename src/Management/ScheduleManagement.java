package Management;

import BasicsClasses.Employee.Employee;
import BasicsClasses.Employee.Enums.EnumWeekDays;
import BasicsClasses.Employee.Schedule;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
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


/////////// SCHEDULE FILE IN ///////////////////////////////////////////////////////////////////////////////////////////

    /**
     * This method implements in the file the toString corresponding to the schedule
     *
     * @param path
     */

    public void scheduleFileIn(String path) {

        Schedule schedule = new Schedule();

        FileWriter fw = null;
        BufferedWriter bw = null;

        //IMPLEMENT IN THE FILE

        try {

            File fileSchedule = new File(path);

            //If the file does not exist it is created
            if (!fileSchedule.exists()) {
                fileSchedule.createNewFile();
            }

            fw = new FileWriter(fileSchedule);
            bw = new BufferedWriter(fw);
            bw.write(schedule.toString());

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {

                bw.close();
                fw.close();

            } catch (Exception ebwfw) {
                ebwfw.printStackTrace();
            }
        }

    }



}
