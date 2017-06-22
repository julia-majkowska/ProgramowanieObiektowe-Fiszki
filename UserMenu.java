//package menuWindow;

//import startWindow.StartWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by mdragula on 12.06.17.
 */
public class UserMenu extends JPanel {
    private final GridBagConstraints c;
    public final JButton btAdd;
    public final JButton btLearn;
    public final JButton btRepeat;
    //public final JButton btExercises;
    public final JButton btLogOut;
    public final JButton btCrNewBase;
    public final JButton btLBase;

    public UserMenu(Frame ramka) {
        super(new GridBagLayout());
        c = new GridBagConstraints();
        c.weightx = 0.5;
        c.weighty = 0.5;

        btLogOut = new JButton("Log Out");
        btLogOut.addActionListener(ramka);
        c.insets = new Insets(10,0,15, 10);
        c.anchor = GridBagConstraints.FIRST_LINE_END;

        add(btLogOut, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.CENTER;


        btAdd = new JButton("Add");
        btAdd.addActionListener(ramka);
        c.gridy = 1;
        c.insets = new Insets(10,240,10,240);

        add(btAdd, c);

        btLearn = new JButton("Learn");
        btLearn.addActionListener(ramka);
        c.gridy = 2;

        add(btLearn, c);

        btRepeat = new JButton("Repeat");
        btRepeat.addActionListener(ramka);
        c.gridy = 3;

        add(btRepeat, c);
/*
        btExercises = new JButton("Exercises");
        btExercises.addActionListener(ramka);
        c.gridy = 4;
        
        add(btExercises, c);
*/
	btCrNewBase = new JButton("Create new base");
	btCrNewBase.addActionListener(ramka);
	c.gridy = 5;

	add(btCrNewBase, c);

	btLBase = new JButton("Load from file");
	btLBase.addActionListener(ramka);
	c.gridy = 6;

	add(btLBase, c);


	//c.insets = new Insets(15,240,100,240);


    }

}
