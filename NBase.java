public class NBase{
	String name = "";
	int size = 0, topics_size = 1, nextid = 0;
	
	NewFiszka[] list = new NewFiszka[10000]; //ew. size exc
	String[] topics = new String[100]; //znowu size exc
	Vector<FieldData> v;
	
	NBase(){
		topics[0] = "wszystkie";
		//wczytaj z pliku
	}
	setBase(LinkedList<Fiszka> l) {
		list = l.toArray(list);
	}
	setBase(String n, LinkedList<Fiszka> l) {
		name = n;
		list = l.toArray(list);
	}
}