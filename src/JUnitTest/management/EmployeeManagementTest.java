package JUnitTest.management;


import Management.EmployeeManagement;

class EmployeeManagementTest {

    public static void main(String [] args) {

        EmployeeManagement em = new EmployeeManagement();

        String ruta = ".\\src\\Files\\Employees";
        String username = "00000000T";
        String password = "e807f1fcf82d132f9bb018ca6738a19f";

        System.out.println("em.getPermisions(): " + em.getPermisons(username,password,ruta));

        System.out.println("\n-----------------------------------------------------------------------\n");

        System.out.println("em.collectEmployeeData(): "+em.collectEmployeeData());


    }

}