package Tests;
import junit.framework.TestCase;
import junit.framework.*;

public class CharacterTestSuite extends TestCase{
	
	public static void main(String[] args){
		junit.textui.TestRunner.run(CharacterTestSuite.class);
		junit.textui.TestRunner.run(TestGameView.class);
		junit.textui.TestRunner.run(TestLawnmower.class);
		junit.textui.TestRunner.run(TestSunflower.class);
		junit.textui.TestRunner.run(TestZombie.class);
		junit.textui.TestRunner.run(TestPeaShooter.class);
		junit.textui.TestRunner.run(TestWalnut.class);
		junit.textui.TestRunner.run(TestGOCManager.class);
	}

	public static void testsuite() {
		TestSuite suite = new TestSuite("Test NPC..");
<<<<<<< HEAD
=======
		suite.addTest(new TestSuite(TestLawnmower.class));
		suite.addTest(new TestSuite(TestSunflower.class));
		suite.addTest(new TestSuite(TestZombie.class));
		suite.addTest(new TestSuite(TestPeaShooter.class));
		suite.addTest(new TestSuite(TestWalnut.class));
		suite.addTest(new TestSuite(TestGameModel.class));
>>>>>>> dd0c20a59abda68e6c5772ab9b16dfd64bf45712
	}
}
