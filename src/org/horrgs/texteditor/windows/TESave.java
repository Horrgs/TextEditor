package org.horrgs.texteditor.windows;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Created by Horrgs on 2/13/2015.
 */
public class TESave extends JFrame {
    JTextField hintFileLoc;
    JTextArea fileLocation;
    JTextField hintFileName;
    JTextField fileName;
    Border bf = BorderFactory.createLineBorder(Color.BLACK);
    public TESave() {
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
    }
    
    public void save() {
        
    }
}
