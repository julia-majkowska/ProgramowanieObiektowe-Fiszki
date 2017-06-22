import java.util.*;
import java.io.*;
public class NBase implements Serializable{
	
	int nextid = 1, baseId;
	
	public LinkedList<Fiszka> list= new LinkedList<Fiszka>();
	public HashMap<String, Vector<Fiszka> > topic_sort = new HashMap<String, Vector<Fiszka> >();
	public Vector<FieldData> v = new Vector<FieldData>();
	
	public void setBase(LinkedList<Fiszka> ilist) {
		list = ilist;
		
		topic_sort.clear();
 		for(int i = 0; i < list.size(); i++)
		{ 
			list.get(i).id = nextid ++;
			Vector<Fiszka> vf;
			if(topic_sort.containsKey(list.get(i).kategoria().get()) == true) 
				vf = topic_sort.get(list.get(i).kategoria().get());
			else vf = new Vector<Fiszka>();
			vf.add(list.get(i));
			topic_sort.put(list.get(i).kategoria().get(), vf);
		}
	}	
	public Vector<FieldData> getData(){
            return v;
	}	
	public LinkedList<Fiszka> getList(){
		return list;
	}
	public void setAll(NBase x){
			baseId = x.baseId;
            nextid = x.nextid;
            list = x.list;
            v = x.v;
	}
	NBase(){}
}
