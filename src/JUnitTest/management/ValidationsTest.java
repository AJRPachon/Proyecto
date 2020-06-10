package JUnitTest.management;

import Management.Validations;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidationsTest {

    public static void main(String [] args){

        Validations va = new Validations();

        String pathOrders = ".\\src\\files\\Orders";

        System.out.println("va.wantContinue: "+va.wantContinue());

        System.out.println("\n-----------------------------------------------------------------------\n");

        System.out.println("va.checkUsername: "+va.checkUsername("53350963W"));

        System.out.println("\n-----------------------------------------------------------------------\n");

        System.out.println("va.readAndValidateUsername");

        System.out.println("\n-----------------------------------------------------------------------\n");

        System.out.println("va.readAndValidatePassword: "+va.readAndValidatePassword());

        System.out.println("\n-----------------------------------------------------------------------\n");

        System.out.println("va.va.readAndValidateOptionsStaff: "+va.readAndValidateOptionsStaff());

        System.out.println("\n-----------------------------------------------------------------------\n");

        System.out.println("va.readAndValidateQuantityOfProduct: "+va.readAndValidateQuantityOfProduct());

        System.out.println("\n-----------------------------------------------------------------------\n");

        //System.out.println("va.chooseOrderByID: "+va.chooseOrderByID());

        //System.out.println("\n-----------------------------------------------------------------------\n");

        System.out.println("va.readAndValidateNewOrderLine(): "+va.readAndValidateNewOrderLine(pathOrders));

        System.out.println("\n-----------------------------------------------------------------------\n");

        //System.out.println("va.readAndValidateIDProductOfOrder(): "+va.readAndValidateIDProductOfOrder());

        //System.out.println("\n-----------------------------------------------------------------------\n");

        System.out.println("va.readAndValidateQuantityToDecrease: "+va.readAndValidateQuantityToDecrease(2));

        System.out.println("\n-----------------------------------------------------------------------\n");

        System.out.println("va.readAndValidateQuantityToIncrease: "+va.readAndValidateQuantityToIncrease());

        System.out.println("\n-----------------------------------------------------------------------\n");

        System.out.println("va.readAndValidateSalary: "+va.readAndValidateSalary());

        System.out.println("\n-----------------------------------------------------------------------\n");

        System.out.println("va.readAndValidateHour: "+va.readAndValidateHour());

        System.out.println("\n-----------------------------------------------------------------------\n");

        System.out.println("va.readAndValidateMinute: "+va.readAndValidateMinute());

        System.out.println("\n-----------------------------------------------------------------------\n");

        System.out.println("va.readAndValidateDay: "+va.readAndValidateDay());

        System.out.println("\n-----------------------------------------------------------------------\n");

        System.out.println("va.readAndValidateMonth: "+va.readAndValidateMonth());

        System.out.println("\n-----------------------------------------------------------------------\n");

        System.out.println("va.readAndValidatePosition: "+va.readAndValidatePosition());

        System.out.println("\n-----------------------------------------------------------------------\n");

        System.out.println("va.readAndValidateCategory: "+va.readAndValidateCategory());

        System.out.println("\n-----------------------------------------------------------------------\n");


    }
}