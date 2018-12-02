# Plants Vs Zombies
### SYSC3110 Third Year Project

### Running The Game
The output directory contains the compiled code.
It must first be added to your classpath.

`make compile` will compile the code and put the class file in `./output`

`export CLASSPATH=$(pwd)/bin` will add the output folder to the java classpath for **linux** and **macos** machines

`make run` will start the game

### Implementation Decisions

### Resources
[Git Cheat Sheet](https://services.github.com/on-demand/downloads/github-git-cheat-sheet/)

### Milestone 3 :rocket:
Milestone 3 focuses the group on finalizing the Graphic User Interface (GUI)-based version of Plants V Zombies that can be run through  eclipse. The game will be fully loaded with all variations of zombies and plants.  The required components of the game for this iteration are sunflowers, multiple types of plants, multiple types of zombies and just one level. Since this is the third  Milestone, everything done was to finalize of the implementation of the game, while including the undo and redo features. The aim for our code design was to loosly couple our code as much as possible during the first Milestone; making it not tedious to reuse it for future Milestones. Justifiably we used interfaces and super to achieve this goal. Going forward we will be working on making the save feature compatiable with the present form of the game. We also aim to make the visual representation of the game run as smooth as possible where the real time bonus can be added, while developing a gradually more intricate UML digram. At this moment there is 1 known issue with the game, zombies walk past plants. 
Complete Implementation: fully-featured with various types of plants and zombies, and unlimited undo/redo feature.

**Deliverables:** A readme file was created to elaborate on the decisions we made to develop the game, the code that allows the game to run, the UML diagram that depicts the relationship between our classes and the Java-documentation that makes our code readable and easier to navigate. The separation of the Models, View, Controllers, tests and into multiple packages. All of these items make up the deliverables for this Milestone; they are put into a zip file for submission.
readme file + code + refined tests + refined design + documentation.
**Deadline:** Sunday November 22nd. Weight: 30% of the overall project grade.

Kurt: Documentation, UML Diagram, New NPCs 
Maxime: View[Package], Undo/Redo
Anthony:
Tareq: Documentation
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
[Anthony](https://github.com/anthonymaevskipopov), 
[Tareq](https://github.com/hanafiswag)
