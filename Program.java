import java.io.*;
import java.lang.*;
import java.util.*;
import java.util.Scanner;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Calendar;
import java.util.Date;
class Options{
	String badOption;
	int max_size;
	public int chooseOption(){
		Scanner scanner = new Scanner(System.in);
		int nr = scanner.nextInt();
		while ( nr < 1 || nr > max_size ){
			System.out.println(badOption);
			nr = scanner.nextInt();
		}
		return nr;
	}
}
class ListOptions extends Options{
	String question;
	String[] options;
	public void showOptions(){
		System.out.println(question);
		for ( int i = 0; i < max_size && options[i] != null; i ++ ) 
			System.out.println(( i + 1 ) + ": " + options[i]);
	}
	ListOptions(String q, String[] o){
		question = q;
		options = o;
		badOption = "Nie ma takiej opcji. Wybierz opcję z listy.";
		max_size = options.length;
	}
}
class NumberOptions extends Options{
	String question;
	public void showOptions(){
		System.out.println(question);
		System.out.println("(max "+Integer.toString(max_size)+")");
	}
	NumberOptions(String q, int n){
		question = q;
		badOption = "Nie ma takiej opcji. Wybierz mniejszy numer.";
		max_size = n;
	}
}
class NewFiszka{
	String key, word, topic;
	int id;
	int level = 2;
	boolean deleted = false;
	public String toString(){
		return topic + ":  " + key + " -> " + word;
	}
	NewFiszka(String k, String w, String t){
		key = k; word = w; topic = t;
	}
	NewFiszka(String k, String w, String t, int i){
		key = k; word = w; topic = t; id = i;
	}
}
class RepeatFiszka{
	NewFiszka fiszka;
	Date date_of_rep;
	int interval = 2;
	RepeatFiszka(NewFiszka f, Date d){
		fiszka = f;
		date_of_rep = d;
	}
}
class Base{
	int size = 0, topics_size = 1, nextid = 0;
	NewFiszka[] list = new NewFiszka[10000];
	String[] topics = new String[100]; //znowu size exc
	
	void defaultBase(){
		size = 10;
		String[] keys = {"kot","pies","kurczak","ryba","kon","slon","wiewiorka","papuga","mysz","zyrafa"};
		String[] words = {"cat","dog","chicken","fish","horse","elephant","squirrel","parrot","mouse","giraffe"};
		
		for(int i = 0; i < 10; i ++ ) {
			list[i] = new NewFiszka(keys[i], words[i], "zwierzęta", nextid);
			nextid ++;
		}
		topics[1] = "zwierzęta";
		topics_size ++;
	}
	void add(NewFiszka nf){ //sizeException
		nf.id = nextid;
		nextid ++;
		list[size] = nf;
		size ++;
		//check if this is a new topic -> add to topics
	}
	Base(){
		topics[0] = "wszystkie";
	}
	Base (int s, NewFiszka[] nfB){
		topics[0] = "wszystkie";
		size = s;
		for ( int i = 0; i < s; i ++ ) {
			list[i] = nfB[i];
			nextid = Math.max ( list[i].id + 1, nextid );
		}
	}
}
class Drawing{
	Date today;
	Base inBase;
	Base toLearn = new Base();
	String topicToLearn = "wszystkie";
	
