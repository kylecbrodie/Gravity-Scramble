package gravityScramble.touchables.powerups;

import gravityScramble.touchables.Player;
import gravityScramble.touchables.Touchable;
import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public class Powerup extends Touchable
  implements Serializable
{
  private boolean activating;
  private int counter;
  private String power;

  public Powerup(int pos, int alt, String pow)
  {
    super(100, 200, pos, alt, "fireballPowerup");
    activating = false;
    counter = 0;
    power = pow;
  }

  public void paintComponent(Graphics g, int x, int y)
  {
    Color color = null;
    if (power.equals("fire"))
    {
      color = Color.red;
    }
    g.setColor(color);
    g.fillRect(drawingPosition(x), 630 - altitude + y, 100, 10);
    g.fillRect(drawingPosition(x), 440 - altitude + y, 10, 200);
    g.fillRect(drawingPosition(x), 440 - altitude + y, 100, 10);
    g.fillRect(drawingPosition(x) + 90, 440 - altitude + y, 10, 200);
    if (activating)
    {
      if (power.equals("fire"))
      {
        Color differentRed = new Color(250, 0, 0, 170);
        g.setColor(differentRed);
      }
      g.fillRect(drawingPosition(x), 440 - altitude + y, 100, 200);
      g.setColor(color);
      g.fillRect(drawingPosition(x), 630 - altitude + y - counter, 100, 10);
    }
  }

  public void activate(Player player)
  {
    if (((isTouching(player)) || (counter > 0)) && (player.getPowerUp() != "fire"))
    {
      activating = true;
      player.setPosition(player.getPosition() + (positionCenter() - player.positionCenter()) / 5);
      player.setAltitude(player.getAltitude() + (altitudeCenter() - player.altitudeCenter()) / 5);
      counter += 2;
      if (counter == 190)
      {
        counter = 0;
        activating = false;
        player.setPowerUp("fire");
      }
    }
  }

  public boolean getActivating()
  {
    return activating;
  }
}