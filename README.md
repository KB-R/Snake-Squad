# Plants Vs Zombies
### SYSC3110 Third Year Project

### Running The Game
The output directory contains the compiled code.
It must first be added to your classpath.

`make compile` will compile the code and put the class file in `./output`

`export CLASSPATH=$(pwd)/output` will add the output folder to the java classpath for **linux** and **macos** machines

`make run` will start the game

### Implementation Decisions

### Resources
[Git Cheat Sheet](https://services.github.com/on-demand/downloads/github-git-cheat-sheet/)

### Milestone 2 :rocket:
Milestone 2 focuses the group on developing a Graphic User Interface (GUI)-based version of Plants V Zombies that can be run through the console of eclipse. The required components of the game for this iteration are sunflowers, one other type of plant, one type of zombie, just one level. Since this is the second Milestone, everything done was to form the rough materialization of the implementation of the game. The aim for our code design was to loosly couple our code as much as possible during the first Milestone; making it not tedious to reuse it for future Milestones. Justifiably we used interfaces and super to achieve this goal. Going forward we will be working to make the visual representation of the game run as smooth as possible, while developing a gradually more intricate UML digram. At this moment there is 1 known issue with the game, zombies walk past plants. 

**Deliverables:** A readme file was created to elaborate on the decisions we made to develop the game, the code that allows the game to run, the UML diagram that depicts the relationship between our classes and the Java-documentation that makes our code readable and easier to navigate. The separation of classes, tests and into multiple packages. All of these items make up the deliverables for this Milestone; they are put into a zip file for submission.

Kurt: Documentation, UML Diagram, Tests
Anthony: Tests
Maxime: View[Package]
Tareq: Documentation

**Deadline:** Friday November 16th. Weight: 20% of the overall project grade.

### Milestone 3
Complete Implementation: fully-featured with various types of plants and zombies, and unlimited undo/redo feature.

**Deliverables:** readme file + code + refined tests + refined design + documentation.

**Deadline:** Monday November 18th. Weight: 30% of the overall project grade.

### Milestone 4

1. Save/load features. You may use Java Serialization to achieve this. 
2. Game level builder. The levels may be saved in XML. 
3. 5% overall project bonus: try a real time version of the game and/or porting it to the Android platform.

**Deliverables:** readme file + code + tests + documentation.

**Deadline:** Monday December 2nd. Weight: 35% of the overall project grade.

### Members
[Plants Vs Zombies](https://github.com/KB-R/Snake_Squad) made with :purple_heart: by:

[Maxime](https://github.com/MaximeNdutiye), 
[Kurt](https://github.com/KB-R), 
[Anthony](anthonymaevskipopov), 
[Tareq]()
