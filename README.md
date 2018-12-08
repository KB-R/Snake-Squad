# Plants Vs Zombies
### SYSC3110 Third Year Project

### Running The Game
The bin directory contains the compiled java code for the project.
For ease of use a makefile has been provided in the PlantsvZombies directory. 
The makefile can compile and run the project with one simple commmand assuming
an a unix based system is in use.

`make run` 

will compile the code and put the class files in `bin` directory then it will
run the project.

`make runjar`

will run the a version of the game from a jar file inside the /bin directory 

If changes are made to the game a new executable jar file can be created using 

`make compile` then `make jar`

### Implementation Decisions

### Resources
[Git Cheat Sheet](https://services.github.com/on-demand/downloads/github-git-cheat-sheet/)
*IMPORTANT:* In order to get the view and game controller test to work, you must click the ok button until the game's frame stops popping up.

### Milestone 4 :rocket:
Milestone 4 focuses the group on finalizing the Graphic User Interface (GUI)-based version of Plants V Zombies, allowing the user to create their levels, allowing the user to save and load their game and transfering the game to android, while the base game can be run through  eclipse. The game is fully loaded with all variations of zombies and plants.  What is required for this Milestone is the entire project to be completed and fully functional without any bugs or glitches, and thats what we have delivered. Since this is the fourth  Milestone, everything done was to finalize of the implementation of the game, while including the load and save features. The aim for our code design was to loosly couple our code as much as possible during the first Milestone; making it not tedious to reuse it for future Milestones. Justifiably we used interfaces and super classes to achieve this goal. 

 :+1: Complete Implementation: fully-featured with various types of plants and zombies, and unlimited undo/redo feature.

1. Save/load features. You may use Java Serialization to achieve this. 
2. Game level builder. The levels may be saved in XML. 
3. 5% overall project bonus: try a real time version of the game and/or porting it to the Android platform.

* Maxime: Save and Load Functionality, Make file and Jar implementation.
* Kurt: Level Builder Functionality, Readme, Documentation and Android conversion. 
* Anthony: UML and Sequence Diagram
* Tareq: User Manual

**Deliverables:** readme file + code + tests + documentation.

**Deadline:** Friday December 7th. Weight: 35% of the overall project grade.

### Members
[Plants Vs Zombies](https://github.com/KB-R/Snake_Squad) made with :purple_heart: by:

[Maxime](https://github.com/MaximeNdutiye), 
[Kurt](https://github.com/KB-R), 
[Anthony](https://github.com/anthonymaevskipopov), 
[Tareq](https://github.com/hanafiswag)
