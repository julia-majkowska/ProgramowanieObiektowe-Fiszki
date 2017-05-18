abstract class Fiszka implements Serializable{
  abstract int cmp(); // cmp - wartość, po której będę porównywać słówka do losowania - dla nowych to level - najpierw ucz się level 1, potem 2, 3
  //dla powtórek to liczba wykonanych powtórek - te, które miały najmniej, najszybciej zostaną zapomniane
  
  int number_of_fields;
  Field [] fields;

//   String topic = "Wszystkie";
//   int level = 2;
}
class NewFiszka extends Fiszka{
  int cmp(){
	for ( int i = 0; i < number_of_fields; i ++ )
	  if ( fields[i].name == "level" ) return fields[i];
	return 0;
  }
}
class RepeatFiska extends Fiszka{
  int cmp(){
	for ( int i = 0; i < number_of_fields; i ++ )
	  if ( fields[i].name == "nr_of_repetition" ) return fields[i];
	return 0;
  }
}