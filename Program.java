class Fiszka //implements Serializable
{
    int number_of_fields;
    Field [] pola;
    Fiszka();
}

class Drawing{
  Lista cardsToDraw;
  Lista drawCards(Lista cardsToDraw, String topic){
	//
  }
  Drawing(Lista cards){
	cardsToDraw = cards;
  }
}
class Learning extends Drawing{

  Lista toLearn = drawCards(cards, "topic");
  void learn(){
	//
  }
}
class Repeating extends Drawing{
 
  Lista toRepeat = drawCards(cards, "topic");
  void repeat(){
	//
  }
}

class User{
  Lista base = Lista();
  String name;
    
  User(){
	//Okienko do podania danych
  }  
  //dodatkowo: edit password, edit name, clone user
}
public class Program{
  User[] List_of_users;
  
  Program(){
	//Okienko
  }
  public static void main(String[] args){
	Program p;
	
  }
}