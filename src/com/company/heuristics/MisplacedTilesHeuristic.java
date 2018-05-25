package com.company.heuristics;

public class MisplacedTilesHeuristic extends Heuristic {

    String name = "Misplaced Tile";
    String goalState = "123456780";

    @Override
    public int calculateHeuristic(String state) {
        int costH = 0;
        for (int i = 0; i < state.length(); i++){
            if (state.charAt(i) != goalState.charAt(i)) {
                costH++;
            }
        }
        return costH;
    }
}
