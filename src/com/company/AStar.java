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
        
                if (parentNode.grid[i][j] == 0) {
                    // check if top move available, if so create new
                    if (i-1 <= 2 && i-1 >= 0){

                        int[][] newGrid = Arrays.copyOf(parentNode.grid, parentNode.grid.length);

//                        System.arraycopy(parentNode.grid, 0, newGrid, 1, parentNode.grid.length);

                        System.out.println("number: " + newGrid[i-1][j]);

                        for (int k = 0; k < newGrid.length; k++) {
                            for (int l = 0; l < newGrid.length; l++) {
                                System.out.print(newGrid[k][l] + " ");
                            }
                            System.out.println();
                        }


                        newGrid[i][j] = newGrid[i-1][j];
                        newGrid[i-1][j] = 0;

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
                    if (i+1 <= 2 && i+1 >= 0){
                        int[][] newGrid = Arrays.copyOf(parentNode.grid, parentNode.grid.length);

                        for (int k = 0; k < parentNode.grid.length; k++) {
                            for (int l = 0; l < parentNode.grid.length; l++) {
                                System.out.print(parentNode.grid[k][l] + " ");
                            }
                            System.out.println();
                        }

                        newGrid[i][j] = newGrid[i+1][j];
                        newGrid[i+1][j] = 0;

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
                    if (j-1 <= 2 && j-1 >= 0){

                        int[][] newGrid = new int[3][3];
                        System.arraycopy(parentNode.grid, 0, newGrid, 0, parentNode.grid.length);

                        newGrid[i][j] = newGrid[i][j-1];
                        newGrid[i][j-1] = 0;
                        Node newNode = new Node(newGrid, misplacedTileHeuristic(newGrid), parentNode.g+1);
                        nodesOpened++;
                        openList.add(newNode);
                    }
                    // check if the right move available, if so create new node
                    if (j+1 <= 2 && j+1 >= 0){

                        int[][] newGrid = new int[3][3];
                        System.arraycopy(parentNode.grid, 0, newGrid, 0, parentNode.grid.length);

                        newGrid[i][j] = newGrid[i][j+1];
                        newGrid[i][j+1] = 0;
                        Node newNode = new Node(newGrid, misplacedTileHeuristic(newGrid), parentNode.g+1);
                        nodesOpened++;
                        openList.add(newNode);
                    }
                }


        Collections.sort(openList);
        Collections.reverse(openList);
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
