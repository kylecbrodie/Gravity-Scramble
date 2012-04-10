package gravityScramble.touchables.powerups;

import gravityScramble.touchables.Switch;
import gravityScramble.touchables.Touchable;
import gravityScramble.touchables.enemies.Enemy;
import gravityScramble.touchables.enemies.GiantCat;
import gravityScramble.touchables.enemies.Group;
import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;

public class Fireball extends Touchable
  implements Serializable
{
  private boolean active;
  private String direction;
  private Touchable thrower;

  public Fireball(int pos, int alt, Touchable t)
  {
    super(30, 30, pos, alt, "fireball");
    active = false;
    direction = "none";
    thrower = t;
  }

  public void paintComponent(Graphics g, int x, int y)
  {
    if (active)
    {
      g.setColor(Color.red);
      g.fillOval(drawingPosition(x), 610 - altitude + y, 30, 30);
    }
  }

  public void move()
  {
    if (direction == "left")
    {
      position -= 5;
    }
    if (direction == "right")
    {
      position += 5;
    }
    if (direction == "none")
    {
      position = (thrower.positionCenter() - width / 2);
      altitude = (thrower.getAltitude() + 75 - height / 2);
    }
    super.setCoordinates(position, altitude);
  }

  public void attack(Enemy e)
  {
    if ((active) && (isTouching(e)) && (e.getOpacity() == e.getSaveOpacity()))
    {
      if (e.whatClass() != "giantCat")
      {
        e.becomeDefeated();
      }
      else
      {
        ((GiantCat)e).becomeWeakened();
      }
    }
    if (e.whatClass().equals("giantCat"))
    {
      attack(((GiantCat)e).summonedCat1);
      attack(((GiantCat)e).summonedCat2);
      attack(((GiantCat)e).summonedBunny1);
      attack(((GiantCat)e).summonedBunny2);
      attack(((GiantCat)e).summonedFollower);
    }
    if ((e instanceof Group))
    {
      for (int x = 0; x < ((Group)e).enemies.size(); x++)
      {
        attack((Enemy)((Group)e).enemies.get(x));
      }
    }
  }

  public void hitSwitch(Switch s)
  {
    if ((active) && (isTouching(s)))
    {
      s.activate(this);
    }
  }

  public void activate(boolean a)
  {
    active = a;
  }

  public void setDirection(String s)
  {
    direction = s;
  }

  public boolean getActive()
  {
    return active;
  }
}