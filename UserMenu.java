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
    public final JButton btLogOut;


    public UserMenu(Frame ramka) {
        super(new GridBagLayout());
        c = new GridBagConstraints();
        c.weightx = 0.5;
        c.weighty = 0.5;

        //System.out.println("bleeeeeeeee");


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

    }

}
