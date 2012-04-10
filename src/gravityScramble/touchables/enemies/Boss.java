package gravityScramble.touchables.enemies;

import gravityScramble.switchActions.SwitchAction2;
import gravityScramble.touchables.Player;
import gravityScramble.touchables.Touchable;
import java.io.Serializable;

public abstract class Boss extends Enemy
  implements Serializable
{
  protected int health;
  protected Player player;
  protected boolean battle;
  protected Touchable leftBoundary;
  protected Touchable rightBoundary;
  protected int difficulty;
  protected SwitchAction2 defeatAction;

  public Boss(int w, int h, int pos, int alt, int opa, int heal, Player pl, Touchable left, Touchable right, SwitchAction2 defAction, String cl)
  {
    super(w, h, pos, alt, false, opa, "none", cl);
    health = heal;
    player = pl;
    battle = false;
    leftBoundary = left;
    rightBoundary = right;
    difficulty = 1;
    defeatAction = defAction;
  }

  public int getHealth()
  {
    return health;
  }

  public boolean getBattle() {
    return battle;
  }

  public void setDifficulty()
  {
    difficulty = 2;
  }
}