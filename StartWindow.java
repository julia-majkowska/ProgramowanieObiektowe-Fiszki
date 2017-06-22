//package startWindow;

//import menuWindow.Frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by mdragula on 12.06.17.
 */
public class StartWindow extends JFrame implements ActionListener {
    private final JPanel pane;
    private final JPasswordField pfPass;
    private final JLabel lbUsername;
    private final JTextField tfUsername;
    private final JLabel lbPassword;
    private JButton btLogIn;
    private JButton btNewUser;


    public StartWindow() throws HeadlessException {
        // window settings
        setSize(500,500);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocation(300,300);

        pane = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.anchor = GridBagConstraints.CENTER;

        lbUsername = new JLabel("Username");
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.5;
        c.weighty = 0.5;
        pane.add(lbUsername, c);

        tfUsername = new JTextField();
        c.gridx = 1;
        c.gridy = 0;
        c.ipadx = 200;
        pane.add(tfUsername, c);

        lbPassword = new JLabel("Password");
        c.gridx = 0;
        c.gridy = 1;
        c.ipadx = 0;
        pane.add(lbPassword, c);

        pfPass = new JPasswordField();
        c.gridx = 1;
        c.gridy = 1;
        c.ipadx = 200;
        pane.add(pfPass, c);

        btLogIn = new JButton("Log in");
        btLogIn.addActionListener(this);
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        c.ipadx = 100;
        pane.add(btLogIn, c);

        btNewUser = new JButton("New user");
        btNewUser.addActionListener(this);
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        c.ipadx = 100;
        pane.add(btNewUser, c);

        add(pane);
	pane.revalidate();
	pane.repaint();
	pane.updateUI();
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Object source =  actionEvent.getSource();

        if(source == btLogIn) {
        
	   	  UserLogin userLog = new UserLogin(tfUsername.getText(), pfPass.getText());
	   	  Login aga = new Login();
	   	  //System.out.println(tfUsername.getText());
	   	  if(aga.tryLogin(userLog, false) == true)
	   	  {
	   	  	User user = aga.mr;
            	new Frame(user);
            	dispose();
            }
            else
            {
            	tfUsername.setText("");
            	pfPass.setText("");
            }
        }
        else if(source == btNewUser) {
            new NewUserWindow();
        }
    }
}
