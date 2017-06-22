//package startWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * Created by mdragula on 12.06.17.
 */
public class NewUserWindow extends JFrame implements ActionListener {
    private final GridBagConstraints c;
    private final JLabel lbUsername;
    private final JTextField tfUsername;
    private final JLabel lbPassword;
    private final JPasswordField pfPass;
    private final JPasswordField pfRepPass;
    private final JLabel lbRepPassword;
    private final JButton btCreateNewAc;
    private final JPanel pane;
    private final JLabel lbWarning;

    public NewUserWindow() {
        setSize(300,300);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocation(400,400);

        pane = new JPanel(new GridBagLayout());
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(0,30,0,30);

        lbUsername = new JLabel("Enter your username:");
        c.weightx = 0.5;
        c.weighty = 0.5;
        pane.add(lbUsername, c);

        tfUsername = new JTextField();
        c.gridy = 1;
        pane.add(tfUsername, c);

        lbPassword = new JLabel("Enter password:");
        c.gridy = 2;
        pane.add(lbPassword, c);

        pfPass = new JPasswordField();
        c.gridy = 3;
        pane.add(pfPass, c);

        c.anchor = GridBagConstraints.CENTER;
        lbRepPassword = new JLabel("Repeat password:");
        c.gridy = 4;
        pane.add(lbRepPassword, c);

        pfRepPass = new JPasswordField();
        c.gridy = 5;
        pane.add(pfRepPass, c);

        lbWarning = new JLabel("");
        c.gridy = 6;
        c.ipadx = 0;
        pane.add(lbWarning, c);
        //lbWarning.setVisible(false);

        btCreateNewAc = new JButton("Create new account");
        btCreateNewAc.addActionListener(this);
        c.gridy = 7;
        c.insets = new Insets(0,30,0,30);
        pane.add(btCreateNewAc, c);

        add(pane);


    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(!Arrays.equals(pfPass.getPassword(), pfRepPass.getPassword())) {
            lbWarning.setText("passwords don't match");
        }
        else {
            UserLogin ul = new UserLogin(tfUsername.getText(), pfPass.getText());
            Login aga = new Login();
            if(aga.tryLogin(ul, true) == true)
            {
            	dispose();
            }
            else
            {
            	tfUsername.setText("");
            	pfPass.setText("");
            	pfRepPass.setText("");
            }
        }
    }
}
