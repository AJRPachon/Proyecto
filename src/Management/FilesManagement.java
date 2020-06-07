package Management;

import BasicsClasses.Employee.Employee;
import BasicsClasses.Employee.Enums.EnumCategory;
import BasicsClasses.Employee.Enums.EnumPosition;
import java.io.*;

import BasicsClasses.Employee.Payslip;
import BasicsClasses.Employee.Schedule;
import BasicsClasses.FoodstuffDrinks.Product;
import BasicsClasses.Orders.Order;
import utils.Utils;

import java.util.ArrayList;
import java.util.GregorianCalendar;



public class FilesManagement {

    /**
     * This method verifies that all the files necessary for the program to work are correct.
     */

    public void checkFiles(){

        File file;

        file = new File(".\\src\\Files\\Consumables");
        checkFile(file);
        file = new File(".\\src\\Files\\Drinks");
        checkFile(file);
        file = new File(".\\src\\Files\\Foods");
        checkFile(file);
        file = new File(".\\src\\Files\\Orders");
        checkFile(file);
        file = new File(".\\src\\Files\\Payslips");
        checkFile(file);
        file = new File(".\\src\\Files\\Receipts");
        checkFile(file);
        file = new File(".\\src\\Files\\Schedules");
        checkFile(file);
        file = new File(".\\src\\Files\\Products");
        checkFile(file);

        file = new File(".\\src\\Files\\Employees");
        checkFileEmployee(file);

        file = new File(".\\src\\Files\\tmp\\ConsumablesTemp");
        checkFile(file);
        file = new File(".\\src\\Files\\tmp\\DrinksTemp");
        checkFile(file);
        file = new File(".\\src\\Files\\tmp\\FoodsTemp");
        checkFile(file);
        file = new File(".\\src\\Files\\tmp\\OrdersTemp");
        checkFile(file);
        file = new File(".\\src\\Files\\tmp\\PayslipsTemp");
        checkFile(file);
        file = new File(".\\src\\Files\\tmp\\ReceiptsTemp");
        checkFile(file);
        file = new File(".\\src\\Files\\tmp\\SchedulesTemp");
        checkFile(file);
        file = new File(".\\src\\Files\\tmp\\EmployeesTemp");
        checkFile(file);
        file = new File(".\\src\\Files\\tmp\\ProductsTemp");
        checkFile(file);

    }


/////////// CHECK FILE //////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * This method check if the file passed by parameter exist. If not exist this file, this method create it.
     * @param file File to check
     */

    public void checkFile(File file){
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


/////////// CHECK FILE EMPLOYEE //////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * This method checks if an employee file exists and it contains at least one employee so that the program can start
     * @param file File to check
     */

    private void checkFileEmployee(File file){
        if (!(file.length() > 0)){

            BufferedWriter bw = null;
            try {
                file.createNewFile();

                bw = new BufferedWriter(new FileWriter(file));
                bw.write(new Employee("Administrator","Administrator","00000000T","281234567840",new GregorianCalendar(),EnumPosition.Manager,EnumCategory.Administrator,"ES3231906288456991923866","e807f1fcf82d132f9bb018ca6738a19f").toString());
                bw.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    if (bw != null) {
                        bw.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }


/////////// INSERT OBJETCT IN FILE //////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * @param object
     * @param path
     * @param <T>
     * @return
     */

    public <T> boolean insertObjectInFile(T object, String path){

        boolean objectInserted = false;
        FileWriter fw = null;
        BufferedWriter bw = null;

        try {
            fw = new FileWriter(path,true);
            bw = new BufferedWriter(fw);
            bw.write(object.toString());
            bw.newLine();
            bw.flush();
            objectInserted = true;

        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if( fw != null ) {
                    fw.close();
                }
                if( bw != null ) {
                    bw.close();
                }
            }catch (IOException|NullPointerException error){
                error.printStackTrace();
            }
        }
        return objectInserted;
    }


/////////// INSERT OBJECT DELETED IN FILE //////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * @param object
     * @param path
     * @param <T>
     * @return
     */

    public <T> boolean insertObjectDeletedInFile(T object, String path){
        boolean objectInserted = false;
        FileWriter fw = null;
        BufferedWriter bw = null;

        try {
            fw = new FileWriter(path,true);
            bw = new BufferedWriter(fw);
            bw.write(object.toString()+"#D");
            bw.newLine();
            bw.flush();
            objectInserted = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if ( fw != null ) {
                    fw.close();
                }
                if ( bw != null ){
                    bw.close();
                }

            }catch (IOException|NullPointerException error){
                error.printStackTrace();
            }
        }
        return objectInserted;
    }


/////////// INSERT OBJECT MODIFIED IN FILE //////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * @param object
     * @param path
     * @param <T>
     * @return
     */

    public <T> boolean insertObjectModifiedInFile(T object, String path){
        boolean objectInserted = false;
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter(path,true);
            bw = new BufferedWriter(fw);
            fw.write(object.toString()+"#M"+"\n");
            bw.newLine();
            fw.flush();
            objectInserted = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if ( fw != null ) {
                    fw.close();
                }
                if ( bw != null ){
                    bw.close();
                }
            }catch (IOException|NullPointerException error){
                error.printStackTrace();
            }
        }
        return objectInserted;
    }


/////////// GET ORDERS NOT SHIPPED //////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * @param path
     * @return
     */

