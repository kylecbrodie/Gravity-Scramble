package gravityScramble.runTimeStuff;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Tester {

	public static void main(String[] args) {
		UniversalLevelPlayer play = new UniversalLevelPlayer();
		try {
			File f1 = new File("Terms of Agreement.txt");
			BufferedWriter w1 = new BufferedWriter(new FileWriter(f1));
			for (int i = 0; i < play.getTerms().length(); i++) {
				while (play.getTerms().charAt(i) == '/') {
					w1.newLine();
					i += 2;
				}
				w1.write(play.getTerms().charAt(i));
			}
			w1.close();
			File f2 = new File("Help.txt");
			BufferedWriter w2 = new BufferedWriter(new FileWriter(f2));
			for (int i = 0; i < play.getHelp().length(); i++) {
				while (play.getHelp().charAt(i) == '/') {
					w2.newLine();
					i += 2;
				}
				w2.write(play.getHelp().charAt(i));
			}
			w2.close();
			File f3 = new File("How to Use Options.txt");
			BufferedWriter w3 = new BufferedWriter(new FileWriter(f3));
			for (int i = 0; i < play.getHowToUseOptions().length(); i++) {
				while (play.getHowToUseOptions().charAt(i) == '/') {
					w3.newLine();
					i += 2;
				}
				w3.write(play.getHowToUseOptions().charAt(i));
			}
			w3.close();
			play.startGame();
		} catch (IOException e) {
			System.err.println("Unknown error occured.");
		}
	}
}