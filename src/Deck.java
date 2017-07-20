import java.util.Random;

public class Deck {
	Random rand = new Random();
	private Card[] deck;
	int count = -1;
	public Deck() {
		deck = new Card[52];
		String[] suits = {"Spades", "Diamonds", "Hearts", "Clubs"};
		String[] values = {"Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};
		int count = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 13; j++) {
				Card c = new Card(values[j], suits[i]);
				deck[count] = c;
				count++;
			}
		}
		shuffle();
	}
	
	public void shuffle() {
		for (int i = 0; i < 200; i++) {
			//pick two random places
			int p1 = rand.nextInt(52);
			int p2 = rand.nextInt(52);
			//swap cards at locations p1 and p2
			Card c1 = deck[p1];
			Card c2 = deck[p2];
			deck[p1] = c2;
			deck[p2] = c1;
		}
	}
	
	public Card drawCard() {
		count++;
		if (count == 51){
			shuffle();
			count = 0;
		}
		return deck[count];
	}
}
