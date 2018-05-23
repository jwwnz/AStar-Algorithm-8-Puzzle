package com.company;

import java.util.*;

public class AStar {

    int[][] startGrid = {
//            {5,4,8},
//            {1,2,7},
//            {0,6,3}

            {0,2,3},
            {1,5,6},
            {4,7,8}

//            {1,2,3},
//            {4,5,6},
//            {7,8,0}

    };

    int[][] goalGrid = {

//            {0,1,2},
//            {3,4,5},
//            {6,7,8}

            {1,2,3},
            {4,5,6},
            {7,8,0}
    };
    Node nodeGoal = new Node(goalGrid, misplacedTileHeuristic(goalGrid));
    PriorityQueue<Node> openList = new PriorityQueue<>();
    Hashtable<String, Node> closedList = new Hashtable<>();
    int nodesOpened = 1;

    public AStar() {
        Node nodeStart = new Node(startGrid, misplacedTileHeuristic(startGrid), 0);
        nodeStart.g = 0;
        nodeStart.h = misplacedTileHeuristic(nodeStart.grid);
        openList.add(nodeStart);
    }

    // This method turns 2 dimensional array into a String of Integers.
    public String stringifyGrid(int[][] grid) {
        String convertedString = "";
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                convertedString += grid[i][j];
            }
        }
        return convertedString;
    }

    public void search() {
        while (openList.peek() != null) {
            // take the top Node from openlist
            Node parentNode = openList.poll();
//            openList.remove(0);

            System.out.println("Below is the PARENT NODE");
            System.out.println(parentNode.grid[0][0]);
            printGrid(parentNode.grid);
            System.out.println("F value = " + parentNode.f + " G: " + parentNode.g + " + H: " + parentNode.h);

            stringifyGrid(parentNode.grid);

            if (Arrays.deepEquals(parentNode.grid, goalGrid)){
                System.out.println("You have reached the goal");
                System.out.println("You have opened: " + nodesOpened + " nodes.");
                return;
            }

            expandNode(parentNode);
            closedList.put(stringifyGrid(parentNode.grid), parentNode);
        }
        System.out.println("Failed to solve, Nodes opened: " + nodesOpened);
    }

    public void expandNode(Node parentNode) {

        //For loop through and check all neighbours of 0.
        // For each of these create a new node with grids with that neighbour
        // exchanged with 0.

                    // check if top move available, if so create new
                    if (parentNode.coordinateYempty-1 <= 2 && parentNode.coordinateYempty-1 >= 0){

                        int[][] newGrid = createNewGrid(parentNode.grid);

//                        System.out.println("top move");
//                        printGrid(newGrid);

                        newGrid[parentNode.coordinateYempty][parentNode.coordinateXempty] = newGrid[parentNode.coordinateYempty-1][parentNode.coordinateXempty];
                        newGrid[parentNode.coordinateYempty-1][parentNode.coordinateXempty] = 0;

//                        printGrid(newGrid);

                        if (!closedList.containsKey(stringifyGrid(newGrid))) {
                            Node newNode = new Node(newGrid, misplacedTileHeuristic(newGrid), parentNode.g+1);
                            nodesOpened++;
                            openList.add(newNode);
                        }
                    }

                    // check if the bottom move available, if so create new node
                    if (parentNode.coordinateYempty+1 <= 2 && parentNode.coordinateYempty+1 >= 0){

                        int[][] newGrid = createNewGrid(parentNode.grid);

//                        System.out.println("bottom move");
//                        printGrid(newGrid);

                        newGrid[parentNode.coordinateYempty][parentNode.coordinateXempty] = newGrid[parentNode.coordinateYempty+1][parentNode.coordinateXempty];
                        newGrid[parentNode.coordinateYempty+1][parentNode.coordinateXempty] = 0;

//                        printGrid(newGrid);

                        if (!closedList.containsKey(stringifyGrid(newGrid))) {
                            Node newNode = new Node(newGrid, misplacedTileHeuristic(newGrid), parentNode.g+1);
                            nodesOpened++;
                            openList.add(newNode);
                        }

                    }
                    // check if the left move available, if so create new node
                    if (parentNode.coordinateXempty-1 <= 2 && parentNode.coordinateXempty-1 >= 0){

                        int[][] newGrid = createNewGrid(parentNode.grid);

//                        System.out.println("left move");

//                        printGrid(newGrid);

                        newGrid[parentNode.coordinateYempty][parentNode.coordinateXempty] = newGrid[parentNode.coordinateYempty][parentNode.coordinateXempty-1];
                        newGrid[parentNode.coordinateYempty][parentNode.coordinateXempty-1] = 0;

//                        printGrid(newGrid);

                        if (!closedList.containsKey(stringifyGrid(newGrid))) {
                            Node newNode = new Node(newGrid, misplacedTileHeuristic(newGrid), parentNode.g+1);
                            nodesOpened++;
                            openList.add(newNode);
                        }
                    }
                    // check if the right move available, if so create new node
                    if (parentNode.coordinateXempty+1 <= 2 && parentNode.coordinateXempty+1 >= 0){

                        int[][] newGrid = createNewGrid(parentNode.grid);

//                        System.out.println("right Move");
//                        printGrid(newGrid);

                        newGrid[parentNode.coordinateYempty][parentNode.coordinateXempty] = newGrid[parentNode.coordinateYempty][parentNode.coordinateXempty+1];
                        newGrid[parentNode.coordinateYempty][parentNode.coordinateXempty+1] = 0;

                        //                        printGrid(newGrid);

                        if (!closedList.containsKey(stringifyGrid(newGrid))) {
                            Node newNode = new Node(newGrid, misplacedTileHeuristic(newGrid), parentNode.g+1);
                            nodesOpened++;
                            openList.add(newNode);
                        }
                    }
    }

    public int[][] createNewGrid(int[][] parentGrid) {

        int[][] newGrid = new int[3][3];

        for (int i = 0; i < parentGrid.length; i++){
            for (int j = 0; j < parentGrid.length; j++){
                newGrid[i][j] = parentGrid[i][j];
            }
        }
        return newGrid;
    }

    public void printGrid(int[][] grid){
        for (int k = 0; k < grid.length; k++) {
            for (int l = 0; l < grid.length; l++) {
                System.out.print(grid[k][l] + " ");
            }
            System.out.println();
        }
        System.out.println("------------------");
    }

    public int misplacedTileHeuristic (int[][] currentGrid) {
        int numberMisplaced = 0;
        for (int i = 0; i < currentGrid.length; i++){
            for (int j = 0; j < currentGrid.length; j++){
                if (currentGrid[i][j] != goalGrid[i][j] && currentGrid[i][j] != 0){
                    numberMisplaced++;
                }
            }
        }
        return numberMisplaced;
    }

    // This method turns String of Integers into a 2 dimensional array.
    public int[][] gridifyString(String stringGrid) {
        int[][] convertedGrid = new int[3][3];

        //chop string into three pieces
        String[] rowStringArray = new String[3];
        rowStringArray[0] = stringGrid.substring(0,3);
        rowStringArray[1] = stringGrid.substring(3,6);
        rowStringArray[2] = stringGrid.substring(6,9);

        // assign each to row 1 by one using for loop.
        for (int i =0; i < rowStringArray.length; i++) {
            convertedGrid[i][0] = Character.getNumericValue(rowStringArray[i].charAt(0));
            convertedGrid[i][1] = Character.getNumericValue(rowStringArray[i].charAt(1));
            convertedGrid[i][2] = Character.getNumericValue(rowStringArray[i].charAt(2));
        }
        return convertedGrid;
//        convertedGrid[0][0] = stringGrid.charAt(0);
//        convertedGrid[0][1] = ;
//        convertedGrid[0][2] = ;
//        convertedGrid[1][0] = ;
//        convertedGrid[1][1] = ;
//        convertedGrid[1][2] = ;
//        convertedGrid[2][0] = ;
//        convertedGrid[2][1] = ;
//        convertedGrid[2][2] = ;
    }

}
