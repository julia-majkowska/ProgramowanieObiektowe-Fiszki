import java.io.*;
import java.util.*;
public abstract class Drawing{
	Date today;
	NBase nfb;
	RBase rfb;
	User mr;
	Queue<RFiszka> selectedCards = new LinkedList<RFiszka>();
	LinkedList<String> topics = new LinkedList<String>();
	HashMap<String,Integer> topicsSize = new HashMap<String,Integer>();

	abstract void countTopicsSize();
	abstract void takeTopic(String t);
	abstract RFiszka takeCard(String topic, int nr);
	int[] chooseCards(String topic, int howMany){
		Random generator = new Random();
		System.out.println("choosing");		
		int range = topicsSize.get(topic) - 1;
		int[] chosenCards = new int[howMany];
		for ( int i = howMany - 1; i >= 0; i -- ){
			System.out.println(i);
			int margin = range - i; //taki mam zapas
			int rand;
			if ( margin == 0 ) rand = 0;
			else rand = generator.nextInt(margin);
			chosenCards[i] = range - rand;
			range = chosenCards[i] - 1;
		}
		return chosenCards;
	}
	void selectCards(String topic, int howMany){
		int[] numbers = chooseCards(topic, howMany);
		System.out.println("przed topic");
		takeTopic(topic);
		System.out.println("po topic");
		for ( int i = 0; i < numbers.length; i ++ ){
			System.out.println("po topic");
			selectedCards.add(takeCard(topic,numbers[i]));
		}
	}
	
	Drawing(User imr){
		nfb = imr.nf;
		mr = imr;
		rfb = new RBase(mr.id, nfb, today);
	}
}
