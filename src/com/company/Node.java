package com.company;

import java.util.Arrays;

public class Node implements Comparable<Node> {
    int[][] grid = new int[3][3];
    int h;
    int g;
    int f;
    int coordinateYempty;
    int coordinateXempty;
    Node parent;


    public Node(int[][] grid, int h) {
        this.grid = grid;
        this.h = h;
        this.g = 0;

        for (int i = 0; i < this.grid.length; i++) {
            for(int j = 0; j < this.grid.length; j++) {
                if (this.grid[i][j] == 0) {
                    coordinateYempty = i;
                    coordinateXempty = j;
                }
            }
        }
    }

    public Node(int[][] grid, int h, Node parentNode) {
        this.grid = grid;
        this.h = h;
        this.parent = parentNode;
        this.g = parentNode.g + 1;
        f = g + h;

        for (int i = 0; i < this.grid.length; i++) {
            for(int j = 0; j < this.grid.length; j++) {
                if (this.grid[i][j] == 0) {
                    coordinateYempty = i;
                    coordinateXempty = j;
                }
            }
        }


    }

    // Compare by f values, if f values the same then compare by h value
    @Override
    public int compareTo(Node o) {

        if (this.f == o.f) {
            return Integer.compare(this.h, o.h);
        }

        return Integer.compare(this.f, o.f);
    }
}
