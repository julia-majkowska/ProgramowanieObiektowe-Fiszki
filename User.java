import java.io.*;
import java.util.*;
class NextBaseId implements Serializable{
	private static final long serialVersionUID = 235783;
	int id = 1;
	public int takeId(){
		id ++;
		return id;
	}
	NextBaseId(){
	}
}
public class User implements Serializable{
	private static final long serialVersionUID = 95847483;
	String login;
	int id;
	NextBaseId nbId;
	NBase nf = new NBase();
// 	Base rfBase = new Base(); //base with fiszkas to repeat
  
	void readObject(ObjectInputStream stream) throws ClassNotFoundException, IOException{
		login = (String) stream.readObject();
		id = stream.readInt();
// 		rfBase = (Base) stream.readObject();
	}
	void writeObject(ObjectOutputStream stream) throws IOException{
		stream.writeObject(login);
		stream.writeObject(id);
// 		stream.writeObject(rfBase);
		//potem base
	}
	void modifyBase(){
		OknoModyfikacji julka = new OknoModyfikacji(nf);
	}
	void takeBase(boolean newOne){
		if (newOne == true) {
			nf.baseId = nbId.takeId();
			Okno_wpisywania julka = new Okno_wpisywania(nf);
			try {
				FileOutputStream fileOut = new FileOutputStream("NextBaseId.txt");
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				out.writeObject(nbId);
				out.close();
				fileOut.close();
			}catch(IOException i) {
				i.printStackTrace();
			}
		}
		else{
			OknoWyboru julka = new OknoWyboru(nf);
			
		}
	}
	User(String l, int i){
		login = l;
		id = i;
		try{
			File f = new File("NextBaseId.txt");
			if ( f.exists() && !f.isDirectory() ){
				FileInputStream fileIn = new FileInputStream("NextBaseId.txt");
				ObjectInputStream in = new ObjectInputStream(fileIn);
				nbId = (NextBaseId) in.readObject();
				in.close();
				fileIn.close();
			}
			else nbId = new NextBaseId();
		}catch(IOException e) {
				e.printStackTrace();
		}catch(ClassNotFoundException e){
				e.printStackTrace();
		}
	}
}
