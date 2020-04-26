package BasicsClasses;

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
import Interfaces.IRecept;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Receipt implements IRecept,Cloneable,Comparable {

    private String nameRestaurant;
    private GregorianCalendar dateOfReceipt;
    private ArrayList<Consumable> consumables;
    private boolean paidFor;

    public Receipt() {
        this.nameRestaurant = "Default";
        this.dateOfReceipt = new GregorianCalendar();
        this.consumables = new ArrayList<>();
        this.paidFor = true;
    }

    public Receipt(String nameRestaurant) {
        this.nameRestaurant = nameRestaurant;
        this.dateOfReceipt = new GregorianCalendar();
        this.consumables = new ArrayList<>();
        this.paidFor = false;
    }

    public Receipt(String nameRestaurant, GregorianCalendar dateOfReceipt, ArrayList<Consumable> consumables, boolean paidFor) {
        this.nameRestaurant = nameRestaurant;
        this.dateOfReceipt = (GregorianCalendar)dateOfReceipt.clone();
        this.consumables = (ArrayList<Consumable>)consumables.clone();
        this.paidFor = paidFor;
    }

    public Receipt(Receipt other) {
        this.nameRestaurant = other.nameRestaurant;
        this.dateOfReceipt = (GregorianCalendar)other.dateOfReceipt.clone();
        this.consumables = (ArrayList<Consumable>)other.consumables.clone();
        this.paidFor = other.paidFor;
    }

    public String getNameRestaurant() {
        return nameRestaurant;
    }

    public void setNameRestaurant(String nameRestaurant) {
        this.nameRestaurant = nameRestaurant;
    }

    public GregorianCalendar getDateOfReceipt() {
        return (GregorianCalendar)dateOfReceipt.clone();
    }

    public int getDayOfDateOfReceipt() {
        return this.dateOfReceipt.get(Calendar.DAY_OF_MONTH);
    }

    public int getMonthOfDateOfReceipt() {
        return this.dateOfReceipt.get(Calendar.MONTH);
    }

    public int getYearOfDateOfReceipt() {
        return this.dateOfReceipt.get(Calendar.YEAR);
    }

    public void setDateOfReceipt(GregorianCalendar dateOfReceipt) {
        this.dateOfReceipt = (GregorianCalendar)dateOfReceipt.clone();
    }

    public void setDayOfDateOfReceipt(int day) {
        this.dateOfReceipt.set(Calendar.DAY_OF_MONTH,day);
    }

    public void setMonthOfDateOfReceipt(int month) {
        this.dateOfReceipt.set(Calendar.MONTH,month);
    }

    public void setYearOfDateOfReceipt(int year) {
        this.dateOfReceipt.set(Calendar.YEAR,year);
    }

    public Consumable getConsumable(int indexOfConsumable) {
        return this.consumables.get(indexOfConsumable);
    }

    public void setConsumable(int indexOfConsumable, Consumable consumable) {
        this.consumables.set(indexOfConsumable,consumable);
    }

    public void addConsumable(Consumable consumable) {
        this.consumables.add(consumable);
    }

    public ArrayList<Consumable> getConsumables() {
        return (ArrayList<Consumable>)consumables.clone();  //TODO CHECK THIS
    }

    public boolean getPaidFor() {
        return paidFor;
    }

    public void setPaidFor(boolean paidFor) {
        this.paidFor = paidFor;
    }

    public void paidFor() {
        this.paidFor = true;
    }

    public void notPaidFor() {
        this.paidFor = false;
    }

    public double getTotalPrice(){
        double totalOfReceipt = 0;
        for (Consumable consumable : consumables){
            totalOfReceipt += consumable.getPrice();
        }
        return totalOfReceipt;
    }

}
