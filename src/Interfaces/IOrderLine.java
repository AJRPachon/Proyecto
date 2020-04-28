package Interfaces;

/*
 * Properties:
 *  - Basics:
 *      > IDProduct: integer, Consulting, Modifiable
 *      > productQuantity: integer, Consulting, Modifiable
 *
 *  - Derivatives:
 *      > None
 *
 *  - Shared:
 *      > None
 *
 * Methods:
 *  - Basics:
 *      > integer getIDProduct()
 *      > none setIDProduct(integer IDProduct)
 *
 *      > integer getProductQuantity()
 *      > none setProductQuantity(integer productQuantity)
 *
 *  - Added:
 *      > None
 *
 */

public interface IOrderLine {

    public int getIDProduct();
    public void setIDProduct(int IDProduct);

    public int getProductQuantity();
    public void setProductQuantity(int productQuantity);


}
