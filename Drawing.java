import java.io.*;
import java.util.*;
public abstract class Drawing{
	Date today;
	NBase nfb;
	RBase rfb;
	User mr;
	
	HashMap<String,Integer> topicsSize;

	abstract void countTopicsSize();
	abstract void takeTopic(String t);
	abstract RFiszka takeCard(String topic, int nr);
	int[] chooseCards(String topic, int howMany){
		Random generator = new Random();
		
		int range = topicsSize.get(topic) - 1;
		int[] chosenCards = new int[howMany];
		for ( int i = howMany - 1; i >= 0; i -- ){
			int margin = range - i; //taki mam zapas
			int rand = generator.nextInt(margin);
			chosenCards[i] = range - rand;
			range = chosenCards[i] - 1;
		}
		return chosenCards;
	}
	Vector<RFiszka> selectCards(String topic, int howMany){
		int[] numbers = chooseCards(topic, howMany);
		
		takeTopic(topic);
		Vector<RFiszka> selectedCards = new Vector<RFiszka>();
		for ( int i = 0; i < numbers.length; i ++ ){
			selectedCards.add(takeCard(topic,numbers[i]));
		}
		return selectedCards;
	}
	
	Drawing(NBase infb, User imr){
		nfb = infb;
		mr = imr;
		rfb = new RBase(mr.id, nfb, today);
		countTopicsSize();
	}
}