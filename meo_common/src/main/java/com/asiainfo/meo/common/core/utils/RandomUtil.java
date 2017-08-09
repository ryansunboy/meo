package com.asiainfo.meo.common.core.utils;

import java.util.Random;

public final class RandomUtil
{
    private static final Random R = new Random();
    
    private static final String DIGITS = "1234567890";
    
    private static final int DIGITS_LENGTH = DIGITS.length();
    
    private static final String DIGITS_LETTERS = "1234567890abcdefghijkmnpqrstuvwxyz";
    
    private static final int DIGITS_LETTERS_LENGTH = DIGITS_LETTERS.length();
    
    private static final String LETTERS = "abcdefghijkmnpqrstuvwxyz";
    
    private static final int LETTERS_LENGTH = LETTERS.length();
    
    public static String randomNum(int length)
    {
        if(length < 0)
        {
            throw new IllegalArgumentException(); // TODO
        }
        char[] numChars = new char[length];
        for(int i = 0; i < length; i++)
        {
            numChars[i] = DIGITS.charAt(R.nextInt(DIGITS_LENGTH));
        }
        return new String(numChars);
    }
    
    public static String randomLetter(int length)
    {
        if(length < 0)
        {
            throw new IllegalArgumentException(); // TODO
        }
        char[] numChars = new char[length];
        for(int i = 0; i < length; i++)
        {
            numChars[i] = LETTERS.charAt(R.nextInt(LETTERS_LENGTH));
        }
        return new String(numChars);
    }
    
    public static String randomDigitLetter(int length)
    {
        if(length < 0)
        {
            throw new IllegalArgumentException(); // TODO
        }
        char[] numChars = new char[length];
        for(int i = 0; i < length; i++)
        {
            numChars[i] = DIGITS_LETTERS.charAt(R.nextInt(DIGITS_LETTERS_LENGTH));
        }
        return new String(numChars);
    }
}
