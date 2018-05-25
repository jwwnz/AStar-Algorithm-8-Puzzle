package com.company;

import com.company.heuristics.ManhattanDistanceHeuristic;

public class PNode {

    String state;
    String goalState = "123456780";
    int epsilon = 1;
    int costG;
    int costH;
    int costF;
    int costP;
    PNode parentNode;

    //  Constructor to create Initial node:
    public PNode(String state, int costG) {
        this.state = state;
        this.costG = costG;
//        this.costH = misplacedHeuristic();
        this.costH = new ManhattanDistanceHeuristic().calculateHeuristic(state, 0);
//        this.costH = manhattanHeuristic(0);
//                this.costH = manhattanHeuristic(1);
        this.costF = costG + costH;
        this.costP = Math.max(this.costG + epsilon, this.costF);
    }

//    public int manhattanHeuristic(int minus){
//        int costH = 0;
//        for (int i = 0; i < state.length(); i++){
//            // the current location of a particular char, and get Y and X (excluding blank tile 0).
//            char currentChar = state.charAt(i);
//
//            // the goal index of the particular char.
//            int goalIndex = goalState.indexOf(currentChar);
//
//            if (currentChar != '0'){
//                // add the differences together
//                costH += Math.abs(retrieveY(i) - retrieveY(goalIndex));
//                costH += Math.abs(retrieveX(i) - retrieveX(goalIndex));
//            }
//        }
//        return Math.max(0, costH - minus);
//    }

    public int misplacedHeuristic() {
        int costH = 0;
        for (int i = 0; i < state.length(); i++){
            if (state.charAt(i) != goalState.charAt(i)) {
                costH++;
            }
        }
        return costH;
    }

//    public int retrieveY (int index){
//        // with the index get the Y value
//        if (index == 0 || index == 1 || index == 2) return 0;
//        else if (index == 3 || index == 4 || index == 5) return 1;
//        else return 2;
//    }
//
//    public int retrieveX (int index){
//        // with the index get the Y value
//        if (index == 0 || index == 3 || index == 6) return 0;
//        else if (index == 1 || index == 4 || index == 7) return 1;
//        else return 2;
//    }
}
