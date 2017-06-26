//package learn;

//import menuWindow.Frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by mdragula on 21.06.17.
 */
public class Exercise3 extends JPanel implements ActionListener {
    private final GridBagConstraints c;
    private final JPanel paneQuestion;
    private final GridBagConstraints cQuest;
    private final JLabel lbQuestion;
    private final JPanel paneAnswer;
    private final GridBagConstraints cAns;
    public final JLabel lbAnswer;
    public final JButton btContinue;
    private final String ans;
    private final JPanel paneButtons;
    private final GridBagConstraints cBt;
    private final JButton btReset;

    public Exercise3(Frame ramka, String question, String answer, String[] letters) {
        super(new GridBagLayout());
        System.out.println("Ex3");
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
        c.insets = new Insets(30,30,15,30);

        paneQuestion.setBackground(Color.white);
        lbQuestion = new JLabel(question);
        lbQuestion.setFont(new Font(lbQuestion.getFont().getName(), Font.PLAIN, 15));
        paneQuestion.add(lbQuestion, cQuest);

        add(paneQuestion, c);

        c.ipady = 90;

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

        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(5,30,0,30);
        paneButtons = new JPanel(new GridBagLayout());
        paneButtons.setBackground(Color.white);
        cBt = new GridBagConstraints();
        cBt.insets = new Insets(5,5,5,5);
        for(int i = 0; i < letters.length; i++){
            if((cBt.gridx + 1)% 11 == 0){
                cBt.gridy++;
                cBt.gridx = 0;
            }
            JButton przycisk = new JButton(letters[i]);
            przycisk.addActionListener(this);
            paneButtons.add(przycisk, cBt);
            cBt.gridx++;
        }
        if(cBt.gridx + 3 >= 11){
            cBt.gridx = 0;
            cBt.gridy++;
        }
        btReset = new JButton("Reset answer");
        btReset.addActionListener(this);
        cBt.gridwidth = 3;
        paneButtons.add(btReset, cBt);

        add(paneButtons, c);





        c.ipady = 70;

        c.gridx = 0;
        c.gridy = 3;
        btContinue = new JButton("Continue");
        btContinue.setFont(new Font(btContinue.getFont().getName(), Font.BOLD, 15));
        c.insets = new Insets(2,2,2,2);
        btContinue.addActionListener(ramka);

        add(btContinue, c);

        ans = answer;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();

        if (source == btReset) {
            lbAnswer.setText(ans);
        } else {
            String curAns = lbAnswer.getText();
            for(int i = 0; i < curAns.length(); i++){
                if(curAns.charAt(i) == '_'){
                    curAns = curAns.substring(0, i) + ((JButton) source).getText() + curAns.substring(i + 1);
                    lbAnswer.setText(curAns);
                    return;
                }
            }
        }
    }
}
