package com.company;

import com.company.heuristics.Heuristic;

public abstract class PuzzleSolver {

    private String goalState;
    private PNode nodeCurrent;
    private int expandedNodes;
    private int generatedNodes;
    private int evaluatedNodes;
    public Heuristic heuristicUsed;

    public abstract void search();
}
