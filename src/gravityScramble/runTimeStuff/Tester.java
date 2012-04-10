package gravityScramble.runTimeStuff;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;

public class Tester
{
  public static void main(String[] args)
  {
    UniversalLevelPlayer play = new UniversalLevelPlayer();
    try {
      File f1 = new File("Terms of Agreement.txt");
      BufferedWriter w1 = new BufferedWriter(new FileWriter(f1));
      for (int x = 0; x < play.getTerms().length(); x++)
      {
        while (play.getTerms().charAt(x) == '/')
        {
          w1.newLine();
          x += 2;
        }
        w1.write(play.getTerms().charAt(x));
      }
      w1.close();
      File f2 = new File("Help.txt");
      BufferedWriter w2 = new BufferedWriter(new FileWriter(f2));
      for (int x = 0; x < play.getHelp().length(); x++)
      {
        while (play.getHelp().charAt(x) == '/')
        {
          w2.newLine();
          x += 2;
        }
        w2.write(play.getHelp().charAt(x));
      }
      w2.close();
      File f3 = new File("How to Use Options.txt");
      BufferedWriter w3 = new BufferedWriter(new FileWriter(f3));
      for (int x = 0; x < play.getHowToUseOptions().length(); x++)
      {
        while (play.getHowToUseOptions().charAt(x) == '/')
        {
          w3.newLine();
          x += 2;
        }
        w3.write(play.getHowToUseOptions().charAt(x));
      }
      w3.close();
      play.startGame();
    }
    catch (IOException e) {
      System.err.println("Unknown error occured.");
    }
  }
}