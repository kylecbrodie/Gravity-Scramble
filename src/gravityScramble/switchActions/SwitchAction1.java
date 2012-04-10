package gravityScramble.switchActions;

import gravityScramble.touchables.obstacles.GravityChamber;

public class SwitchAction1 implements SwitchAction {

	private GravityChamber grav;

	public SwitchAction1(GravityChamber g) {
		grav = g;
	}

	public void action() {
		grav.goBack();
	}
}