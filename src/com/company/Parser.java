package com.company;

/*
Parser Class: The puzzle codes are in prologue inverted form.
This parser will parse this to proper puzzle form.
 */
public class Parser {

    public Parser() {}

    public String parseThis(String oldNum) {
        String newString = "";
        String[] stringArray = new String[9];

        for (int i = 0; i < oldNum.length(); i++) {
            int newIndex = oldNum.charAt(i) - '0';
            stringArray[newIndex] = i + "";
        }

        for (String c : stringArray) {
            newString += c;
        }
        return newString;
    }
}
