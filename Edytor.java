import javax.swing.*;
import javax.swing.event.MenuKeyListener;
import javax.swing.event.DocumentListener;
import javax.swing.event.MenuKeyEvent;
import javax.swing.event. *;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

class SelFileWindow extends JFileChooser implements ActionListener
{
    SelectedPicture p;

    SelFileWindow(SelectedPicture sp){ 
        p = sp; 
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()  == this){
            p.set_file(getSelectedFile());
        }
        else{
            p.add(this);
            int result = this.showOpenDialog(p);
            if (result == JFileChooser.APPROVE_OPTION) {
                System.out.println("Open was selected");
                p.mojplik = this.getSelectedFile();
                p.obraz.build();
            } else if (result == JFileChooser.CANCEL_OPTION) {
                System.out.println("Cancel was selected");
            }
        }
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
 extends JPanel
 implements ActionListener, Selected
{
    JPanel kontener_out;
    Fiszka akt_fiszka;
    int numer_pola;
    //JFileChooser plik;
    File mojplik= new File("a.png");
    JButton wybierz_plik;
    public ShowPicture obraz;

    public void set_file(File f){
        mojplik = f;
    }
    public SelectedPicture(JPanel kont, Fiszka a, int n)
    {
        kontener_out = kont;
        kontener_out.add(this);
        akt_fiszka = a;
        numer_pola = n;
        wybierz_plik = new JButton("Wybierz plik");
        wybierz_plik.addActionListener(new SelFileWindow(this));
        JPanel kontener_na_obrazek = new JPanel();
        this.add(kontener_na_obrazek);
        obraz = new ShowPicture(kontener_na_obrazek, this);
        obraz.build();
        this.updateUI();
    }

    public void clear_cont()
    {
        this.removeAll(); 
        this.revalidate();
        this.repaint();
        this.updateUI();
    }   
    public void build(){
        //kontener.removeAll(); 
        this.add(wybierz_plik);
        this.revalidate();
        this.repaint();
        this.updateUI();
        this.setVisible(true);
    }
    public void actionPerformed(ActionEvent e){
        clear_cont();
        akt_fiszka.setith(new Picture(this.mojplik), numer_pola);
        //clear_cont();
        build();
    }

}

class ShowPicture 
implements ActionListener
{
    JPanel kontener;
    SelectedPicture wyborPliku;
    public ShowPicture(JPanel kont, SelectedPicture wyb){
        kontener = kont;
        wyborPliku = wyb;
    }

    public void build()
    {
         String plik;
        try {
            plik= wyborPliku.mojplik.getCanonicalPath();
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
            wyborPliku.updateUI();
            kontener.setVisible(true);
            System.out.println("Harry Potter");
        } catch (Exception er) {
            System.out.println("źle z plikiem");
            //Zrob okienko bledu
        } 
    }
    public void actionPerformed(ActionEvent e)
    {
        System.out.println("działam");
        kontener.removeAll();
        build();
    }
       
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

class UpLay implements ActionListener
{
    JPanel mr;
    LinkedList<Fiszka> lista;
    Fiszka akt_fiszka;
    int numer; 
    public UpLay(JPanel m,LinkedList<Fiszka> l, Fiszka a, int n){ mr = m;lista = l; akt_fiszka = a;numer = n;}


    public void actionPerformed(ActionEvent e)
    {   
        lista.get(numer).number_of_fields = -1; // fiszka usunieta
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
    int numer;

    public InputVerse(JPanel kont, Vector<FieldData> dane, LinkedList<Fiszka> l)
    {
        kontener = kont;
        danePol = dane;
        lista = l;
        numer = lista.size();
        akt_fiszka = new NewFiszka(danePol.size());
    }

    public InputVerse(JPanel kont, Vector<FieldData> dane, LinkedList<Fiszka> l, int i )
    {
        kontener = kont;
        danePol = dane;
        lista = l;
        numer = i;
        akt_fiszka =l.get(i);
    }

    public void actionPerformed(ActionEvent e)
    {
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
         usun.addActionListener(new UpLay(kontener, lista, akt_fiszka, numer));
     }
}

/*class Saver implements ActionListener
{

}*/

class Writer implements ActionListener
{
    Base pomoc;
    LinkedList<Fiszka> lista;
    public Writer(Fiszka[] p, Base l)
    {
        pomoc = p;
        lista = l;
    }
     public void actionPerformed(ActionEvent e)
     {
          l.set_list(lista);
     }
}
public class Edytor
extends JPanel
implements ActionListener
{
    public LinkedList<Fiszka> lista;
    Vector<FieldData> danePol;
    JFrame okno;
    Base pomoc;
    public Edytor(Vector<FieldData> dan, JFrame o)
    {
        danePol = dan;
        lista = new LinkedList<Fiszka> ();
        okno = o;
        pomoc = new Fiszka[1];
    }

    public Edytor(Vector<FieldData> dan, JFrame o, Fiszka[] l)
    {
        pomoc = l;
        danePol = dan;
        lista = new LinkedList<Fiszka> (Arrays.asList(l));
        okno = o;
    }

    public void wyczysc_okno(){
        okno.getContentPane().removeAll();
        okno.getContentPane().repaint();

        okno.setTitle("Wpisz pola");
        okno.setSize(600, 400);
        okno.setVisible(true);
    }

    public void init()
    {
        wyczysc_okno();
        for (FieldData var : danePol) {
            System.out.println(var.nazwa+var.typ);
        }

        this.setLayout(new BorderLayout(3, 1));
        okno.add(this);

        JPanel kontener2 = new JPanel();
        this.add(kontener2, BorderLayout.PAGE_END);
        JPanel kontener = new JPanel();
        this.add(kontener, BorderLayout.CENTER);
        kontener.setLayout(new GridLayout(1, danePol.size()+1));
        kontener.updateUI();

        JButton zapisz = new JButton("Zapisz");
        kontener2.add(zapisz);
        zapisz.addActionListener(new Writer(pomoc, lista));
        zapisz.addActionListener(new SaverWindow(this));

        JButton zakoncz = new JButton("Zapisz i zakoncz");
        kontener2.add(zakoncz);
        zakoncz.addActionListener(new Writer(pomoc, lista));
        
        this.updateUI();

        
        for (FieldData var : danePol) {
            kontener.add(new JLabel(var.nazwa));
            kontener.updateUI();
        }

        JButton nowa_fiszka = new JButton("Nowa fiszka");
        kontener.add(nowa_fiszka);
        nowa_fiszka.addActionListener(new InputVerse(kontener, danePol, lista));

    }
    
    public void actionPerformed(ActionEvent e)
    {
        this.init();
    }
}