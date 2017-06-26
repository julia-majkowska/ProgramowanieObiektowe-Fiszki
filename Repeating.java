import java.util.*;
import java.io.*;
public class Repeating extends Drawing{
	
	int[] nrExcercise = {1};
	
	RFiszka takeCard(int nr){
		RFiszka card = topicF.get(nr);
		return card;
	}
	Vector<RFiszka> topicF = new Vector<RFiszka>();
	void takeTopic(String topic){

		topicF.clear();
		if ( topic.equals(".wszystkie.") ){
			takeAll();
			return;
		}
	 
		Vector<RFiszka> topicV = mr.rfb.topic_sort.get(topic);
		for ( RFiszka toCheck : topicV ){
			if ( !mr.today.before(toCheck.next_rep) ) topicF.add(toCheck); 
		}
	}
	
	void takeAll(){
	
		for ( String topic : mr.rfb.topic_sort.keySet() ){
			Vector<RFiszka> topicV = mr.rfb.topic_sort.get(topic);

			for ( RFiszka toCheck : topicV ){
				if ( !mr.today.before(toCheck.next_rep) ) topicF.add(toCheck); 
			}
		}
	}
	void countTopicsSize(){
		super.countTopicsSize();

		int all = 0;
		HashMap<String, Vector<RFiszka> > sortedRF = mr.rfb.topic_sort;
		for (String topic: sortedRF.keySet() ){
		
			topics.add(topic);
			int size = sortedRF.get(topic).size();
			
			topicsSize.put(topic, size);
			all += size;
		}
		topics.add(".wszystkie.");
		topicsSize.put(".wszystkie.", all);
	}

	Queue<Exc> justLearn (String topic, int howMany){
		return super.justLearn(topic, howMany, nrExcercise);
	}
	Queue<Exc> justLearn (int howMany){
		return super.justLearn(".wszystkie.", howMany, nrExcercise);
	}

	Repeating(User imr){
		super(imr);
	}
}
