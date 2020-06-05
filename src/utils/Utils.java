package utils;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Utils {

    /////////// ASSIGN BIRTHDAY //////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Convertimos una fecha String a enteros y se la asignamos a una variable de tipo GregorianCalendar
     *
     * @param separaciones
     * @return
     */

    public GregorianCalendar assignBirthday(String[] separaciones){

        String[] fechaString;
        GregorianCalendar birthday = new GregorianCalendar();
        int[] fecha = new int[3];

        //Separamos el String de la fecha en sus distintos numeros
        fechaString = separaciones[4].split("/");

        //Recorremos el array y hacemos un parseInt para obtener los enteros
        for(int cont = 0; cont < fechaString.length; cont++){
            fecha[cont] = Integer.parseInt(fechaString[cont]);
        }

        //Metemos la fecha de cumpleaños en nuestra variable tipo fecha y ya la podemos usar en nuestro constructor
        birthday.set(Calendar.DAY_OF_MONTH, fecha[0]);
        birthday.set(Calendar.MONTH, fecha[1]);
        birthday.set(Calendar.YEAR, fecha[2]);

        return birthday;

    }

}
