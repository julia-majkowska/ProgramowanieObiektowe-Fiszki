class FieldData
{
    public String typ = "TYP"; //TEKST LICZBA OBRAZ
    public String nazwa = "nazwa";
    
    FieldData(String t, String n){
		typ = t;
		nazwa = n;
	}
}
class IntField extends FieldData{
	public int value = 0;
	
	IntField(int v, String n){
		super("LICZBA", n);
		value = v;
	}
}
class StringField extends FieldData{
	public String value = "";
	
	StringField(String v, String n){
		super("TEKST", n);
		value = v;
	}
}
class PictureField extends FieldData{
}
class NewFiszka{// implements Serializable{
	FieldData[] key;
	int nr_of_keys = 1;

	FiledData meaning;
	
	IntField level = new IntField(2, "level");
	
	StringField[] topic;
	int nr_of_topics = 1;
	
	NewFiszka() {
		topic[0] = new StringField("Wszystkie", "topic");
	}
	FieldData[] fiszkadata{
		//załóżmy, ze fiszki mają klucz, wartośc i level dla jednej wartości mogą mieć kilka kluczy, ale jeden level do tego klucze i wartości mogą być 
		//stringami/intami/obrazkami to wtedy vector<fieldData> dla listy będize mówi, jak ma wyglądać tabelka do wyswietlenia tej listy np. są 2 słowa i jedno ma klucz-obrazek, 
		//jedno ma 2 klucza słowa i wtedy tabelka musi miec oddzielną kolumne na klucz-obrazek i 2 kolumny na klucze-słowa po prostu niektóre pola będą puste
	}
	int cmp(){ // cmp - wartość, po której będę porównywać słówka do losowania - dla nowych to level - najpierw ucz się level 1, potem 2, 3
	//dla powtórek to liczba wykonanych powtórek - te, które miały najmniej, najszybciej zostaną zapomniane
	}
}
class RepeatFiszka extends NewFiszka{
	int nr_of_repetitions = 0;
	int start_day;
	int next_repetition_day;

	int cmp(){
	}
}