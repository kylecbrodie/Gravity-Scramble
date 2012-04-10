package gravityScramble.clickables;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;

public class HelpBubbleDisplay
  implements Serializable
{
  private HelpBubble bubble;
  private HelpBubble defaultBubble;

  public HelpBubbleDisplay()
  {
    defaultBubble = new HelpBubble(0, 0, ' ', "");
    bubble = defaultBubble;
  }

  public void changeBubble(HelpBubble h)
  {
    bubble.press(false);
    if (bubble == h)
    {
      bubble = defaultBubble;
    }
    else
    {
      bubble = h;
      bubble.press(true);
    }
  }

  public void paintComponent(Graphics g)
  {
    g.setColor(Color.black);
    g.setFont(new Font("Serif", 0, 40));
    ArrayList lines = new ArrayList();
    String text = bubble.getText();
    for (int x = 0; x < text.length() - 1; x++)
    {
      while ((x < text.length()) && (text.substring(x, x + 2).equals("/n")))
      {
        lines.add(text.substring(0, x));
        text = text.substring(x + 2);
        x = 0;
      }
    }
    lines.add(text);
    int position = 640 - 40 * (lines.size() - 1);
    for (int x = 0; x < lines.size(); x++)
    {
      g.drawString((String)lines.get(x), 100, position);
      position += 40;
    }
  }
}