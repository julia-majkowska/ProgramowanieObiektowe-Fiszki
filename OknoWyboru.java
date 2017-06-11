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
     NBase baza;
     JPanel c = new JPanel();
     //JButton modyfikuj = new JButton("Modify");
     JButton dalej= new JButton("Learn");
     public OknoWyboru(NBase l){
        baza = l;
        this.setTitle("List Creator"); //= new JFrame("Tworzenie listy");
        this.setVisible(true);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 400);
        this.add(c);
        c.setLayout(new BoxLayout(c, BoxLayout.PAGE_AXIS));
        refreshCont(c);
        buttons();

     }
     public void refreshCont(JPanel c)
     {
         c.revalidate();
         c.repaint();
         c.updateUI();
     }

     public void buttons(){
         JButton wczytaj_liste = new JButton("Read list from file");
         c.add(wczytaj_liste);
         refreshCont(c);
         //wczytaj_liste.addActionListener(new ReaderWindow(this)); //zapisuje juz tutaj
         wczytaj_liste.addActionListener(this);
         
         //wczytaj
     }

     public void actionPerformed(ActionEvent e)
     {
         new ReaderWindow(this).open();
         c.add(dalej);
         refreshCont(c);
         dalej.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e)
                                {
                                  System.exit(0);
                                }});
        
     }

}