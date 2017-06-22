//package learn;

//import menuWindow.Frame;

import javax.swing.*;
import java.awt.*;

/**
 * Created by mdragula on 21.06.17.
 */
public class Exercise1 extends JPanel {
    private final GridBagConstraints c;
    private final JPanel paneQuestion;
    private final JPanel paneAnswer;
    private final GridBagConstraints cQuest;
    private final GridBagConstraints cAns;
    private final JLabel lbQuestion;
    public final JTextField tfAnswer;
    public final JButton btContinue;

    public Exercise1(Frame ramka, String question) {
        super(new GridBagLayout());
        c = new GridBagConstraints();
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.CENTER;

        c.ipady = 200;

        c.gridx = 0;
        c.gridy = 0;
        paneQuestion = new JPanel(new GridBagLayout());
        cQuest = new GridBagConstraints();
        c.insets = new Insets(30,30,15,30);

        paneQuestion.setBackground(Color.white);
        lbQuestion = new JLabel(question);
        lbQuestion.setFont(new Font(lbQuestion.getFont().getName(), Font.PLAIN, 15));
        paneQuestion.add(lbQuestion, cQuest);

        add(paneQuestion, c);

        c.gridx = 0;
        c.gridy = 1;
        paneAnswer = new JPanel(new GridBagLayout());
        cAns = new GridBagConstraints();
        c.insets = new Insets(15,30,0,30);

        tfAnswer = new JTextField();
        tfAnswer.setFont(new Font(tfAnswer.getFont().getName(), Font.PLAIN, 25));
        tfAnswer.setPreferredSize(new Dimension(380,70));
        paneAnswer.add(tfAnswer, cAns);

        add(paneAnswer, c);


        c.ipady = 70;

        c.gridx = 0;
        c.gridy = 2;
        btContinue = new JButton("Continue");
        btContinue.setFont(new Font(btContinue.getFont().getName(), Font.BOLD, 15));
        c.insets = new Insets(2,2,2,2);
        btContinue.addActionListener(ramka);

        add(btContinue, c);


    }
}
