package Management;

import BasicsClasses.Employee.Employee;
import BasicsClasses.Employee.Enums.EnumCategory;
import BasicsClasses.Employee.Enums.EnumPosition;
import BasicsClasses.Employee.Payslip;
import BasicsClasses.Employee.Schedule;
import BasicsClasses.Orders.Order;
import BasicsClasses.Orders.OrderLine;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class DatabaseManagement {

    /**
     * @param username
     * @param password
     * @param connection
     * @return
     */


    public String getPermisons(String username, String password, Connection connection){

        String category = null;
        Statement sentence = null;
        ResultSet employees = null;

        String select = "SELECT Category " +
                "FROM Employees " +
                "WHERE DNI = '"+username+"' " +
                "AND Password = '"+password+"'"+
                "AND Active = 1";

        try {
            sentence = connection.createStatement();
            employees = sentence.executeQuery(select);
            while (employees.next()){
                category = employees.getString("Category");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            employees.close();
            sentence.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return category;
    }


    /**
     * @param employee
     * @param connection
     * @return
     */


    public boolean insertEmployee(Employee employee, Connection connection){

        boolean succes = false;
        Statement sentence = null;

        String select = "INSERT INTO Employees (DNI, Password, Name, Surname, NAF, Birthday, Position, Category, BankAccount, Active)" +
                "values ('"+employee.getDNI()+"', '"+employee.getPassword()+"', '"+employee.getName()+"', '"+employee.getSurname()+"', '"+employee.getnAF()+"', " +
                "'"+employee.getBirthday().get(Calendar.MONTH)+"/"+employee.getBirthday().get(Calendar.DAY_OF_MONTH)+"/"+employee.getBirthday().get(Calendar.YEAR)+"', " +
                "'"+employee.getPosition()+"', '"+employee.getCategory()+"', '"+employee.getBankAccountN()+"',1)";

        try {
            sentence = connection.createStatement();
            succes = (sentence.executeUpdate(select) > 0);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            sentence.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return succes;
    }


    /**
     * @param employee
     * @param connection
     * @return
     */


    public boolean deleteEmployee(Employee employee, Connection connection){

        boolean succes = false;
        Statement sentence = null;

        String update = "UPDATE Employees " +
                        "SET Active = 0 " +
                        "WHERE DNI = '"+employee.getDNI()+"'";

        try {
            sentence = connection.createStatement();
            succes = (sentence.executeUpdate(update) > 0);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        finally {
            try {
                sentence.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return succes;
    }


    /**
     * @param connection
     * @return
     */


    public ArrayList<Order> getOrdersNotShipped(Connection connection){
        ArrayList<Order> ordersNotShipped = new ArrayList<>();

        Statement sentence = null;
        ResultSet orders = null;
        GregorianCalendar date;
        String dateString;
        int day, month, year;
        boolean sent, cancel;
        Order orderRead;

        String select = "SELECT ID,DateOrder,[Sent],Cancel " +
                        "FROM Orders";

        try {
            sentence = connection.createStatement();
            orders = sentence.executeQuery(select);
            while (orders.next()){
                dateString = orders.getString("DateOrder");
                day = Integer.parseInt(dateString.split("-")[2].substring(0,2));
                month =Integer.parseInt(dateString.split("-")[1]);
                year = Integer.parseInt(dateString.split("-")[0]);
                date = new GregorianCalendar(year,month,day);

                sent = orders.getBoolean("Sent");
                cancel = orders.getBoolean("Cancel");

                orderRead = new Order(orders.getInt("ID"),date,sent,cancel);

                if (!orderRead.getSent() && !orderRead.getCancel()){
                    ordersNotShipped.add(orderRead);
                }

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        finally {
            try {
                orders.close();
                sentence.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return ordersNotShipped;
    }


    /**
     * @param order
     * @param orderLine
     * @param connection
     * @return
     */


    public boolean insertNewOrderLine(Order order, OrderLine orderLine, Connection connection){

        boolean succes = false;
        Statement sentence = null;

        String select = "INSERT INTO OrdersLines(IDOrder,IDProduct,Quantity) " +
                        "VALUES("+order.getID()+", "+orderLine.getIDProduct()+", "+orderLine.getProductQuantity()+")";

        try {
            sentence = connection.createStatement();
            succes = (sentence.executeUpdate(select) > 0);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        finally {
            try {
                sentence.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return succes;
    }


    public Employee getEmployeeByDNI(String DNI, Connection connection){
        Employee employeeExtract = null;

        Statement sentence = null;
        ResultSet employees = null;

        GregorianCalendar date;
        String dateString;
        int day, month, year;

        String select = "SELECT DNI, [Password], [Name], [Surname], NAF, Birthday, Position, Category, BankAccount " +
                        "FROM Employees " +
                        "WHERE DNI = '"+DNI+"'";

        try {
            sentence = connection.createStatement();
            employees = sentence.executeQuery(select);
            employees.next();

            dateString = employees.getString("Birthday");
            day = Integer.parseInt(dateString.split("-")[2].substring(0,2));
            month =Integer.parseInt(dateString.split("-")[1]);
            year = Integer.parseInt(dateString.split("-")[0]);
            date = new GregorianCalendar(year,month,day);

            employeeExtract = new Employee(employees.getString("Name"), employees.getString("Surname"), employees.getString("DNI"),
                                        employees.getString("NAF"), date, EnumPosition.valueOf(employees.getString("Position")), EnumCategory.valueOf(employees.getString("Category")),
                                        employees.getString("BankAccount"), employees.getString("Password"));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        finally {
            try {
                employees.close();
                sentence.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return employeeExtract;
    }


    public boolean insertSchedule(Schedule schedule, String DNI, Connection connection){

        boolean succes = false;
        Statement sentence = null;
        String startDate, endDate;
        int day, month, year;
        GregorianCalendar date;

        date = schedule.getStartDate();
        day = date.get(Calendar.DAY_OF_MONTH);
        month = date.get(Calendar.MONTH);
        year = date.get(Calendar.YEAR);

        startDate = month+"/"+day+"/"+year;

        date = schedule.getEndDate();
        day = date.get(Calendar.DAY_OF_MONTH);
        month = date.get(Calendar.MONTH);
        year = date.get(Calendar.YEAR);

        endDate = month+"/"+day+"/"+year;

        String select = "INSERT INTO Schedules (DNIEmployee, [WeekDay], StartDate, EndDate) " +
                        "VALUES ('"+DNI+"','"+schedule.getWeekDay()+"','"+startDate+"','"+endDate+"')";

        try {
            sentence = connection.createStatement();
            succes = (sentence.executeUpdate(select) > 0);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        finally {
            try {
                sentence.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return succes;
    }

    public boolean insertSchedules(Schedule[] schedule, String DNI, Connection connection){

        boolean succes = false;
        Statement sentence = null;
        String startDate, endDate;
        int day, month, year;
        GregorianCalendar date;

        String select = "INSERT INTO Schedules (DNIEmployee, [WeekDay], StartDate, EndDate) VALUES";

        for (int i = 0; i < schedule.length; i++){

            date = schedule[i].getStartDate();
            day = date.get(Calendar.DAY_OF_MONTH);
            month = date.get(Calendar.MONTH);
            year = date.get(Calendar.YEAR);

            startDate = month+"/"+day+"/"+year;

            date = schedule[i].getEndDate();
            day = date.get(Calendar.DAY_OF_MONTH);
            month = date.get(Calendar.MONTH);
            year = date.get(Calendar.YEAR);

            endDate = month+"/"+day+"/"+year;

            if (i == schedule.length-1){
                select+= " ('"+DNI+"','"+schedule[i].getWeekDay()+"','"+startDate+"','"+endDate+"')";
            }else{
                select+= " ('"+DNI+"','"+schedule[i].getWeekDay()+"','"+startDate+"','"+endDate+"'),";
            }
        }

        try {
            sentence = connection.createStatement();
            succes = (sentence.executeUpdate(select) > 0);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        finally {
            try {
                sentence.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return succes;
    }


    public boolean modifySalary(Payslip payslip, Connection connection){

        boolean succes = false;
        Statement sentence = null;

        String update = "UPDATE Payslips " +
                        "SET Salary = "+payslip.getSalary()+" "+
                        "WHERE DNIEmployee = '"+payslip.getEmployee().getDNI()+"'";

        try {
            sentence = connection.createStatement();
            succes = (sentence.executeUpdate(update) > 0);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            sentence.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return succes;
    }

}
