import java.util.*;
import java.io.*;
public class RBase{
	
	public LinkedList<RFiszka> toRepeat;
	public HashMap<String, Vector<RFiszka> > topic_sort = new HashMap<String, Vector<RFiszka> >();
	public HashSet<Integer> ids = new HashSet<Integer>();
	int userId, baseId;
	String name;
	
	RBase(int iuserId, int ibaseId){
		userId = iuserId;
		baseId = ibaseId;
		name = Integer.toString(userId)+"$"+baseId;
	}
	void readRBase(NBase infb, Calendar today){
		try{
			File f = new File(name);
			if ( f.exists() && !f.isDirectory() ){
				FileInputStream fileIn = new FileInputStream(name);
				ObjectInputStream in = new ObjectInputStream(fileIn);
				toRepeat = (LinkedList<RFiszka>) in.readObject();
				in.close();
				fileIn.close();
			}
			else toRepeat = new LinkedList<RFiszka>();
		}catch (Exception e){
			e.printStackTrace();
			return;
		}
		fillRFiszkas(infb, today);
	}
	void fillRFiszkas(NBase infb, Calendar today){
	
		topic_sort.clear();
		HashMap<Integer, Fiszka> nfmap = new HashMap<Integer, Fiszka>();
		for ( int i = 0; i < infb.list.size(); i ++ ){
			Fiszka nf = infb.list.get(i);
			nfmap.put(new Integer(nf.id), nf);
		}
		for ( int i = 0; i < toRepeat.size(); i ++ ){
			RFiszka rf = toRepeat.get(i);
			rf.nf = nfmap.get(rf.idF);
			rf.updated = false;
			if ( rf.nf.number_of_fields == -1 ){
				continue;
			}
			ids.add(rf.idF);
			Vector<RFiszka> vf;
			if(topic_sort.containsKey(rf.nf.kategoria().get()) == true) 
				vf = topic_sort.get(rf.nf.kategoria().get());
			else vf = new Vector<RFiszka>();
			vf.add(rf);
			topic_sort.put(rf.nf.kategoria().get(), vf);
		}
	}
	void writeRBase(){
		try{
			File f = new File(name);
			FileOutputStream fileOut = new FileOutputStream(name);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(toRepeat);
			out.close();
			fileOut.close();
		}catch(Exception e){
			return;
		}
	}
}