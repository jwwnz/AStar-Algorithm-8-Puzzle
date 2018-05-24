package com.company;

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

//        Parser parser = new Parser();
//        BufferedReader reader;
//        List<Node> nodes;
//        try {
//            reader = new BufferedReader(new java.io.FileReader("25.pl"));
//            String line = "";
//            while ((line = reader.readLine()) != null){
//                Pattern pattern = Pattern.compile("( \\d+)");
//                Matcher matcher = pattern.matcher(line);
//
//                if (matcher.find()) {
////                    System.out.println(matcher.group().trim());
//                    System.out.println(parser.parseThis(matcher.group().trim()));
//                }
//            }
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        System.out.println(parser.parseThis("035162874"));
        long timeFirst = System.nanoTime();
        PuzzleAStar myAStar = new PuzzleAStar("382065714");
        myAStar.search();
        long timeSecond = System.nanoTime() - timeFirst;

        double inSeconds = TimeUnit.MILLISECONDS.convert(timeSecond, TimeUnit.NANOSECONDS);
        System.out.println(inSeconds);
    }
}

// Old Code:

//
//    AStar astar = new AStar();
//
//        for(Node node : astar.openList) System.out.println(node.f);
//
//                astar.search();
//                System.out.println(astar.nodesOpened);
//
////        for(Node node : astar.closedList) System.out.println(node.f);