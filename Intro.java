import javax.swing.*;
import java.awt.*;

public class Intro extends JPanel{
	private final GridBagConstraints c;
	private final JLabel lbText;
	public final JButton btContinue;

	public Intro(Frame ramka){
		super(new GridBagLayout());
        c = new GridBagConstraints();
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(70,50,30,50);

        c.gridx = 0;
        c.gridy = 0;
        lbText = new JLabel("<html>" + "Dear User, <br/>"
+ "We are very glad you have chosen LearnIt. <br/>"
+ "We hope your study with the programme will prove both interesting and successfull. <br/>"
+ "<br/>"
+ "Here is a short manual: <br/>"
+ "Alfter logging in you choose the base of cue cards, then you can learn from them or repeat what you"
+ " already have learnt. You can also modify the chosen base by adding or deleting cue cards in it.</br>"
+ "</br>"
+ "Remember that continuity is the most important part of studying. That’s why we recommend you"
+ " begin with the repetition before you learn new phrases.</br>"
+ "<br/>"
+ "Have fun studying!" + "</html>");

		//lbText = new JLabel("<html>" + "Maciek" + "</html>");

        add(lbText, c);

        c.insets = new Insets(90,240,90,240);
        c.gridx = 0;
        c.gridy = 1;
        btContinue = new JButton("Continue");
        btContinue.addActionListener(ramka);

        add(btContinue, c);
	}
}