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
    private JScrollPane scroll;
    public TEEditor(boolean isNewWindow, JTextArea jTextArea) {
        if(isNewWindow) setVisible(false);
        jTextArea.setText("");
    }
    
    public TEEditor(String text) {
        setSize(800, 726);
        setPreferredSize(new Dimension(800, 726));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Text Editor v1.0 by Horrgs");
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        textEditorArea = new JTextArea(text);
        textEditorArea.setLineWrap(true);
        textEditorArea.setWrapStyleWord(true);
        textEditorArea.setLineWrap(true);
        //textEditorArea.setPreferredSize(new Dimension(600, 560));
        //textEditorArea.setMinimumSize(new Dimension(600, 560));
        textEditorArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        scroll = new JScrollPane(textEditorArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setPreferredSize(new Dimension(600, 560));
        //add(textEditorArea, gbc);
        add(scroll, gbc);
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
                new TESave(textEditorArea);
            } else if(e.getSource() == newButton) {
                new TENew(textEditorArea);
            }
        }
    }
}
