package gravityScramble.switchActions;

import gravityScramble.touchables.obstacles.GravityChamber;

public class SwitchAction2 implements SwitchAction {

	GravityChamber[] gravityChambers;

	public SwitchAction2(GravityChamber[] g) {
		gravityChambers = g;
	}

	public void action() {
		for (int i = 0; i < gravityChambers.length; i++) {
			gravityChambers[i].setActive();
		}
	}
}