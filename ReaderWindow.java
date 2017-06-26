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
        FileNameExtensionFilter filter2 = new FileNameExtensionFilter("Tekst", "txt");
        this.setFileFilter(filter2);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Fiszki", "fiszka");
        this.setFileFilter(filter);
        
    }
    
    public static void translate(NBase n, File plik)
    {

        //File plik= new File("lista.txt");
        //plik.createNewFile();
        Scanner s;
        String x = "";
        String[] tablica =x.split("\t");
        LinkedList<Fiszka>  fiszki = new LinkedList<Fiszka>();
        try {
            s = new Scanner(plik);
            while(s.hasNextLine())
            {
                x = s.nextLine();
                System.out.println(x);
                tablica = x.split("\t");
                Fiszka f = new NewFiszka(3);
                f.setith(new Text("domyślna kategoria"), 0);
                f.setith(new Text(tablica[0]), 1);
                /*int j = 1;
                while(j<tablica.length && tablica[j]=="\t") j++;*/
                f.setith(new Text(tablica[tablica.length -1]), 2);
                fiszki.add(f);
            }
            
        }
        catch(Exception e){
            System.err.println("Caught IOException: " + e.getMessage());
        }
        
        Vector<FieldData> v = new Vector<FieldData>();
        v.add(new FieldData("TEKST", "kategoria"));
        v.add(new FieldData("TEKST", "hasło"));
        v.add(new FieldData("TEKST", "znaczenie"));
        n.v = v;
        n.setBase(fiszki);
    }

    void read()
    {
        File plik= this. getSelectedFile();
        if(this.getSelectedFile().getAbsolutePath().endsWith(".txt")){
            translate(obiekt.baza, plik);
        }
        //plik.createNewFile();
        else if(this.getSelectedFile().getAbsolutePath().endsWith(".fiszka")){
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
