package BasicsClasses.Employee.Enums;

public enum EnumWeekDays {
    Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday, Spaguetti;


/////////////////////////////////////////////////////////////////////////////////////////////////////////

    /*

        SIGNATURE:
            - public static EnumWeekDays getWeekDay(int cont)   ( It has to be static. No object is being instantiating )

        COMENTARY:
            - Returns a specific day of the week

        INPUTS:
            - cont

        OUTPUTS:
            - enum

        INTPUT/OUTPUT:
            - Not one

        PRECONDITION:
            - Not one

        POSTCONDITION:
            - The method should return the specific enum

     */

    public static EnumWeekDays getWeekDay(int cont){

        return EnumWeekDays.values()[cont];

    }

}
