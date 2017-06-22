import javax.swing.*;
import javax.swing.event. *;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.filechooser.FileNameExtensionFilter;
public class ReaderWindow extends JFileChooser implements ActionListener
{
    OknoWyboru obiekt;
    public ReaderWindow (OknoWyboru x1)
    {
        obiekt = x1;
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Fiszki", "fiszka");
        this.setFileFilter(filter);
    }

    void read()
    {
        File plik= this. getSelectedFile();
        //plik.createNewFile();
        NBase l = new NBase();
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream(plik);
            ois = new ObjectInputStream(fis);
            l = (NBase) ois.readObject();
            System.out.println(l.topic_sort.size());
            obiekt.baza.setAll(l);
            fis.close();
            ois.close();
        }
        catch(Exception e){
            System.err.println("Caught IOException: " + e.getMessage());
        }
        
    }

    public void open()
    {
        obiekt.add(this);
        int result = this.showOpenDialog(obiekt);
            if (result == JFileChooser.APPROVE_OPTION) {
                System.out.println("Save was selected");
                read();
            } else if (result == JFileChooser.CANCEL_OPTION) {
                System.out.println("Cancel was selected");
        }
    }
    public void actionPerformed(ActionEvent e)
    {
        open();
    }
    
}
