import java.util.*;
import java.io.*;
public class Repeating extends Drawing{
	HashMap<String,Vector<RFiszka> > goodF = rfb.topic_sort;

	RFiszka takeCard(String topic, int nr){
		return goodF.get(topic).get(nr);
	}
	void takeTopic(String topic){
		return;
		/*if ( goodF.get(topic) != null ) return;
	 
		Vector<RFiszka> topicV = new Vector<RFiszka>();
		for ( int i = 0; i < rfb.topic_sort.get(topic).size(); i ++ ){
			RFiszka toCheck = rfb.topic_sort.get(topic)[i];
			if ( !today.before(toCheck.next_rep) ) topicV.add(toCheck);
		}
		goodF.put(topic, topicV);
	*/}

	void countTopicsSize(){
		topicsSize = new HashMap<String,Integer>();
		for (String topic: rfb.topic_sort.keySet() ){
			topicsSize.put(topic, rfb.topic_sort.get(topic).size());
		}
	}
	Repeating(NBase infb, User imr){
		super(infb, imr);
	}
}
