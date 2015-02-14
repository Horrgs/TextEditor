package org.horrgs.texteditor.windows;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Horrgs on 2/13/2015.
 */
public class TEStart extends JFrame {
    public JButton openFile;
    public JButton newFile;
    public Color c = new Color(238, 238, 238);
    public TEStart() {
        setSize(400, 800);
        setLayout(new GridBagLayout());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new GridBagLayout());
        openFile = new JButton();
        openFile.setIcon(new ImageIcon("C:\\Users\\Horrgs\\Desktop\\Java\\imgs\\folder2.png"));
        openFile.setForeground(c);
        openFile.setBackground(c);
        openFile.addActionListener(new DesignListener());
        openFile.setBorder(BorderFactory.createLineBorder(c));
        openFile.setToolTipText("Open an existing file.");
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.gridx = 0;
        add(openFile, gbc);
        newFile = new JButton();
        newFile.setIcon(new ImageIcon("C:\\Users\\Horrgs\\Desktop\\Java\\imgs\\new.png"));
        newFile.setForeground(c);
        newFile.setBackground(c);
        newFile.addActionListener(new DesignListener());
        newFile.setBorder(BorderFactory.createLineBorder(c));
        newFile.setToolTipText("Create a new file.");
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(newFile, gbc);
        setVisible(true);
        //FORGROUND: 51, 51, 51
    }
    
    private class DesignListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ev) {
            Color foreground = new Color(51, 51, 51);
            Border buttonBorder =  UIManager.getBorder("Button.border");
            Border resetBorder = BorderFactory.createLineBorder(c); 
            if(ev.getSource() == openFile) {
                openFile.setForeground(foreground);
                openFile.setBorder(buttonBorder);
                newFile.setForeground(c);
                newFile.setBorder(resetBorder);
            } else if(ev.getSource() == newFile) {
                newFile.setForeground(foreground);
                newFile.setBorder(buttonBorder);
                openFile.setForeground(c);
                openFile.setBorder(resetBorder);
                setVisible(false);
                new TEEditor(null);
            }
        }
        
    }
}
