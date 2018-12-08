compile: 
	javac -d ../bin src/Characters/* src/Controller/* src/gameModel/* src/Views/* > /dev/null 2>&1

run: compile
	cd ../bin && java Controller.GameController > /dev/null 2>&1

jar:
	cd ../bin && jar cvfe pvz.jar Controller.GameController .

runjar:
	cd ../bin && java -jar pvz.jar

compiletest:
	javac -d ../bin src/Tests/TestBasic.java

ct:
	javac -d ../bin -cp .:"/Users/apple/Downloads/java/*" src/Tests/* src/Characters/* src/Controller/* src/gameModel/* src/Views/*

rt:
	cd ../bin && java -cp .:"/Users/apple/Downloads/java/*" Tests.CharacterTestSuite Tests.TestGameView