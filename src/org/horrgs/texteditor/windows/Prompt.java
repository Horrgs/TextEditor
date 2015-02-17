package org.horrgs.texteditor.windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * Created by Horrgs on 2/15/2015.
 */
public class Prompt {
    public JButton okay;
    private JFrame jFrame;
    private JTextArea jTextArea;
    private JButton overRide;
    private File file;
    private char[] text;
    public void setText(char[] text) {
        this.text = text;
    }
    
    public char[] getText() {
        return text;
        
    }
    private JFrame toClose;
    public Prompt(String text, boolean overRideOption, File file, JFrame toClose) {
        this.toClose = toClose;
        this.file = file;
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
        if(overRideOption) {
            overRide = new JButton("Override Existing File");
            gbc.gridy = 2;
            gbc.insets = new Insets(0, 10, 10, 10);
            overRide.addActionListener(new PromptListener());
            jFrame.add(overRide, gbc);
        }
        jFrame.setVisible(true);
    }
    
    private class PromptListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ev) {
            if(ev.getSource() == okay) {
                System.out.println(jFrame.getSize());
                jFrame.setVisible(false);
            } else if(ev.getSource() == overRide) {
                if(file != null) {
                    if(file.exists()) {
                        //TODO: THIS SHOULD BE DONE DIFFERENTLY, TEMP
                        file.delete();
                        try {
                            file.createNewFile();
                            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(file)));
                            if(getText() != null) {
                                for(int x = 0; x < getText().length; x++) {
                                    char[] text = getText();
                                    if(text[x] != '\n') {
                                        writer.print(text[x]);
                                        writer.flush();
                                    } else {
                                        writer.println("");
                                    }
                                }
                                jFrame.setVisible(false);
                                toClose.setVisible(false);
                            } else {
                                new Prompt("The Text Editor contains no text, please add text or delete the file if that's your goal.", false, null, null);
                            }
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
