import java.io.*;
import java.util.*;
public class Learning extends Drawing{
	HashMap<String,Vector<Fiszka> > goodF;
	
	RFiszka takeCard(String topic, int nr){
		return new RFiszka(goodF.get(topic).get(nr), today);
	}
// 			int newVal = topicsSize.get(topic)-1;
// 			if ( newVal == 0 ) topicsSize.remove(topic);
// 			else topicsSize.put(topic,topicsSize.get(topic)-1); i zmienic rf
	void takeTopic(String topic){
		if ( goodF.get(topic) != null ) return;
	 
		Vector<Fiszka> topicV = new Vector<Fiszka>();
		for ( int i = 0; i < nfb.topic_sort.get(topic).size(); i ++ ){
			Fiszka toCheck = nfb.topic_sort.get(topic).get(i);
			if ( !rfb.ids.contains(toCheck.id) ) topicV.add(toCheck);
		}
		goodF.put(topic, topicV);
	}
	void countTopicsSize(){
		for (String topic: nfb.topic_sort.keySet() ){
			topics.add(topic);
			int size = nfb.topic_sort.get(topic).size();
			if (rfb.topic_sort.get(topic) != null ) 
				size -= rfb.topic_sort.get(topic).size();
			if ( size > 0 ) topicsSize.put(topic, size);
		}
	}
	Learning(User imr){
		super(imr);
		countTopicsSize();
	}
}