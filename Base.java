import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by mdragula on 12.06.17.
 */
public class Base extends JPanel {
	private final GridBagConstraints c;
	public final JButton btCrNewBase;
    public final JButton btLBase;

    public Base(Frame ramka) {
    	super(new GridBagLayout());
        c = new GridBagConstraints();
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.insets = new Insets(10,240,10,240);

        btCrNewBase = new JButton("Create new base");
    	btCrNewBase.addActionListener(ramka);
    	c.gridy = 0;

	   	add(btCrNewBase, c);

	   	btLBase = new JButton("Load from file");
	   	btLBase.addActionListener(ramka);
	   	c.gridy = 1;

	   	add(btLBase, c);
	}
}