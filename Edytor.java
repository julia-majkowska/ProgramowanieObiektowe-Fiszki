import javax.swing.*;
import javax.swing.event.DocumentListener;
import javax.swing.event. *;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.lang.Object;
import javax.swing.filechooser.FileNameExtensionFilter;

class SelFileWindow extends JFileChooser implements ActionListener
{
    SelectedPicture p;
    SelFileWindow(SelectedPicture sp){ 
        p = sp; 
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "png", "gif", "jpeg");
        this.setFileFilter(filter);
    }
    public void actionPerformed(ActionEvent e)
    {
        p.add(this);
        int result = this.showOpenDialog(p);
        if (result == JFileChooser.APPROVE_OPTION) {
            System.err.println("Open was selected");
            p.set_file(this.getSelectedFile());
            p.obraz.build();
        } else if (result == JFileChooser.CANCEL_OPTION) {
            System.err.println("Cancel was selected");
        }
    }
}

interface Selected
{
    public abstract void build();
}


class SelectedPicture
 extends JPanel
 implements ActionListener, Selected
{
    JPanel kontener_out;
    Fiszka akt_fiszka;
    int numer_pola;
    File mojplik= new File("a.png");
    JButton wybierz_plik;
    public ShowPicture obraz;

    public void set_file(File f){
        mojplik = f;
        akt_fiszka.setith(new Picture(f),numer_pola);
    }
    public SelectedPicture(JPanel kont, Fiszka a, int n)
    {
        akt_fiszka = a;
        numer_pola = n;
        mojplik = ((Picture) a.getith(n)).get();
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        kontener_out = kont;
        kontener_out.add(this);
        wybierz_plik = new JButton("Select file");
        wybierz_plik.addActionListener(new SelFileWindow(this));
        JPanel kontener_na_obrazek = new JPanel();
        this.add(kontener_na_obrazek);
        obraz = new ShowPicture(kontener_na_obrazek, this);
        obraz.build();
        this.updateUI();       
    }

    public void build(){
        this.add(wybierz_plik);
        this.updateUI();
    }
    public void actionPerformed(ActionEvent e){
        akt_fiszka.setith(new Picture(this.mojplik), numer_pola);
        build();
    }

}

