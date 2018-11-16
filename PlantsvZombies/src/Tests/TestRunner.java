package Tests;

import junit.framework.*;

public class TestRunner extends junit.framework.TestCase{
	
	public static void main(String[] args){
		junit.textui.TestRunner.run(TestRunner.class);
	}

	public static void testsuite() {
		TestSuite suite = new TestSuite("Test NPC..");
		suite.addTest(new TestSuite(TestNPC.class));
		// add more tests here
	}
}

