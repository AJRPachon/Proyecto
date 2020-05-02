package BasicsClasses.ReceiptLine;

/*
 * Properties:
 *  - Basics:
 *      > consumable: Consumable, Consulting
 *      > consumableQuantity: integer, Consulting, Modifiable
 *
 *  - Derivatives:
 *      > totalPrice: realNumber, Consulting
 *
 *  - Shared:
 *      > None
 *
 * Methods:
 *  - Basics:
 *      > integer getIDConsumable();
 *      > string getNameConsumable();
        > string getDescriptionConsumable();
 *      > listOfEnumAllergies getAllergiesConsumable();
 *      > realNumber getPriceConsumable();
 *
 *      > integer getConsumableQuantity()
 *      > none setConsumableQuantity(integer consumableQuantity)
 *
 *      > realNumber getTotalPrice();
 *
 *  - Added:
 *      > None
 *
 */

import BasicsClasses.FoodstuffDrinks.Consumable;
import BasicsClasses.FoodstuffDrinks.Enums.EnumAllergies;
import BasicsClasses.Interfaces.IReceiptLine;

import java.util.ArrayList;

public class ReceiptLine implements IReceiptLine, Comparable, Cloneable {

    private Consumable consumable;
    private int consumableQuantity;

    public ReceiptLine() {
        consumable = new Consumable();
        consumableQuantity = 0;
    }

    public ReceiptLine(Consumable consumable, int consumableQuantity) {
        this.consumable = consumable.clone();
        this.consumableQuantity = consumableQuantity;
    }

    public ReceiptLine(ReceiptLine other) {
        this.consumable = other.consumable.clone();
        this.consumableQuantity = other.consumableQuantity;
    }

    public int getIDConsumable() {
        return consumable.getIDConsumable();
    }

    public String getNameConsumable() {
        return consumable.getName();
    }

    public String getDescriptionConsumable() {
        return consumable.getDescription();
    }

    public double getPriceConsumable() {
        return consumable.getPrice();
    }

    public ArrayList<EnumAllergies> getAllergiesConsumable() {
        return (ArrayList<EnumAllergies>)this.consumable.getAllergies().clone();
    }

    public int getConsumableQuantity() {
        return this.consumableQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.consumableQuantity = productQuantity;
    }

    public double getTotalPrice() {
        return consumable.getPrice() * consumableQuantity;
    }

    public String toString(){
        return consumable.toString()+"|"+consumableQuantity;
    }

    @Override
    public int compareTo(Object ob){
        int result = -1;
        ReceiptLine otherReceiptLine = (ReceiptLine)ob;
        if (this.consumableQuantity == otherReceiptLine.consumableQuantity){
            result = 0;
        }else{
            if (this.consumableQuantity > otherReceiptLine.consumableQuantity){
                result = 1;
            }
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        boolean isEquals = false;
        if (this == obj){
            isEquals = true;
        }else{
            if (obj != null && obj instanceof ReceiptLine){
                ReceiptLine newReceiptLine = (ReceiptLine)obj;
                if (this.consumableQuantity == newReceiptLine.consumableQuantity
                    && this.consumable.equals(newReceiptLine.consumable)){
                    isEquals = true;
                }
            }
        }
        return isEquals;
    }

    @Override
    public ReceiptLine clone() {
        ReceiptLine newReceiptLine = null;

        try {
            newReceiptLine = (ReceiptLine)super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return newReceiptLine;
    }

}
