import javax.swing.*;
import javax.swing.event.MenuKeyListener;
import javax.swing.event.DocumentListener;
import javax.swing.event.MenuKeyEvent;
import javax.swing.event. *;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
//zamienic hasła na 2 haslo i klucz i temat (na razie), hint

abstract class Field implements Serializable
{
    //void show(JPanel kontener);

}

class Picture extends Field
{
    File source; 

    public Picture(File f){
        source = f;
    }
}

class Text extends Field
{
    String tresc;

    public Text(String s)
    {
        tresc = s;
    }
}
abstract class Fiszka implements Serializable{
  abstract int cmp(); // cmp - wartość, po której będę porównywać słówka do losowania - dla nowych to level - najpierw ucz się level 1, potem 2, 3
  //dla powtórek to liczba wykonanych powtórek - te, które miały najmniej, najszybciej zostaną zapomniane
  
  int number_of_fields;
  Field [] fields;


  public void setith(Field a, int i){
    //fields[i] = a;
  }

//   String topic = "Wszystkie";
//   int level = 2;
}
class NewFiszka extends Fiszka{

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

interface Selected
{
    //JPanel kontener;
    public void clear_cont();

    public abstract void build();

    //public abstract Component get_comp();

}
class SelectedPicture
 extends JFileChooser 
 implements ActionListener, Selected
{
    JPanel kontener;
    Fiszka akt_fiszka;
    int numer_pola;
    //JFileChooser plik;
    File mojplik;
    public SelectedPicture(JPanel kont, Fiszka a, int n)
    {
        kontener = kont;
        akt_fiszka = a;
        numer_pola = n;
        //plik = new JFileChooser();
    }

    public void clear_cont()
    {
        kontener.removeAll(); 
        kontener.revalidate();
        kontener.repaint();
        kontener.updateUI();
    }   
    public void build(){
        //kontener.removeAll(); 
        
        this.addActionListener(new ShowPicture(kontener, this));
        this.addActionListener(this);
        kontener.add(this);
        kontener.revalidate();
        kontener.repaint();
        kontener.updateUI();
        kontener.setVisible(true);
    }
    public void actionPerformed(ActionEvent e){
        akt_fiszka.setith(new Picture(this.getSelectedFile()), numer_pola);
        //clear_cont();
        //build();
    }

    /*public Component get_comp()
    {
        return this;//plik;
    }*/

    
}

class SelectedText 
extends JTextField 
implements ActionListener, Selected,  DocumentListener
{
    JPanel kontener;
    String default_text;
    String current_text;
    Fiszka akt_fiszka;
    int numer_pola;
    //JTextField tresc;
    public SelectedText(JPanel kont, String def, Fiszka a, int n) 
    {
        kontener = kont;
        default_text = def;
        akt_fiszka = a;
        numer_pola = n;
        //tresc = new JTextField(default_text);
        this.getDocument().addDocumentListener(this);
        this.setText(def);
        
    }
    public void clear_cont()
    {
        kontener.removeAll(); 
        kontener.revalidate();
        kontener.repaint();
        kontener.updateUI();
    }

    public void build()
    {
        kontener.add(this);
        kontener.revalidate();
        kontener.repaint();
        kontener.updateUI();
        kontener.setVisible(true);
    }
    public void actionPerformed(ActionEvent e){
        clear_cont();
        build();
    }

    public void changedUpdate(DocumentEvent e)
    {
        akt_fiszka.setith(new Text(this.getText()), numer_pola);
    }
    public void insertUpdate(DocumentEvent e){
        //String s = this.getText();
        //System.out.println(s);
        //if(akt_fiszka == null) System.out.println(numer_pola);
        akt_fiszka.setith(new Text(this.getText()), numer_pola);
    }

    public void removeUpdate(DocumentEvent e){
        akt_fiszka.setith(new Text(this.getText()), numer_pola);
    }

    /*public Component get_comp()
    {
        return this;
    }*/
    
}

class ShowPicture implements ActionListener
{
    JPanel kontener;
    JFileChooser wyborPliku;
    public ShowPicture(JPanel kont, JFileChooser wyb){
        kontener = kont;
        wyborPliku = wyb;
    }

    public void actionPerformed(ActionEvent e)
    {
        kontener.removeAll();
        String plik;
        try {
            plik= wyborPliku.getSelectedFile().getCanonicalPath();
            System.out.println(plik);
            ImageIcon icon = new ImageIcon(plik);
            Image image = icon.getImage(); // transform it 
            Image newimg = image.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
            icon = new ImageIcon(newimg);  // transform it back
            JLabel label = new JLabel(icon);
        // label.setMinimumSize(new Dimension(width, height));
        // label.setPreferedSize(new Dimension(width, height));
            label.setMaximumSize(new Dimension(20, 20));
            kontener.add(label);
            kontener.revalidate();
            kontener.repaint();
            kontener.updateUI();
            kontener.setVisible(true);
        } catch (Exception er) {
            //Zrob okienko bledu
        } 
        
    }
}



class FieldData
{
    public String typ = "TEKST";
    public String nazwa = "nazwa";
}
class EqualsAL implements ActionListener, DocumentListener
{
    FieldData a;
    String b;
    JTextField text;
    public EqualsAL(FieldData a1, String b1, JTextField t){
        a = a1;
        b = b1;
        text = t;
    }

