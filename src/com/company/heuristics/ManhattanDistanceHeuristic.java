package com.company.heuristics;

public class ManhattanDistanceHeuristic extends Heuristic {

    public String name = "Manhattan Distance";
    String goalState = "123456780";
    int minus;

    public ManhattanDistanceHeuristic() {
    }

    public ManhattanDistanceHeuristic(int minus) {
        this.minus = minus;
        this.name += -minus;
    }

    @Override
    public int calculateHeuristic(String state) {
        int costH = 0;
        for (int i = 0; i < state.length(); i++){
            // the current location of a particular char, and get Y and X (excluding blank tile 0).
            char currentChar = state.charAt(i);

            // the goal index of the particular char.
            int goalIndex = goalState.indexOf(currentChar);

            if (currentChar != '0'){
                // add the differences together
                costH += Math.abs(retrieveY(i) - retrieveY(goalIndex));
                costH += Math.abs(retrieveX(i) - retrieveX(goalIndex));
            }
        }
        return Math.max(0, costH - this.minus);
    }

    public int retrieveY (int index){
        // with the index get the Y value
        if (index == 0 || index == 1 || index == 2) return 0;
        else if (index == 3 || index == 4 || index == 5) return 1;
        else return 2;
    }

    public int retrieveX (int index){
        // with the index get the Y value
        if (index == 0 || index == 3 || index == 6) return 0;
        else if (index == 1 || index == 4 || index == 7) return 1;
        else return 2;
    }
}
