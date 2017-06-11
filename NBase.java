import java.util.*;
import java.io.*;
public class NBase implements Serializable{
	public String name = "";
	public int size = 0, topics_size = 1, nextid = 0;
	
	public NewFiszka[] list = new NewFiszka[10000]; //ew. size exc
	public String[] topics = new String[100]; //znowu size exc
	public Vector<FieldData> v = new Vector<FieldData>();
	
	NBase(){
		topics[0] = "wszystkie";
		//wczytaj z pliku
	}
	public void setBase(LinkedList<Fiszka> l) {
                size = l.size();
		list = l.toArray(list);
	}
	public void setBase(String n, LinkedList<Fiszka> l) {
		name = n;
		size = l.size();
		list = l.toArray(list);
	}
	
	public Fiszka[] getBase()
	{
            return list;
	}
	
	public Vector<FieldData> getData()
	{
            return v;
	}
	
	public LinkedList<Fiszka> getList()
	{
            LinkedList<Fiszka> wynik = new LinkedList<Fiszka>();
            for(int i = 0; i<size; i++) wynik.add(list[i]);
            return wynik;
	}
	
	public void setAll(NBase x)
	{
            name = x.name;
            size = x.size;
            topics_size = x.topics_size;
            nextid = x.nextid;
            list = x.list;
            topics = x.topics;
            v = x.v;
	}
}