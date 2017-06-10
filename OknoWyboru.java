import javax.swing.*;
import javax.swing.event.MenuKeyListener;
import javax.swing.event.DocumentListener;
import javax.swing.event.MenuKeyEvent;
import javax.swing.event. *;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class OknoWyboru extends JFrame implements ActionListener
{
     LinkedList<Fiszka> lista = new LinkedList<Fiszka>();
     NBase baza;
     JPanel c = new JPanel();
     JPanel c1 = new JPanel();
     JPanel c2 = new JPanel();
     JButton modyfikuj = new JButton("Modify");
     JButton dalej= new JButton("Learn");
     public OknoWyboru(NBase l){
        baza = l;
        this.setTitle("List Creator"); //= new JFrame("Tworzenie listy");
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 400);
        this.add(c);
        c.setLayout(new BoxLayout(c, BoxLayout.PAGE_AXIS));
        c.add(c1);
        c.add(c2);
        c.updateUI();
        buttons(c1, c2);

     }

     public void buttons(JPanel c, JPanel c1){
         JButton wczytaj_liste = new JButton("Read list from file");
         JButton stworz_liste = new JButton("Create new list");
         c.add(wczytaj_liste);
         c.add(stworz_liste);
         
         //wczytaj_liste.addActionListener(new ReaderWindow(this)); //zapisuje juz tutaj
         wczytaj_liste.addActionListener(this);
         stworz_liste.addActionListener(new Okno_wpisywania(this, baza));
         c.updateUI();
         //wczytaj
     }

     public void actionPerformed(ActionEvent e)
     {
         new ReaderWindow(this).open();
         c2.add(modyfikuj);
         c2.add(dalej);
         c2.updateUI();
         modyfikuj.addActionListener(new Okno_wpisywania(this, baza));
         dalej.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e)
                                {
                                  System.exit(0);
                                }});
        c.updateUI();
     }

}