class ShowPicture 
implements ActionListener
{
    JPanel kontener;
    SelectedPicture wyborPliku;
    JLabel label = new JLabel();
    public ShowPicture(JPanel kont, SelectedPicture wyb){
        kontener = kont;
        wyborPliku = wyb;
        kontener.add(label);
    }

    public void build()
    {
         String plik;
        try {
            plik= wyborPliku.mojplik.getCanonicalPath();
            System.err.println(plik);
            ImageIcon icon = new ImageIcon(plik);
            Image image = icon.getImage(); // transform it 
            Image newimg = image.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
            icon = new ImageIcon(newimg);  // transform it back
            label.setIcon(icon);
            label.setMaximumSize(new Dimension(20, 20));
            wyborPliku.updateUI();

        } catch (Exception er) {
            System.err.println("źle z plikiem");
            //Zrob okienko bledu
        } 
    }
    public void actionPerformed(ActionEvent e)
    {
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
    public SelectedText(JPanel kont, Fiszka a, int n) 
    {
        kontener = kont;
        default_text = ((Text)a.getith(n)).get();
        akt_fiszka = a;
        numer_pola = n;
        this.getDocument().addDocumentListener(this);
        this.setText(default_text);
    }
    public void build()
    {
        kontener.add(this);
        kontener.updateUI();

    }
    public void actionPerformed(ActionEvent e){
        build();
    }

    public void changedUpdate(DocumentEvent e)
    {
        akt_fiszka.setith(new Text(this.getText()), numer_pola);
    }
    public void insertUpdate(DocumentEvent e){
        akt_fiszka.setith(new Text(this.getText()), numer_pola);
    }

    public void removeUpdate(DocumentEvent e){
        akt_fiszka.setith(new Text(this.getText()), numer_pola);
    }    
}

class UpLay implements ActionListener
{
    JPanel mr;
    LinkedList<Fiszka> lista;
    Fiszka akt_fiszka;
    int numer; 
    public UpLay(JPanel m,LinkedList<Fiszka> l, Fiszka a, int n){ 
        mr = m;lista = l; 
        akt_fiszka = a;
        numer = n;
    }

    public void actionPerformed(ActionEvent e)
    {   
        lista.get(numer).number_of_fields = -1; // fiszka usunieta
        ((GridLayout) mr.getLayout()).setRows(((GridLayout) mr.getLayout()).getRows() -1);
        mr.updateUI();
    }

}

class InputVerse implements ActionListener
{
    JPanel kontener;
    Vector<FieldData> danePol;
    JButton usun;
    LinkedList<Fiszka> lista;
    Fiszka akt_fiszka;
    int numer, numer2=0;

    public InputVerse(){}
    public InputVerse(JPanel kont, Vector<FieldData> dane, LinkedList<Fiszka> l)
    {
        kontener = kont;
        danePol = dane;
        lista = l;
        numer = -1;
    }
    public void fillInFiszka()
    {
        akt_fiszka.ensure_capacity(danePol.size() -1);
        for (int i = 0; i< danePol.size(); i++)
        {
            if(danePol.get(i).typ.equals("PICTURE") ) akt_fiszka.setDefault(new Picture("sad-smiley.png"), i);
            else akt_fiszka.setDefault(new Text("Input contents"), i);
        }
    }

    public void build()
    {
      fillInFiszka();
      if(akt_fiszka == null) System.err.println("Jest juz zle");
        kontener.setLayout(new GridLayout(( (GridLayout) kontener.getLayout()).getRows()+1, danePol.size()));
        Component[] s = new Component [danePol.size()];
        for(int i = 0 ; i< danePol.size(); i++)
        {
            FieldData f = danePol.get(i);
            Selected s1;
            if(f.typ.equals("PICTURE"))     s1= new SelectedPicture(kontener, akt_fiszka, i);
            else    s1 = new SelectedText(kontener, akt_fiszka, i);
            s1.build();
            s[i] = ((Component) s1);
         }
         usun = new JButton("Delete"); 
         kontener.add(usun);
         usun.addActionListener(new KillComponents( s, kontener));
         usun.addActionListener(new KillComponents( new Component[]{usun}, kontener));
         usun.addActionListener(new UpLay(kontener, lista, akt_fiszka, numer));
         kontener.updateUI();
     }

    public void actionPerformed(ActionEvent e)
    {
        if(numer == -1){
            numer = lista.size();
            akt_fiszka = new NewFiszka(danePol.size());
            lista.add(akt_fiszka);
        }
        build();
        numer = -1;
     }
}

class OldVerse extends InputVerse
{
    public OldVerse(JPanel kont, Vector<FieldData> dane, LinkedList<Fiszka> l, int i )
    {
        kontener = kont;
        danePol = dane;
        lista = l;
        numer = i;
        akt_fiszka =l.get(i);
    }

    public void actionPerformed(ActionEvent e) {build();}
}

public class Edytor
extends JPanel
implements ActionListener
{
    public LinkedList<Fiszka> lista;
    Vector<FieldData> danePol;
    JFrame okno;
    NBase pomoc;
    public Edytor(NBase l)
    {
		pomoc = l;
        danePol = l.v;
        lista = l.getList();
        okno = new JFrame();
    }

    public Edytor(JFrame o, NBase l)
    {
        pomoc = l;
        danePol = l.v;
        lista = l.getList();
        okno = o;
    }

    public void wyczysc_okno(){
        okno.getContentPane().removeAll();
        okno.add(this);
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        JScrollPane scrPane = new JScrollPane(this);
        okno.getContentPane().add(scrPane);
        okno.getContentPane().repaint();
        okno.setTitle("Input fields");
        // /okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        okno.setSize(600, 400);
        okno.setVisible(true); 
    }

    public void init()
    {
        wyczysc_okno();
        for (FieldData var : danePol) {
            System.err.println(var.nazwa+" "+ var.typ);
        }

        //this.setLayout(new BorderLayout(3, 1));        
        JPanel kontener = new JPanel();
        this.add(kontener);//, BorderLayout.CENTER);
        kontener.setLayout(new GridLayout(1, danePol.size()+1));
        JPanel kontener2 = new JPanel();
        this.add(kontener2);//, BorderLayout.PAGE_END);

        JButton zapisz = new JButton("Save");
        kontener2.add(zapisz);
        zapisz.addActionListener(new Writer(pomoc, lista));
        zapisz.addActionListener(new SaverWindow(this));

        JButton zakoncz = new JButton("Learn");
        kontener2.add(zakoncz);
        //zakoncz.addActionListener(new Writer(pomoc, lista));
        //zakoncz.addActionListener(new SaverWindow(this));
        zakoncz.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e)
                                {
                                  pomoc.setBase(lista);
                                  SaverWindow x = new SaverWindow();
                                  x.make();
                                  okno.dispose();
                                  //System.exit(0);
                                }});

        for (FieldData var : danePol) {
            kontener.add(new JLabel(var.nazwa));
        }
        JButton nowa_fiszka = new JButton("New cue card");
        kontener.add(nowa_fiszka);
        //System.err.println("Wypisuje liste" + lista.size());
        for (int i = 0; i< lista.size(); i++) 
            if(lista.get(i).number_of_fields >0) new OldVerse(kontener, danePol, lista, i).build();
        
        nowa_fiszka.addActionListener(new InputVerse(kontener, danePol, lista));
        this.revalidate();
        this.repaint();
        this.updateUI();

    }
    
    public void actionPerformed(ActionEvent e)
    {
        this.init();
    }
}