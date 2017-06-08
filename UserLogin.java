import java.io.*;
public class UserLogin implements Serializable{
	private static final long serialVersionUID = 45649324;
	
	int id;
	String login, password;
	boolean toDelete = false;

	UserLogin(String l, String p){
		login = l;
		password = p;
	}	
	UserLogin(String l, String p, boolean d){
		login = l;
		password = p;
		toDelete = d;
	}
	//serialization
	void readObject(ObjectInputStream stream) throws ClassNotFoundException, IOException{
		id = stream.readInt();
		login = (String) stream.readObject();
		password = (String) stream.readObject();
	}
	void writeObject(ObjectOutputStream stream) throws IOException{
		stream.writeInt(id);
		stream.writeObject(login);
		stream.writeObject(password);
	}
}