public class Text extends Field
{
    String tresc;

    public Text(String s)
    {
        tresc = s;
    }

    public String get() {return tresc;}

    public String toString (){
        return "Tekst "+ tresc;
    }

}