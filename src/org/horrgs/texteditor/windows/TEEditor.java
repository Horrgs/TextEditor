package org.horrgs.texteditor.windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Horrgs on 2/13/2015.
 */
public class TEEditor {
    public JTextArea textEditorArea;
    public JButton saveButton;
    public JButton newButton;
    public JButton openDoc;
    private JScrollPane scroll;
    private String absoluteFilePath;
    public JFrame jFrame = new JFrame();
    /** 
     * @param isNewWindow When "new" document is clicked, you have the choice
     * in whether to use the current window or a new one
     * @param jTextArea
     */
    public TEEditor(boolean isNewWindow, JTextArea jTextArea) {
        if(isNewWindow) jFrame.setVisible(false);
        jTextArea.setText("");
    }

    /**
     * @param text This will be the text within the JTextArea. This is set when opening
     * a file. Leaving it null will leave the JTextArea empty.             
     * @param absoluteFilePath This is the file path of the .txt file that's being opened,
     * leave it null when you're creating a new file.                         
     */
    
    public TEEditor(String text, String absoluteFilePath) {
        this.absoluteFilePath = absoluteFilePath;
        jFrame.setSize(800, 726);
        jFrame.setPreferredSize(new Dimension(800, 726));
        jFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        jFrame.setTitle("Text Editor v1.0 by Horrgs");
        jFrame.setLayout(new GridBagLayout());
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
        textEditorArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        scroll = new JScrollPane(textEditorArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setPreferredSize(new Dimension(600, 560));
        jFrame.add(scroll, gbc);
        saveButton = new JButton("Save");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 5, 5, 5);
        saveButton.addActionListener(new TEActionListener());
        jFrame.add(saveButton, gbc);
        newButton = new JButton("New");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(5, 5, 5, 5);
        newButton.setPreferredSize(saveButton.getPreferredSize());
        newButton.setMinimumSize(saveButton.getMinimumSize());
        newButton.addActionListener(new TEActionListener());
        jFrame.add(newButton, gbc);
        openDoc = new JButton("Open");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.insets = new Insets(5, 5, 5, 5);
        openDoc.setPreferredSize(newButton.getPreferredSize());
        openDoc.setMinimumSize(newButton.getMinimumSize());
        openDoc.addActionListener(new TEActionListener());
        jFrame.add(openDoc, gbc);
        jFrame.setVisible(true);
    }
    
    private class TEActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == saveButton) {
                new TESave(textEditorArea, false, absoluteFilePath, true, jFrame);
            } else if(e.getSource() == newButton) {
                new TENew(textEditorArea);
            } else if(e.getSource() == openDoc) {
                new TESave(textEditorArea, true, absoluteFilePath, true, jFrame);
            }
        }
    }
}
