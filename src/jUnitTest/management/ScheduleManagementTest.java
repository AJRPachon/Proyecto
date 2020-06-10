package jUnitTest.management;

import management.ScheduleManagement;

class ScheduleManagementTest {

    public static void main(String[] args){

        ScheduleManagement sm = new ScheduleManagement();

        for (int cont = 0; cont < 7; cont++){
            System.out.println("sm.setScheduleData :" + sm.setScheduleData()[cont].toString());
        }
        System.out.println("\n-----------------------------------------------------------------------\n");


    }
}