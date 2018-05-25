package com.company.CSV;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVWriter {

    String outputDirectory = "output.csv";
    List<String> listOfHeaders = new ArrayList<>();
    int problemLength;

    public CSVWriter(int problemLength) {
        this.problemLength = problemLength;

        this.listOfHeaders.add("Algorithm");
        this.listOfHeaders.add("Heuristic");
        this.listOfHeaders.add("CPUTime (milliseconds)");
        this.listOfHeaders.add("Initial State");
        this.listOfHeaders.add("Goal State");
        this.listOfHeaders.add("Optimal sol cost");

        for (int i = 0; i <= problemLength; i++){
            this.listOfHeaders.add("Nodes Generated Level " + i);
            this.listOfHeaders.add("Nodes Expanded Level " + i);
            this.listOfHeaders.add("Nodes Evaluated Level " + i);
        }

        this.listOfHeaders.add("Total nodes Generated");
        this.listOfHeaders.add("Total nodes Expanded");
        this.listOfHeaders.add("Total nodes Evaluated");
    }

    public void setupCSVWriter (String outputDirectory) {
        this.outputDirectory = outputDirectory;
        try {
            BufferedWriter bW = new BufferedWriter(new FileWriter(this.outputDirectory));

            // Print the list of Headers
            CSVUtility.writeLine(bW, this.listOfHeaders);

            bW.flush();
            bW.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addEntry (List<String> items) {
        try {
            BufferedWriter bW = new BufferedWriter(new FileWriter(this.outputDirectory, true));

            // Add an entry
            CSVUtility.writeLine(bW, items);

            bW.flush();
            bW.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
