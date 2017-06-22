//package learn;

//import menuWindow.Frame;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

/**
 * Created by mdragula on 21.06.17.
 */
public class WhatToLearn extends JPanel implements ActionListener, ChangeListener {

    private final GridBagConstraints c;
    private final JComboBox ltCategories;
    private final JLabel lbCardsTL;
    private final JLabel lbNumCards;
    public final JButton btContinue;
    private JSlider slidCardsTL;
    private final Map<String, Integer> mapCat;

    public WhatToLearn(Frame ramka, String[] categories, Map<String, Integer> mapa) {
        super(new GridBagLayout());
        c = new GridBagConstraints();
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.CENTER;

        ltCategories = new JComboBox(categories);
        ltCategories.addActionListener(this);
        c.insets = new Insets(60,120,60, 120);

        add(ltCategories, c);

        c.gridy = 1;
        c.insets = new Insets(30,200,0, 200);
        lbCardsTL = new JLabel("Number of cards to learn");
        lbCardsTL.setHorizontalAlignment(JLabel.CENTER);

        add(lbCardsTL, c);

        c.gridy = 2;
        slidCardsTL = new JSlider(JButton.HORIZONTAL, 0, mapa.get(categories[0]), mapa.get(categories[0])/2);
        slidCardsTL.addChangeListener(this);
        c.insets = new Insets(10,100,10, 100);

        add(slidCardsTL, c);

        c.gridy = 3;
        lbNumCards = new JLabel(Integer.toString(mapa.get(categories[0])/2));
        lbNumCards.setFont(new Font(lbNumCards.getFont().getName(), Font.PLAIN, 25));
        c.insets = new Insets(10,200,30, 200);
        lbNumCards.setHorizontalAlignment(JLabel.CENTER);

        add(lbNumCards, c);

        c.gridy = 4;
        c.insets = new Insets(0,0,0,0);
        btContinue = new JButton("Continue");
        btContinue.setFont(new Font(btContinue.getFont().getName(), Font.BOLD, 15));
        btContinue.addActionListener(ramka);

        add(btContinue, c);

        mapCat = mapa;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();

        if(source == ltCategories){
            String newCategory = (String)ltCategories.getSelectedItem();
            slidCardsTL.setMaximum(mapCat.get(newCategory));
            slidCardsTL.setValue(mapCat.get(newCategory)/2);
        }
    }

    @Override
    public void stateChanged(ChangeEvent changeEvent) {
        Object source = changeEvent.getSource();

        if(source == slidCardsTL) {
            lbNumCards.setText(Integer.toString(slidCardsTL.getValue()));
        }
    }
}
