compile: 
	javac -d ../bin src/Characters/* src/Controller/* src/gameModel/* src/Views/* > /dev/null 2>&1

run: compile
	cd ../bin && java Controller.GameController > /dev/null 2>&1

jar:
	cd ../bin && jar cvfe pvz.jar Controller.GameController .

runjar:
	cd ../bin && java -jar pvz.jar