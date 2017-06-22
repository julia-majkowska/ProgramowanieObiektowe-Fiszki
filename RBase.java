import java.util.*;
import java.io.*;
public class RBase{
	
	public LinkedList<RFiszka> toRepeat;
	public HashMap<String, Vector<RFiszka> > topic_sort = new HashMap<String, Vector<RFiszka> >();
	public HashSet<Integer> ids;
	int userId, baseId;
	
	void readRBase(NBase infb, Date today) throws ClassNotFoundException, IOException{
		String fileName = Integer.toString(userId)+"$"+baseId;
		
		File f = new File(fileName);
		if ( f.exists() && !f.isDirectory() ){
			FileInputStream fileIn = new FileInputStream(fileName);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			toRepeat = (LinkedList<RFiszka>) in.readObject();
			in.close();
			fileIn.close();
		}
		else toRepeat = new LinkedList<RFiszka>();
		
		HashMap<Integer, Fiszka> nfmap = new HashMap<Integer, Fiszka>();
		for ( int i = 0; i < infb.list.size(); i ++ ){
			Fiszka nf = infb.list.get(i);
			nfmap.put(new Integer(nf.id), nf);
		}
		for ( int i = 0; i < toRepeat.size(); i ++ ){
			RFiszka rf = toRepeat.get(i);
			ids.add(rf.idF);

			if ( today.before(rf.next_rep) ) continue;

			rf.nf = nfmap.get(rf.idF);
			
			Vector<RFiszka> vf;
			if(topic_sort.containsKey(rf.nf.kategoria().get()) == true) 
				vf = topic_sort.get(rf.nf.kategoria().get());
			else vf = new Vector<RFiszka>();
			vf.add(rf);
			topic_sort.put(rf.nf.kategoria().get(), vf);
		}
	}
	void writeRBase() throws ClassNotFoundException, IOException{
		String fileName = Integer.toString(userId)+"$"+baseId;
		File f = new File(fileName);
		FileOutputStream fileOut = new FileOutputStream(fileName);
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		out.writeObject(toRepeat);
		out.close();
		fileOut.close();
	}
	RBase(int iuserId, NBase infb, Date itoday ){
		userId = iuserId;
		baseId = infb.baseId;
		
		try{
			readRBase(infb, itoday);
		}catch(IOException e) {
			e.printStackTrace();
			return;
		}catch(ClassNotFoundException e) {
			System.out.println("RBase class not found");
			e.printStackTrace();
			return;
		}
	}
}