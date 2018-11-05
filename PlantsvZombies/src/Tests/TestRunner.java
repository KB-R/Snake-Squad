package Tests;

import Tests.*;
import junit.framework.*;

public class TestRunner{
	
	public static void main(String[] args){
		junit.textui.TextRunner.run(TestRunner.class);
	}

	public static Test suite() {
		TestSuite suite = new TestSuite("Test NPC..");
		suite.addTest(new TestSuite(TestNPC.class));
		// add more tests here
	}
}

