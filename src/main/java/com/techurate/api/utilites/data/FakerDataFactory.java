package com.techurate.api.utilites.data;

import static com.techurate.api.config.ConfigManage.getConfig;

import java.util.Locale;

import org.apache.commons.lang3.RandomStringUtils;

import net.datafaker.Faker;

public class FakerDataFactory {

	private static final Faker faker = new Faker(new Locale(getConfig().faker()));

	private FakerDataFactory() {
		
	}

	public static String getCompanyName() {
		return faker.company().name().replaceAll("[^a-zA-Z ]", "");
	}

	public static String getUrl() {
		return faker.company().url();
	}

	public static String getAddress() {
		return faker.address().streetAddress();
	}
	
	public static String getCity() {
		return faker.address().city();
	}

	
	public static String getCountry() {
		return faker.address().country();
	}

	public static String getFirstName() {
		return faker.name().firstName();
	}
	
	public static String getLastName() {
		return faker.name().lastName();
	}
	
	public static String getEmailAddress() {
		return faker.internet().emailAddress();
	}
	
	public static String getContactNumber() {
		return faker.phoneNumber().cellPhone();
	}
	
	public static String getMobileNumber() {
		return "96"+faker.number().randomNumber(8, false);
	}

	public static int getAmount() {
		return faker.number().numberBetween(1, 99);
	}
	public static String getID() {
		return ""+faker.number().randomNumber(10, false);
	}
	
	public static String getBankAccountNumber() {
		return ""+faker.number().randomNumber(10, false);
	}
	
	public static String getSwiftCode() {
		return ""+faker.number().randomNumber(4, false);
	}
	public static String getTaxNumber() {
		return ""+faker.number().randomNumber(7, false);
	}
	
	public static String getRating() {
		return faker.options().option("Hot","Warm", "Cold");
	}
	
	public static String getSalutation() {
		return faker.options().option("Mr.","Ms.", "Mrs.", "Dr.", "Prof.");
	}
	
	public static String getNumberRange() {
		return ""+faker.number().numberBetween(1990, RequiredDate.getCurrentYear());
	}

	public static String getRandomNumber(int count) {
        return faker.number().digits(count);
    }

    public static int getRandomNumber(int min, int max) {
        return faker.number().numberBetween(min, max);
    }

    public static String getRandomAlphabets(int count) {
        return RandomStringUtils.randomAlphabetic(count);
    }

    public static String getRandomWebsiteName() {
        return "https://"+RandomStringUtils.randomAlphabetic(10)+".com";
    }
}
