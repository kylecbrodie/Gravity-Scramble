package gravityScramble.clickables;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.Serializable;

public class HelpBubble extends Clickable
  implements Serializable
{
  private char symbol;
  private String text;
  private int drawingPosition = 0;
  private int drawingAltitude = 0;
  private boolean isPressed;

  public HelpBubble(int pos, int alt, char c, String t)
  {
    super(pos, alt);
    symbol = c;
    text = t;
    isPressed = false;
  }

  public void paintComponent(Graphics g, int x, int y)
  {
    g.setColor(Color.white);
    g.fillOval(drawingPosition(x), drawingAltitude(y) - 40, 40, 40);
    if (isPressed)
    {
      g.setColor(Color.blue.darker());
    }
    else
    {
      g.setColor(Color.blue);
    }
    g.fillOval(drawingPosition(x) + 2, drawingAltitude(y) - 38, 36, 36);
    if (isPressed)
    {
      g.setColor(Color.white.darker());
    }
    else
    {
      g.setColor(Color.white);
    }
    g.setFont(new Font("Serif", 1, 20));
    g.drawString("" + symbol, drawingPosition(x) + 16, drawingAltitude(y) - 13);
    drawingPosition = drawingPosition(x);
    drawingAltitude = drawingAltitude(y);
  }

  public String getText()
  {
    return text;
  }

  public boolean isClickedBy(int x, int y)
  {
    double x2 = Math.pow(x - drawingPosition - 20, 2.0D);
    double y2 = Math.pow(y - drawingAltitude + 20, 2.0D);
    double distance = Math.sqrt(x2 + y2);
    return distance <= 20.0D;
  }

  public void press(boolean b)
  {
    isPressed = b;
  }
}