package co.edu.sena.adso.ejercicios;

import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.time.ZoneOffset;
import java.util.*;

public class TestDates {

    public static void main(String[] args) {

        //TimeZone.setDefault(TimeZone.getTimeZone("GMT-05:00"));

        Calendar.getInstance(TimeZone.getTimeZone('GMT')).format("EEE, dd MMM yyyy HH:mm:ss z")


        Calendar calendar = new GregorianCalendar();
        TimeZone timeZone = TimeZone.getTimeZone("GMT-05:00");
        calendar.setTimeZone(timeZone);

        Calendar calendarCanada = new GregorianCalendar();
        TimeZone timeZoneCanada = TimeZone.getTimeZone("GMT+05:00");
        calendarCanada.setTimeZone(timeZoneCanada);
        calendarCanada.setTime(calendar.getTime());

        System.out.println(calendar.getTime().toString());
        System.out.println(calendar.getTimeZone());
        System.out.println(calendarCanada.getTime().toString());
        System.out.println(calendarCanada.getTimeZone());
    }

}
