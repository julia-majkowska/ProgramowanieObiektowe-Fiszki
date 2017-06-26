import java.io.*;
import java.util.*;
public class Exc{
	int nr;
	RFiszka rf;
	boolean tmpLearnt = false;
	Exc(int inr, RFiszka irf){
		nr = inr;
		rf = irf;
	}
	public static String next = "aeouiyabwbtdtcscghgkcqurlrfvfjgjzs";
    public static char findnext(char x){
		for ( int i = 0; i < next.length() - 1; i ++ ){
			if ( next.charAt(i) == x ) return next.charAt(i + 1);
		}
		return 'a';
	}
	public static Vector<String> hideLetters(String word){
		Set<String> chosenLetters = new HashSet<String>();

		String newword = "";
		Random generator = new Random();
		
		int j = 1, k = Math.max(word.length() / 5, 2);
		for ( int i = 0; i < word.length(); i ++ ){
			char letter = word.charAt(i);
			if ( i != j || i + 1 == word.length()){
				newword += letter;
			}
			else {
				if ( letter == ' ' ){
					newword += letter;
					j += 2;
					continue;
				}
				newword += '_';
				if ( chosenLetters.size() < 8 ) chosenLetters.add(""+letter);
				if ( chosenLetters.size() < 8 ) chosenLetters.add(""+findnext(letter));
				j += k;
			}
		}
		
		while ( chosenLetters.size() < 10 ){
			int rand = generator.nextInt(26);
			String letter = String.valueOf((char)(rand + 97));
			chosenLetters.add(""+letter);
		}
		Vector<String> otherLetters = new Vector<String>();
		for ( String letter : chosenLetters ){
			otherLetters.add(letter);
		}
		Collections.sort(otherLetters);
		otherLetters.add(newword);
		return otherLetters;
	}
	public static boolean equalWords(String w1, String w2){
		return w1.equals(w2);
	}
	public static void main(String[] args){
		System.out.println(hideLetters("encyklopedia"));
	}
}