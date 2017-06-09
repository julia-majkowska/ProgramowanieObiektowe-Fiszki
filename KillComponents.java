import javax.swing.*;
import javax.swing.event.MenuKeyListener;
import javax.swing.event.DocumentListener;
import javax.swing.event.MenuKeyEvent;
import javax.swing.event. *;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class KillComponents implements ActionListener
{
    Component[] arr;
    JPanel kontener; 

    public KillComponents(Component[] newarr, JPanel kont)
    {
        arr = newarr;
        kontener = kont;
    }

  
    public void actionPerformed(ActionEvent e)
    {
        for(Component x : arr)
        {
            kontener. remove(x);
        }
        kontener.revalidate();
        kontener.repaint();
        kontener.updateUI();
    }
}