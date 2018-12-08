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
	}
}

