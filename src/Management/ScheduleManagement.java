package Management;

import BasicsClasses.Employee.Schedule;
import java.util.Scanner;


public class ScheduleManagement {

    ///// ASSIGN SCHEDULE //////////////////////

    /**
     *
     *
     */

    public void assignSchedule(Schedule schedule){

        Scanner teclado = new Scanner(System.in);

        int SDHour;
        int SDMinute;
        int EDHour;
        int EDMinute;

        System.out.println("Start time of the day");
        SDHour = teclado.nextInt();
        schedule.setSDHour(SDHour);

        System.out.println("minutes start of the day");
        SDMinute = teclado.nextInt();
        schedule.setSDMinutes(SDMinute);

        System.out.println("Hora fin de la jornada");
        EDHour = teclado.nextInt();
        schedule.setEDHour(EDHour);

        System.out.println("Minutos fin de la jornada");
        EDMinute = teclado.nextInt();
        schedule.setEDMinutes(EDMinute);

    }


///// SHOW SCHEDULE //////////////////////

    public void showSchedule(Schedule schedule){

        System.out.println("Hora de inicio: "+schedule.getSDHour()+":"+schedule.getSDMinutes());
        System.out.println("Hora de fin: "+schedule.getEDHour()+":"+schedule.getEDMinutes());

    }


}
