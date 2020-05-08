package Management;

import BasicsClasses.Employee.Schedule;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;


public class ScheduleManagement {

///// ASSIGN SCHEDULE //////////////////////

    /**
     *
     * @return
     */

    public int readSDHour() {

        Scanner kb = new Scanner(System.in);

        int SDHour;  //SD = START DATE

        Schedule schedule = new Schedule();

        //SD HOUR
        System.out.println("Enter the start time of the day");
        SDHour = kb.nextInt();

        //Validation
        while (SDHour < 0 || SDHour > 24) {
            System.out.println("The time must be between 0 and 24");
            SDHour = kb.nextInt();
        }

        return SDHour;

    }


    /**
     *
     * @return
     */

    public int readSDMinute(){

        Scanner kb = new Scanner(System.in);

        int SDMinute;  //SD = START DATE

        Schedule schedule = new Schedule();

        //SD MINUTE
        System.out.println("Enter minute of start of the day");
        SDMinute = kb.nextInt();

        //Validation
        while (SDMinute < 0 || SDMinute > 59) {
            System.out.println("The minutes must be between 0 and 59");
            SDMinute = kb.nextInt();
        }

        return SDMinute;

    }



    /**
     *
     * @return
     */


    public int readEDHour(){

        Scanner kb = new Scanner(System.in);

        int EDHour;  //SD = START DATE

        Schedule schedule = new Schedule();

        //ED HOUR
        System.out.println("Enter end-of-day hour");
        EDHour = kb.nextInt();

        //Validation
        while (EDHour < 0 || EDHour > 24) {
            System.out.println("The time must be between 0 and 24");
            EDHour = kb.nextInt();
        }

        return EDHour;

    }


    /**
     *
     * @return
     */

    public int readEDMinute(){

        Scanner kb = new Scanner(System.in);

        int EDMinute;  //SD = START DATE

        Schedule schedule = new Schedule();

        //ED MINUTE
        System.out.println("Enter end-of-day minute");
        EDMinute = kb.nextInt();

        //Validation
        while (EDMinute < 0 || EDMinute > 59) {
            System.out.println("The minutes must be between 0 and 59");
            EDMinute = kb.nextInt();
        }

        return EDMinute;

    }


    /**
     *
     * @param SDHour
     * @param SDMinute
     * @param EDHour
     * @param EDMinute
     */
    
    public void assignSchedule(int SDHour, int SDMinute, int EDHour, int EDMinute){

        Schedule schedule = new Schedule();

        schedule.setSDHour(SDHour);
        schedule.setSDMinutes(SDMinute);
        schedule.setEDHour(EDHour);
        schedule.setEDMinutes(EDMinute);

    }



    FileWriter fw = null;
        BufferedWriter bw = null;

        //IMPLEMENT IN THE FILE

        try {

            File fileSchedule = new File(".\\src\\Files\\Schedule");

            //If the file does not exist it is created
            if (!fileSchedule.exists()) {
                fileSchedule.createNewFile();
            }

            fw = new FileWriter(fileSchedule);
            bw = new BufferedWriter(fw);
            bw.write(schedule.toString());

        } catch (Exception e){
            e.printStackTrace();
        }


        finally {
            try {

                bw.close();
                fw.close();

            }catch (Exception ebwfw){
                ebwfw.printStackTrace();
            }
        }




////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
