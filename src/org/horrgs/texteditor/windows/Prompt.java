package org.horrgs.texteditor.windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Horrgs on 2/15/2015.
 */
public class Prompt {
    private JFrame jFrame;
    private JTextArea jTextArea;
    public JButton okay;
    public Prompt(String text) {
        jFrame = new JFrame("Prompt");
        jFrame.setLayout(new GridBagLayout());
        jFrame.setSize(400, 497);
        jFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        jTextArea = new JTextArea(text);
        jTextArea.setBackground(jFrame.getBackground());
        jTextArea.setEditable(false);
        jTextArea.setLineWrap(true);
        jFrame.add(jTextArea, gbc);
        okay = new JButton("Okay");
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 10, 10, 10);
        okay.addActionListener(new PromptListener());
        jFrame.add(okay, gbc);
        jFrame.setVisible(true);
    }
    
    private class PromptListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ev) {
            if(ev.getSource() == okay) {
                System.out.println(jFrame.getSize());
                jFrame.setVisible(false);
            }
            
        }
    }
}
