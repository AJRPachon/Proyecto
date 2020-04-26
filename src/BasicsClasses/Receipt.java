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

import Interfaces.IRecept;
import java.util.ArrayList;
import java.util.GregorianCalendar;

//public class Receipt implements IRecept,Cloneable,Comparable {
public class Receipt{

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

    public ArrayList<Consumable> getConsumables() {
        return (ArrayList<Consumable>)consumables.clone();
    }

    public boolean isPaidFor() {
        return paidFor;
    }

    public void setPaidFor(boolean paidFor) {
        this.paidFor = paidFor;
    }

    public double getTotalOfReceipt(){
        double totalOfReceipt = 0;
        for (Consumable consumable : consumables){
            totalOfReceipt += consumable.getPrice();
        }
        return totalOfReceipt;
    }

}
