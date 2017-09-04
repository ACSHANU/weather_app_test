package com.weatherapp;

/**
 * Created by Duvvuri on 03/09/2017.
 */
import java.util.ArrayList;
public class CommonObjectRepository {

    protected static String[] getExpectedHourValues(String selectedDay) {
        // TODO : use Calendar Object and get All valid Values of Hours in
        //24 hour format by considering the start time , if today was selected
        ArrayList<String> listHours = new ArrayList<String>();
        if(Integer.parseInt(selectedDay)!=20)
        {
            listHours.add("0100");
            listHours.add("0400");
            listHours.add("0700");
            listHours.add("1000");
        }

        listHours.add("1300");
        listHours.add("1600");
        listHours.add("1900");
        listHours.add("2200");

        String[] arrHours = listHours.toArray(new String[listHours.size()]);
        return arrHours;
    }
}
