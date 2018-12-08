package Tests;
import junit.framework.TestCase;
import junit.framework.*;

public class CharacterTestSuite extends TestCase{
	
	public static void main(String[] args){
		junit.textui.TestRunner.run(CharacterTestSuite.class);
	}

	public static void testsuite() {
		TestSuite suite = new TestSuite("Test NPC..");
		suite.addTest(new TestSuite(TestLawnmower.class));
		suite.addTest(new TestSuite(TestSunflower.class));
		suite.addTest(new TestSuite(TestZombie.class));
		suite.addTest(new TestSuite(TestPeaShooter.class));
		suite.addTest(new TestSuite(TestWalnut.class));
		suite.addTest(new TestSuite(TestGameModel.class));
		suite.addTest(new TestSuite(TestGameView.class));
	}
}

