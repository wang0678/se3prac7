package au.edu.flinders.uni.se3;

import javax.swing.*;

import au.edu.flinders.uni.se3.util.GameUtil;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @author
 */
public class Game2048Board extends JPanel {
	private static final long serialVersionUID = 1L;
	private static final Color BG_COLOR = new Color(0xbbada0);
	private static final String FONT_NAME = "Arial";
	private static final int TILE_SIZE = 64;
	private static final int TILES_MARGIN = 16;

	private Game2048ProcesserIf processer = null;

	public Game2048Board() {
		processer = new Game2048Core();
		setFocusable(true);
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					processer.resetGame();
				}
				if (!processer.canMove()) {
					processer.markGameLose();
				}

				if (!processer.win() && !processer.lose()) {
					switch (e.getKeyCode()) {
					case KeyEvent.VK_LEFT:
						processer.tilt_board_left();
						break;
					case KeyEvent.VK_RIGHT:
						processer.tilt_board_right();
						break;
					case KeyEvent.VK_DOWN:
						processer.tilt_board_down();
						break;
					case KeyEvent.VK_UP:
						processer.tilt_board_up();
						break;
					}
				}

				if (!processer.win() && !processer.canMove()) {
					processer.markGameLose();
				}

				repaint();
			}
		});
		processer.resetGame();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(BG_COLOR);
		g.fillRect(0, 0, this.getSize().width, this.getSize().height);
		for (int y = 0; y < GameUtil.COLS; y++) {
			for (int x = 0; x < GameUtil.COLS; x++) {
				drawTile(g, new Tile(processer.getNumber(y, x)), x, y);
			}
		}
	}

	private void drawTile(Graphics g2, Tile tile, int x, int y) {
		if (tile == null) {
			return;
		}
		Graphics2D g = ((Graphics2D) g2);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,
				RenderingHints.VALUE_STROKE_NORMALIZE);
		int value = tile.value;
		int xOffset = offsetCoors(x);
		int yOffset = offsetCoors(y);
		g.setColor(tile.getBackground());
		g.fillRoundRect(xOffset, yOffset, TILE_SIZE, TILE_SIZE, 14, 14);
		g.setColor(tile.getForeground());
		final int size = value < 100 ? 36 : value < 1000 ? 32 : 24;
		final Font font = new Font(FONT_NAME, Font.BOLD, size);
		g.setFont(font);

		String s = String.valueOf(value);
		final FontMetrics fm = getFontMetrics(font);

		final int w = fm.stringWidth(s);
		final int h = -(int) fm.getLineMetrics(s, g).getBaselineOffsets()[2];

		if (value != 0)
			g.drawString(s, xOffset + (TILE_SIZE - w) / 2, yOffset + TILE_SIZE
					- (TILE_SIZE - h) / 2 - 2);

		if (processer.win() || processer.lose()) {
			g.setColor(new Color(255, 255, 255, 30));
			g.fillRect(0, 0, getWidth(), getHeight());
			g.setColor(new Color(78, 139, 202));
			g.setFont(new Font(FONT_NAME, Font.BOLD, 48));
			if (processer.win()) {
				g.drawString("You won!", 68, 150);
			}
			if (processer.lose()) {
				g.drawString("Game over!", 50, 130);
				g.drawString("You lose!", 64, 200);
			}
			if (processer.win() || processer.lose()) {
				g.setFont(new Font(FONT_NAME, Font.PLAIN, 16));
				g.setColor(new Color(128, 128, 128, 128));
				g.drawString("Press ESC to play again", 80, getHeight() - 40);
			}
		}
		g.setFont(new Font(FONT_NAME, Font.PLAIN, 18));
		g.drawString("Score: " + processer.score(), 200, 365);

	}

	private static int offsetCoors(int arg) {
		return arg * (TILES_MARGIN + TILE_SIZE) + TILES_MARGIN;
	}

}
