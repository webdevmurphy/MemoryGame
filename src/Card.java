import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class Card extends JPanel {
private BufferedImage face;
private BufferedImage back;
private boolean flipped;
private static int ID = 0;
private int cardId;
private boolean matched = false;
private String faceId;



	public Card(String file) {
		try {
			face = ImageIO.read(new File(file));
			back = ImageIO.read(new File("texture-back.jpg"));
			flipped = true;
			cardId = ID++;
			faceId = file;
			
		}catch(IOException e) {
			
		}
	}
	 public void setMatched(boolean matched){
	        this.matched = matched;
	    }

	    public boolean getMatched(){
	        return this.matched;
	    }
	
	
	@Override
	protected void paintComponent(Graphics g) {
		if(matched) {
			g.setColor(Color.black);
		}
		else if (flipped) {
			g.drawImage(this.back, 0, 0, 150, 150, null);
		} else {
			g.drawImage(this.face, 0, 0, 150, 150, null);
		}
	}
	
	
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(150, 150);
	}
	public boolean isFlipped() {
		return flipped;
	}
	public void setFlipped(boolean flipped) {
		this.flipped = flipped;
	}
	public int getCardId() {
		return cardId;
	}
	public String getFace() {
		return faceId;
	}
}
