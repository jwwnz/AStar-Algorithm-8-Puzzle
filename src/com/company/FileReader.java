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
    public int problemLength;
    public FileReader() {

    }

    public void readFile(String fileLocation, int problemLength) {

        this.problemLength = problemLength;

        //Setting up writing of CSV
        this.myWriter = new CSVWriter(problemLength);
        myWriter.setupCSVWriter("length"+problemLength+"data.csv");

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
                    runAlgorithm(myAStarManhattan, puzzleSequence, "Man", "A*");
//
                    System.out.println("| Manhattan - 1 |");
                    PuzzleAStar myAStarManhattanM = new PuzzleAStar(puzzleSequence, new ManhattanDistanceHeuristic(1));
                    runAlgorithm(myAStarManhattanM, puzzleSequence, "Man-1","A*");

                    System.out.println("| Misplaced Tile |");
                    PuzzleAStar myAStarMisplaced = new PuzzleAStar(puzzleSequence, new MisplacedTilesHeuristic());
                    runAlgorithm(myAStarMisplaced, puzzleSequence, "Misplaced","A*");

                    //Run A Sharp Algorithm
                    System.out.println(">>>> A-Sharp <<<<");

                    System.out.println("| Manhattan |");
                    PuzzleASharp myASharpManhattan = new PuzzleASharp(puzzleSequence, new ManhattanDistanceHeuristic());
                    runAlgorithm(myASharpManhattan, puzzleSequence, "Man", "A#");
//
                    System.out.println("| Manhattan - 1 |");
                    PuzzleASharp myASharpManhattanM = new PuzzleASharp(puzzleSequence, new ManhattanDistanceHeuristic(1));
                    runAlgorithm(myASharpManhattanM, puzzleSequence, "Man-1", "A#");

                    System.out.println("| Misplaced Tile |");
                    PuzzleASharp myASharpMisplaced = new PuzzleASharp(puzzleSequence, new MisplacedTilesHeuristic());
                    runAlgorithm(myASharpMisplaced, puzzleSequence, "Misplaced","A#");

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

    public void runAlgorithm(PuzzleSolver solver, String startState, String heuristicName, String algorithmName) {
        // Creating list of first six items.

        List<String> preliminarySixList= new ArrayList<>();
        // 1. Adding Algorithm name
        preliminarySixList.add(algorithmName);

        // 2. Adding heuristic name
        preliminarySixList.add(heuristicName);

        long timeFirst = System.nanoTime();

        List<String> secondList = solver.search();

        long timeSecond = System.nanoTime() - timeFirst;
        double inMilliseconds = TimeUnit.MILLISECONDS.convert(timeSecond, TimeUnit.NANOSECONDS);

        System.out.println("CPU time in milliseconds: " + inMilliseconds);
        System.out.println("-------------------");

        // 3. Adding CPU time
        preliminarySixList.add(inMilliseconds+"");

        // 4. Adding Initial state
        preliminarySixList.add(startState);

        // 5. Adding goalState
        preliminarySixList.add("123456780");

        // 6. Adding problem length
        preliminarySixList.add(this.problemLength + "");

        //adding following lists together using list.addAll(a,b) 6 items + 11 * 3 + 3
        preliminarySixList.addAll(secondList);

        myWriter.addEntry(preliminarySixList);
    }

}
