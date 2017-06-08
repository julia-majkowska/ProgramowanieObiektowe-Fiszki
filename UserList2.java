import java.io.*;
import java.util.Scanner;
class User implements Serializable{
	private static final long serialVersionUID = 95847483;
	String login;
	int id;
// 	Base rfBase = new Base(); //base with fiszkas to repeat
  
	public String toString(){
		return login;
	}
	public void readObject(ObjectInputStream stream) throws ClassNotFoundException, IOException{
		login = (String) stream.readObject();
		id = stream.readInt();
// 		rfBase = (Base) stream.readObject();
	}
	public void writeObject(ObjectOutputStream stream) throws IOException{
		stream.writeObject(login);
		stream.writeObject(id);
// 		stream.writeObject(rfBase);
		//potem base
	}
	User(String l, int i){
		login = l;
		id = i;
	}
}
public class UserList2 implements Serializable{
	private static final long serialVersionUID = 95847483;

	int size = 0, nextid = 0;
	UserLogin[] uLogins = new UserLogin[10];
	
	void addUser(UserLogin newLogin) throws SizeException, StopException{
		if ( size == 10 ) throw new SizeException("Nie możesz dodać 11. użytkownika, to za dużo.");
	 
		for ( int i = 0; i < size; i ++ )
			if ( uLogins[i].login.equals(newLogin.login) ) throw new StopException("Użytkownik o takim loginie już istnieje. Wybierz inny login.");
		
		newLogin.id = nextid ++;
		uLogins[size++] = newLogin;
	}
	void deleteUser(int nr){
		for(int i = nr + 1; i < size; i ++ ) uLogins[i - 1] = uLogins[i];
		size --;
	}
	User chooseUser(UserLogin typedLogin) throws StopException{
		for ( int i = 0; i < size; i ++ ){
			if ( uLogins[i].login.equals(typedLogin.login) ){
				if ( uLogins[i].password.equals(typedLogin.password) == false )
					throw new StopException("Podałeś złe hasło. Spróbuj jeszcze raz.");
				if (uLogins[i].toDelete == true ) {
					deleteUser(i);
					return null;
				}
				else{
					User user = new User(uLogins[i].login, uLogins[i].id);
					return user;
				}
			}
		}
		return null;
	}
	UserList2(){
		try{
			readUserList2();
		}catch(IOException e) {
			e.printStackTrace();
			return;
		}catch(ClassNotFoundException e) {
			System.out.println("UserList2 class not found");
			e.printStackTrace();
			return;
		}
	}
	void readUserList2() throws ClassNotFoundException, IOException{
		File f = new File("Users.txt");
		if ( f.exists() && !f.isDirectory() ){
			FileInputStream fileIn = new FileInputStream("Users.txt");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			size = in.readInt();
			nextid = in.readInt();		
			for ( int i = 0; i < size; i ++ ) uLogins[i] = (UserLogin) in.readObject();

			in.close();
			fileIn.close();
		}
	}
	//serialization
	void writeObject(ObjectOutputStream stream) throws IOException{
		stream.writeInt(size);
		stream.writeInt(nextid);
		for ( int i = 0; i < size; i ++ ) stream.writeObject(uLogins[i]);
	}
}
