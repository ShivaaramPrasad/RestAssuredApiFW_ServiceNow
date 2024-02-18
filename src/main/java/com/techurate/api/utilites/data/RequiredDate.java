package com.techurate.api.utilites.data;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.TimeZone;



public class RequiredDate {
	public static String calcReqDate(int days) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//Getting current date
		Calendar cal = Calendar.getInstance();
		//Displaying current date in the desired format
		System.out.println("Current Date: "+sdf.format(cal.getTime()));
		   
		//Number of Days to add
	     cal.add(Calendar.DAY_OF_MONTH, days);  
		//Date after adding the days to the current date
		String newDate = sdf.format(cal.getTime());  
		//Displaying the new Date after addition of Days to current date
		System.out.println("Date after Addition: "+newDate);
		
		return newDate;
	}
	
	public static String calcDateOfBirth(int days) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//Getting current date
		Calendar cal = Calendar.getInstance();
		//Displaying current date in the desired format
		System.out.println("Current Date: "+sdf.format(cal.getTime()));
		   
		//Number of Days to add
	     cal.add(Calendar.DAY_OF_MONTH, days);  
		//Date after adding the days to the current date
		String newDate = sdf.format(cal.getTime());  
		//Displaying the new Date after addition of Days to current date
		System.out.println("Date after Addition: "+newDate);
		
		return newDate;
	}

	
	//Get The Current Day
    public static String getCurrentDay() {
        //Create a Calendar Object
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        //Get Current Day as a number
        int todayInt = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println("Today Int: " + todayInt + "\n");
        //Integer to String Conversion
        String todayStr = Integer.toString(todayInt);
        System.out.println("Today Str: " + todayStr + "\n");
        return todayStr;
    }
    //Get The Current Day plus days. You can change this method based on your needs.
    public static String getCurrentDayPlus(int days) {
        LocalDate currentDate = LocalDate.now();
        int dayOfWeekPlus = currentDate.getDayOfWeek().plus(days).getValue();
        return Integer.toString(dayOfWeekPlus);
    }
    
    public static int getCurrentYear() {
        return LocalDate.now().getYear();
    }
}
