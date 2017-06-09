import javax.swing.*;
import javax.swing.event.MenuKeyListener;
import javax.swing.event.DocumentListener;
import javax.swing.event.MenuKeyEvent;
import javax.swing.event. *;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class EqualsAL implements ActionListener, DocumentListener
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


