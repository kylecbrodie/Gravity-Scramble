package gravityScramble.clickables;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

public class HelpBubbleDisplay {

	private HelpBubble bubble;
	private HelpBubble defaultBubble;

	public HelpBubbleDisplay() {
		defaultBubble = new HelpBubble(0, 0, ' ', "");
		bubble = defaultBubble;
	}

	public void changeBubble(HelpBubble h) {
		bubble.press(false);
		if (bubble == h) {
			bubble = defaultBubble;
		} else {
			bubble = h;
			bubble.press(true);
		}
	}

	public void paintComponent(Graphics g) {
		g.setColor(Color.black);
		g.setFont(new Font("Serif", 0, 40));
		
		ArrayList<String> lines = new ArrayList<String>();
		String text = bubble.getText();
		
		for (int i = 0; i < text.length() - 1; i++) {
			while (i < text.length() && text.substring(i, i + 2).equals("/n")) {
				lines.add(text.substring(0, i));
				text = text.substring(i + 2);
				i = 0;
			}
		}
		
		lines.add(text);
		
		int position = 640 - 40 * (lines.size() - 1);
		
		for (int i = 0; i < lines.size(); i++) {
			g.drawString(lines.get(i), 100, position);
			position += 40;
		}
	}
}