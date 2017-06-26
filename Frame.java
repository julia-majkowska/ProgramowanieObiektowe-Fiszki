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
import java.io.*;
import java.util.*;

/**
 * Created by mdragula on 21.06.17.
 */
public class Frame extends JFrame implements ActionListener {
    public JPanel pane;
    public User user;
    private Learning learn;
    private Repeating repeat;
    private Queue<Exc>cards;
    private Exc fiszka;
    private boolean ifLearn;

    public Frame(User usr) throws HeadlessException {
        setSize(650, 500);
        setLocation(270, 270);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("LearnIt");
        pane = new Intro(this);
        add(pane);
        setVisible(true);
        user = usr;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();
		try {
        	if (source == ((Intro) pane).btContinue) {
        		remove(pane);
        		pane = new Base(this);
        		add(pane);
        		revalidate();
        		repaint();
        	}
        } catch (Exception e) {
        }
        try {
            if (source == ((UserMenu) pane).btLogOut) {
                dispose();
                new StartWindow();
            }
        } catch (Exception e) {
        }
        try {
            if (source == ((UserMenu) pane).btLearn) {
				user.rfb = new RBase(user.id, user.nfb.baseId);

				ifLearn = true;
				remove(pane);
				learn = new Learning(user);
				Map<String, Integer> mapa = learn.topicsSize;
				String[] lista = learn.topics.toArray(new String[learn.topics.size()] );
				System.out.println(mapa.keySet().size());
                pane = new WhatToLearn(this, lista, mapa);
				add(pane);
				revalidate();
                repaint();
            }
        } catch (Exception e) {
        }
        try {
            if (source == ((WhatToLearn) pane).btContinue) {
                 remove(pane);
                 cards = learn.justLearn((String)((WhatToLearn) pane).ltCategories.getSelectedItem(), ((WhatToLearn) pane).slidCardsTL.getValue());
                 fiszka = cards.poll();
                 if(fiszka == null) {
                     pane = new UserMenu(this);
                     learn = null;
                 }
                 else if(fiszka.nr == 1) pane = new Exercise1(this, ((Text)fiszka.rf.nf.getith(1)).get());
                 else if(fiszka.nr == 2) pane = new Exercise2(this, ((Text)fiszka.rf.nf.getith(1)).get(), ((Text)fiszka.rf.nf.getith(2)).get());
                 else if(fiszka.nr == 3) {
                    Vector<String>letters = fiszka.hideLetters(((Text)fiszka.rf.nf.getith(2)).get());
                    String toFill = letters.lastElement();
                    letters.remove(letters.size() - 1);
                    pane = new Exercise3(this, ((Text)fiszka.rf.nf.getith(1)).get(), toFill, letters.toArray(new String[letters.size()]));
                 }
                 else pane = new Exercise4(this, ((Text)fiszka.rf.nf.getith(1)).get(), ((Text)fiszka.rf.nf.getith(2)).get());
                 add(pane);
                 revalidate();
                 repaint();
            }
        } catch (Exception e) {
        }
        try {
            if (source == ((Exercise1) pane).btContinue) {
                 String s1 = ((Text)fiszka.rf.nf.getith(2)).get();
                 String s2 = ((Exercise1) pane).tfAnswer.getText();
                 fiszka.rf.update(fiszka.equalWords(s1, s2), user.today);
                 remove(pane);
                 if(fiszka.equalWords(s1, s2) == false) 
                 {
                 	cards.add(fiszka);
                 	fiszka = new Exc(4, fiszka.rf);
                 }
                 else fiszka = cards.poll();
                 if(fiszka == null) {
					user.rfb.writeRBase();
					pane = new UserMenu(this);
	
                    learn = null;
                 }
                 else if(fiszka.nr == 1) pane = new Exercise1(this, ((Text)fiszka.rf.nf.getith(1)).get());
                 else if(fiszka.nr == 2) pane = new Exercise2(this, ((Text)fiszka.rf.nf.getith(1)).get(), ((Text)fiszka.rf.nf.getith(2)).get());
                 else if(fiszka.nr == 3) {
                    Vector<String>letters = fiszka.hideLetters(((Text)fiszka.rf.nf.getith(2)).get());
                    String toFill = letters.lastElement();
                    letters.remove(letters.size() - 1);
                    pane = new Exercise3(this, ((Text)fiszka.rf.nf.getith(1)).get(), toFill, letters.toArray(new String[letters.size()]));
                 }
                 else pane = new Exercise4(this, ((Text)fiszka.rf.nf.getith(1)).get(), ((Text)fiszka.rf.nf.getith(2)).get());
                 add(pane);
                 revalidate();
                 repaint();
            }
        } catch (Exception e) {

        }
        try {
            if (source == ((Exercise2) pane).btOK) {
                 remove(pane);
                 fiszka = cards.poll();
                 if(fiszka == null) {
                     pane = new UserMenu(this);
                     learn = null;
                     ifLearn = false;
                 }
                 else if(fiszka.nr == 1) pane = new Exercise1(this, ((Text)fiszka.rf.nf.getith(1)).get());
                 else if(fiszka.nr == 2) pane = new Exercise2(this, ((Text)fiszka.rf.nf.getith(1)).get(), ((Text)fiszka.rf.nf.getith(2)).get());
                 else if(fiszka.nr == 3) {
                    Vector<String>letters = fiszka.hideLetters(((Text)fiszka.rf.nf.getith(2)).get());
                    String toFill = letters.lastElement();
                    letters.remove(letters.size() - 1);
                    pane = new Exercise3(this, ((Text)fiszka.rf.nf.getith(1)).get(), toFill, letters.toArray(new String[letters.size()]));
                 }
                 else pane = new Exercise4(this, ((Text)fiszka.rf.nf.getith(1)).get(), ((Text)fiszka.rf.nf.getith(2)).get());
                 add(pane);
                 revalidate();
                 repaint();
                   
            }
        } catch (Exception e) {

        }
        try {
            if (source == ((Exercise2) pane).btFail) {
                 remove(pane);
                 cards.add(fiszka);
                 fiszka = cards.poll();
                 if(fiszka == null) {
                     pane = new UserMenu(this);
                     learn = null;
                 }
                 else if(fiszka.nr == 1) pane = new Exercise1(this, ((Text)fiszka.rf.nf.getith(1)).get());
                 else if(fiszka.nr == 2) pane = new Exercise2(this, ((Text)fiszka.rf.nf.getith(1)).get(), ((Text)fiszka.rf.nf.getith(2)).get());
                 else if(fiszka.nr == 3) {
                    Vector<String>letters = fiszka.hideLetters(((Text)fiszka.rf.nf.getith(2)).get());
                    String toFill = letters.lastElement();
                    letters.remove(letters.size() - 1);
                    pane = new Exercise3(this, ((Text)fiszka.rf.nf.getith(1)).get(), toFill, letters.toArray(new String[letters.size()]));
                 }
                 else pane = new Exercise4(this, ((Text)fiszka.rf.nf.getith(1)).get(), ((Text)fiszka.rf.nf.getith(2)).get());
                 add(pane);
                 revalidate();
                 repaint();
            }
        } catch (Exception e) {

        }
        try {
            if (source == ((Exercise3) pane).btContinue) {
            	 String s1 = ((Text)fiszka.rf.nf.getith(2)).get();
                 String s2 = ((Exercise3) pane).lbAnswer.getText();
                 remove(pane);
                 if(fiszka.equalWords(s1, s2) == false){
                 	cards.add(fiszka);
                 	fiszka = new Exc(4, fiszka.rf);
                 }
                 else fiszka = cards.poll();
                 if(fiszka == null) {
                     pane = new UserMenu(this);
                     learn = null;
                 }
                 else if(fiszka.nr == 1) pane = new Exercise1(this, ((Text)fiszka.rf.nf.getith(1)).get());
                 else if(fiszka.nr == 2) pane = new Exercise2(this, ((Text)fiszka.rf.nf.getith(1)).get(), ((Text)fiszka.rf.nf.getith(2)).get());
                 else if(fiszka.nr == 3) {
                    Vector<String>letters = fiszka.hideLetters(((Text)fiszka.rf.nf.getith(2)).get());
                    String toFill = letters.lastElement();
                    letters.remove(letters.size() - 1);
                    pane = new Exercise3(this, ((Text)fiszka.rf.nf.getith(1)).get(), toFill, letters.toArray(new String[letters.size()]));
                 }
                 else pane = new Exercise4(this, ((Text)fiszka.rf.nf.getith(1)).get(), ((Text)fiszka.rf.nf.getith(2)).get());
                 add(pane);
                 revalidate();
                 repaint();

            }
        } catch (Exception e) {

        }

        try {
        	  if (source == ((Base) pane).btLBase) {
        	 	 user.takeBase(false);
                 remove(pane);
                 pane = new UserMenu(this);
                 add(pane);
                 revalidate();
                 repaint();
        	  }
        } catch (Exception e) {
        
        }
        try {
        	  if (source == ((Base) pane).btCrNewBase) {
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
                 remove(pane);
                 fiszka = cards.poll();
                 if(fiszka == null) {
                     pane = new UserMenu(this);
                     learn = null;
                 }
                 else if(fiszka.nr == 1) pane = new Exercise1(this, ((Text)fiszka.rf.nf.getith(1)).get());
                 else if(fiszka.nr == 2) pane = new Exercise2(this, ((Text)fiszka.rf.nf.getith(1)).get(), ((Text)fiszka.rf.nf.getith(2)).get());
                 else if(fiszka.nr == 3) {
                    Vector<String>letters = fiszka.hideLetters(((Text)fiszka.rf.nf.getith(2)).get());
                    String toFill = letters.lastElement();
                    letters.remove(letters.size() - 1);
                    pane = new Exercise3(this, ((Text)fiszka.rf.nf.getith(1)).get(), toFill, letters.toArray(new String[letters.size()]));
                 }
                 else pane = new Exercise4(this, ((Text)fiszka.rf.nf.getith(1)).get(), ((Text)fiszka.rf.nf.getith(2)).get());
                 add(pane);
                 revalidate();
                 repaint();
        	  }
        } catch (Exception e) {
        
        }
        try {
              if (source == ((UserMenu) pane).btRepeat) {
				 user.rfb = new RBase(user.id, user.nfb.baseId);
				 user.rfb.readRBase(user.nfb, user.today);
                 repeat = new Repeating(user);
                 cards = repeat.justLearn(10);
                 remove(pane);
                 fiszka = cards.poll();
                 if(fiszka == null) {
                     pane = new UserMenu(this);
                     learn = null;
                 }
                 else if(fiszka.nr == 1) pane = new Exercise1(this, ((Text)fiszka.rf.nf.getith(1)).get());
                 else if(fiszka.nr == 2) pane = new Exercise2(this, ((Text)fiszka.rf.nf.getith(1)).get(), ((Text)fiszka.rf.nf.getith(2)).get());
                 else if(fiszka.nr == 3) {
                    Vector<String>letters = fiszka.hideLetters(((Text)fiszka.rf.nf.getith(2)).get());
                    String toFill = letters.lastElement();
                    letters.remove(letters.size() - 1);
                    pane = new Exercise3(this, ((Text)fiszka.rf.nf.getith(1)).get(), toFill, letters.toArray(new String[letters.size()]));
                 }
                 else pane = new Exercise4(this, ((Text)fiszka.rf.nf.getith(1)).get(), ((Text)fiszka.rf.nf.getith(2)).get());
                 add(pane);
                 revalidate();
                 repaint();
              }
        } catch (Exception e) {

        }
    }
}
