package Interfaces;

/*
 * Properties:
 *  - Basics:
 *      > nameRestaurant: string, Consulting, Modifiable
 *      > dateOfReceipt: date, Consulting, Modifiable
 *      > consumables: list of Consumables, Consulting, Modifiable
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
 *      > Consumable getConsumable(integer indexOfConsumable);
 *      > none setConsumable(integer indexOfConsumable, Consumable consumable);
 *      > none addConsumable(Consumable consumable);
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

import java.util.GregorianCalendar;

public interface IRecept {

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

    public Consumable getConsumable(int indexOfConsumable);
    public void setConsumable(int indexOfConsumable, Consumable consumable);
    public void addConsumable(Consumable consumable);

    public boolean getPaidFor();
    public void setPaidFor(boolean paidFor);
    public void paidFor();
    public void notPaidFor();

    public double getTotalPrice();


}
