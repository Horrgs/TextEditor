package org.horrgs.texteditor.windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Horrgs on 2/13/2015.
 */
public class TEEditor extends JFrame {
    public JTextArea textEditorArea;
    public JButton saveButton;
    public JButton newButton;
    public TEEditor() {
        setSize(800, 726);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Text Editor v1.0 by Horrgs");
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        textEditorArea = new JTextArea();
        textEditorArea.setLineWrap(true);
        textEditorArea.setWrapStyleWord(true);
        textEditorArea.setPreferredSize(new Dimension(600, 560));
        textEditorArea.setMinimumSize(new Dimension(600, 560));
        textEditorArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(textEditorArea, gbc);
        saveButton = new JButton("Save");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 5, 5, 5);
        saveButton.addActionListener(new TEActionListener());
        add(saveButton, gbc);
        newButton = new JButton("New");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(5, 5, 5, 5);
        newButton.setPreferredSize(saveButton.getPreferredSize());
        newButton.setMinimumSize(saveButton.getMinimumSize());
        newButton.addActionListener(new TEActionListener());
        add(newButton, gbc);
        setVisible(true);
    }
    
    private class TEActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == saveButton) {
                new TESave();
            }
        }
    }
}
