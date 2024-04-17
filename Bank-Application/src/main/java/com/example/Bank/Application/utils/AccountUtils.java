package com.example.Bank.Application.utils;

import java.time.Year;

public class AccountUtils {
    public static final String ACCOUNT_EXISTS_CODE = "001";
    public static final String ACCOUNT_EXISTS_MESSAGE= "This user already have an account";

    public static final String ACCOUNT_CREATION_SUCESS ="002";
    public static final String ACCOUNT_CRAETION_MESSAGE = "Account Successfully Created";
    public static String generateAccountNumber(){
        //    current year + random 6 digits

        Year currentYear = Year.now();
        int min = 100000;
        int max = 999999;

        //generate random number
        int randNumber = (int) Math.floor(Math.random()* (max - min +1) + min);

        //convert the current and random number in to strings, then concatinate
        String year = String.valueOf(currentYear);
        String randomNumber = String.valueOf(randNumber);
        StringBuilder accountNumber = new StringBuilder();
        return accountNumber.append(year).append(randomNumber).toString();

    }
}
