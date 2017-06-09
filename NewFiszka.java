public class NewFiszka extends Fiszka{

    NewFiszka(int n){
        number_of_fields = n;
        fields = new Field[n];
    }
  int cmp(){
	/*for ( int i = 0; i < number_of_fields; i ++ )
	  if ( fields[i].name == "level" ) return fields[i];
	return 0;*/
    return 0;
  }
}