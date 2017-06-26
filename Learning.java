import java.io.*;
import java.util.*;

public class Learning extends Drawing{
	
	int[] nrExcercise = {4,2,3,1};
	RFiszka takeCard(int nr){
		RFiszka card = new RFiszka(topicF.get(nr), mr.today);
		card.idF = card.nf.id;
		
		mr.rfb.toRepeat.add(card);
		return card;
	}
	
	Vector<Fiszka> topicF = new Vector<Fiszka>();
	void takeTopic(String topic){
		topicF.clear();
		if ( topic.equals(".wszystkie.") ){
			takeAll();
			return;
		}

		Vector<Fiszka> topicV = mr.nfb.topic_sort.get(topic);
		
		for ( Fiszka toCheck : topicV ){
			if ( !mr.rfb.ids.contains(toCheck.id) ) topicF.add(toCheck);
		}
	}
	void takeAll(){
		for ( String topic : mr.nfb.topic_sort.keySet() ){
			Vector<Fiszka> topicV = mr.nfb.topic_sort.get(topic);

			for ( Fiszka toCheck : topicV ){
				if ( !mr.rfb.ids.contains(toCheck.id) ) topicF.add(toCheck);
			}
		}
	}

	void countTopicsSize(){
		super.countTopicsSize();
		HashMap<String, Vector<Fiszka> > sortedNF = mr.nfb.topic_sort;
		HashMap<String, Vector<RFiszka> > sortedRF = mr.rfb.topic_sort;
		int all = 0;
		for (String topic: sortedNF.keySet() ){
		
			int size = sortedNF.get(topic).size();
			
			if (sortedRF.get(topic) != null ) 
				size -= sortedRF.get(topic).size();
				
			if ( size > 0 ) {
				topics.add(topic);
				topicsSize.put(topic, size);
				all += size;
			}
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

	Learning(User imr){
		super(imr);
	}
}