	void drawCards(){
		String[] options = {"Konkretny temat", "Wszystkie"};
		ListOptions opt = new ListOptions("Chcesz uczyć się słówek z konkretnego tematu czy z wszystkich?", options);
		opt.showOptions();
		int nr = opt.chooseOption();
		if ( nr == 1 ) {
			ListOptions top = new ListOptions("Z jakiego tematu chcesz się uczyć słówek?", inBase.topics);
			top.showOptions();
			topicToLearn = inBase.topics[top.chooseOption()-1];
		}
		Base toDraw;
		if ( topicToLearn == "wszystkie" ) toDraw = inBase;
		else{
			toDraw = new Base();
			for ( int i = 0; i < inBase.size; i ++ ){
				if ( inBase.list[i].topic.equals(topicToLearn) ) toDraw.add(inBase.list[i]);
				else System.out.println(inBase.list[i].topic);
			}
			toDraw.topics[1] = topicToLearn;
			toDraw.topics_size = 2;
		}
		NumberOptions how_many= new NumberOptions("Ilu słówek chcesz się uczyć?", Math.min(toDraw.size, 50));
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
	int[] shuffleArray ( int[] ar ){
		Random generator = new Random();
		for ( int i = ar.length - 1; i > 0; i -- ){
			int poz = generator.nextInt(i + 1);
			int t = ar[i];
			ar[i] = ar[poz];
			ar[poz] = t;
		}
		return ar;
	}
	int learnt = 0;
	RepeatFiszka[] rf = new RepeatFiszka[1000]; //size exception
	void learn(){
		drawCards();

		excercise1();
		excercise2();
	}
	void excercise1(){
		System.out.println("Przejrzyj słówka");
		
// 		shuffleArray(toLearn);
		int k = 4;
		for ( int i = 0; i < toLearn.size; i += k ) {
			for ( int j = i; j < Math.min ( i + k, toLearn.size ); j ++ )
				System.out.println(toLearn.list[j]);
		}
		System.out.println("1: Dalej");
		Scanner scanner = new Scanner(System.in);
		int nr = scanner.nextInt();
		while ( nr != 1 ) nr = scanner.nextInt();
		
		for ( int i = 0; i < 40; i ++ ) System.out.println("");
	}
	void excercise2(){
		System.out.println("Dopasuj słówka");
		
// 		shuffleArray(toLearn);
		int k = 4;
		boolean[] ok = new boolean[toLearn.size];
		boolean all_ok = false;
		
		while ( all_ok == false ){
			all_ok = true;
			for ( int i = 0; i < toLearn.size; i += k ) {
				if ( ok[i] == true ) continue;
				ok[i] = true;
				
				int mink = Math.min ( k, toLearn.size - i );
				int[] tmp_word = new int[mink];
				for(int j = 0; j < mink; j ++ ) tmp_word[j] = i + j;
				shuffleArray (tmp_word);
				
				for ( int j = 0; j < mink; j ++ )
					System.out.println((j + 1) + ": "+ toLearn.list[tmp_word[j]].key);
				System.out.println("");
				
				int[] tmp_key = new int[mink];
				for(int j = 0; j < mink; j ++ ) tmp_key[j] = i + j;
				shuffleArray(tmp_key);
				
				for ( int j = 0; j < mink; j ++ )
					System.out.println((j + 1) + ": " + toLearn.list[tmp_key[j]].word);
				System.out.println("");
				
				Scanner scanner = new Scanner(System.in);
				
				for ( int j = 0; j < mink; j ++ ) {
					System.out.print((j + 1) + ": ");
					int ans = scanner.nextInt();
					if ( !toLearn.list[tmp_word[j]].word.equals(toLearn.list[tmp_key[ans-1]].word) ) ok[i] = false;
				}
				System.out.println("");
				if ( ok[i] == false ){
					all_ok = false;
					System.out.println("Źle dopasowałeś/aś numery. Te słowa tłumaczą się tak:");
					for ( int j = 0; j < mink; j ++ ){
						System.out.println(toLearn.list[tmp_word[j]]);
					}
					for ( int j = 0; j < 40; j ++ ) System.out.println("");
				}
				else{
					for ( int j = 0; j < mink; j ++ ){
						rf[learnt] = new RepeatFiszka(toLearn.list[tmp_word[j]], today);
						learnt ++;
					}
				}
			}
		}
		System.out.println("Dobrze!");
	}
	Learning (Base nfBase, Date d){
		inBase = nfBase;
		today = d;
	}
}
class Repeating extends Drawing{
//   Date today; 
//   Lista toRepeat = drawCards(cards, "topic");
//   void repeat(){
// 	//
//   }
}

class MainMenu{
	int user;
	Base newfBase;
	Calendar cal = Calendar.getInstance();

	String[] options = {"Dodaj słówka", "Ucz się nowych słówek", "Powtarzaj słówka" };
	ListOptions opt = new ListOptions("Co chcesz zrobić?", options);
	
	void openMenu(){
		opt.showOptions();
		int cO = opt.chooseOption();
		if ( cO == 1 ){
			//Julka
		}
		if ( cO == 2 ) {
			Learning L = new Learning(newfBase, cal.getTime());
			L.learn();
		}
	}
	MainMenu(int u, Base db){
		user = u;
		newfBase = db;
		cal.setTime(cal.getTime());
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