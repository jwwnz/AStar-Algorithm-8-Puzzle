# A Star v A# the application

## Quick overview
This is a Java application which was build as part of an experiment
to see whether we can improve the node expansion 
optimality of A Star by including small information that we know about the problem
domain.

#### The Algorithm #A
This new algorithm A# is a state of the art algorithm developed by Mike
Barley and the Computer Science Department at the University of Auckland.

The two optimisations to A Star in this algorithm are:
1. Termination condition occurs when a neighbouring node is the goalstate.
2. Ordering nodes in the open list by a P-value instead of F-Value. This P value
is obtained by getting the max(G-Cost + 1, F-Value).

Full details about the experiment's purpose, methodology and results can be 
found in the accompanying report.

The purpose of this Readme file is to give a quick overview as to how to initiate
and run this

## Instructions to run the experiment

The classes within this package is self-contained to run the experiment.

Please note:
- that this package has been specifically made to run A* and A# for the 
8 Puzzle domain with the goal state being "123456780". 
- Also due to the problem sets used being produced through Prolog, the problem sets will
need to define the index of the numbers at its displayed location, and not the actual
problem set.
- Due to the structure of the CSV output, this program will need you to give uniform optimal 
lengths for all problems inside of a specific problem set (Eg. the exemplar ones
are using 10 and 25).
Please keep this in mind when running any subsequent experiments.

### To change the problem files

In the Main class, specify the location of the file and problem length (optimal path length)
of your specified problem set.

To tweak or change the settings used in the experiment, please change the following 
parameters in the Main class:
1. fileLocation: specify the location of the problem set file.
2. problemLength: specify the problemLength of your problem set.

### To specify any new heuristics 

Currently we have implemented three heuristics (set up as classes):
1. Manhattan.
2. Manhattan - 1 (Using same class as Manhattan but with overloaded method taking the minus value).
3. Misplaced Tiles.

These can be seen in the FileReader Class at line 47 - 79. 

Should you wish to add a new heuristic, please extend the Heuristic 
abstract class, and override the calculateHeuristic() method in
a similar way to that in the Manhattan and MisplacedTile classes.

### To run the project.
Everything has been set up to run.

You can initiate the experiment by running the Main class.

Output will be given as a CSV file in the format of:

"length[problemLength]data.csv"

This will be outputed to the root directory of this project.

For example. For the 10 and 25 problem length files currently 
wired up these will be outputed as "length10data.csv"
and "length25data.csv" respectively.

 