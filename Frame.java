//package menuWindow;

//import learn.Exercise1;
//import learn.Exercise2;
//import learn.Exercise3;
//import learn.WhatToLearn;
//import startWindow.StartWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mdragula on 21.06.17.
 */
public class Frame extends JFrame implements ActionListener {
    public JPanel pane;
    public User user;
    private Learning learn;

    public Frame(User usr) throws HeadlessException {
        setSize(650, 500);
        setLocation(270, 270);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pane = new UserMenu(this);
        add(pane);
        setVisible(true);
        user = usr;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();
        try {
            if (source == ((UserMenu) pane).btLogOut) {
                dispose();
                new StartWindow();
            }
        } catch (Exception e) {
        }
        try {
            if (source == ((UserMenu) pane).btLearn) {
                remove(pane);
                System.out.println("blabla");
                learn = new Learning(user);
                System.out.println("blabla2");
                Map<String, Integer> mapa = learn.topicsSize;
                System.out.println(learn.topics.size());
                String[] lista = learn.topics.toArray(new String[learn.topics.size()] );
                System.out.println("blabla3");
                pane = new WhatToLearn(this, lista, mapa); // od Agi potrzebuje liste kategorii i mape
                add(pane);
                revalidate();
                repaint();
            }
        } catch (Exception e) {
        }
        try {
            if (source == ((WhatToLearn) pane).btContinue) {
                remove(pane);
                // funckja podające rodzaj ćwiczenia, pytanie, odpowiedz
                // jesli jest kolejne cwiczenie to je odpal jak nie to koniec
                learn.selectCards((String)((WhatToLearn) pane).ltCategories.getSelectedItem(), ((WhatToLearn) pane).slidCardsTL.getValue());
                System.out.println("here");
                pane = new Exercise1(this, "To jest jakies pytanie?");
                add(pane);
                revalidate();
                repaint();
            }
        } catch (Exception e) {
        }
        try {
            if (source == ((Exercise1) pane).btContinue) {
                String ans = ((Exercise1) pane).tfAnswer.getText();
                remove(pane);
                //ta sama funkcja co wyzej
                //odpalamy ktores cwiczenie jak nei to koniec
                pane = new Exercise2(this, "To znowu jakies pytanie?", "A tu jakas odpowiedz");
                add(pane);
                revalidate();
                repaint();
            }
        } catch (Exception e) {

        }
        try {
            if (source == ((Exercise2) pane).btOK) {
                remove(pane);
                //DOBRA ODPOWIEDZ trzeba to jakos przekacac do funkcji Agi
                String[] letters = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n"};
                pane = new Exercise3(this, "A to kolejne pytanie", "od_wi_e__", letters);
                add(pane);
                revalidate();
                repaint();
            }
        } catch (Exception e) {

        }
        try {
            if (source == ((Exercise2) pane).btFail) {
                remove(pane);
                //ZLA ODPOWIEDZ jakos to trzeba dac Adze
                String[] letters = {"a", "b", "c", "d", "e", "f", "g", "h", "i"}; // nie wiecej niz 19 liter !
                pane = new Exercise3(this, "Tutaj cos innego dla odmiany", "p__a", letters);
                add(pane);
                revalidate();
                repaint();
            }
        } catch (Exception e) {

        }
        try {
            if (source == ((Exercise3) pane).btContinue) {
                remove(pane);
                pane = new UserMenu(this);
                add(pane);
                revalidate();
                repaint();
            }
        } catch (Exception e) {

        }
        try {
        	  if (source == ((UserMenu) pane).btLBase) {
        	 	 user.takeBase(false); 
        	  }
        } catch (Exception e) {
        
        }
        try {
        	  if (source == ((UserMenu) pane).btCrNewBase) {
        	  	 user.takeBase(true);
        	  }
        } catch (Exception e) {
        
        }
        try {
        	  if (source == ((UserMenu) pane).btAdd) {
        	  	 user.modifyBase();
        	  }
        } catch (Exception e) {
        
        }
        try {
        	  if (source == ((Exercise4) pane).btContinue) {
        	  			
        	  }
        } catch (Exception e) {
        
        }
    }
}
