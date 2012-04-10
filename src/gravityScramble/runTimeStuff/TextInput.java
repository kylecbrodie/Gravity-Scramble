package gravityScramble.runTimeStuff;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class TextInput
{
  private String name;
  private String input;
  private int position;
  private int altitude;
  private int width;
  private String menu;
  private boolean shift;

  public TextInput(String n, int pos, int alt, int w, String m)
  {
    name = n;
    input = "";
    position = pos;
    altitude = alt;
    width = w;
    menu = m;
  }

  public void addLetter(int letter)
  {
    if (((letter >= 48) && (letter <= 57)) || ((letter >= 65) && (letter <= 90)) || (letter == 32))
    {
      if ((!shift) && (letter >= 65) && (letter <= 90))
      {
        letter += 32;
      }
      input += (char)letter;
    }
    if ((letter == 8) && (input.length() > 0))
    {
      input = input.substring(0, input.length() - 1);
    }
  }

  public void paintComponent(Graphics g)
  {
    g.setColor(Color.black);
    g.setFont(new Font("Serif", 0, 30));
    g.drawString(name, position + 5, altitude - 10);
    g.fillRect(position, altitude, width, 40);
    g.setColor(Color.white);
    g.drawString(input + "|", position + 2, altitude + 38);
  }

  public String getInput()
  {
    return input;
  }

  public String getMenu() {
    return menu;
  }

  public void setShift(boolean b) {
    shift = b;
  }

  public void reset() {
    input = "";
  }
}