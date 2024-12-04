package com.example.bid.util;

import java.util.Random;

public class BidUtils {

    public static String generateOTP() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000); // Generate a random 6-digit number
        return String.valueOf(otp); // Return as a string
    }
}
