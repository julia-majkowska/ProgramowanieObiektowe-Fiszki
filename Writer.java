import javax.swing.*;
import javax.swing.event.MenuKeyListener;
import javax.swing.event.DocumentListener;
import javax.swing.event.MenuKeyEvent;
import javax.swing.event. *;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
public class Writer implements ActionListener
{
    NBase pomoc;
    LinkedList<Fiszka> lista;
    public Writer(NBase p, LinkedList<Fiszka> l)
    {
        pomoc = p;
        lista = l;
    }
     public void actionPerformed(ActionEvent e)
     {
         System.out.println("zapisuje do bazy" + lista.size());
         for(Fiszka f : lista)
         {
             for(Field pol : f.fields)
             {
                 System.out.println(pol);
             }
         }
          pomoc.setBase(lista);
     }
}