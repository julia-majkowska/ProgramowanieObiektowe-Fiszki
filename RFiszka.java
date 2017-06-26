import java.io.*;
import java.util.*;
public class RFiszka implements Serializable{
	private static final long serialVersionUID = 90863423;

	int idF;
	Fiszka nf;
	boolean updated = false;
	Calendar next_rep = Calendar.getInstance();
	int interval = 1;
	
	RFiszka(Fiszka n, Calendar today){
		nf = n;
		next_rep = (Calendar) today.clone();
	}
	void update(boolean nextLevel, Calendar today){ //true, jeśli dobrze powtórzona, false, jeśli cały cykl od nowa
		if ( updated == true ) return;
		updated = true;
		if ( nextLevel == true ) interval = new Double(1.7*interval + 1).intValue();
		else interval = 1;
		next_rep = (Calendar) today.clone();
		next_rep.add(Calendar.DATE, interval);
	}
	
	//serialization
	void readObject(ObjectInputStream stream) throws ClassNotFoundException, IOException{
		idF = stream.readInt();
		
		next_rep = (Calendar) stream.readObject();
		interval = stream.readInt();
	}
	void writeObject(ObjectOutputStream stream) throws IOException{
		stream.writeInt(idF);
		
		stream.writeObject(next_rep);
		stream.writeObject(interval);
	}
}