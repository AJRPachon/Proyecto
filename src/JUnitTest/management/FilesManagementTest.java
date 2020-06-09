package JUnitTest.management;


import BasicsClasses.Employee.Employee;
import BasicsClasses.Employee.Payslip;
import BasicsClasses.Employee.Schedule;
import Management.FilesManagement;

import java.io.File;

class FilesManagementTest {

    public static void main(String [] args) {

        FilesManagement fm = new FilesManagement();
        Payslip pa = new Payslip();
        Employee emp = new Employee();
        Schedule[] sch = new Schedule[7];


        File file = new File(".\\src\\files\\Payslips");
        String pathEmployeeTemp = ".\\src\\files\\tmp\\EmployeesTemp";
        String pathEmployee = ".\\src\\files\\Employees";
        String pathOrders = ".\\src\\files\\Orders";
        String pathProduct = ".\\src\\files\\Products";
        String pathPaySlip = ".\\src\\files\\Payslips";
        String pathSchedule = ".\\src\\files\\Schedules";
        String pathScheduleTemp = ".\\src\\files\\SchedulesTemp";

        fm.insertObjectInFile(pa, pathPaySlip);


        System.out.println("fm.checkFile: "); fm.checkFile(file);

        System.out.println("\n-----------------------------------------------------------------------\n");

        //System.out.println("fm.checkFileEmployee: "); fm.checkFileEmployee(file);

        //System.out.println("\n-----------------------------------------------------------------------\n");

        System.out.println("fm.insertObjectInFile: "+fm.insertObjectInFile(emp,pathEmployeeTemp));

        System.out.println("\n-----------------------------------------------------------------------\n");

        System.out.println("fm.insertObjectDeletedInFile: "+fm.insertObjectDeletedInFile(emp,pathEmployeeTemp));

        System.out.println("\n-----------------------------------------------------------------------\n");

        System.out.println("fm.insertObjectModifiedInFile: "+fm.insertObjectModifiedInFile(emp,pathEmployeeTemp));

        System.out.println("\n-----------------------------------------------------------------------\n");

        System.out.println("fm.getOrdersNotShipped: "+fm.getOrdersNotShipped(pathOrders).toString());

        System.out.println("\n-----------------------------------------------------------------------\n");

        System.out.println("fm.getProductFromFile: "+fm.getProductFromFile(0 , pathProduct));

        System.out.println("\n-----------------------------------------------------------------------\n");

        System.out.println("fm.printEmployeePersonalData: "); fm.printEmployeePersonalData("00000000T" , pathEmployee);

        System.out.println("\n-----------------------------------------------------------------------\n");

        System.out.println("fm.insertSalary: "+fm.insertSalary(pathEmployee,"00000000T", 24.00));

        System.out.println("\n-----------------------------------------------------------------------\n");

        System.out.println("fm.getSelectedEmployee: "+fm.getSelectedEmployee(pathEmployee, "00000000T"));

        System.out.println("\n-----------------------------------------------------------------------\n");


        for (int cont = 0; cont < sch.length; cont++){
            sch[cont] = new Schedule();
        }
        fm.insertObjectInFile(sch,pathSchedule);

        System.out.println("fm.inserScheduleOnFile: "); fm.insertScheduleOnFile(pathSchedule,"00000000T", pathScheduleTemp, sch );

        System.out.println("\n-----------------------------------------------------------------------\n");

        System.out.print("fm.showFileData: "); fm.showFileData(pathProduct);

        System.out.println("\n-----------------------------------------------------------------------\n");

        System.out.print("fm.printScheduleFromFile: "); fm.printScheduleFromFile(pathSchedule, "00000000T");

        System.out.println("\n-----------------------------------------------------------------------\n");


    }
}