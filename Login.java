import java.io.*;
public class Login{
	User mr = null;
	UserList2 users = new UserList2();
	
	boolean tryLogin(UserLogin typedLogin, boolean newOne){
		if ( newOne == true ) return users.addUser(typedLogin);
		else {
			mr = users.chooseUser( typedLogin );
			return mr != null;
		}
	}
	Login(){
	}
}