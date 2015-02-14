package org.horrgs.texteditor.windows;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * Created by Horrgs on 2/13/2015.
 */
public class TESave extends JFrame {
    JTextField hintFileLoc;
    JTextArea fileLocation;
    JTextField hintFileName;
    JTextField fileName;
    JTextArea textEditor;
    Border bf = BorderFactory.createLineBorder(Color.BLACK);
    public JButton save;
    public JButton cancel;
    public TESave(JTextArea textEditor) {
        this.textEditor = textEditor;
        setResizable(false);
        setSize(600, 400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Saving File...");
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        hintFileLoc = new JTextField("File Location: ");
        hintFileLoc.setBackground(getBackground());
        hintFileLoc.setEditable(false);
        hintFileLoc.setBorder(BorderFactory.createLineBorder(getBackground()));
        add(hintFileLoc, gbc);
        gbc.gridx = 1;
        gbc.insets = new Insets(5, 0, 5, 5);
        fileLocation = new JTextArea("");
        fileLocation.setBorder(bf);
        fileLocation.setPreferredSize(new Dimension(267, 18));
        fileLocation.setMinimumSize(new Dimension(267, 18));
        add(fileLocation, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 5, 5, 5);
        hintFileName = new JTextField("File Name: ");
        hintFileName.setBackground(getBackground());
        hintFileName.setEditable(false);
        hintFileName.setBorder(BorderFactory.createLineBorder(getBackground()));
        add(hintFileName, gbc);
        gbc.gridx = 1;
        gbc.insets = new Insets(0, 0, 5, 5);
        fileName = new JTextField("");
        fileName.setBorder(bf);
        fileName.setPreferredSize(new Dimension(75, 18));
        fileName.setPreferredSize(new Dimension(75, 18));
        add(fileName, gbc);
        setVisible(true);
        System.out.println(fileLocation.getPreferredSize());
        System.out.println(fileName.getPreferredSize());
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 5, 5, 5);
        save = new JButton("Save");
        save.addActionListener(new TESaveListener());
        add(save, gbc);
        gbc.gridx = 1;
        gbc.insets = new Insets(0, 0, 5, 5);
        cancel = new JButton("Cancel");
        cancel.addActionListener(new TESaveListener());
        add(cancel, gbc);
    }
    
    private class TESaveListener implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent ev) {
            if(ev.getSource() == cancel) {
                setVisible(false);
            } else if(ev.getSource() == save) {
                if(fileLocation.getText().toCharArray()[fileLocation.getText().length() - 1] != '/' || fileLocation.getText().toCharArray()[fileLocation.getText().length() - 1] != '\\') {
                    fileLocation.setText(fileLocation.getText() + "\\");
                }

                File f = new File(fileLocation.getText() + fileName.getText() + ".txt");
                if(f.getParentFile().exists()) {
                    if(!f.exists()) {
                        try {
                            f.createNewFile();
                            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(f)));
                            for(int x = 0; x < textEditor.getText().length(); x++) {
                                char[] text = textEditor.getText().toCharArray();
                                if(text[x] != '\n') {
                                    writer.print(text[x]);
                                    writer.flush();
                                } else {
                                    writer.println("");
                                }
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
