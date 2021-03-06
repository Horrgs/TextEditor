package org.horrgs.texteditor.windows;

import org.horrgs.texteditor.TEH;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;

/**
 * Created by Horrgs on 2/13/2015.
 */
public class TEStart extends JFrame {
    public JButton openFile;
    public JButton newFile;
    public Color c = new Color(238, 238, 238);
    private String text = "";
    public TEStart() {
        setSize(400, 800);
        setLayout(new GridBagLayout());
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLayout(new GridBagLayout());
        openFile = new JButton();
        newFile = new JButton();
        try {
            Image openImg = ImageIO.read(getClass().getResource("/resources/folder2.png"));
            System.out.println(getClass().getResource("/resources/folder2.png").getPath());
            System.out.println(getClass().getResource(File.separator + "resources" + File.separator + "folder2.png"));
            openFile.setIcon(new ImageIcon(openImg));
            Image newImg = ImageIO.read(getClass().getResource("/resources/new.png"));
            newFile.setIcon(new ImageIcon(newImg));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        openFile.setForeground(c);
        openFile.setBackground(c);
        openFile.addActionListener(new DesignListener());
        openFile.setBorder(BorderFactory.createLineBorder(c));
        openFile.setToolTipText("Open an existing file.");
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.gridx = 0;
        add(openFile, gbc);
        newFile.setForeground(c);
        newFile.setBackground(c);
        newFile.addActionListener(new DesignListener());
        newFile.setBorder(BorderFactory.createLineBorder(c));
        newFile.setToolTipText("Create a new file.");
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(newFile, gbc);
        setVisible(true);
    }
    
    public void setText(String text) {
        if(this.text != null) {
            this.text = this.text + "\n" + text;
        } else {
            this.text = text;
        }
    }
    
    public String getText() {
        return text;
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
                JFileChooser jfc = new JFileChooser("");
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");
                jfc.setFileFilter(filter);
                int returnVal = jfc.showOpenDialog(openFile);
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    File f = jfc.getSelectedFile();
                    //String ext = FilenameUtils.getExtension(jfc.getSelectedFile().getAbsolutePath().toString());
                    //System.out.println("Opening: " + f.getName() + ".");
                    try {
                        Scanner reader = new Scanner(new FileReader(f));
                        for(int x = 0; x < TEH.countLines(f.getAbsolutePath().toString()); x++) {
                           setText(reader.nextLine());
                        }
                        new TEEditor(getText(), f.getAbsolutePath());
                        setVisible(false);
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            } else if(ev.getSource() == newFile) {
                newFile.setForeground(foreground);
                newFile.setBorder(buttonBorder);
                openFile.setForeground(c);
                openFile.setBorder(resetBorder);
                setVisible(false);
                new TEEditor(null, null);
            }
        }
    }
}
