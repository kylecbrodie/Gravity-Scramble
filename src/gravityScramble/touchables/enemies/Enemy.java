package gravityScramble.touchables.enemies;

import gravityScramble.touchables.Touchable;
import java.awt.Graphics;
import java.io.Serializable;

public abstract class Enemy extends Touchable
  implements Serializable
{
  protected boolean defeated;
  protected int bounceCounter;
  protected int opacity;
  protected int saveOpacity;
  protected String powerUp;
  private boolean opacityChanger;
  private int initialPosition;
  private int initialAltitude;

  public Enemy(int w, int h, int pos, int alt, boolean def, int opa, String power, String cl)
  {
    super(w, h, pos, alt, cl);
    initialPosition = pos;
    initialAltitude = alt;
    defeated = def;
    bounceCounter = 0;
    opacity = opa;
    saveOpacity = opa;
    powerUp = power;
    opacityChanger = false;
  }

  public boolean getDefeated()
  {
    return defeated;
  }

  public void paintComponent(Graphics g, int x, int y)
  {
    if (getOpacity() != 0)
    {
      drawing(g, x, y);
    }
  }

  public abstract void drawing(Graphics paramGraphics, int paramInt1, int paramInt2);

  public abstract void becomeDefeated();

  public void becomeSummoned() {
    defeated = false;
    setCoordinates(initialPosition, initialAltitude);
  }

  public void changeBounceCounter()
  {
    if ((defeated) && (bounceCounter < 5))
    {
      bounceCounter += 1;
    }
    if (!defeated)
    {
      bounceCounter = 0;
    }
  }

  public int getBounceCounter()
  {
    return bounceCounter;
  }

  public void changeOpacity(double n)
  {
    opacity -= (int)(n * 15.0D);
  }

  public void setOpacity(int n) {
    opacity = n;
  }

  public void differentOpacity(int n) {
    opacity = n;
    saveOpacity = n;
  }

  public int getOpacity()
  {
    return opacity;
  }

  public int getSaveOpacity() {
    return saveOpacity;
  }

  public String getPowerUp()
  {
    return powerUp;
  }
  public abstract void setDirection(String paramString);

  public abstract void changePowerUp(String paramString);

  public abstract void changePosition();

  public void update() { if (((!defeated) && (opacity == saveOpacity)) || ((defeated) && (opacity != saveOpacity)) || ((this instanceof Boss)))
    {
      changePosition();
    }
    if (opacityChanger)
    {
      if ((defeated) && (opacity != 0))
      {
        changeOpacity(1.0D);
      }
      if ((!defeated) && (opacity != saveOpacity) && (!(this instanceof Boss)))
      {
        changeOpacity(-0.3333333333333333D);
      }
    }
    opacityChanger = (!opacityChanger);
    if (opacity < 0)
    {
      opacity = 0;
    }
    changeBounceCounter();
  }

  public void setInitialCoordinates(int pos, int alt)
  {
    initialPosition = pos;
    initialAltitude = alt;
  }
}