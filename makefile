compile: 
	javac -d output src/models/* src/controllers/*

run:
	java controller.GameController

uncompress:
	jar cf ...

compress:
	jar xf ..