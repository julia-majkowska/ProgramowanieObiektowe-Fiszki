import javax.swing.*;
import javax.swing.event.MenuKeyListener;
import javax.swing.event.DocumentListener;
import javax.swing.event.MenuKeyEvent;
import javax.swing.event. *;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class SaverWindow extends JFileChooser implements ActionListener
{
    Edytor obiekt;

    public SaverWindow(Edytor s){
        obiekt = s;   
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Fiszki", "fiszka");
        this.setFileFilter(filter);     
    }

    void save()
    {
        File plik= this. getSelectedFile();
        if(!this.getSelectedFile().getAbsolutePath().endsWith(".fiszka")){
            plik = new File(this.getSelectedFile() + ".fiszka");
        }
        //plik.createNewFile();
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(plik);
            oos = new ObjectOutputStream(fos);
            obiekt.pomoc.setBase(obiekt.lista);
            oos.writeObject(obiekt.pomoc); //zapisuje base
            fos.close();
            oos.close();
        }
        catch(Exception e){
            System.err.println("Caught IOException: " + e.getMessage());
        }
        
    }

    public void actionPerformed(ActionEvent e)
    {
        obiekt.add(this);
        int result = this.showSaveDialog(obiekt);
            if (result == JFileChooser.APPROVE_OPTION) {
                System.out.println("Save was selected");
                save();
            } else if (result == JFileChooser.CANCEL_OPTION) {
                System.out.println("Cancel was selected");
        }
    }
}