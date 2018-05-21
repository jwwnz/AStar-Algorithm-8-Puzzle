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

    public Node() {
    }

    public Node(int[][] grid, int h) {
        this.grid = grid;
        this.h = h;
    }

    public Node(int[][] grid, int h, int g) {
        this.grid = grid;
        this.h = h;
        this.g = g;
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

    @Override
    public int compareTo(Node o) {
        return Integer.compare(this.f, o.f);
    }
}
