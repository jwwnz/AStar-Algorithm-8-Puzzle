package com.company;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileReader {
    public BufferedReader reader;
    public List<Node> nodes;

    public FileReader() {
    }

    public void readFile(String fileLocation) {
        Parser parser = new Parser();
        try {
            reader = new BufferedReader(new java.io.FileReader(fileLocation));
            String line;
            while ((line = reader.readLine()) != null) {
                Pattern pattern = Pattern.compile("( \\d+)");
                Matcher matcher = pattern.matcher(line);

                if (matcher.find()) {
                    String puzzleSequence = parser.parseThis(matcher.group().trim());
                    System.out.println("-------------------");
                    System.out.println("Puzzle with sequence: " + puzzleSequence);

                    //Run A Star Algorithm
                    System.out.println("A-Star");
                    long timeFirst = System.nanoTime();
                    PuzzleAStar myAStar = new PuzzleAStar(puzzleSequence);
                    myAStar.search();
                    long timeSecond = System.nanoTime() - timeFirst;
                    double inMicroseconds = TimeUnit.MICROSECONDS.convert(timeSecond, TimeUnit.NANOSECONDS);
                    System.out.println("CPU time in microseconds: " + inMicroseconds);

                    //Run A Sharp Algorithm
                    System.out.println("A-Sharp");
                    timeFirst = System.nanoTime();
                    PuzzleASharp myASharp = new PuzzleASharp(puzzleSequence);
                    myASharp.search();
                    timeSecond = System.nanoTime() - timeFirst;
                    inMicroseconds = TimeUnit.MICROSECONDS.convert(timeSecond, TimeUnit.NANOSECONDS);
                    System.out.println("CPU time in microseconds: " + inMicroseconds);

                    System.out.println("Round finished");
                    System.out.println("-------------------");
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
