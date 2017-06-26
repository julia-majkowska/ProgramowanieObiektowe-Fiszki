//package learn;

//import menuWindow.Frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by mdragula on 21.06.17.
 */
public class Exercise4 extends JPanel {
    private final GridBagConstraints c;
    private final JPanel paneQuestion;
    private final GridBagConstraints cQuest;
    private final JLabel lbQuestion;
    private final JPanel paneAnswer;
    private final GridBagConstraints cAns;
    private final JLabel lbAnswer;
    public final JButton btContinue;


    public Exercise4(Frame ramka, String question, String answer) {
        super(new GridBagLayout());
        System.out.println("Ex4");
        c = new GridBagConstraints();
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.CENTER;

        c.ipady = 200;

        c.gridx = 0;
        c.gridy = 0;
        paneQuestion = new JPanel(new GridBagLayout());
        cQuest = new GridBagConstraints();
        c.insets = new Insets(30,30,30,30);

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

        paneAnswer.setBackground(Color.white);
        lbAnswer = new JLabel(answer);
        lbAnswer.setFont(new Font(lbAnswer.getFont().getName(), Font.PLAIN, 15));
        paneAnswer.add(lbAnswer, cAns);

        add(paneAnswer, c);



        c.ipady = 70;

        c.gridx = 0;
        c.gridy = 2;
        btContinue = new JButton("Next");
        btContinue.setFont(new Font(btContinue.getFont().getName(), Font.BOLD, 15));
        c.insets = new Insets(2,2,2,2);
        btContinue.addActionListener(ramka);

        add(btContinue, c);

    }
}
