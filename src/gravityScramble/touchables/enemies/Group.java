package gravityScramble.touchables.enemies;

import gravityScramble.touchables.Touchable;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;

public class Group extends MovingEnemy
  implements Serializable
{
  public ArrayList<MovingEnemy> enemies;
  private ArrayList<MovingEnemy> initialEnemies;
  private int space;
  private int counter;

  public Group(int pos, int alt, ArrayList<MovingEnemy> e, int sp, Touchable l, Touchable r)
  {
    super(0, 0, pos, alt, false, 255, "none", "right", l, r, "group");
    enemies = e;
    space = sp;
    for (int x = 0; x < enemies.size(); x++)
    {
      width += ((MovingEnemy)enemies.get(x)).getWidth();
      width += space;
      if (x == 0)
      {
        ((MovingEnemy)enemies.get(x)).setCoordinates(pos, alt);
      }
      else
      {
        ((MovingEnemy)enemies.get(x)).setCoordinates(((MovingEnemy)enemies.get(x - 1)).getPosition() + ((MovingEnemy)enemies.get(x - 1)).getWidth() + space, alt);
      }

      ((MovingEnemy)enemies.get(x)).setInitialCoordinates(((MovingEnemy)enemies.get(x)).getPosition(), ((MovingEnemy)enemies.get(x)).getAltitude());
    }

    width -= space;
    for (int x = 0; x < enemies.size(); x++)
    {
      updateBoundaries(x);
    }
    initialEnemies = new ArrayList();
    for (int x = 0; x < e.size(); x++)
    {
      initialEnemies.add(e.get(x));
    }
    counter = 0;
  }

  public void drawing(Graphics g, int x, int y)
  {
    for (int a = 0; a < enemies.size(); a++)
    {
      ((MovingEnemy)enemies.get(a)).paintComponent(g, x, y);
    }
  }

  public void updateBoundaries(int x)
  {
    if (x == 0)
    {
      ((MovingEnemy)enemies.get(x)).leftBoundary = leftBoundary;
    }
    else
    {
      ((MovingEnemy)enemies.get(x)).leftBoundary = ((Touchable)enemies.get(x - 1));
    }
    if (x == enemies.size() - 1)
    {
      ((MovingEnemy)enemies.get(x)).rightBoundary = rightBoundary;
    }
    else
    {
      ((MovingEnemy)enemies.get(x)).rightBoundary = ((Touchable)enemies.get(x + 1));
    }
  }

  public void changePosition()
  {
    for (int a = 0; a < enemies.size(); a++)
    {
      if ((((MovingEnemy)enemies.get(a)).opacity != 0) || (!((MovingEnemy)enemies.get(a)).getDefeated()))
        continue;
      enemies.remove(a);
      if (enemies.size() > 0)
      {
        if (a > 0)
        {
          updateBoundaries(a - 1);
        }
        if (a < enemies.size())
        {
          updateBoundaries(a);
        }
      }
      width = 0;
      for (int b = 0; b < enemies.size(); b++)
      {
        width += ((MovingEnemy)enemies.get(b)).getWidth();
        width += space;
      }
      width -= space;
    }

    for (int x = 0; x < enemies.size(); x++)
    {
      ((MovingEnemy)enemies.get(x)).update();
    }
    if (counter > 0)
    {
      if (counter == 1)
      {
        enemies = new ArrayList();
        for (int x = 0; x < initialEnemies.size(); x++)
        {
          enemies.add(initialEnemies.get(x));
        }
        for (int x = 0; x < enemies.size(); x++)
        {
          ((MovingEnemy)enemies.get(x)).becomeDefeated();
          ((MovingEnemy)enemies.get(x)).setOpacity(0);
          ((MovingEnemy)enemies.get(x)).becomeSummoned();
          updateBoundaries(x);
        }
      }
      counter += 1;
      if (counter >= 52)
      {
        counter = 0;
      }
    }
  }

  public void changePowerUp(String p) {
  }

  public void becomeDefeated() {
  }

  public void becomeSummoned() {
    counter = 1;
  }
}