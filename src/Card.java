import processing.core.PImage;

public class Card {
	//PImage cardImage;
	String val;
	String suit;
	String path = "";
	int numval = 0;
	public Card(String value, String s) {
		val = value;
		suit = s;
		setPath();
	}
	
	public String getPath() {
		return path;
	}
	
	public String getValue() {
		return val;
	}
	
	public String getSuit() {
		return suit;
	}
	
	public int getNumVal() {
		return numval;
	}
	
	private void setPath() {
		path = "PNG-cards-1.3/";
		if (!val.equals("Jack") && !val.equals("Queen") && !val.equals("King") && !val.equals("Ace")) {
			if (val.equals("Two")) {
				path += 2 + "_of_";
				numval = 2;
			}
			else if (val.equals("Three")) {
				path += 3 + "_of_";
				numval = 3;
			}
			else if (val.equals("Four")) {
				path += 4 + "_of_";
				numval = 4;
			}
			else if (val.equals("Five")) {
				path += 5 + "_of_";
				numval = 5;
			}
			else if (val.equals("Six")) {
				path += 6 + "_of_";
				numval = 6;
			}
			else if (val.equals("Seven")) {
				path += 7 + "_of_";
				numval = 7;
			}
			else if (val.equals("Eight")) {
				path += 8 + "_of_";
				numval = 8;
			}
			else if (val.equals("Nine")) {
				path += 9 + "_of_";
				numval = 9;
			}
			else if (val.equals("Ten")) {
				path += 10 + "_of_";
				numval = 10;
			}
		}
		else {
			path += val + "_of_";
			if (val.equals("Jack")) {
				numval = 11;
			}
			else if (val.equals("Queen")) {
				numval = 12;
			}
			else if (val.equals("King")) {
				numval = 13;
			}
			else {
				numval = 1;
			}
		}
		path += suit + ".png";
	}
	
}
