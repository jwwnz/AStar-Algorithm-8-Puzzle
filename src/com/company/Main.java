package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        AStar astar = new AStar();

        for(Node node : astar.openList) System.out.println(node.f);

        astar.search();
        System.out.println(astar.nodesOpened);

        for(Node node : astar.closedList) System.out.println(node.f);

    }
}
