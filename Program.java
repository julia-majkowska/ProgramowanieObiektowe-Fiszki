class Drawing (Lista[] cards, String topic){
  Lista[] draw(){
  }
}
class Learning extends Drawing{
  Lista toLearn = draw();
  void learn(){
  }
}
class Repeating extends Drawing{
  Lista toRepeat = draw();
  void repeat(){
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