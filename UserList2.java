import java.io.*;
import java.util.*;
public class UserList2 implements Serializable{
	private static final long serialVersionUID = 95847483;

	int size = 0, nextid = 0;
	LinkedList<UserLogin> uLogins;

	boolean addUser(UserLogin newLogin){
		for ( UserLogin ul: uLogins ){
			if ( ul.login.equals(newLogin.login) ) {
				System.out.println("Użytkownik o takim loginie już istnieje. Wybierz inny login.");
				return false;
			}
		}
		newLogin.id = nextid ++;
		uLogins.add(newLogin);
		try{
			writeUserList2();
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	User chooseUser(UserLogin typedLogin){
		for ( UserLogin ul: uLogins ){
			if ( ul.login.equals(typedLogin.login) ){
				if ( ul.password.equals(typedLogin.password) == false ){
					System.out.println("Podałeś złe hasło. Spróbuj jeszcze raz.");
					return null;
				}
				User user = new User(ul.login, ul.id);
				return user;
			}
		}
		System.out.println("Użytkownik o takim loginie nie istnieje.");
		return null;
	}
	UserList2(){
		try{
			readUserList2();
		}catch(Exception e) {
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
			uLogins = (LinkedList<UserLogin>)in.readObject();

			in.close();
			fileIn.close();
		}
		else uLogins = new LinkedList<UserLogin>();
	}
	void writeUserList2() throws ClassNotFoundException, IOException{
		File f = new File("Users.txt");
		FileOutputStream fileOut = new FileOutputStream("Users.txt");
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		out.writeInt(size);
		out.writeInt(nextid);
		out.writeObject(uLogins);
		out.close();
		fileOut.close();
	}
}
