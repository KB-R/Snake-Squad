package Tests;

import static org.junit.jupiter.api.Assertions.*;

import gameModel.GameObjectsController;
import Characters.DoublePeaShooter;
import Characters.PeaShooter;
import Characters.Sunflower;
import Characters.Walnut;
import Characters.Zombie;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestGameModel {
	GameObjectsController goc;
	Zombie z1;
	Zombie z2;
	Sunflower sf1;
	Sunflower sf2;
	Sunflower sf3;
	Sunflower sf4;
	Sunflower sf5;
	PeaShooter ps1;
	PeaShooter ps2;
	PeaShooter ps3;
	PeaShooter ps4;
	PeaShooter ps5;

	@BeforeEach
	void setUp() {
		goc = new GameObjectsController();
		z1 = new Zombie(10, 1, 0);
		z2 = new Zombie(20, 2, 0);

		goc.addZombie(z1);
		goc.addZombie(z2);
		goc.addZombie(z1);
		goc.addZombie(z2);
		goc.addZombie(z1);

		sf1 = new Sunflower(0, 0, 0);
		sf2 = new Sunflower(1, 0, 0);
		sf3 = new Sunflower(3, 0, 0);
		sf4 = new Sunflower(2, 1, 0);
		sf5 = new Sunflower(1, 2, 0);

		goc.addSunflower(sf5);
		goc.addSunflower(sf4);
		goc.addSunflower(sf3);
		goc.addSunflower(sf2);
		goc.addSunflower(sf1);

		ps1 = new PeaShooter(0, 1, 1, 0);
		ps2 = new PeaShooter(1, 1, 1, 0);
		ps3 = new PeaShooter(3, 1, 1, 0);
		ps4 = new PeaShooter(4, 2, 1, 0);
		ps5 = new PeaShooter(1, 3, 1, 0);

		goc.addPeaShooter(ps5);
		goc.addPeaShooter(ps4);
		goc.addPeaShooter(ps3);
		goc.addPeaShooter(ps2);
		goc.addPeaShooter(ps1);

		Walnut w1 = new Walnut(3, 3, 0);
		Walnut w2 = new Walnut(5, 3, 0);

		goc.addWalnut(w2);
		goc.addWalnut(w1);
		goc.addWalnut(w1);
	}

	@Test
	void testTime() {
		goc.updateTime();
		goc.updateTime();
		goc.updateTime();
		
		assertEquals(3, goc.getTime());
		
		goc.setUndo();
		goc.updateTime();
		
		assertEquals(4, goc.getTime());
	}

	@Test
	void testSunflowers() {
		int x = goc.getSunflowers().size();
		
		assertEquals(5, x);
		
		goc.addSunflower(sf1);
		x = goc.getSunflowers().size();
		
		assertEquals(6, x);
	}

	@Test
	void testPeashooters() {
		DoublePeaShooter d = new DoublePeaShooter(9, 1, 2, 0);
		goc.addDoublePeaShooter(d);
	
		assertEquals(5, goc.getPeaShooters().size());
		assertEquals(1, goc.getDoublePeaShooters().size());
	}

}
