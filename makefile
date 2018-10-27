compile: 
	javac -d output src/models/* src/controllers/*

run:
	java controller.GameController

uncompress:
	jar cf project *.java

compress:
	jar xf project 
