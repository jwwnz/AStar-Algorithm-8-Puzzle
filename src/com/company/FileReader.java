package com.company;

import com.company.CSV.CSVWriter;
import com.company.heuristics.Heuristic;
import com.company.heuristics.ManhattanDistanceHeuristic;
import com.company.heuristics.MisplacedTilesHeuristic;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileReader {
    public BufferedReader reader;
    public List<Node> nodes;
    public CSVWriter myWriter;

    public FileReader() {

    }

    public void readFile(String fileLocation) {

        //Setting up writing of CSV
        this.myWriter = new CSVWriter(10);
        myWriter.setupCSVWriter();

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
                    System.out.println(">>>> A-Star <<<<");

                    System.out.println("| Manhattan |");
                    PuzzleAStar myAStarManhattan = new PuzzleAStar(puzzleSequence, new ManhattanDistanceHeuristic());
                    runAlgorithm(myAStarManhattan);
//
//                    System.out.println("| Manhattan - 1 |");
//                    PuzzleAStar myAStarManhattanM = new PuzzleAStar(puzzleSequence, new ManhattanDistanceHeuristic(1));
//                    runAlgorithm(myAStarManhattanM);

//                    System.out.println("| Misplaced Tile |");
//                    PuzzleAStar myAStarMisplaced = new PuzzleAStar(puzzleSequence, new MisplacedTilesHeuristic());
//                    runAlgorithm(myAStarMisplaced);

                    //Run A Sharp Algorithm
                    System.out.println(">>>> A-Sharp <<<<");

//                    System.out.println("| Manhattan |");
//                    PuzzleASharp myASharpManhattan = new PuzzleASharp(puzzleSequence, new ManhattanDistanceHeuristic());
//                    runAlgorithm(myASharpManhattan);
//
//                    System.out.println("| Manhattan - 1 |");
//                    PuzzleASharp myASharpManhattanM = new PuzzleASharp(puzzleSequence, new ManhattanDistanceHeuristic(1));
//                    runAlgorithm(myASharpManhattanM);

//                    System.out.println("| Misplaced Tile |");
//                    PuzzleASharp myASharpMisplaced = new PuzzleASharp(puzzleSequence, new MisplacedTilesHeuristic());
//                    runAlgorithm(myASharpMisplaced);

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

    public void runAlgorithm(PuzzleSolver solver) {
        // Creating list of first six items.
        List<String> preliminarySixList= new ArrayList<>();
        preliminarySixList.add(solver.heuristicUsed.name);

        long timeFirst = System.nanoTime();

        solver.search();

        long timeSecond = System.nanoTime() - timeFirst;
        double inMicroseconds = TimeUnit.MICROSECONDS.convert(timeSecond, TimeUnit.NANOSECONDS);

        System.out.println("CPU time in microseconds: " + inMicroseconds);
        System.out.println("-------------------");

        //adding following lists together using list.addAll(a,b) 6 items + 11 * 3 + 3
//        myWriter.addEntry();
    }

}
