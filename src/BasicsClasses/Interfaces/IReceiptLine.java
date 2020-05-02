package BasicsClasses.Interfaces;

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

import BasicsClasses.FoodstuffDrinks.EnumAllergies;

import java.util.ArrayList;

public interface IReceiptLine {

    public int getIDConsumable();
    public String getNameConsumable();
    public String getDescriptionConsumable();
    public double getPriceConsumable();
    public ArrayList<EnumAllergies> getAllergiesConsumable();

    public int getConsumableQuantity();
    public void setProductQuantity(int productQuantity);

    public double getTotalPrice();

}