    public ArrayList<Order> getOrdersNotShipped(String path){
        ArrayList<Order> ordersNotShipped = new ArrayList<>();
        BufferedReader br = null;
        String line;
        Order orderRead;
        try {
            br = new BufferedReader(new FileReader(path));
            line = br.readLine();
            while (line != null){
                orderRead = Order.stringToOrder(line);
                if (!orderRead.getSent() && !orderRead.getCancel()){
                    ordersNotShipped.add(Order.stringToOrder(line));
                }
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ordersNotShipped;
    }



/////////// GET PRODUCT FROM FILE //////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * @param ID
     * @param path
     * @return
     */


    public Product getProductFromFile(int ID, String path){
        Product newProduct = null;
        BufferedReader br = null;
        String[] lineParted;
        String line;

        try {
            br = new BufferedReader(new FileReader(path));
            line = br.readLine();
            while (line != null && newProduct == null){
                lineParted = line.split("#");
                if (Integer.parseInt(lineParted[0]) == ID){
                    newProduct = new Product(Integer.parseInt(lineParted[0]),lineParted[1],lineParted[2],Double.parseDouble(lineParted[3]));
                }
                line = br.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return newProduct;
    }


/////////// PRINT PERSONAL DATA //////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @param DNIEmployee
     * @param path
     */


    public void printEmployeePersonalData(String DNIEmployee, String path) {
        BufferedReader br = null;
        String[] lineParted = null;
        String line;
        boolean employeeFind = false;

        try {
            br = new BufferedReader(new FileReader(path));
            line = br.readLine();
            while (line != null && !employeeFind) {
                lineParted = line.split("#");
                employeeFind = (lineParted[2].equals(DNIEmployee));
                line = br.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (employeeFind) {
            System.out.println("Name of employee: " + lineParted[0]);
            System.out.println("Suname of employee: " + lineParted[1]);
            System.out.println("DNI of employee: " + lineParted[2]);
            System.out.println("Number of SS of employee: " + lineParted[3]);
            System.out.println("Birthday of employee: " + lineParted[4]);
            System.out.println("Position of employee: " + lineParted[5]);
            System.out.println("Category of employee: " + lineParted[6]);
            System.out.println("Number of bank account of employee: " + lineParted[7]);
        } else {
            System.out.println("This employee wasn`t found");
        }
    }


/////////// MODIFY SALARY //////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Este metodo crea el nuevo objeto modificado y lo inserta en el archivo temporal
     *
     * @param path
     * @param dNI
     * @param salary
     */

    public Payslip insertSalary(String path, String dNI, double salary){

        Utils u = new Utils();

        String line;
        String contenido;
        Employee employee;
        Payslip payslip = null;
        String[] separaciones;
        GregorianCalendar birthday;
        boolean salir = false;

        FileReader fr = null;
        BufferedReader br = null;


        try{

            fr = new FileReader(path);
            br = new BufferedReader(fr);

            line = br.readLine();

            while (line != null && !salir) {
                separaciones = line.split("#");
                contenido = separaciones[2];  //DNI se encuentra en la posición 2

                //Si contenido es igual a nuestro DNI, creamos un objeto empleado con los valores recogidos
                if( contenido.equals(dNI) ){

                    birthday = u.createVariableGregorianCalendar(separaciones);

                    employee = new Employee(separaciones[0],separaciones[1],separaciones[2],separaciones[3],birthday,EnumPosition.valueOf(separaciones[5]),EnumCategory.valueOf(separaciones[6]),separaciones[7],separaciones[8]);

                    //Creamos un objeto payslip y hacemos setters para el salario y para nuestro empleado
                    payslip = new Payslip();
                    payslip.setSalary(salary);
                    payslip.setEmployee(employee);

                    salir = true;

                }

                line = br.readLine();
            }

        }catch (IOException e){
            e.printStackTrace();
        }
        finally {
            try {
                if (fr != null){
                    fr.close();
                }

                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return payslip;

    }




////////// TERMINATE AN EMPLOYEE ///////////////////////////////////////////////////////////////////////////////////////

    /**
     * Introduce en nuestro archivo temporal el empleado pasado por parametros que será dado de baja
     *
     * @param path
     * @param dNI
     */

    public Employee getSelectedEmployee(String path, String dNI){

        Utils u = new Utils();

        String line;
        String contenido;
        Employee employee = null;
        String[] separaciones;
        GregorianCalendar birthday;
        boolean salir = false;

        FileReader fr = null;
        BufferedReader br = null;

        try{

            fr = new FileReader(path);
            br = new BufferedReader(fr);

            line = br.readLine();


            while (line != null && !salir) {

                separaciones = line.split("#");
                contenido = separaciones[2];  //DNI se encuentra en la posición 2

                //Si contenido es igual a nuestro DNI, creamos un objeto empleado con los valores recogidos
                if( contenido.equals(dNI) ){

                    //Separamos el String de la fecha en sus distintos numeros
                    birthday = u.createVariableGregorianCalendar(separaciones);

                    employee = new Employee(separaciones[0],separaciones[1],separaciones[2],separaciones[3],birthday,EnumPosition.valueOf(separaciones[5]),EnumCategory.valueOf(separaciones[6]),separaciones[7],separaciones[8]);

                    salir = true;
                }

                line = br.readLine();
            }


        }catch (IOException e){
            e.printStackTrace();
        }

        finally {
            try {
                if (fr != null){
                    fr.close();
                }

                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return employee;

    }




/////////// INSERT NEW SCHEDULE ////////////////////////////////////////////////////////////////////////////////////////

    //TODO cambiar nombre método y tipo devuelto, no puede insertar en el archivo en este método

    /**
     * Este metodo crea el nuevo objeto modificado y lo inserta en el archivo temporal
     *
     * @param path
     * @param dNI
     * @param tempPath
     */

    public void insertEmployeeOnSchedule(String path, String dNI, String tempPath, Schedule[] schedule){

        Utils u = new Utils();

        String line;
        String contenido;
        Employee employee;
        Schedule[] newSchedule = new Schedule[schedule.length];
        String[] separaciones;
        GregorianCalendar birthday;
        boolean salir = false;

        FileReader fr = null;
        BufferedReader br = null;


        try{

            fr = new FileReader(path);
            br = new BufferedReader(fr);

            line = br.readLine();

            while (line != null) {
                separaciones = line.split("#");
                contenido = separaciones[3];  //DNI se encuentra en la posición 3

                //Si contenido es igual a nuestro DNI, creamos un objeto empleado con los valores recogidos
                if( contenido.equals(dNI) && !salir ){

                    birthday = u.createVariableGregorianCalendar(separaciones);

                    employee = new Employee(separaciones[0],separaciones[1],separaciones[2],separaciones[3],birthday,EnumPosition.valueOf(separaciones[5]),EnumCategory.valueOf(separaciones[6]),separaciones[7],separaciones[8]);


                    for(int cont = 0; cont < schedule.length; cont++) {

                        newSchedule[cont] = new Schedule(schedule[cont].getWeekDay(), schedule[cont].getStartDate(), schedule[cont].getEndDate(), employee);

                        //Una vez hecho esto, metemos nuestro objeto schedule en el archivo temporal
                        //En este caso, como hay que recorrer el array, lo insertamos directamente desde este método
                        insertObjectModifiedInFile(newSchedule[cont], tempPath);

                    }

                    salir = true;

                }

                line = br.readLine();
            }

        }catch (IOException e){
            e.printStackTrace();
        }
        finally {
            try {
                if (fr != null){
                    fr.close();
                }

                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

/////////// SHOW FILE DATA //////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Muestra el contenido de un archivo
     *
     * @param path
     */

    public void showFileData(String path){

        String line;

        FileReader fr = null;
        BufferedReader br = null;

        try{

            fr = new FileReader(path);
            br = new BufferedReader(fr);

            line = br.readLine();

            while (line != null) {
                System.out.println(line);
                line = br.readLine();
            }

        }catch (IOException e){
            e.printStackTrace();
        }
        finally {
            try {
                if (fr != null){
                    fr.close();
                }

                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }



/////////// GET SCHEDULE FROM FILE /////////////////////////////////////////////////////////////////////////////////////


    public void printScheduleFromFile(String path, String dNI){

        String line;
        String contenido;
        String[] separaciones;
        boolean impreso = false;

        FileReader fr = null;
        BufferedReader br = null;


        try{

            fr = new FileReader(path);
            br = new BufferedReader(fr);

            line = br.readLine();

            while (line != null) {
                separaciones = line.split("#");
                contenido = separaciones[0];  //DNI se encuentra en la posición 0

                //Si contenido es igual a nuestro DNI
                if( contenido.equals(dNI)){

                    System.out.println("Employee DNI: "+separaciones[0]);
                    System.out.println("WeekDay: "+separaciones[1]);
                    System.out.println("Start date: "+separaciones[2]);
                    System.out.println("SD Time: "+separaciones[3]);
                    System.out.println("End date "+separaciones[4]);
                    System.out.println("ED Time: "+separaciones[5]);
                    System.out.println();

                    impreso = true;

                }

                line = br.readLine();

            }

            if (!impreso){
                System.out.println("Employee was not found");
            }

        }catch (IOException e){
            e.printStackTrace();
        }
        finally {
            try {
                if (fr != null){
                    fr.close();
                }

                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


}
