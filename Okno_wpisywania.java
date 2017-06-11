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
class TypeSelection extends JPanel implements ActionListener
{
    JPanel c;
    JPanel kontener_out;
    Vector<FieldData> danePol;
    public TypeSelection(JPanel kont, Vector<FieldData> dan, JPanel cp) 
    {
        kontener_out = kont;
        danePol = dan;
        c = cp;
    }

    public void init(){
        kontener_out.add(this);
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        kontener_out.updateUI();
        FieldData f = new FieldData();
        danePol.add(f);
        add_text(f);
        add_menu(f);
    }
    public void add_text(FieldData f)
    {
        JTextField nazwa = new JTextField("Input a name for this field");
        nazwa.getDocument().addDocumentListener(new EqualsAL(f, null, nazwa));
        this.add(nazwa);
    }

    public void add_menu(FieldData f)
    {
        JPanel menucont = new JPanel();
        menucont.setVisible(true);
        this.add(menucont);

        String [] hasla = {"TEKST", "OBRAZEK"};
        JComboBox menu = new JComboBox(hasla);
        menu.setSelectedIndex(0);
        menu.addActionListener(new ActionListener(){
                                    public void actionPerformed(ActionEvent e)
                                    {
                                        JComboBox cb = (JComboBox)e.getSource();
                                        f.typ = (String)cb.getSelectedItem();
                                    }
                                });
        menucont.add(menu);
        this.updateUI();

    }
    public void actionPerformed(ActionEvent e){

        init();
    }
}

class DefaultSelection extends TypeSelection
{
    String nazwa;
    public DefaultSelection(JPanel kont, Vector<FieldData> dan, JPanel cp, String n) 
    {
        super(kont, dan, cp);        
        nazwa = n;
        //this.init();
    }

    public void add_text(FieldData f)
    {
        JLabel nazwa_pola = new JLabel(nazwa);
        f.nazwa = nazwa;
        this.add(nazwa_pola);
    }
}

class ImportedSelection extends DefaultSelection
{
    String typ;
    int numer; 
    public ImportedSelection(JPanel kont, Vector<FieldData> dan,JPanel cp, String n, String t, int i) 
    {
        super(kont, dan, cp, n);        
        nazwa = n;
        typ = t;
        numer =i;
    }
    public void init()
    {
        kontener_out.add(this);
        kontener_out.updateUI();
        FieldData f = danePol.get(numer);
        add_text(f);
        add_menu(f);
    }

    public void add_menu(FieldData f)
    {
        JLabel typ_pola = new JLabel(typ);
        f.typ = typ;
        this.add(typ_pola);
    }
}
public class Okno_wpisywania
extends JPanel
implements ActionListener
{
    Vector<FieldData> danePol;
    NBase lista;
    JFrame okno;

    public Okno_wpisywania(NBase l)
    {
        lista = l;
        okno = new JFrame();
        okno.add(this);
        danePol = l.v;
        build();
    }
    public Okno_wpisywania(JFrame o, NBase l)
    {
        lista = l;
        okno = o;
        okno.add(this);
        danePol = l.v;
    }

    public void wyczysc_okno(){
        okno.getContentPane().removeAll();
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        JScrollPane scrPane = new JScrollPane(this);
        okno.getContentPane().add(scrPane);
        okno.getContentPane().repaint();
        okno.setTitle("Input fields");
        okno.setSize(600, 400);
        okno.setVisible(true); 
    }

    void build(){
        System.out.println("WYpisuje liste FieldData");
        for(FieldData x : danePol) System.out.println(x.typ +" "+ x.nazwa);
        JPanel kontener; 
        JPanel kontener2;
        JButton guzik_dodawania; 
        JButton guzik_dalej;

        wyczysc_okno();
        JPanel c =this;
        kontener2 = new JPanel();
        c.add(kontener2);
        guzik_dodawania = new JButton("Add field");
        kontener2.add(guzik_dodawania);

        guzik_dalej = new JButton("Continue");
        kontener2.add(guzik_dalej);

        kontener = new JPanel();
        kontener.setLayout(new BoxLayout(kontener, BoxLayout.PAGE_AXIS));
        c.add(kontener);
        if(danePol.isEmpty())
        {
            new DefaultSelection(kontener, danePol,c, "Hasło").init();
            new DefaultSelection(kontener, danePol,c, "Znaczenie").init();
            new DefaultSelection(kontener, danePol,c, "Kategoria").init();
        }
        else for(int i = 0; i< danePol.size(); i++){
                FieldData f = danePol.get(i);
                new ImportedSelection(kontener, danePol,c, f.nazwa, f.typ, i).init();
             }

        guzik_dodawania.addActionListener(new TypeSelection(kontener, danePol, c));        
        guzik_dalej.addActionListener(new Edytor(okno, lista));
        this.updateUI();
       
    }

    public void actionPerformed(ActionEvent e)  
    {
        build();
    } 
}

