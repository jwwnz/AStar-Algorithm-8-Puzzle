package com.company;

import java.util.*;

public class AStar {

    int[][] startGrid = {
            {6,4,2},
            {5,1,0},
            {8,3,7}
    };

    int[][] goalGrid = {
            {1,2,3},
            {4,5,6},
            {7,8,0}
    };
    Node nodeGoal = new Node(goalGrid, misplacedTileHeuristic(goalGrid));

    List<Node> openList = new ArrayList<>();
    List<Node> closedList = new ArrayList<>();
    Node nodeCurrent;
    int currentGvalue;
    int nodesOpened = 1;
    int[] shortestPath;

    public AStar() {

        Node nodeStart = new Node(startGrid, misplacedTileHeuristic(startGrid), 0);
        nodeStart.g = 0;
        nodeStart.h = misplacedTileHeuristic(nodeStart.grid);
        openList.add(nodeStart);
        Collections.sort(openList);
        Collections.reverse(openList);
    }

    public void search() {
        while (openList != null) {
            // take the top Node from openlist
            Node parentNode = openList.get(0);

//            for (int i = 0; i < parentNode.grid.length; i++) {
//                for (int j = 0; j < parentNode.grid.length; j++) {
//                    System.out.print(parentNode.grid[i][j] + " ");
//                }
//                System.out.println();
//            }

            System.out.println(parentNode);

            if (parentNode.h == 0){
                System.out.println("You have reached the goal");
                return;
            }

            expandNode(parentNode);
            closedList.add(parentNode);
            openList.remove(0);
        }
    }

    public void expandNode(Node parentNode) {

        //For loop through and check all neighbours of 0.
        // For each of these create a new node with grids with that neighbour
        // exchanged with 0.

                    // check if top move available, if so create new
                    if (parentNode.coordinateYempty-1 <= 2 && parentNode.coordinateYempty-1 >= 0){

                        int[][] newGrid = createNewGrid(parentNode.grid);

                        System.out.println("top move");
                        for (int k = 0; k < newGrid.length; k++) {
                            for (int l = 0; l < newGrid.length; l++) {
                                System.out.print(newGrid[k][l] + " ");
                            }
                            System.out.println();
                        }

                        newGrid[parentNode.coordinateYempty][parentNode.coordinateXempty] = newGrid[parentNode.coordinateYempty-1][parentNode.coordinateXempty];
                        newGrid[parentNode.coordinateYempty-1][parentNode.coordinateXempty] = 0;

                        for (int k = 0; k < newGrid.length; k++) {
                            for (int l = 0; l < newGrid.length; l++) {
                                System.out.print(newGrid[k][l] + " ");
                            }
                            System.out.println();
                        }

                        Node newNode = new Node(newGrid, misplacedTileHeuristic(newGrid), parentNode.g+1);
                        nodesOpened++;
                        openList.add(newNode);
                    }

                    // check if the bottom move available, if so create new node
                    if (parentNode.coordinateYempty+1 <= 2 && parentNode.coordinateYempty+1 >= 0){

                        int[][] newGrid = createNewGrid(parentNode.grid);

                        System.out.println("bottom move");
                        for (int k = 0; k < parentNode.grid.length; k++) {
                            for (int l = 0; l < parentNode.grid.length; l++) {
                                System.out.print(parentNode.grid[k][l] + " ");
                            }
                            System.out.println();
                        }

                        newGrid[parentNode.coordinateYempty][parentNode.coordinateXempty] = newGrid[parentNode.coordinateYempty+1][parentNode.coordinateXempty];
                        newGrid[parentNode.coordinateYempty+1][parentNode.coordinateXempty] = 0;

                        for (int k = 0; k < newGrid.length; k++) {
                            for (int l = 0; l < newGrid.length; l++) {
                                System.out.print(newGrid[k][l] + " ");
                            }
                            System.out.println();
                        }

                        Node newNode = new Node(newGrid, misplacedTileHeuristic(newGrid), parentNode.g+1);
                        nodesOpened++;
                        openList.add(newNode);
                    }
                    // check if the left move available, if so create new node
                    if (parentNode.coordinateXempty-1 <= 2 && parentNode.coordinateXempty-1 >= 0){

                        int[][] newGrid = createNewGrid(parentNode.grid);

                        System.out.println("left move");

                        for (int k = 0; k < newGrid.length; k++) {
                            for (int l = 0; l < newGrid.length; l++) {
                                System.out.print(newGrid[k][l] + " ");
                            }
                            System.out.println();
                        }

                        newGrid[parentNode.coordinateYempty][parentNode.coordinateXempty] = newGrid[parentNode.coordinateYempty][parentNode.coordinateXempty-1];
                        newGrid[parentNode.coordinateYempty][parentNode.coordinateXempty-1] = 0;

                        for (int k = 0; k < newGrid.length; k++) {
                            for (int l = 0; l < newGrid.length; l++) {
                                System.out.print(newGrid[k][l] + " ");
                            }
                            System.out.println();
                        }

                        Node newNode = new Node(newGrid, misplacedTileHeuristic(newGrid), parentNode.g+1);
                        nodesOpened++;
                        openList.add(newNode);
                    }
                    // check if the right move available, if so create new node
                    if (parentNode.coordinateXempty+1 <= 2 && parentNode.coordinateXempty+1 >= 0){

                        int[][] newGrid = createNewGrid(parentNode.grid);

                        System.out.println("right Move");

                        newGrid[parentNode.coordinateYempty][parentNode.coordinateXempty] = newGrid[parentNode.coordinateYempty][parentNode.coordinateXempty+1];
                        newGrid[parentNode.coordinateYempty][parentNode.coordinateXempty+1] = 0;
                        Node newNode = new Node(newGrid, misplacedTileHeuristic(newGrid), parentNode.g+1);
                        nodesOpened++;
                        openList.add(newNode);
                    }



        Collections.sort(openList);
        Collections.reverse(openList);
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

}
