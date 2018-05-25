package com.company;

import com.company.CSV.CSVWriter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {

        // Specify your file and the optimal problem length of your dataset.
        new FileReader().readFile("10.pl", 10);
        new FileReader().readFile("25.pl", 25);

    }
}