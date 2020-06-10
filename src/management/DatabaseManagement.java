package management;

import basicsClasses.employee.Employee;
import basicsClasses.employee.Enums.EnumCategory;
import basicsClasses.employee.Enums.EnumPosition;
import basicsClasses.employee.Enums.EnumWeekDays;
import basicsClasses.employee.Payslip;
import basicsClasses.employee.Schedule;
import basicsClasses.foodstuffDrinks.Product;
import basicsClasses.orders.Order;
import basicsClasses.orders.OrderLine;


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

        String insert = "INSERT INTO Employees (DNI, Password, Name, Surname, NAF, Birthday, Position, Category, BankAccount, Active)" +
                "values ('"+employee.getDNI()+"', '"+employee.getPassword()+"', '"+employee.getName()+"', '"+employee.getSurname()+"', '"+employee.getnAF()+"', " +
                "'"+employee.getBirthday().get(Calendar.MONTH)+"/"+employee.getBirthday().get(Calendar.DAY_OF_MONTH)+"/"+employee.getBirthday().get(Calendar.YEAR)+"', " +
                "'"+employee.getPosition()+"', '"+employee.getCategory()+"', '"+employee.getBankAccountN()+"',1)";

        try {
            sentence = connection.createStatement();
            succes = (sentence.executeUpdate(insert) > 0);
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
     * @param order
     * @param connection
     * @return
     */


    public boolean insertNewOrder(String DNIEmployee, Order order, Connection connection){

        boolean succes = false;
        Statement sentence = null;
        ResultSet ID;
        int IDOrder = 0;

        String dateOrder = order.getMonthOfDateOrder()+"-"+order.getDayOfDateOrder()+"-"+order.getYearOfDateOrder();

        String insert = "INSERT INTO Orders (DNIEmployee, DateOrder, Sent, Cancel) " +
                        "VALUES ('"+DNIEmployee+"','"+dateOrder+"',0,0)";

        try {
            sentence = connection.createStatement();
            succes = (sentence.executeUpdate(insert) > 0);
            ID = sentence.executeQuery("SELECT @@IDENTITY AS ID");
            while (ID.next()){
                IDOrder = ID.getInt("ID");
            }
            if (succes){

                insert = "INSERT INTO OrdersLines(IDOrder, IDProduct, Quantity) VALUES ";

                for (int i = 0; i < order.getOrdersLines().size(); i++){

                    if (i == order.getOrdersLines().size()-1){
                        insert+= " ("+IDOrder+","+order.getOrderLineIndex(i).getIDProduct()+","+order.getOrderLineIndex(i).getProductQuantity()+")";
                    }else{
                        insert+= " ("+IDOrder+","+order.getOrderLineIndex(i).getIDProduct()+","+order.getOrderLineIndex(i).getProductQuantity()+"), ";
                    }
                }

                succes = (sentence.executeUpdate(insert) > 0);

            }
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
        ArrayList<OrderLine> ordersLines;

        String select = "SELECT ID,DateOrder,[Sent],Cancel " +
                        "FROM Orders " +
                        "WHERE Sent = 0 AND Cancel = 0";

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

                ordersLines = getOrderLineByIDOrder(orders.getInt("ID"), connection);

                orderRead = new Order(orders.getInt("ID") ,ordersLines ,date ,sent ,cancel);

                ordersNotShipped.add(orderRead);

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


    public ArrayList<OrderLine> getOrderLineByIDOrder(int IDOrder, Connection connection){
        ArrayList<OrderLine> ordersLines = new ArrayList<>();

        Statement sentence = null;
        ResultSet ordersLinesRead = null;
        OrderLine orderLineRead;
        Product productRead;
        int quantity;

        String select = "SELECT IDOrder, IDProduct, Quantity " +
                        "FROM OrdersLines " +
                        "WHERE IDOrder = "+IDOrder;

        try {

            sentence = connection.createStatement();
            ordersLinesRead = sentence.executeQuery(select);
            while (ordersLinesRead.next()){

                productRead = getProductByID(ordersLinesRead.getInt("IDProduct"), connection);

                quantity = ordersLinesRead.getInt("Quantity");

                orderLineRead = new OrderLine(productRead,quantity);

                ordersLines.add(orderLineRead);

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        finally {
            try {
                ordersLinesRead.close();
                sentence.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return ordersLines;
    }


    /**
     * @param IDProduct
     * @param connection
     * @return
     */


    public Product getProductByID(int IDProduct, Connection connection){
        Product productReturn = null;

        Statement sentence = null;
        ResultSet products = null;

        String select = "SELECT ID, Name, Characteristics ,Price " +
                        "FROM Products " +
                        "WHERE ID = "+IDProduct;

        try {
            sentence = connection.createStatement();
            products = sentence.executeQuery(select);
            while (products.next()){
                productReturn = new Product(products.getInt("ID"),products.getString("Name"),products.getString("Characteristics"),products.getInt("Price"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        finally {
            try {
                products.close();
                sentence.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return productReturn;
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


    /**
     * @param order
     * @param IDProductToDelete
     * @param connection
     * @return
     */


    public boolean deleteOrderLine(Order order, int IDProductToDelete, Connection connection){

        boolean succes = false;
        Statement sentence = null;

        String delete = "DELETE FROM OrdersLines " +
                        "WHERE IDOrder = "+order.getID()+" AND IDProduct ="+IDProductToDelete;

        try {
            sentence = connection.createStatement();
            succes = (sentence.executeUpdate(delete) > 0);
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
     * @param DNI
     * @param connection
     * @return
     */


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


    /**
     * @param schedule
     * @param DNI
     * @param connection
     * @return
     */


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

        String insert = "INSERT INTO Schedules (DNIEmployee, [WeekDay], StartDate, EndDate) " +
                        "VALUES ('"+DNI+"','"+schedule.getWeekDay()+"','"+startDate+"','"+endDate+"')";

        try {
            sentence = connection.createStatement();
            succes = (sentence.executeUpdate(insert) > 0);
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
     * @param schedule
     * @param DNI
     * @param connection
     * @return
     */


    public boolean insertSchedules(Schedule[] schedule, String DNI, Connection connection){

        boolean succes = false;
        Statement sentence = null;
        String startDate, endDate;
        int day, month, year;
        GregorianCalendar date;

        String insert = "INSERT INTO Schedules (DNIEmployee, [WeekDay], StartDate, EndDate) VALUES";

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
                insert+= " ('"+DNI+"','"+schedule[i].getWeekDay()+"','"+startDate+"','"+endDate+"')";
            }else{
                insert+= " ('"+DNI+"','"+schedule[i].getWeekDay()+"','"+startDate+"','"+endDate+"'),";
            }
        }

        try {
            sentence = connection.createStatement();
            succes = (sentence.executeUpdate(insert) > 0);
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
     * @param payslip
     * @param connection
     * @return
     */


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

    public boolean decreaseAmountProductOrderLine(Order orderChoosed, int IDProductToDecrease, int amountToDecrease, Connection connection){

        boolean succes = false;
        Statement sentence = null;

        String update = "UPDATE OrdersLines " +
                        "SET Quantity -= "+amountToDecrease +
                        " WHERE IDOrder = "+orderChoosed.getID()+" AND IDProduct = "+IDProductToDecrease;

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


    /**
     * @param orderChoosed
     * @param IDProductToDecrease
     * @param amountToDecrease
     * @param connection
     * @return
     */


    public boolean increaseAmountProductOrderLine(Order orderChoosed, int IDProductToDecrease, int amountToDecrease, Connection connection){

        boolean succes = false;
        Statement sentence = null;

        String update = "UPDATE OrdersLines " +
                        "SET Quantity += "+amountToDecrease +
                        " WHERE IDOrder = "+orderChoosed.getID()+" AND IDProduct = "+IDProductToDecrease;

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


    /**
     * @param orderChoosed
     * @param connection
     * @return
     */


    public boolean markCancelProduct(Order orderChoosed, Connection connection){

        boolean succes = false;
        Statement sentence = null;

        String update = "UPDATE Orders " +
                        "SET Cancel = 1 " +
                        "WHERE ID = "+orderChoosed.getID();

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


    public ArrayList<Schedule> getEmployeeSchedules(String DNIEmployee, Connection connection){

        ArrayList<Schedule> schedules = new ArrayList<>();

        Statement sentence = null;
        ResultSet schedulesRead = null;

        String lineDate, weekDay;
        int day ,month ,year;
        GregorianCalendar startDate;
        GregorianCalendar endDate;

        String select = "SELECT WeekDay, StartDate, EndDate " +
                        "FROM Schedules " +
                        "WHERE YEAR(StartDate) = YEAR(CURRENT_TIMESTAMP) " +
                        "AND MONTH(StartDate) = MONTH(CURRENT_TIMESTAMP) " +
                        "AND DNIEmployee = '"+DNIEmployee+"'";

        try {
            sentence = connection.createStatement();
            schedulesRead = sentence.executeQuery(select);
            while (schedulesRead.next()){

                lineDate = schedulesRead.getString("StartDate");
                day = Integer.parseInt(lineDate.split("-")[2].substring(0,2));
                month =Integer.parseInt(lineDate.split("-")[1]);
                year = Integer.parseInt(lineDate.split("-")[0]);
                startDate = new GregorianCalendar(year,month,day);

                lineDate = schedulesRead.getString("EndDate");
                day = Integer.parseInt(lineDate.split("-")[2].substring(0,2));
                month =Integer.parseInt(lineDate.split("-")[1]);
                year = Integer.parseInt(lineDate.split("-")[0]);
                endDate = new GregorianCalendar(year,month,day);

                weekDay = schedulesRead.getString("WeekDay");

                schedules.add(new Schedule(EnumWeekDays.valueOf(weekDay), startDate, endDate));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            sentence.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return schedules;
    }

}
