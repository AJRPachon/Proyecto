package Management;

import BasicsClasses.Employee.Schedule;
import java.util.Scanner;


public class ScheduleManagement {

    ///// ASSIGN SCHEDULE //////////////////////


    /*
     *   SIGNATURE:
     *       public void assignSchedule()
     *
     *   COMENTARY:
     *       This method assigns a schedule to an employee
     *
     *   INPUTS:
     *       Not one
     *
     *   OUTPUTS:
     *       Not one
     *
     *   INPUT/OUTPUTS:
     *       Not one
     *
     *   PRECONDITION:
     *       Not one
     *
     *   POSTCONDITION:
     *       The schedule must have been assigned
     *
     */

    public void assignSchedule(Schedule schedule){

        Scanner kb = new Scanner(System.in);

        int SDHour;  //SD = START DATE
        int SDMinute;
        int EDHour;  //ED = END DATE
        int EDMinute;


        //SD HOUR
        System.out.println("Enter the start time of the day");
        SDHour = kb.nextInt();

        //Validation
        while( SDHour < 0 || SDHour > 24 ){
            System.out.println("The time must be between 0 and 24");
            SDHour = kb.nextInt();
        }
        schedule.setSDHour(SDHour);


        //SD MINUTE
        System.out.println("Enter minute of start of the day");
        SDMinute = kb.nextInt();

        //Validation
        while( SDMinute < 0 || SDMinute > 59 ){
            System.out.println("The minutes must be between 0 and 59");
            SDMinute = kb.nextInt();
        }
        schedule.setSDMinutes(SDMinute);


        //ED HOUR
        System.out.println("Enter end-of-day hour");
        EDHour = kb.nextInt();

        //Validation
        while( EDHour < 0 || EDHour > 24 ){
            System.out.println("The time must be between 0 and 24");
            EDHour = kb.nextInt();
        }
        schedule.setEDHour(EDHour);


        //ED MINUTE
        System.out.println("Enter end-of-day minute");
        EDMinute = kb.nextInt();

        //Validation
        while( EDMinute < 0 || EDMinute > 59 ){
            System.out.println("The minutes must be between 0 and 59");
            EDMinute = kb.nextInt();
        }
        schedule.setEDMinutes(EDMinute);

    }


///// SHOW SCHEDULE //////////////////////

    public void showSchedule(Schedule schedule){

        System.out.println("Hora de inicio: "+schedule.getSDHour()+":"+schedule.getSDMinutes());
        System.out.println("Hora de fin: "+schedule.getEDHour()+":"+schedule.getEDMinutes());

    }


}
