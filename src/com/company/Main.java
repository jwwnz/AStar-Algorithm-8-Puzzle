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

//        System.out.println(parser.parseThis("035162874"));

        CSVWriter newWriter = new CSVWriter(10);
        newWriter.setupCSVWriter();

//        new FileReader().readFile("10.pl");
//
//        System.out.println("-----------------------");
//        System.out.println("25 PL");
//        new FileReader().readFile("25.pl");


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