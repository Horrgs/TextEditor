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
    //continueWithoutSaving is only used when they click "new" in TEEdtitor.
    public JButton continueWithoutSaving;
    public JButton cancel;
    public boolean teStart;
    public String aFp;
    private JFrame jfR;
    private File saveFile;
    /**
     * @param textEditor Text being saved.
     * @param teStart Used when continueWithoutSaving is clicked. Will open the TEStart GUI.
     * @param aFp This is the absoluteFilePath. It sets the file location and file name. Editable.
     * @param cWs Option to use closeWithoutSaving. Should only be used when "Open" is clicked in
     *            in the TEEditor.*
     * @param jfR Provides the JFrame of the TextEditor so it can be closed once it's done saving.
     */
    public TESave(JTextArea textEditor, boolean teStart, String aFp, boolean cWs, JFrame jfR) {
        this.jfR = jfR;
        this.aFp = aFp;
        this.teStart = teStart;
        this.textEditor = textEditor;
        setResizable(false);
        setSize(600, 400);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
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
        if(aFp != null) {
            saveFile = new File(aFp);
            fileLocation = new JTextArea(saveFile.getParent());
            fileName = new JTextField(saveFile.getName());
        } else {
            fileLocation = new JTextArea("");
            fileName = new JTextField("");
        }
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
        fileName.setBorder(bf);
        fileName.setPreferredSize(new Dimension(75, 18));
        fileName.setPreferredSize(new Dimension(75, 18));
        add(fileName, gbc);
        if(cWs) {
            continueWithoutSaving = new JButton("Continue Without Saving");
            gbc.weightx = 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.gridx = 0;
            gbc.gridy = 4;
            continueWithoutSaving.addActionListener(new TESaveListener());
            add(continueWithoutSaving, gbc);
        }
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
        setVisible(true);
    }
    
    private class TESaveListener implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent ev) {
            if(ev.getSource() == cancel) {
                setVisible(false);
            } else if(ev.getSource() == save) {
                String a = String.valueOf(fileLocation.getText().charAt(fileLocation.getText().length() - 1));
                if(!a.equals("/") && !a.equals("\\")) {
                    fileLocation.setText(fileLocation.getText() + "\\");
                }
                File f;
                if(saveFile == null) {
                    f = new File(fileLocation.getText() + fileName.getText() + ".txt");
                } else {
                    f = saveFile;
                }
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
                            if(teStart) new TEStart();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    } else {
                        new Prompt("There is already a file with the name " + f.getName() + " in " + f.getParent());
                    }
                }
            } else if(ev.getSource() == continueWithoutSaving) {
                if(teStart) {
                    jfR.setVisible(false);
                    new TEStart();
                    setVisible(false);
                }
            }
        }
    }
}
