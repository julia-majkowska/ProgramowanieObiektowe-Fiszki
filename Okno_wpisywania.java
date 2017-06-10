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
    //JPanel kontener;
    public TypeSelection(JPanel kont, Vector<FieldData> dan, JPanel cp) 
    {
        kontener_out = kont;
        danePol = dan;
        c = cp;
        //kontener = new JPanel();
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
        JTextField nazwa = new JTextField("Wpisz nazwę pola (nie treść tylko typ hasła)");
        nazwa.getDocument().addDocumentListener(new EqualsAL(f, null, nazwa));
        this.add(nazwa);
    }

    public void add_menu(FieldData f)
    {
        JPanel menucont = new JPanel();
        menucont.setVisible(true);
        this.add(menucont);
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
        this.add(kontenerNaWybor);
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
        this.updateUI();
        c.revalidate();

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
        //init();
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
    public Okno_wpisywania(JFrame o, NBase l)
    {
        lista = l;
        okno = o;
        okno.add(this);
        danePol = l.v;
    }

    public void wyczysc_okno(){
        okno.getContentPane().removeAll();
        okno.getContentPane().repaint();

        okno.setTitle("Wpisz pola");
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
        JPanel c = new JPanel();
        this.add(c);
        c.setLayout(new BoxLayout(c, BoxLayout.PAGE_AXIS));

        JScrollPane scrPane = new JScrollPane(c);
        okno.getContentPane().add(scrPane);

        kontener2 = new JPanel();
        c.add(kontener2);
        guzik_dodawania = new JButton("Dodaj pole");
        kontener2.add(guzik_dodawania);

        guzik_dalej = new JButton("Dalej");
        kontener2.add(guzik_dalej);
        kontener2.updateUI();
        c.updateUI();
        this.updateUI();

        kontener = new JPanel();
        kontener.setLayout(new BoxLayout(kontener, BoxLayout.PAGE_AXIS));
        c.add(kontener);
        if(danePol.isEmpty())
        {
            System.out.println("BRUUUUUUM");
            new DefaultSelection(kontener, danePol,c, "Hasło").init();
            new DefaultSelection(kontener, danePol,c, "Znaczenie").init();
            new DefaultSelection(kontener, danePol,c, "Kategoria").init();
            c.revalidate();
        }
        else for(int i = 0; i< danePol.size(); i++){
                FieldData f = danePol.get(i);
                new ImportedSelection(kontener, danePol,c, f.nazwa, f.typ, i).init();
                c.revalidate();
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

