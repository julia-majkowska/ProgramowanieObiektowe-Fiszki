import java.io.*;
import java.util.Scanner;
public class UserList2{
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
	void deleteUser(UserLogin toDeleteLogin) throws StopException{
		for ( int i = 0; i < size; i ++ ){
			if (toDeleteLogin.login == uLogins[i].login){
				if ( toDeleteLogin.password == uLogins[i].password ){
					for ( int j = i + 1; j < size; j ++ ) uLogins[i - 1] = uLogins[i];
					size --;
					return;
				}
				else throw new StopException("Podałeś złe hasło");	   
			}
		}
		throw new StopException("Użytkownik o takim loginie nie istnieje");
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
	void writeUserList2() throws ClassNotFoundException, IOException{
		File f = new File("Users.txt");
		FileOutputStream fileOut = new FileOutputStream("Users.txt");
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		out.writeInt(size);
		out.writeInt(nextid);
		for ( int i = 0; i < size; i ++ ) out.writeObject(uLogins[i]);
		out.close();
		fileOut.close();
	}
}
