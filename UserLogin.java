import java.io.*;
public class UserLogin implements Serializable{
	private static final long serialVersionUID = 45649324;

	int id;
	String login, password;

	UserLogin(String l, String p){
		login = l;
		password = p;
	}	
}