package Management;

import java.util.Scanner;

public class PaySlipManagement {

    /**
     * Asigna un salario y lo devulve como parametro
     * @return
     */

    public double assignSalary(){

        Scanner sc =  new Scanner(System.in);

        double salary;

        System.out.println("Inserte salario para el empleado seleccionado");
        salary = sc.nextDouble();

        while(salary <= 0){
            System.out.println("El salario tiene que ser mayor que 0");
            salary = sc.nextDouble();
        }

        return salary;

    }

}
