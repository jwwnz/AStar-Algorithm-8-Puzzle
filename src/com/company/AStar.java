package com.company;

import java.util.*;

public class AStar {

    int[][] startGrid;

//    int[][] startGrid =
//
//            {
//                    {2, 3, 0},
//                    {5, 6, 4},
//                    {8, 7, 1}
//
////            {1, 2, 3},
////            {4, 5, 6},
////            {7, 0, 8}
//            };

    int[][] goalGrid = {

//            {0,1,2},
//            {3,4,5},
//            {6,7,8}

            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 0}
    };
    //    Node nodeGoal = new Node(goalGrid, misplacedTileHeuristic(goalGrid));
    Node nodeGoal = new Node(goalGrid, manhattanHeuristic(goalGrid));

    PriorityQueue<Node> openList = new PriorityQueue<>();
    Hashtable<String, Node> closedList = new Hashtable<>();
    int nodesOpened = 1;

    public AStar() {
        startGrid = gridifyString("632510874");

        Node nodeStart = new Node(startGrid, manhattanHeuristic(startGrid));
        nodeStart.g = 0;
        nodeStart.h = manhattanHeuristic(nodeStart.grid);
        openList.add(nodeStart);
    }

    public void search() {

        while (openList.peek() != null) {
            // take the top Node from openlist
            Node currentNode = openList.poll();

            System.out.println("Below is the Current NODE");
            printGrid(currentNode.grid);
            System.out.println("F value = " + currentNode.f + " G: " + currentNode.g + " + H: " + currentNode.h);

            // If goal reached, end
            if (Arrays.deepEquals(currentNode.grid, goalGrid)) {
                System.out.println("You have reached the goal");
                System.out.println("You have opened: " + nodesOpened + " nodes.");
                return;
            }

            System.out.println("Open list currently has: " + openList.size());

            currentNode.g++;
            currentNode.f = currentNode.g + currentNode.h;
            System.out.println("F value = " + currentNode.f + " G: " + currentNode.g + " + H: " + currentNode.h);

            // Put expanded node in closedList.
            closedList.put(currentNode.stringifyGrid(currentNode.grid), currentNode);

            // For all children nodes expand.
            // Expanding nodes top, bottom, left, right if move possible, and add them to openlist
            if (currentNode.zeroY - 1 >= 0) {
                expandSingleChildNode(currentNode, "top");
            }
            if (currentNode.zeroY + 1 <= 2) {
                expandSingleChildNode(currentNode, "bottom");
            }
            if (currentNode.zeroX - 1 >= 0) {
                expandSingleChildNode(currentNode, "left");
            }
            if (currentNode.zeroX + 1 <= 2) {
                expandSingleChildNode(currentNode, "right");
            }

        }
        System.out.println("Failed to solve, Nodes opened: " + nodesOpened);
    }

    public void expandSingleChildNode(Node parentNode, String direction) {

        int newY = parentNode.zeroY;
        int newX = parentNode.zeroX;

        switch (direction) {
            case "top":
                newY--;
                break;
            case "bottom":
                newY++;
                break;
            case "left":
                newX--;
                break;
            case "right":
                newX++;
        }

        // Create new grid with old grid.
        int[][] newGrid = createNewGrid(parentNode.grid);

        // Apply move to change 0, with top tile.
        newGrid[parentNode.zeroY][parentNode.zeroX] = newGrid[newY][newX];
        newGrid[newY][newX] = 0;

        Node newNode = new Node(newGrid, manhattanHeuristic(newGrid), parentNode);

        // If node with same position exists on Open list with a lower F value skip it
        for (Node node : openList) {
            if (node.stringGrid.equals(newNode.stringGrid) && node.f < newNode.f) {
                return;
            }
        }

        if (closedList.get(newNode.stringGrid) != null && closedList.get(newNode.stringGrid).g > newNode.g) {
            closedList.put(newNode.stringGrid, newNode);
            return;
        }

        if (!closedList.containsKey(newNode.stringifyGrid(newGrid)) || closedList.get(newNode.stringGrid).f < newNode.f) {
            nodesOpened++;
            openList.add(newNode);
        }
    }

    public int[][] createNewGrid(int[][] parentGrid) {

        int[][] newGrid = new int[3][3];

        for (int i = 0; i < parentGrid.length; i++) {
            for (int j = 0; j < parentGrid.length; j++) {
                newGrid[i][j] = parentGrid[i][j];
            }
        }
        return newGrid;
    }

    public void printGrid(int[][] grid) {
        for (int k = 0; k < grid.length; k++) {
            for (int l = 0; l < grid.length; l++) {
                System.out.print(grid[k][l] + " ");
            }
            System.out.println();
        }
        System.out.println("------------------");
    }

    public int misplacedTileHeuristic(int[][] currentGrid) {

        int numberMisplaced = 0;
        for (int i = 0; i < currentGrid.length; i++) {
            for (int j = 0; j < currentGrid.length; j++) {
                if (currentGrid[i][j] != goalGrid[i][j]) {
                    numberMisplaced++;
                }
            }
        }
        return numberMisplaced;
    }

    public int manhattanHeuristic(int[][] currentGrid) {

        int manHattanDistance = 0;

        for (int i = 0; i < currentGrid.length; i++) {
            for (int j = 0; j < currentGrid.length; j++) {

                if (currentGrid[i][j] == 0) continue;
                manHattanDistance += manhattanPerTile(i, j, calculateGoalY(currentGrid[i][j]), calculateGoalX(currentGrid[i][j]));
            }
        }

        return manHattanDistance;
    }

    public int calculateGoalY(int tileNum) {

        if (tileNum == 1 || tileNum == 2 || tileNum == 3) {
            return 0;
        } else if (tileNum == 4 || tileNum == 5 || tileNum == 6) {
            return 1;
        } else if (tileNum == 7 || tileNum == 8 || tileNum == 0) {
            return 2;
        } else {
            return -1;
        }
    }

    public int calculateGoalX(int tileNum) {

        if (tileNum == 1 || tileNum == 4 || tileNum == 7) {
            return 0;
        } else if (tileNum == 2 || tileNum == 5 || tileNum == 8) {
            return 1;
        } else if (tileNum == 3 || tileNum == 6 || tileNum == 0) {
            return 2;
        } else {
            return -1;
        }
    }

    public int manhattanPerTile(int currentY, int currentX, int goalY, int goalX) {

        // Algorithm to finding manhattan distance: abs(currentX - goalX) + abs(currentY- goalY)
        return Math.abs(currentY - goalY) + Math.abs(currentX - goalX);
    }

    // This method turns String of Integers into a 2 dimensional array.
    public int[][] gridifyString(String stringGrid) {
        int[][] convertedGrid = new int[3][3];

        //chop string into three pieces
        String[] rowStringArray = new String[3];
        rowStringArray[0] = stringGrid.substring(0, 3);
        rowStringArray[1] = stringGrid.substring(3, 6);
        rowStringArray[2] = stringGrid.substring(6, 9);

        // assign each to row 1 by one using for loop.
        for (int i = 0; i < rowStringArray.length; i++) {
            convertedGrid[i][0] = Character.getNumericValue(rowStringArray[i].charAt(0));
            convertedGrid[i][1] = Character.getNumericValue(rowStringArray[i].charAt(1));
            convertedGrid[i][2] = Character.getNumericValue(rowStringArray[i].charAt(2));
        }
        return convertedGrid;
    }

//    // This method turns 2 dimensional array into a String of Integers.
//    public String stringifyGrid(int[][] grid) {
//        String convertedString = "";
//        for (int i = 0; i < grid.length; i++) {
//            for (int j = 0; j < grid.length; j++) {
//                convertedString += grid[i][j];
//            }
//        }
//        return convertedString;
//    }

}
