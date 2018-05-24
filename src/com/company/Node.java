package com.company;

public class Node implements Comparable<Node> {
    int[][] grid;
    String stringGrid;
    int h;
    int g;
    int f;
    int zeroY;
    int zeroX;
    Node parent;


    public Node(int[][] grid, int h) {
        this.grid = grid;
        this.stringGrid = stringifyGrid(grid);
        this.h = h;
        this.g = 0;

        for (int i = 0; i < this.grid.length; i++) {
            for(int j = 0; j < this.grid.length; j++) {
                if (this.grid[i][j] == 0) {
                    zeroY = i;
                    zeroX = j;
                }
            }
        }
    }

    public Node(int[][] grid, int h, Node parentNode) {
        this.grid = grid;
        this.stringGrid = stringifyGrid(grid);
        this.h = h;
        this.parent = parentNode;
        this.g = parentNode.g;
        f = g + h;

        for (int i = 0; i < this.grid.length; i++) {
            for(int j = 0; j < this.grid.length; j++) {
                if (this.grid[i][j] == 0) {
                    zeroY = i;
                    zeroX = j;
                }
            }
        }
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

    // Compare by f values, if f values the same then compare by h value
    @Override
    public int compareTo(Node o) {

        if (this.f == o.f) {
            return Integer.compare(this.h, o.h);
        }

        if (this.h == o.h) {
            return Integer.compare(this.g, o.g);
        }

        return Integer.compare(this.f, o.f);
    }
}
