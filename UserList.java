import java.io.*;
import java.util.Scanner;
class User implements Serializable{
	private static final long serialVersionUID = 95847483;
	
	String name;
	int id;
// 	Base rfBase = new Base(); //base with fiszkas to repeat
  
	public String toString(){
		return name;
	}
	public void readObject(ObjectInputStream stream) throws ClassNotFoundException, IOException{
		name = (String) stream.readObject();
		id = stream.readInt();
// 		rfBase = (Base) stream.readObject();
		//potem base
	}
	public void writeObject(ObjectOutputStream stream) throws IOException{
		stream.writeObject(name);
		stream.writeObject(id);
// 		stream.writeObject(rfBase);
		//potem base
	}
	User(String n, int nextid){
		name = n;
		id = nextid;
		//Okienko do podania danych
	}  
	//dodatkowo: edit password, edit name, clone user
}
public class UserList implements Serializable{
	int size = 0, nextid = 0;
	User[] UList = new User[10]; //od 1 do 9 (od 1 do size)
	private static final long serialVersionUID = 95847483;
	void readObject(ObjectInputStream stream) throws ClassNotFoundException, IOException{
		size = stream.readInt();
		nextid = stream.readInt();

		System.out.println(size);
		for ( int i = 1; i <= size; i ++ )
			UList[i] = (User)stream.readObject();
	}
	void writeObject(ObjectOutputStream stream) throws IOException{
		stream.writeInt(size);
		stream.writeInt(nextid);
		for ( int i = 1; i <= size; i ++ )
			stream.writeObject(UList[i]);
	}
	void addUser(String n) throws SizeException{
		if ( size == 9 ) throw new SizeException("Nie możesz dodać 10. użytkownika, to za dużo.");
		size ++;
		UList[size] = new User(n, nextid ++);
	}
	void deleteUser(int nr) throws SizeException{
		if ( nr < 1 || nr > size ) throw new SizeException("Nie możesz usunąć użytkownika, który nie istnieje.");
		for(int i = nr + 1; i <= size; i ++ ) UList[i - 1] = UList[i];
		size --;
	}
	int ChooseUser(){
		//wybranie użytkownika
		Scanner scanner = new Scanner(System.in);
		System.out.println("\nWybierz numer użytkownika:");
		
		//wypisanie użytkowników do wyboru
		if (size < 9 ) System.out.println("0: stwórz nowego użytkownika");	
		for ( int i = 1; i <= size; i ++ ) 
			System.out.println(i + ": " + UList[i].toString());
		if (size > 0 ) System.out.println((size + 1) + ": usuń użytkownika");
		System.out.println("-1: wyjdź z programu");
		
		int nr = scanner.nextInt();
		if ( nr == 0 ){
			System.out.println("Podaj nazwę dla nowego użytkownika:");
			String n = scanner.next();
			try{ addUser(n);
			} catch(SizeException e){
			}
// 			return theUsers.size;
		}
		if ( nr == size + 1 ){
			System.out.println("Podaj numer użytkownika, którego chcesz usunąć:");
			int n = scanner.nextInt();
			try{ deleteUser(n);
			} catch(SizeException e){
			}
		}
		return nr;
	}
	UserList(){}
}
