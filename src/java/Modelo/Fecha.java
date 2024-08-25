package Modelo;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Fecha {

    public static Calendar calendar = Calendar.getInstance();
    public static String fecha;

    public static String Fecha() {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
        fecha = sdf.format(calendar.getTime());
        return fecha;

    }

    public static String FechaBD() {

        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
        fecha = sdf.format(calendar.getTime());
        return fecha;

    }

}
