import java.io.*;
import java.util.Scanner;
import java.util.Random;
class Options{
	String question;
	String[] options;
	int maxnr = 0;
	void showOptions(){
		System.out.println(question);
		if ( maxnr > 0 ) {
			System.out.println("(max "+Integer.toString(maxnr)+")");
		}
		else{
			for ( int i = 0; i < options.length; i ++ ) 
				System.out.println(( i + 1 ) + ": " + options[i]);
		}
	}
	int chooseOption(){
		Scanner scanner = new Scanner(System.in);
		int nr = scanner.nextInt();
		if ( maxnr > 0 ){ //liczba
			while ( nr < 1 || nr > maxnr ){
				System.out.println("Nie ma takiej opcji. Wybierz mniejszy numer.");
				nr = scanner.nextInt();
			}
		}
		else{ //opcje z listy
			while ( nr < 1 || nr > options.length ){
				System.out.println("Nie ma takiej opcji. Wybierz opcję z listy.");
				nr = scanner.nextInt();
			}
		}
		return nr;
	}
	Options(String q, String[] o){
		question = q;
		options = o;
	}
	Options(String q, int n){
		question = q;
		maxnr = n;
	}
}
class NewFiszka{
	String key, word, topic;
	int level = 2;
	public String toString(){
		return topic + ":  " + key + " -> " + word;
	}
	NewFiszka(String k, String w, String t){
		key = k;
		word = w;
		topic = t;
	}
}
class Base{
	int size = 0, topics_size = 1;
	NewFiszka[] list = new NewFiszka[10000];
	String[] topics = new String[100]; //znowu size exc
	
	void defaultBase(){
		size = 10;
		String[] keys = {"kot","pies","kurczak","ryba","kon","slon","wiewiorka","papuga","mysz","zyrafa"};
		String[] words = {"cat","dog","chicken","fish","horse","elephant","squirrel","parrot","mouse","giraffe"};
		
		for(int i = 0; i < 10; i ++ ) {
			list[i] = new NewFiszka(keys[i], words[i], "zwierzęta");
		}
		topics[1] = "zwierzęta";
		topics_size ++;
	}
	void add(NewFiszka nf){ //sizeException
		list[size] = nf;
		size ++;
		//check if this is a new topic -> add to topics
	}
	Base(){}
	Base (int s, NewFiszka[] nfB){
		topics[0] = "wszystkie";
		size = s;
		for ( int i = 0; i < s; i ++ ) list[i] = nfB[i];
	}
}
 class Drawing{
	Base inBase;
	Base toLearn = new Base();
	String topicToLearn = "wszystkie";
	
	void drawCards(){
		String[] options = {"Konkretny temat", "Wszystkie"};
		Options opt = new Options("Chcesz uczyć się słówek z konkretnego tematu czy z wszystkich?", options);
		opt.showOptions();
		int nr = opt.chooseOption();
		String topic = "wszystkie";
		if ( nr == 1 ) {
			Options top = new Options("Z jakiego tematu chcesz się uczyć słówek?", inBase.topics);
			top.showOptions();
			topicToLearn = inBase.topics[top.chooseOption()];
		}
		Base toDraw;
		if ( topicToLearn == "wszystkie" ) toDraw = inBase;
		else{
			toDraw = new Base();
			for ( int i = 0; i < inBase.size; i ++ )
				if ( inBase.list[i].topic == topicToLearn ) toDraw.add(inBase.list[i]);
			toDraw.topics[toDraw.topics_size] = topicToLearn;
			toDraw.topics_size++;
		}
		Options how_many= new Options("Ilu słówek chcesz się uczyć?", Math.min(toDraw.size, 50));
		how_many.showOptions();
		int howm = how_many.chooseOption(), size = toDraw.size;
		Random generator = new Random();
		while ( howm > 0 ){
			int wordNr = generator.nextInt(size);
			toLearn.add(toDraw.list[wordNr]);
			toDraw.list[wordNr] = toDraw.list[size - 1];
			size --;
			howm --;
		}
	}
  Drawing(){
  }
}
class Learning extends Drawing{
	void learn(){
		drawCards();
		
		for ( int i = 0; i < toLearn.size; i ++ ) 
			System.out.println(toLearn.list[i]);
	}
	Learning (Base nfBase){
		inBase = nfBase;
	}
}
class Repeating extends Drawing{
 
//   Lista toRepeat = drawCards(cards, "topic");
//   void repeat(){
// 	//
//   }
}

class MainMenu{
	int user;
	Base newfBase;

	String[] options = {"Dodaj słówka", "Ucz się nowych słówek", "Powtarzaj słówka" };
	Options opt = new Options("Co chcesz zrobić?", options);
	
	void openMenu(){
		opt.showOptions();
		int cO = opt.chooseOption();
		if ( cO == 1 ){
			//Julka
		}
		if ( cO == 2 ) {
			Learning L = new Learning(newfBase);
			L.learn();
		}
	}
	MainMenu(int u, Base db){
		user = u;
		newfBase = db;
	}
}
public class Program{
	int user = -1;
	UserList theUsers = new UserList();
	
	Base dfBase = new Base();
	
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
		dfBase.defaultBase();

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
		MainMenu m = new MainMenu(user, dfBase);
		m.openMenu();
	}
	public static void main(String[] args){	
		Program p = new Program();
		if ( p.user == -1 ) return;
	}
}