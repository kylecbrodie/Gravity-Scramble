package gravityScramble.switchActions;

import gravityScramble.touchables.obstacles.FireCube;
import java.io.Serializable;

public class SwitchAction3
  implements SwitchAction, Serializable
{
  FireCube[] fireCubes;

  public SwitchAction3(FireCube[] fire)
  {
    fireCubes = fire;
  }

  public void action()
  {
    for (int x = 0; x < fireCubes.length; x++)
    {
      fireCubes[x].turnOff();
    }
  }
}