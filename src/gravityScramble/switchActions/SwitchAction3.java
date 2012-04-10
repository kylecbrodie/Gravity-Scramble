package gravityScramble.switchActions;

import gravityScramble.touchables.obstacles.FireCube;

public class SwitchAction3 implements SwitchAction {

	FireCube[] fireCubes;

	public SwitchAction3(FireCube[] fire) {
		fireCubes = fire;
	}

	public void action() {
		for (int i = 0; i < fireCubes.length; i++) {
			fireCubes[i].turnOff();
		}
	}
}