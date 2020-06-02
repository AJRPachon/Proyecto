package Management;

import java.util.Scanner;

public class PaySlipManagement {

    public void modifySalary(){

        Scanner sc = new Scanner(System.in);

        FilesManagement fm = new FilesManagement();
        String path = ".\\src\\files\\Payslips";
        String tempPath = ".\\src\\files\\temp\\PayslipsTemp";
        String employeeDNI;
        double salary;

        //Mostrar todos los empleados registrados
        fm.showFileData(path);

        //Elegir empleado al que deseamos modificar el salario
        employeeDNI = sc.nextLine();

        System.out.println("Introduzca la cantidad para el nuevo salario (mensual)");
        salary = sc.nextDouble();

        fm.insertSalary(path,employeeDNI,salary,tempPath);

    }

}