    public void actionPerformed(ActionEvent e)
    {
        System.out.println("Przypisuje ");
        if(b != null){
            System.out.println(b);
            a.typ = b;
        }
        if(text != null){
            System.out.println(text.getText());
            a.nazwa = text.getText();
        }

    }
    public void changedUpdate(DocumentEvent e)
    {
        System.out.println("Przypisuje ");
        if(b != null){
            System.out.println(b);
            a.typ = b;
        }
        if(text != null){
            System.out.println(text.getText());
            a.nazwa = text.getText();
        }
    }
    public void insertUpdate(DocumentEvent e){
        System.out.println("Przypisuje ");
        if(b != null){
            System.out.println(b);
            a.typ = b;
        }
        if(text != null){
            System.out.println(text.getText());
            a.nazwa = text.getText();
        }
    }

    public void removeUpdate(DocumentEvent e){
        System.out.println("Przypisuje ");
        if(b != null){
            System.out.println(b);
            a.typ = b;
        }
        if(text != null){
            System.out.println(text.getText());
            a.nazwa = text.getText();
        }
    }
}

class TypeSelection implements ActionListener
{
    JPanel kontener_out;
    Vector<FieldData> danePol;
    public TypeSelection(JPanel kont, Vector<FieldData> dan) 
    {
        kontener_out = kont;
        danePol = dan;
    }

    public void actionPerformed(ActionEvent e){

        JPanel kontener = new JPanel();
        kontener_out.add(kontener);
        kontener_out.updateUI();

        FieldData f = new FieldData();
        danePol.add(f);
        JTextField nazwa = new JTextField("Wpisz nazwę pola (nie treść tylko typ hasła)");
        nazwa.getDocument().addDocumentListener(new EqualsAL(f, null, nazwa));
        kontener.add(nazwa);
        JPanel menucont = new JPanel();
        menucont.setVisible(true);
        kontener.add(menucont);
        JMenuBar menuBar;
        JMenu menu;
        JMenuItem poleTekstoweItem, poleObrazkoweItem;

        //Create the menu bar.
        menuBar = new JMenuBar();

        //Build the first menu.
        menu = new JMenu("Wybierz typ pola");
        menu.setMnemonic(KeyEvent.VK_A);
        menu.getAccessibleContext().setAccessibleDescription("Wybór typu pola w fiszce");
        menuBar.add(menu);

        JPanel kontenerNaWybor = new JPanel();
        kontener.add(kontenerNaWybor);
        //a group of JMenuItems
        poleTekstoweItem = new JMenuItem("Pole tekstowe", KeyEvent.VK_T);
        poleTekstoweItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
        poleTekstoweItem.getAccessibleContext().setAccessibleDescription("Wybiera pole na tekstowe");
        poleTekstoweItem.addActionListener(new EqualsAL (f, "TEKST", null));//new SelectedText(kontenerNaWybor, "Wpisz hasło"));
        menu.add(poleTekstoweItem);

        poleObrazkoweItem = new JMenuItem("Obraz", KeyEvent.VK_T);
        poleObrazkoweItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
        poleObrazkoweItem.getAccessibleContext().setAccessibleDescription("Wybiera pole na obraz");
        poleObrazkoweItem.addActionListener(new EqualsAL(f, "OBRAZEK",  null));//new SelectedPicture(kontenerNaWybor));
        menu.add(poleObrazkoweItem);

        
        menucont.add(menuBar);
        kontener.updateUI();
    }
}

class KillComponents implements ActionListener
{
    Component[] arr;
    JPanel kontener; 

    public KillComponents(Component[] newarr, JPanel kont)
    {
        arr = newarr;
        kontener = kont;
    }

  
    public void actionPerformed(ActionEvent e)
    {
        for(Component x : arr)
        {
            kontener. remove(x);
        }
        kontener.revalidate();
        kontener.repaint();
        kontener.updateUI();
    }
}

class UpLay implements ActionListener
{
    JPanel mr;
    LinkedList<Fiszka> lista;
    Fiszka akt_fiszka;
    public UpLay(JPanel m,LinkedList<Fiszka> l, Fiszka a){ mr = m;lista = l; akt_fiszka = a;}


