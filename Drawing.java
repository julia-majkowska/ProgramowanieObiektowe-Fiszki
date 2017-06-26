import java.io.*;
import java.util.*;
public abstract class Drawing{
	
	User mr;	
	LinkedList<String> topics = new LinkedList<String>();
	HashMap<String,Integer> topicsSize = new HashMap<String,Integer>();

	void countTopicsSize(){
		topics.clear();
		topicsSize.clear();
		mr.rfb.readRBase(mr.nfb, mr.today);
	}
	abstract void takeTopic(String t);
	
	abstract RFiszka takeCard(int nr);
	
	int[] chooseCards(String topic, int howMany){
		Random generator = new Random();
		int range = topicsSize.get(topic) - 1;
		howMany = Math.min(howMany, range + 1);
		int[] chosenCards = new int[howMany];
		for ( int i = howMany - 1; i >= 0; i -- ){
			int margin = range - i; //taki mam zapas
			int rand;
			if ( margin == 0 ) rand = 0;
			else rand = generator.nextInt(margin + 1);
			chosenCards[i] = range - rand;
			range = chosenCards[i] - 1;
		}
		return chosenCards;
	}
	Vector<RFiszka> selectCards(String topic, int howMany){
		int[] numbers = chooseCards(topic, howMany);
		
		takeTopic(topic);
		
		Vector<RFiszka> selectedCards = new Vector<RFiszka>();
		for ( int i = 0; i < numbers.length; i ++ )
			selectedCards.add(takeCard(numbers[i]));
		
		return selectedCards;
	}
	void shuffle(Vector<RFiszka> Cards){
		Random generator = new Random();
		int range = Cards.size();
		for ( int i = Cards.size() - 1; i > 0; i -- ){
		
			int rand = generator.nextInt(i + 1);
			Collections.swap(Cards,rand,i);
		}
	}
	Queue<Exc> justLearn(String topic, int howMany, int[] nrExcercise){
		Vector<RFiszka> selectedCards = selectCards(topic, howMany);

		Queue<Exc> toLearn = new LinkedList<Exc>();
		for ( int i = 0; i < nrExcercise.length; i ++ ){
			shuffle(selectedCards);
			
			for ( RFiszka rf : selectedCards )
				toLearn.add(new Exc(nrExcercise[i], rf));
		}
		return toLearn;
	}
	
	Drawing(User imr){
		mr = imr;
		countTopicsSize();
	}
}
