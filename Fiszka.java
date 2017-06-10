import java.io.*;
public abstract class Fiszka implements Serializable{
  abstract int cmp(); // cmp - wartość, po której będę porównywać słówka do losowania - dla nowych to level - najpierw ucz się level 1, potem 2, 3
  //dla powtórek to liczba wykonanych powtórek - te, które miały najmniej, najszybciej zostaną zapomniane
  
  int number_of_fields;
  Field [] fields;


  public void setith(Field a, int i){
    fields[i] = a;
  }
  public Field getith(int i ){return fields[i];}
  public void setDefault(Field a, int i)
  {
    if(fields[i] == null) fields[i] = a;
  }

//   String topic = "Wszystkie";
//   int level = 2;
}