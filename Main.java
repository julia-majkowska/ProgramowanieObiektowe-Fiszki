import javax.swing.*;
import javax.swing.event.MenuKeyListener;
import javax.swing.event.DocumentListener;
import javax.swing.event.MenuKeyEvent;
import javax.swing.event. *;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
class Main
{
    public static void main(String[] args) {
        Okno_wpisywania x = new Okno_wpisywania(new JFrame(), new NBase());
        OknoWyboru y= new OknoWyboru(new NBase());
        OknoModyfikacji z = new OknoModyfikacji(new NBase());
    }
}