package Management;

import BasicsClasses.Employee.Employee;
import BasicsClasses.Employee.Enums.EnumCategory;
import BasicsClasses.Employee.Enums.EnumPosition;
import java.io.*;

import BasicsClasses.Employee.Payslip;
import BasicsClasses.Employee.Schedule;
import BasicsClasses.FoodstuffDrinks.Product;
import BasicsClasses.Orders.Order;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;



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

            BufferedWriter BW = null;
            try {
                file.createNewFile();

                BW = new BufferedWriter(new FileWriter(file));
                BW.write(new Employee("Administrator","Administrator","00000000T","281234567840",new GregorianCalendar(),EnumPosition.Manager,EnumCategory.Administrator,"ES3231906288456991923866","e807f1fcf82d132f9bb018ca6738a19f").toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    BW.close();
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
        FileWriter FW = null;

        try {
            FW = new FileWriter(path,true);
            FW.write(object.toString()+"\n");
            FW.flush();
            objectInserted = true;

        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                FW.close();
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
        FileWriter FW = null;
        try {
            FW = new FileWriter(path,true);
            FW.write(object.toString()+"#D"+"\n");
            FW.flush();
            objectInserted = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                FW.close();
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
        FileWriter FW = null;
        try {
            FW = new FileWriter(path,true);
            FW.write(object.toString()+"#M"+"\n");
            FW.flush();
            objectInserted = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                FW.close();
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
        BufferedReader BR = null;
        String line;
        Order orderRead;
        try {
            BR = new BufferedReader(new FileReader(path));
            line = BR.readLine();
            while (line != null){
                orderRead = Order.stringToOrder(line);
                if (!orderRead.getSent() && !orderRead.getCancel()){
                    ordersNotShipped.add(Order.stringToOrder(line));
                }
                line = BR.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                BR.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ordersNotShipped;
    }


/////////// READ AND SEARCH PRODUCT //////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * @return
     */

    public Product readAndSearchProduct(){
        Scanner sc = new Scanner(System.in);

        int ID;
        String pathProductFile = ".\\src\\Files\\Products";
        Product productGet;

        //Validate Product
        do {
            System.out.print("Insert IDProduct: ");
            ID = sc.nextInt();
            productGet = getProductFromFile(ID,pathProductFile);
            if (productGet == null){
                System.out.println("This product don't exist. Please insert a product existing");
            }
        }while (productGet == null);

        return productGet;

    }


/////////// GET PRODUCT FROM FILE //////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * @param ID
     * @param path
     * @return
     */


    public Product getProductFromFile(int ID, String path){
        Product newProduct = null;
        BufferedReader BR;
        String[] lineParted;
        String line;

        try {
            BR = new BufferedReader(new FileReader(path));
            line = BR.readLine();
            while (line != null && newProduct == null){
                lineParted = line.split("#");
                if (Integer.parseInt(lineParted[0]) == ID){
                    newProduct = new Product(Integer.parseInt(lineParted[0]),lineParted[1],lineParted[2],Double.parseDouble(lineParted[3]));
                }
                line = BR.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return newProduct;
    }


/////////// PRINT PERSONAL DATA //////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @param DNIEmployee
     * @param path
     */


    public void printPersonalData(String DNIEmployee, String path) {
        BufferedReader BR;
        String[] lineParted = null;
        String line;
        boolean employeeFind = false;

        try {
            BR = new BufferedReader(new FileReader(path));
            line = BR.readLine();
            while (line != null && !employeeFind) {
                lineParted = line.split("#");
                employeeFind = (lineParted[2].equals(DNIEmployee));
                line = BR.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
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

        String line;
        String contenido;
        Employee employee;
        Payslip payslip = null;
        String[] separaciones;
        GregorianCalendar birthday;
        boolean salir = false;

        FileReader fr;
        BufferedReader br;


        try{

            fr = new FileReader(path);
            br = new BufferedReader(fr);

            line = br.readLine();

            while (line != null && !salir) {
                separaciones = line.split("#");
                contenido = separaciones[3];  //DNI se encuentra en la posición 3

                //Si contenido es igual a nuestro DNI, creamos un objeto empleado con los valores recogidos
                if( contenido.equals(dNI) ){

                    birthday = assignBirthday(separaciones);

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

        String line;
        String contenido;
        Employee employee = null;
        String[] separaciones;
        GregorianCalendar birthday;
        boolean salir = false;

        FileReader fr;
        BufferedReader br;

        try{

            fr = new FileReader(path);
            br = new BufferedReader(fr);

            line = br.readLine();


            while (line != null && !salir) {

                separaciones = line.split("#");
                contenido = separaciones[3];  //DNI se encuentra en la posición 3

                //Si contenido es igual a nuestro DNI, creamos un objeto empleado con los valores recogidos
                if( contenido.equals(dNI) ){

                    //Separamos el String de la fecha en sus distintos numeros
                    birthday = assignBirthday(separaciones);

                    employee = new Employee(separaciones[0],separaciones[1],separaciones[2],separaciones[3],birthday,EnumPosition.valueOf(separaciones[5]),EnumCategory.valueOf(separaciones[6]),separaciones[7],separaciones[8]);

                    salir = true;
                }

                line = br.readLine();
            }


        }catch (IOException e){
            e.printStackTrace();
        }

        return employee;

    }


/////////// INSERT NEW SCHEDULE ////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Este metodo crea el nuevo objeto modificado y lo inserta en el archivo temporal
     *
     * @param path
     * @param dNI
     * @param tempPath
     */

    public void insertNewSchedule(String path, String dNI, String tempPath){

        String line;
        String contenido;
        Employee employee;
        Schedule[] schedule;
        Schedule newSchedule;
        String[] separaciones;
        GregorianCalendar birthday;

        ScheduleManagement sm =  new ScheduleManagement();

        FileReader fr;
        BufferedReader br;


        try{

            fr = new FileReader(path);
            br = new BufferedReader(fr);

            line = br.readLine();

            while (line != null) {
                separaciones = line.split("#");
                contenido = separaciones[3];  //DNI se encuentra en la posición 3

                //Si contenido es igual a nuestro DNI, creamos un objeto empleado con los valores recogidos
                if( contenido.equals(dNI) ){

                    birthday = assignBirthday(separaciones);

                    employee = new Employee(separaciones[0],separaciones[1],separaciones[2],separaciones[3],birthday,EnumPosition.valueOf(separaciones[5]),EnumCategory.valueOf(separaciones[6]),separaciones[7],separaciones[8]);

                    //Creamos un objeto schedule y le pasamos nuestro empleado
                    schedule = sm.obtenerDatosHorario();

                    for(int cont = 0; cont < schedule.length; cont++) {

                        newSchedule = new Schedule(schedule[cont].getWeekDay(), schedule[cont].getStartDate(), schedule[cont].getEndDate(), employee);

                        //Una vez hecho esto, metemos nuestro objeto schedule en el archivo temporal
                        insertObjectModifiedInFile(newSchedule, tempPath);
                    }

                }

                line = br.readLine();
            }

        }catch (IOException e){
            e.printStackTrace();
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

        FileReader fr;
        BufferedReader br;

        try{

            fr = new FileReader(path);
            br = new BufferedReader(fr);

            line = br.readLine();

            while (line != null) {
                System.out.println(line);
            }

        }catch (IOException e){
            e.printStackTrace();
        }

    }


/////////// ASSIGN BIRTHDAY //////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Convertimos una fecha String a enteros y se la asignamos a una variable de tipo GregorianCalendar
     *
     * @param separaciones
     * @return
     */

    public GregorianCalendar assignBirthday(String[] separaciones){

        String[] fechaString;
        GregorianCalendar birthday = new GregorianCalendar();
        int[] fecha = new int[3];

        //Separamos el String de la fecha en sus distintos numeros
        fechaString = separaciones[4].split("/");

        //Recorremos el array y hacemos un parseInt para obtener los enteros
        for(int cont = 0; cont < fechaString.length; cont++){
            fecha[cont] = Integer.parseInt(fechaString[cont]);
        }

        //Metemos la fecha de cumpleaños en nuestra variable tipo fecha y ya la podemos usar en nuestro constructor
        birthday.set(Calendar.DAY_OF_MONTH, fecha[0]);
        birthday.set(Calendar.MONTH, fecha[1]);
        birthday.set(Calendar.YEAR, fecha[2]);

        return birthday;

    }


/////////// DEVOLVER HORARIO //////////////////////////////////////////////////////////////////////////////////////////////
    /*
    public Schedule[] devolverHorario(String[] separaciones){

        String[] calendarioString;
        Schedule[] schedule = new Schedule[7];
        int[] enteros = new int[3];

        calendarioString = separaciones[9].split("/");

        for(int cont = 0; cont < calendarioString.length; cont++){
            enteros[cont] = Integer.parseInt(calendarioString[cont]);
            schedule[cont].set
        }



        return schedule;
    }

*/
}
