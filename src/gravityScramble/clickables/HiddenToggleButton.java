package gravityScramble.clickables;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class HiddenToggleButton extends Clickable {

	private int width;
	private int option;
	private String[] names;
	private String menuType;
	private String tag;

	public HiddenToggleButton(int pos, int alt, int w, String[] n, int o, String m, String t) {
		super(pos, alt);
		width = w;
		option = o;
		names = n;
		menuType = m;
		tag = t;
	}

	public void paintComponent(Graphics g, int x, int y) {
		g.setColor(Color.gray.darker());
		g.fillRect(position, altitude, width, 40);
		g.setColor(Color.white);
		g.setFont(new Font("Serif", 0, 30));
		g.drawString(names[option], position + 5, altitude + 35);
	}

	public boolean isClickedBy(int x, int y) {
		return x > position && x <= position + width && y > altitude && y < altitude + 40;
	}

	public void toggle() {
		option += 1;
		if (option >= names.length) {
			option = 0;
		}
	}

	public String getMenu() {
		return menuType;
	}

	public int getOption() {
		return option;
	}

	public String getTag() {
		return tag;
	}
}