import java.io.*;
import java.util.*;
public class Program2{
	User user = null;
	NBase nf = new NBase();
//	RBase rf;
	// 		Okno_m m = new Okno_m();

	boolean logIn(){
		UserList2 ulist = new UserList2();
		
		while ( user == null ){
			UserLogin typedLogin = new UserLogin("wybierz użytkownika, jeśli chcesz stworzyć/usunąć użytkownika wpisz +/-");//m.menu(); //
						
			while ( typedLogin.login.equals("+") || typedLogin.login.equals("-") ){
				if ( typedLogin.login.equals("-") ) {
					UserLogin toDeleteLogin = new UserLogin("jakiego użytkownika chcesz usunąć");

					try{
						ulist.deleteUser(toDeleteLogin);
						ulist.writeUserList2();
					}catch(StopException e){
						System.out.println("użytkownik o takich danych nie istnieje");
						e.printStackTrace();
						return false;
					}catch(ClassNotFoundException e) {
						System.out.println("UserLogin class not found");
						e.printStackTrace();
						return false;
					}catch(IOException e) {
						e.printStackTrace();
						return false;
					}
				}
				if ( typedLogin.login.equals("+")){
					UserLogin newLogin = new UserLogin("jakiego użytkownika chcesz stworzyć");//m.newUserMenu();//
					try{
						ulist.addUser(newLogin);
						ulist.writeUserList2();
					}catch(SizeException e){
					}catch(StopException e){
					}catch(ClassNotFoundException e) {
						System.out.println("UserLogin class not found");
						e.printStackTrace();
						return false;
					}catch(IOException e) {
						e.printStackTrace();
						return false;
					}
				}
// 				typedLogin = m.menu();
				typedLogin = new UserLogin("wybierz użytkownika, jeśli chcesz stworzyć/usunąć użytkownika wpisz +/-");//m.menu(); //
			}
			try{
				user = ulist.chooseUser( typedLogin );
			}catch(StopException e) {
				System.out.println("Użytkownik o takich danych nie istnieje");
			}
		}
		return true;
	}
	boolean chooseBase(){
		String[] options = {"Wybierz bazę z pliku", "Stwórz nową bazę"};
		ListOptions opt = new ListOptions("Co chcesz zrobić?", options);
		
// 		int opt = m.chooseNr(options);
		int nr = opt.chooseOption();
		if ( nr == -1 ) return false;
		if ( nr == 1 ) {
			OknoWyboru createNewBase = new OknoWyboru(nf);
			System.out.println("here");
		}
		if ( nr == 2 ) {
			Edytor chooseFromFileBase = new Edytor(nf);
		}
		System.out.println("here");
		return true;
	}
	boolean learn(Date today){
	
		String[] options = {"Dodaj słówka", "Ucz się nowych słówek", "Powtarzaj słówka", "Ćwiczenia dodatkowe" };
		ListOptions opt = new ListOptions("Co chcesz zrobić?", options);
		
// 		int opt = m.chooseNr(options);
		int nr = opt.chooseOption();
		if ( nr == -1 ) return false;
		if ( nr == 1 ) {
			Okno_wpisywania addNewWords = new Okno_wpisywania(nf);
		}
		if ( nr == 2 ) System.out.println("Learning");
		if ( nr == 3 ) System.out.println("Repeating");

		return true;
	}
	Program2(){
		if ( logIn() == true ) System.out.println("Hello " + user.login + "!");

		Calendar cal = Calendar.getInstance();
		Date today = cal.getTime();
// 		
		while ( chooseBase() == true )
			while ( learn(today) == true );
	}
	public static void main(String[] args){	
		Program2 p = new Program2();
	}
}
class Options{
	String badOption;
	int max_size;
	public void showOptions(){}
	
	public int chooseOption(){
		showOptions();

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

