package gravityScramble.switchActions;

import gravityScramble.touchables.obstacles.GravityChamber;
import java.io.Serializable;

public class SwitchAction1
  implements SwitchAction, Serializable
{
  private GravityChamber grav;

  public SwitchAction1(GravityChamber g)
  {
    grav = g;
  }

  public void action()
  {
    grav.goBack();
  }
}