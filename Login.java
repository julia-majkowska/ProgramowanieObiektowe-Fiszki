import java.io.*;
public class Login{
	User mr = null;
	UserList2 users = new UserList2();
	
	boolean tryLogin(UserLogin typedLogin, boolean newOne){
		if ( newOne == true ) {
			try{
				users.addUser(typedLogin);
				users.writeUserList2();
			}catch(SizeException e){
				return false;
			}catch(StopException e){
				return false;
			}catch(ClassNotFoundException e) {
				System.out.println("UserLogin class not found");
				e.printStackTrace();
				return false;
			}catch(IOException e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}
		else {
			try{
				mr = users.chooseUser( typedLogin );
			}catch(StopException e) {
				System.out.println("Użytkownik o takich danych nie istnieje");
				return false;
			}
			return true;
		}
	}
	Login(){
		try{
			users.readUserList2();
		}catch(IOException e) {
			e.printStackTrace();
			return;
		}catch(ClassNotFoundException e) {
			System.out.println("UserList2 class not found");
			e.printStackTrace();
			return;
		}
	}
	public static void main(String[] args){
	
	}
}