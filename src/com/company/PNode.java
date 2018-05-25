package com.company;

import com.company.heuristics.Heuristic;
import com.company.heuristics.ManhattanDistanceHeuristic;
import com.company.heuristics.MisplacedTilesHeuristic;

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
    public PNode(String state, int costG, Heuristic heuristic) {
        this.state = state;
        this.costG = costG;
        this.costH = heuristic.calculateHeuristic(state);
        this.costF = costG + costH;
        this.costP = Math.max(this.costG + epsilon, this.costF);
    }
}
