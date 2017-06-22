import javax.swing.*;
import javax.swing.event.MenuKeyListener;
import javax.swing.event.DocumentListener;
import javax.swing.event.MenuKeyEvent;
import javax.swing.event. *;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class OknoModyfikacji extends OknoWyboru implements ActionListener
{
     JButton modyfikuj = new JButton("Modify");

     public OknoModyfikacji(NBase b)
     {
         super(b);
     }
     public void buttons(){
         JButton wczytaj_liste = new JButton("Read list from file");
         c1.add(wczytaj_liste);
         refreshCont(c1);
         //wczytaj_liste.addActionListener(new ReaderWindow(this)); //zapisuje juz tutaj
         wczytaj_liste.addActionListener(this);
        //wczytaj
     }

     public void actionPerformed(ActionEvent e)
     {
         new ReaderWindow(this).open();
         c2.add(modyfikuj);
         refreshCont(c2);
         modyfikuj.addActionListener(new Okno_wpisywania(this, baza));
     }

}
