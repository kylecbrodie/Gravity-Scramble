package gravityScramble.touchables.obstacles;

import gravityScramble.touchables.Player;
import gravityScramble.touchables.Touchable;
import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public class GravityChamber extends Touchable
  implements Serializable
{
  private String direction;
  private String moveDirection;
  private int counter;
  private int startPosition;
  private boolean switchStarted;
  private int distanceToStart;
  private boolean active;
  private int opacity;
  private boolean canMove;
  private boolean adjust;
  private int counterLimit;

  public GravityChamber(int pos, int alt, String dir)
  {
    super(0, 0, pos, alt, "gravityChamber");
    if ((dir == "up") || (dir.equals("down")))
    {
      width = 150;
      height = 150;
    }
    else
    {
      width = 75;
      height = 225;
    }
    direction = dir;
    moveDirection = "none";
    counter = 0;
    startPosition = pos;
    switchStarted = false;
    distanceToStart = 0;
    active = true;
    opacity = 170;
    canMove = false;
    adjust = false;
    counterLimit = 0;
  }

  public GravityChamber(int pos, int alt, String dir, String movdir, boolean switchOperated, boolean adj, int move) {
    super(0, 0, pos, alt, "gravityChamber");
    if (dir == "up")
    {
      width = 150;
      height = 150;
    }
    else
    {
      width = 75;
      height = 225;
    }
    position = pos;
    altitude = alt;
    direction = dir;
    moveDirection = movdir;
    counter = 0;
    startPosition = pos;
    switchStarted = false;
    distanceToStart = 0;
    active = true;
    opacity = 170;
    canMove = switchOperated;
    adjust = adj;
    counterLimit = move;
    whichClass = "gravityChamber";
  }

  public void paintComponent(Graphics g, int x, int y)
  {
    if (direction == "up")
    {
      Color transparentWhite = new Color(255, 255, 255, opacity);
      g.setColor(transparentWhite);
      g.fillRect(drawingPosition(x), drawingAltitude(y) - height, width, height);
      transparentWhite = new Color(255, 255, 255, 170);
      g.setColor(transparentWhite);
      g.drawRect(drawingPosition(x), drawingAltitude(y) - height, width, height);
    }
    if (direction == "left")
    {
      Color transparentPurple = new Color(220, 0, 220, opacity);
      g.setColor(transparentPurple);
      g.fillRect(drawingPosition(x), drawingAltitude(y) - height, width, height);
      transparentPurple = new Color(220, 0, 220, 170);
      g.setColor(transparentPurple);
      g.drawRect(drawingPosition(x), drawingAltitude(y) - height, width, height);
    }
    if (direction == "right")
    {
      Color transparentGreen = new Color(0, 255, 0, opacity);
      g.setColor(transparentGreen);
      g.fillRect(drawingPosition(x), drawingAltitude(y) - height, width, height);
      transparentGreen = new Color(0, 255, 0, 170);
      g.setColor(transparentGreen);
      g.drawRect(drawingPosition(x), drawingAltitude(y) - height, width, height);
    }
    if (direction.equals("down"))
    {
      Color transparentYellow = new Color(255, 255, 0, opacity);
      g.setColor(transparentYellow);
      g.fillRect(drawingPosition(x), drawingAltitude(y) - height, width, height);
      transparentYellow = new Color(255, 255, 0, 170);
      g.setColor(transparentYellow);
      g.drawRect(drawingPosition(x), drawingAltitude(y) - height, width, height);
    }
  }

  public void pushPlayer(Player player)
  {
    if (adjust)
    {
      altitude = player.getAltitude();
    }
    if ((isTouching(player)) && (player.getAltitude() != altitude + height) && (active))
    {
      if (direction == "up")
      {
        player.changeAltitude(3);
      }
      if (direction == "left")
      {
        player.changePosition(-2);
      }
      if (direction == "right")
      {
        player.changePosition(2);
      }
      if (direction.equals("down"))
      {
        player.changeAltitude(-3);
      }
    }
  }

  public void move()
  {
    if (canMove)
    {
      if (moveDirection == "left")
      {
        position -= 1;
      }
      else if (moveDirection == "right")
      {
        position += 1;
      }

      if (moveDirection == "up")
      {
        altitude += 1;
      }
      else if (moveDirection == "down")
      {
        altitude -= 1;
      }

      counter += 1;
      if (counter == counterLimit)
      {
        counter = 0;
        if (moveDirection == "left")
        {
          moveDirection = "right";
        }
        else if (moveDirection == "right")
        {
          moveDirection = "left";
        }

        if (moveDirection == "up")
        {
          moveDirection = "down";
        }
        else if (moveDirection == "down")
        {
          moveDirection = "up";
        }
      }
    }
  }

  public void goBack()
  {
    if (!switchStarted)
    {
      counter = 0;
      switchStarted = true;
      distanceToStart = (position - startPosition);
      canMove = false;
    }
    counter += 1;
    position = (startPosition + distanceToStart * (100 - counter) / 100);
    super.setCoordinates(position, altitude);
    if (counter == 100)
    {
      counter = 0;
      switchStarted = false;
      moveDirection = "right";
      canMove = true;
    }
  }

  public void setActive()
  {
    active = false;
  }

  public void setClass(String cl)
  {
    whichClass = cl;
    super.setClass(cl);
  }

  public String getDirection()
  {
    return direction;
  }

  public GravityChamber setSize(int x, int y)
  {
    width = x;
    height = y;
    return this;
  }

  public void changeOpacity()
  {
    if ((!active) && (opacity != 0))
    {
      opacity -= 5;
    }
  }
}