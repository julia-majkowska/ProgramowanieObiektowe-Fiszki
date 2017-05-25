// class Fiszka
// class Drawing{
//   Drawing(Lista cards, String topic, int how_many){
// 	  Lista cardsToDraw;
// 	  
// 	  cards.sort();
// 	  for ( int i = 0; i < cards.size(); i ++ ){
// 		  
// 	  }
//   }
// }
// class Learning extends Drawing{
// 
//   Lista toLearn = drawCards(cards, "topic");
//   void learn(){
// 	
//   }
// }
// class Repeating extends Drawing{
//  
//   Lista toRepeat = drawCards(cards, "topic");
//   void repeat(){
// 	//
//   }
// }

import java.io.*;
import java.util.Scanner;
class MainMenu{
	String[] options = {"Dodaj słówka", "Ucz się nowych słówek", "Powtarzaj słówka" };
	
	void openMenu(){
		System.out.println("Co chcesz zrobić?");
		for ( int i = 0; i < options.length; i ++ ) 
			System.out.println(( i + 1 ) + ": " + options[i]);
		int cO = chooseOption();
// 		if ( c0 == 2 ) {
// 			Learning L = new Learning();
// 		}
	}
	int chooseOption(){
		Scanner scanner = new Scanner(System.in);
		int nr = scanner.nextInt();
		while ( nr < 1 || nr > options.length ){
			System.out.println("Nie ma takiej opcji. Wybierz opcję z listy.");
			nr = scanner.nextInt();
		}
		return nr;
	}
}
public class Program{
	int user = -1;
	UserList theUsers = new UserList();
	//czy to mogłoby być w klasie UserList?
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
		this.writeUsers(); //temporary
		MainMenu m = new MainMenu();
		m.openMenu();
	}
	public static void main(String[] args){	
		Program p = new Program();
		if ( p.user == -1 ) return;
	}
}