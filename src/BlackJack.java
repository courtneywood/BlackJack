import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;
/*
 * hit and stand buttons
 * deck for each
 * number total
 * betting
 * draw chips, maybe every 20 chips is one image
 */

public class BlackJack extends PApplet{
	PFont f;
	Deck d = new Deck();
	int playertotal = 0;
	int dealertotal = 0;
	int secretdealertotal = 0;
	int numChips = 4;
	ArrayList<Card> dealerhand;
	ArrayList<Card> playerhand;
	boolean hiddenDealer = true;
	boolean gameEnded = false;
	String result = "";
	boolean goingHome = false;
	public void draw() {
		background(0, 153, 51);
		textAlign(CENTER);
		buttons();
		drawHands();
		showScores();
		drawChips();
		if (gameEnded) {
			fill(255, 255, 255, 50);
			noStroke();
			rect(100, 100, 800, 550, 20);
			textFont(f, 42);
			fill(0, 0, 0);
			textAlign(CENTER);
			if (!result.equals("Dealer busted! You win!")) text(result, 500, 250);
			else text(result, 550, 250);
			textAlign(CENTER);
			
			fill(204, 0, 0);
			textFont(f, 24);
			rect(170, 300, 250, 100, 20);
			fill(0, 153, 0);
			rect(560, 300, 250, 100, 20);
			fill(0, 0, 0);
			text("Leave Table", 300, 350);
			textAlign(CENTER);
			text("Play Again", 700, 350);
			textAlign(CENTER);
		}
		else if (goingHome) {
			showEnd();
		}
	}
	
	public void drawHands() {
		int offset = 50;
		for (int i = 0; i < dealerhand.size(); i++) {
			String path = dealerhand.get(i).getPath();
			PImage dimage;
			if (i == 0 && hiddenDealer) {
				dimage = loadImage("PNG-cards-1.3/face_down.png");
			}
			else dimage = loadImage(path);
			image(dimage, 370 + i * offset, 20, 160, 232);
		}
		
		for (int i = 0; i < playerhand.size(); i++) {
			String path = playerhand.get(i).getPath();
			PImage dimage = loadImage(path);
			image(dimage, 370 + i * offset, 450, 160, 232);
		}
	}
	
	public void showScores() {
		textFont(f, 65);
		fill(0, 0, 0);
		text("" + dealertotal, 280, 150);
		text("" + playertotal, 280, 570);
	}
	
	public void drawChips() {
		int offset = 10;
		for (int i = 0; i < numChips; i++) {
			PImage chip = loadImage("PNG-cards-1.3/chip.png");
			image(chip, 20, 550 - offset*i, 145, 104);
		}
	}
	
	
	public void dealerHand() {
		//one face down
		Card d1 = d.drawCard();
		dealerhand.add(d1);
		//one face up
		Card d2 = d.drawCard();
		dealerhand.add(d2);
		calculate();
	}
	
	public void playerHand() {
		Card p1 = d.drawCard();
		playerhand.add(p1);
		Card p2 = d.drawCard();
		playerhand.add(p2);
		calculate();
	}
	
	public void calculate() {
		//dealer total
		dealertotal = 0;
		secretdealertotal = 0;
		//10, 5
		for (int i = 0; i < dealerhand.size(); i++) {
			Card c = dealerhand.get(i);
			if (i == 0) {
				if (c.getNumVal() >= 10) {
					secretdealertotal += 10;
				}
				else {
					secretdealertotal += c.getNumVal();
				}
			}
			else {
				if (c.getNumVal() >= 10) {
					dealertotal += 10;
					secretdealertotal += 10;
				}
				else {
					dealertotal += c.getNumVal();
					secretdealertotal += c.getNumVal();
				}
			}
		}
		if (!hiddenDealer) dealertotal = secretdealertotal;
		
		playertotal = 0;
		for (int i = 0; i < playerhand.size(); i++) {
			Card c = playerhand.get(i);
			if (c.getNumVal() >= 10) {
				playertotal += 10;
			}
			else if (c.getNumVal() == 1) {
				if (playertotal + 11 <= 21) {
					playertotal += 11;
				}
				else playertotal += 1;
			}
			else {
				playertotal += c.getNumVal();
			}
		}
	}
	
	public void calculateWinner() {
		hiddenDealer = false;
		calculate();
		while (secretdealertotal < 16) {
			Card n = d.drawCard();
			dealerhand.add(n);
			calculate();
		}
		
		if (playertotal > secretdealertotal) {
			result = "You win!";
			numChips++;
		}
		
		else if (secretdealertotal <= 21) {
			result = "Dealer won!";
			numChips--;
		}
		else {
			result = "Dealer busted! You win!";
			numChips++;
		}
		gameEnded = true;
		
	}
	
	
	public void startGame() {
		dealerhand = new ArrayList<>();
		playerhand = new ArrayList<>();
		hiddenDealer = true;
		dealerHand();
		playerHand();
		result = "";
		gameEnded = false;
	}
	
	public void buttons() {
		fill(0, 0, 0);
		ellipse(350, 350, 100, 100);
		ellipse(600, 350, 100, 100);
		fill(255, 255, 255);
		textFont(f, 30);
		text("Stand", 350, 360);
		text("Hit", 600, 360);
	}
	
	public void setup() {
		f = createFont("Noteworthy", 50, true);
		d = new Deck();
		background(0, 153, 51);
		startGame();
	}
	
	public void settings() {
		size(1000, 700);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PApplet.main("BlackJack");
	}
	
	public void showEnd() {
		fill(0, 153, 51);
		rect(0, 0, 1000, 700);
		textFont(f, 55);
		textAlign(CENTER);
		fill(0, 0, 0);
		text("You're going home with " + numChips + " chips!", 500, 350);
	}
	
	public void mousePressed() {
		if (gameEnded) {
			if (mouseX > 170 && mouseX < 420 && mouseY > 300 && mouseY < 400) {
				goingHome = true;
				gameEnded = false;
			}
			else if (mouseX > 560 && mouseX < 810 && mouseY > 300 && mouseY < 400) {
				if (numChips > 0) startGame();
				else {
					gameEnded = false;
					goingHome = true;
					showEnd();
				}
			}
		}
		else if (!goingHome){
			/*
			 * rect(170, 300, 250, 100, 20);
			fill(0, 153, 0);
			rect(560, 300, 250, 100, 20);
			 */
			if (mouseX > 300 && mouseX < 500 && mouseY > 300 && mouseY < 500) {
				//Stand
				calculateWinner();
			}
			else if (mouseX > 500 && mouseX < 700 && mouseY > 300 && mouseY < 450) {
				Card c = d.drawCard();
				playerhand.add(c);
				calculate();
				if (playertotal > 21) {
					
					result = "You busted :(";
					numChips--;
					gameEnded = true;
				}
			}
		}
	}

}
