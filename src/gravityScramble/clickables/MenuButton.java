package gravityScramble.clickables;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class MenuButton extends Clickable {

	private int width;
	private String name;
	private boolean clicking;
	private String menuType;
	private String sendTo;

	public MenuButton(int pos, int alt, int w, String n, String menu, String send) {
		super(pos, alt);
		width = w;
		name = n;
		clicking = false;
		menuType = menu;
		sendTo = send;
	}

	public void paintComponent(Graphics g) {
		paintComponent(g, 0, 0);
	}

	public void paintComponent(Graphics g, int x, int y) {
		if (clicking) {
			g.setColor(Color.blue.darker());
		} else {
			g.setColor(Color.gray.darker());
		}
		g.fillRect(position, altitude, width, 40);
		g.setColor(Color.white);
		g.setFont(new Font("Serif", 0, 30));
		g.drawString(name, position + 5, altitude + 35);
	}

	public boolean isClickedBy(int x, int y) {
		return x > position && x <= position + width && y > altitude && y < altitude + 40;
	}

	public String getName() {
		return name;
	}

	public String getMenu() {
		return menuType;
	}

	public String getSend() {
		return sendTo;
	}
}