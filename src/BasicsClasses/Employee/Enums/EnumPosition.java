package BasicsClasses.Employee.Enums;

public enum EnumPosition {
    CommisChef, HeadChef, ChefThePartie, SousChef, Cook, Waiter, Waitress, Busser, Manager, Host, Bartender, Spaguetti;


    public EnumPosition getEmumPosition(String position){

        EnumPosition e = Spaguetti;

        for (int cont = 0; cont < EnumPosition.values().length; cont++){

            if ( position.equals(EnumPosition.values()[cont].toString()) ){

                e = EnumPosition.values()[cont];

            }

        }

        return e;

    }
}
