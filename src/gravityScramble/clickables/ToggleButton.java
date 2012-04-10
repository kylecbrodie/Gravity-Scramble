package gravityScramble.clickables;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class ToggleButton extends Clickable {

	private int width;
	private String name1;
	private String name2;
	private boolean select1;
	private String menuType;
	private String tag;

	public ToggleButton(int pos, int alt, int w, String n1, String n2, boolean s1, String menu, String t) {
		super(pos, alt);
		width = w;
		name1 = n1;
		name2 = n2;
		select1 = s1;
		menuType = menu;
		tag = t;
	}

	public void paintComponent(Graphics g, int x, int y) {
		g.setColor(Color.white);
		g.fillRect(position, altitude, width * 2 + 10, 40);
		
		g.setFont(new Font("Serif", 0, 30));
		g.setColor(Color.black);
		
		g.drawString(name1, position + 5, altitude + 35);
		g.drawString(name2, position + width + 15, altitude + 35);
		
		if (select1) {
			g.fillRect(position - 10, altitude - 10, width + 20, 10);
			g.fillRect(position - 10, altitude - 10, 10, 60);
			g.fillRect(position - 10, altitude + 40, width + 20, 10);
			g.fillRect(position + width, altitude - 10, 10, 60);
		} else {
			g.fillRect(position + width, altitude - 10, width + 20, 10);
			g.fillRect(position + width, altitude - 10, 10, 60);
			g.fillRect(position + width, altitude + 40, width + 20, 10);
			g.fillRect(position + 10 + width * 2, altitude - 10, 10, 60);
		}
	}

	public boolean isClickedBy(int x, int y) {
		if (x > position && x <= position + width * 2 + 10 && y > altitude && y < altitude + 40) {
			if (x <= position + width) {
				select1 = true;
			}
			if (x >= position + width + 10) {
				select1 = false;
			}
			return true;
		}

		return false;
	}

	public boolean getSelected() {
		return select1;
	}

	public String getMenu() {
		return menuType;
	}

	public void reset() {
		select1 = true;
	}

	public String getTag() {
		return tag;
	}
}