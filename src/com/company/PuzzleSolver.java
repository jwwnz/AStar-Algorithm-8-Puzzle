package com.company;

import com.company.heuristics.Heuristic;

import java.util.List;

public abstract class PuzzleSolver {

    public String startState;
    private String goalState;
    private PNode nodeCurrent;
    private int expandedNodes;
    private int generatedNodes;
    private int evaluatedNodes;
    public String solverName;
    public Heuristic heuristicUsed;
    public int level;

    public abstract List<String> search();
}
