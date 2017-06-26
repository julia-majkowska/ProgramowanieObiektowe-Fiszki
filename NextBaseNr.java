import java.io.*;
import java.util.*;
public class NextBaseNr implements Serializable{
	private static final long serialVersionUID = 235783;
	
	int nr = 1;
	int giveNr(){
		nr ++;
		writeNextBaseNr();
		return nr;
	}
	void readNextBaseNr(){
		try{
			String fileName = "NextBaseNr.txt";
			File f = new File(fileName);
			if ( f.exists() && !f.isDirectory() ){
				FileInputStream fileIn = new FileInputStream(fileName);
				ObjectInputStream in = new ObjectInputStream(fileIn);
				nr = in.readInt();
				in.close();
				fileIn.close();
			}
		}catch(Exception e) {
				e.printStackTrace();
		}
	}
	void writeNextBaseNr(){
		try {
			String fileName = "NextBaseNr.txt";
			FileOutputStream fileOut = new FileOutputStream(fileName);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeInt(nr);
			out.close();
			fileOut.close();
		}catch(Exception i) {
				i.printStackTrace();
		}
	}
	NextBaseNr(){
		readNextBaseNr();
	}
}
