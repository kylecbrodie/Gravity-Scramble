package gravityScramble.touchables.obstacles;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public class Spikes extends GravityChamber
  implements Serializable
{
  public Spikes(int pos, int alt, String dir)
  {
    super(pos, alt, dir);
    width = 100;
    whichClass = "spikes";
  }

  public void paintComponent(Graphics g, int x, int y)
  {
    g.setColor(Color.red);
    int[] xPoints = { drawingPosition(x), drawingPosition(x), drawingPosition(x) + 50 };
    int[] yPoints = { 640 + y - altitude, 490 + y - altitude, 640 + y - altitude };
    g.fillPolygon(xPoints, yPoints, 3);
    int[] xPoints2 = { drawingPosition(x), drawingPosition(x) + 50, drawingPosition(x) + 100 };
    g.fillPolygon(xPoints2, yPoints, 3);
    int[] xPoints3 = { drawingPosition(x) + 50, drawingPosition(x) + 100, drawingPosition(x) + 100 };
    g.fillPolygon(xPoints3, yPoints, 3);
  }
}