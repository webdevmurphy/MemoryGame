import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Board extends JFrame {
	List<String> list = new ArrayList<String>();
	List<Card> newCards = new ArrayList<Card>();

	private Card prevCard;
	private boolean prevFlip;
	private boolean disabled;
	private int gameOver = 5;

	public Board() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(900, 400);
		this.setLayout(new FlowLayout());

		list.add("AlanTuring.jpg");
		list.add("DennisRitchie.jpg");
		list.add("Dijkstra.jpg");
		list.add("graceHopper.jpg");
		list.add("pika.png");

		for (int i = 0; i < 2; i++) {
			for (String cardImages : list) {
				Card card = new Card(cardImages);
				card.addMouseListener(new myListener());
				newCards.add(card);
			}
		}

		Collections.shuffle(newCards);

		for (int i = 0; i < 10; i++) {
			add(newCards.get(i));
		}
		setVisible(true);
	}

	public void isGameOver(int gameOver) {
		if (gameOver == 0) {
			JFrame parent = new JFrame();
			JOptionPane.showMessageDialog(parent, "You Win!! Game Over!");
			dispose();
			System.exit(0);
		}
	}

	private class myListener implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			Card card = (Card) e.getSource();
			
			if (prevFlip) {

				if (disabled) {
					return;
				}

				System.out.println("NEW CARDS PrevID " + prevCard);

				if (card.getCardId() == prevCard.getCardId()) {
					return;
				}

				if (card.getMatched()) {
					return;
				}
				prevFlip = !prevFlip;
				card.setFlipped(!card.isFlipped());

				if (card.getFace() == prevCard.getFace()) {
					card.setMatched(true);
					prevCard.setMatched(true);
					System.out.println("match");
					gameOver--;
					isGameOver(gameOver);
				} else {
					TimerTask task = new TimerTask() {
						public void run() {
							card.setFlipped(!card.isFlipped());
							disabled = false;
							prevCard.setFlipped(true);
							repaint();
						}
					};

					Timer timer = new Timer("Timer");
					disabled = true;
					long delay = 500L;
					timer.schedule(task, delay);
				}
			} else {
				card.setFlipped(!card.isFlipped());
				prevFlip = true;
				prevCard = card;
			}
			repaint();
		}

		@Override
		public void mousePressed(MouseEvent e) {

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}

	public static void main(String[] args) {
		Board b = new Board();
	}
}
