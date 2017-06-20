import java.io.*;
import java.util.*;
public class RFiszka implements Serializable{
	private static final long serialVersionUID = 90863423;

	int idF;
	Fiszka nf;

	Date next_rep;
	int interval = 1;
	
	RFiszka(Fiszka n, Date today){
		nf = n;
		next_rep = today;
	}
	void update(boolean nextLevel, Date today){ //true, jeśli dobrze powtórzona, false, jeśli cały cykl od nowa
		if ( nextLevel == true ) interval = new Double(1.7*interval + 1).intValue();
		else interval = 1;
		next_rep = DateUtil.addDays(today, interval);
	}
	
	//serialization
	void readObject(ObjectInputStream stream) throws ClassNotFoundException, IOException{
		idF = stream.readInt();
		
		next_rep = (Date) stream.readObject();
		interval = stream.readInt();
	}
	void writeObject(ObjectOutputStream stream) throws IOException{
		stream.writeInt(idF);
		
		stream.writeObject(next_rep);
		stream.writeObject(interval);
	}
}