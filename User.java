import java.io.*;
import java.util.*;
public class User implements Serializable{
	private static final long serialVersionUID = 95847483;
	String login;
	int id;
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
			Okno_wpisywania julka = new Okno_wpisywania(nf);
		}
		else{
			OknoWyboru julka = new OknoWyboru(nf);
		}
	}
	User(String l, int i){
		login = l;
		id = i;
	}
}
