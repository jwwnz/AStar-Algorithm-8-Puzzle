package com.company;

import com.company.heuristics.Heuristic;

import java.util.*;

public class PuzzleASharp extends PuzzleSolver {

    private String goalState = "123456780";
    private PNode nodeCurrent;
    private int expandedNodes = 0;
    private int generatedNodes = 0;
    private int evaluatedNodes = 0;
    private Heuristic heuristicUsed;
    private boolean finished = false;

    private Comparator<PNode> comparator = new Comparator<PNode>() {
        @Override
        public int compare(PNode o1, PNode o2) {
            // ASharp algorithm differentiator #2: sorts openList by P-cost ( max(G-cost + 1, F-cost) )
            if (o1.costP == o2.costP) {
                return Integer.compare(o1.costH, o2.costH);
            }
            return Integer.compare(o1.costP, o2.costP);
        }
    };

    private PriorityQueue<PNode> openList = new PriorityQueue<>(comparator);
    private HashMap<String, PNode> closedList = new HashMap<>();

    // Setup new Puzzle and starting node to openList.
    public PuzzleASharp(String startState, Heuristic heuristicUsed) {
        this.heuristicUsed = heuristicUsed;
        PNode startNode = new PNode(startState, 0, this.heuristicUsed);
        evaluatedNodes++;
        openList.add(startNode);
    }

    @Override
    public void search() {
        // While openList contains node, continue search.
        while (!openList.isEmpty()) {
            // Take best Node from open list (lowest F value)

            nodeCurrent = openList.poll();
//            System.out.println("Current Node F-level: " + nodeCurrent.costF);
//            System.out.println("Current Node H-level: " + nodeCurrent.costH);

            if(nodeCurrent.state.equals(goalState)){
                finishedOutput();
                return;
            }

//            System.out.println("Expanded nodes currently: "+ expandedNodes);
            expandNode(nodeCurrent);
            // If Neighbours state satisfied Goal then exit
            if (finished){
                finishedOutput();
                return;
            }

            expandedNodes++;


        }
    }

    private void expandNode(PNode nodeCurrent) {
        // find possible node expansions (up, down, left, right).
        // generator successor if expansion possible.
        int index0 = nodeCurrent.state.indexOf('0');
        int moveZeroBy = 0;
        int currentCostG = nodeCurrent.costG;

        // Can go up
        if (index0 > 2) {
            generateSuccessor(updateState(nodeCurrent.state, index0, moveZeroBy - 3), currentCostG);
        }
        // Can go down
        if (index0 < 6){
            generateSuccessor(updateState(nodeCurrent.state, index0, moveZeroBy + 3), currentCostG);
        }
        // can go left
        if (index0 != 0 && index0 != 3 && index0 != 6){
            generateSuccessor(updateState(nodeCurrent.state, index0, moveZeroBy - 1), currentCostG);
        }
        // can go right
        if (index0 != 2 && index0 != 5 && index0 != 8){
            generateSuccessor(updateState(nodeCurrent.state, index0, moveZeroBy + 1), currentCostG);
        }

        closedList.put(nodeCurrent.state, nodeCurrent);
    }

    private void generateSuccessor(String newState, int parentCostG) {

        generatedNodes++;

        // A# differentiator #1: if newState of nodeSuccessor equals to goalState finish output!
        if (newState.equals(goalState)) {
            System.out.println("A# helped");
            this.finished = true;
            return;
        }
        // create a new Node (nodeSuccessor).
        PNode nodeSuccessor = new PNode(newState, parentCostG + 1, this.heuristicUsed);
        evaluatedNodes++;

        // check if openList contains this new State?
        Iterator<PNode> iter = openList.iterator();

        while (iter.hasNext()) {
            PNode node = iter.next();
            // if the nodeSuccessor is on the openList BUT if existing is just as good then discard
            if (node.state.equals(newState) && node.costF <= nodeSuccessor.costF) {
                return;
            } else if (node.state.equals(newState) && node.costF > nodeSuccessor.costF) {
                iter.remove();
            }
        }

        // if nodeSuccessor is on the Closed list, but the existing one is as good then discard
        if (closedList.containsKey(newState) && closedList.get(newState).costF <= nodeSuccessor.costF){
            return;
        } else if (closedList.containsKey(newState) && closedList.get(newState).costF > nodeSuccessor.costF) {
            closedList.remove(newState);
        }

        // add parent of nodeSuccessor to nodeCurrent (pointer)
        nodeSuccessor.parentNode = nodeCurrent;

        // add nodeSuccessor to openList
        openList.add(nodeSuccessor);
    }

    private String updateState(String currentState, int indexZero, int moveZeroBy){
        // Change state by swapping around 0 value with move
        char movedChar = currentState.charAt(indexZero + moveZeroBy);

        // swapping moving Char with 0.
        char[] charSet = currentState.toCharArray();
        charSet[indexZero] = movedChar;
        charSet[indexZero + moveZeroBy] = '0';

        return new String(charSet);
    }

    private void finishedOutput() {
        System.out.println("Reached Goalstate!");
        System.out.println("Final F-level: " + nodeCurrent.costF);
        System.out.println("Final H-level: " + nodeCurrent.costH);
        System.out.println("Nodes expanded: " + expandedNodes);
        System.out.println("Nodes generated: " + generatedNodes);
        System.out.println("Nodes evaluated: " + evaluatedNodes);
        return;
    }

}