    public void actionPerformed(ActionEvent e)
    {   
        lista.remove(akt_fiszka);
        ((GridLayout) mr.getLayout()).setRows(((GridLayout) mr.getLayout()).getRows() -1);
    }

}

class InputVerse implements ActionListener
{
    JPanel kontener;
    Vector<FieldData> danePol;
    JButton usun;
    LinkedList<Fiszka> lista;
    Fiszka akt_fiszka;
    //JPanel wiersz;
    public InputVerse(JPanel kont, Vector<FieldData> dane, LinkedList<Fiszka> l)
    {
        kontener = kont;
        danePol = dane;
        lista = l;
        akt_fiszka = new NewFiszka(danePol.size());
    }

    public void actionPerformed(ActionEvent e)
    {
        //pola + przycisk usun
         //wiersz = new JPanel();
         //kontener.add(wiersz);
        //wiersz = kontener;
        if(akt_fiszka == null) System.out.println("Jest juz zle");
        lista.add(akt_fiszka);
        kontener.setLayout(new GridLayout(( (GridLayout) kontener.getLayout()).getRows()+1, danePol.size()));
        Component[] s = new Component [danePol.size()];
        for(int i = 0 ; i< danePol.size(); i++)
        {
            FieldData f = danePol.get(i);
            Selected s1;
            if(f.typ == "OBRAZEK")     s1= new SelectedPicture(kontener, akt_fiszka, i);
            else    s1 = new SelectedText(kontener, "Wpisz treść fiszki", akt_fiszka, i);
            s1.build();
            s[i] = ((Component) s1);
         }
         kontener.revalidate();
         kontener.repaint();
         kontener.updateUI();
         usun = new JButton("Usuń"); 
         kontener.add(usun);
         usun.addActionListener(new KillComponents( s, kontener));
         usun.addActionListener(new KillComponents( new Component[]{usun}, kontener));
         usun.addActionListener(new UpLay(kontener, lista, akt_fiszka));
     }
}

class Edytor
extends JFrame
implements ActionListener
{
    LinkedList<Fiszka> lista;
    Vector<FieldData> danePol;
    public Edytor(Vector<FieldData> dan)
    {
        danePol = dan;
        lista = new LinkedList<Fiszka> ();
    }
    
    public void actionPerformed(ActionEvent e)
    {
        for (FieldData var : danePol) {
            System.out.println(var.nazwa+var.typ);
        }

        //JFrame edytor = new JFrame("Wpisz pola");
        this.setTitle("Wpisz pola");
        this.setSize(600, 400);
        this.setVisible(true);

        JPanel c = new JPanel();
        c.setLayout(new BorderLayout(3, 1));
        this.add(c);

        JPanel kontener2 = new JPanel();
        c.add(kontener2, BorderLayout.PAGE_END);
        JPanel kontener = new JPanel();
        c.add(kontener, BorderLayout.CENTER);
        kontener.setLayout(new GridLayout(1, danePol.size()+1));
        kontener.updateUI();

        JButton zapisz = new JButton("Zapisz");
        kontener2.add(zapisz);

        JButton zakoncz = new JButton("Zapisz i zakoncz");
        kontener2.add(zakoncz);

        c.updateUI();

        
        for (FieldData var : danePol) {
            kontener.add(new JLabel(var.nazwa));
            kontener.updateUI();
        }

        JButton nowa_fiszka = new JButton("Nowa fiszka");
        kontener.add(nowa_fiszka);
        nowa_fiszka.addActionListener(new InputVerse(kontener, danePol, lista));

        

        //kont2.updateUI();
    }
}

class okno_wpisywania
extends JFrame
{
    Vector<FieldData> danePol;
    okno_wpisywania()
    {
        danePol = new Vector<FieldData>();
        //JFrame wpisz_pola;
        JPanel kontener; 
        JPanel kontener2;
        JButton guzik_dodawania; 
        JButton guzik_dalej;

        this.setTitle("Tworzenie listy"); //= new JFrame("Tworzenie listy");
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 400);

        JPanel c = new JPanel();
        this.add(c);
        c.setLayout(new BoxLayout(c, BoxLayout.PAGE_AXIS));

        kontener2 = new JPanel();
        c.add(kontener2);
        
        kontener = new JPanel();
        kontener.setLayout(new BoxLayout(kontener, BoxLayout.PAGE_AXIS));
        c.add(kontener);

        guzik_dodawania = new JButton("Dodaj pole");
        guzik_dodawania.addActionListener(new TypeSelection(kontener, danePol));
        kontener2.add(guzik_dodawania);
        kontener.updateUI();

        guzik_dalej = new JButton("Dalej");
        guzik_dalej.addActionListener(new Edytor(danePol));
        kontener2.add(guzik_dalej);

        


    }
   
}

class Main
{
    public static void main(String[] args) {
        okno_wpisywania x = new okno_wpisywania();
    }
}