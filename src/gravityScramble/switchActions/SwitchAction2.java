package gravityScramble.switchActions;

import gravityScramble.touchables.obstacles.GravityChamber;
import java.io.Serializable;

public class SwitchAction2
  implements SwitchAction, Serializable
{
  GravityChamber[] gravityChambers;

  public SwitchAction2(GravityChamber[] g)
  {
    gravityChambers = g;
  }

  public void action()
  {
    for (int x = 0; x < gravityChambers.length; x++)
    {
      gravityChambers[x].setActive();
    }
  }
}