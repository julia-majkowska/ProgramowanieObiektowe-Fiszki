// class Drawing{
//   Drawing(Lista cards, String topic, int how_many){
// 	  Lista cardsToDraw;
// 	  
// // 	  cards.sort();
// // 	  for ( int i = 0; i < cards.size(); i ++ ){
// 		  
// // 	  }
//   }
// }
// class Learning extends Drawing{
// 
//   Lista toLearn = drawCards(cards, "topic");
//   void learn(){
// 	//
//   }
// }
// class Repeating extends Drawing{
//  
//   Lista toRepeat = drawCards(cards, "topic");
//   void repeat(){
// 	//
//   }
// }
// 
import java.io.*;
public class Program{
	int user = -1;
	UserList theUsers = new UserList();
	void readUsers(){
		try{
			File f = new File("Users.txt");
			if ( f.exists() && !f.isDirectory() ){
				FileInputStream fileIn = new FileInputStream("Users.txt");
				ObjectInputStream in = new ObjectInputStream(fileIn);
				theUsers = (UserList)in.readObject();
				in.close();
				fileIn.close();
			}
		}catch(IOException i) {
			i.printStackTrace();
			return;
		}catch(ClassNotFoundException c) {
			System.out.println("UserList class not found");
			c.printStackTrace();
			return;
		}
	}
	void writeUsers(){
		try {
			FileOutputStream fileOut = new FileOutputStream("Users.txt");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(theUsers);
			out.close();
			fileOut.close();
		}catch(IOException i) {
			i.printStackTrace();
		}
	}
	Program(){ 
		this.readUsers();
		user = theUsers.ChooseUser();
		while ( user < 1 || user > theUsers.size ) {
			if ( user == -1 ){
				this.writeUsers();

				//save everything
				break;
			}
			user = theUsers.ChooseUser();
		}
	}
	public static void main(String[] args){	
		Program p = new Program();
		if ( p.user == -1 ) return;
	}
}