package BasicsClasses.Interfaces;

/*
 * Properties:
 *  - Basics:
 *      > nameRestaurant: string, Consulting, Modifiable
 *      > dateOfReceipt: date, Consulting, Modifiable
 *      > consumables: list of ConsumablesTemp, Consulting, Modifiable
 *      > paidFor: boolean, Consulting, Modifiable
 *
 *  - Derivatives:
 *      > double totalPrice, Consulting
 *
 *  - Shared:
 *      > None
 *
 * Methods:
 *  - Basics:
 *      > string getNameRestaurant();
 *      > none setNameRestaurant(string nameRestaurant);
 *
 *      > date getDateOfReceipt();
 *          > integer getDayOfDateOfReceipt();
 *          > integer getMonthOfDateOfReceipt();
 *          > integer getYearOfDateOfReceipt();
 *      > none setDateOfReceipt(date dateOfReceipt);
 *          > none setDayOfDateOfReceipt(integer day);
 *          > none setMonthOfDateOfReceipt(integer month);
 *          > none setYearOfDateOfReceipt(integer year);
 *
 *      > ConsumablesTemp getConsumable(integer indexOfConsumable);
 *      > none setConsumable(integer indexOfConsumable, ConsumablesTemp consumable);
 *      > none addConsumable(ConsumablesTemp consumable);
 *
 *      > boolean getPaidFor();
 *      > none setPaidFor(boolean paidFor);
 *      > none paidFor();
 *      > none notPaidFor();
 *
 *      > double getTotalPrice();
 *
 *  - Added:
 *      > None
 *
 */

import BasicsClasses.FoodstuffDrinks.Consumable;
import BasicsClasses.FoodstuffDrinks.Enums.EnumAllergies;
import BasicsClasses.ReceiptLine.ReceiptLine;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public interface IReceipt {

    public String getNameRestaurant();
    public void setNameRestaurant(String nameRestaurant);

    public GregorianCalendar getDateOfReceipt();
        public int getDayOfDateOfReceipt();
        public int getMonthOfDateOfReceipt();
        public int getYearOfDateOfReceipt();
    public void setDateOfReceipt(GregorianCalendar dateOfReceipt);
        public void setDayOfDateOfReceipt(int day);
        public void setMonthOfDateOfReceipt(int month);
        public void setYearOfDateOfReceipt(int year);


    public ReceiptLine getLineReceipt(int index);
        public int getIDConsumable(int IDConsumable);
        public String getNameConsumable(int IDConsumable);
        public String getDescriptionConsumable(int IDConsumable);
        public double getPriceConsumable(int IDConsumable);
        public ArrayList<EnumAllergies> getAllergiesConsumable(int IDConsumable);
    public int getQuantityConsumable(int index);

    public void addConsumable(Consumable consumable);
    public void addConsumable(Consumable consumable, int quantity);

    public boolean getPaidFor();
    public void setPaidFor(boolean paidFor);
    public void paidFor();
    public void notPaidFor();

    public double getTotalPrice();


}
