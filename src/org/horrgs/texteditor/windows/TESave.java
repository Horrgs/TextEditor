package org.horrgs.texteditor.windows;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * Created by Horrgs on 2/13/2015.
 */
public class TESave {
    private JFrame jFrame;
    //JTextField hintFileLoc;
    //JTextArea fileLocation;
    //JTextField hintFileName;
    //JTextField fileName;
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
    //TODO: File isn't saved if the file doesn't exist.
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
        jFrame = new JFrame("");
        jFrame.setResizable(false);
        jFrame.setSize(600, 400);
        jFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        jFrame.setTitle("Saving File...");
        jFrame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        if(cWs) {
            continueWithoutSaving = new JButton("Continue Without Saving");
            gbc.weightx = 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.gridx = 0;
            gbc.gridy = 4;
            continueWithoutSaving.addActionListener(new TESaveListener());
            jFrame.add(continueWithoutSaving, gbc);
        }
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 5, 5, 5);
        save = new JButton("Save");
        save.addActionListener(new TESaveListener());
        jFrame.add(save, gbc);
        gbc.gridx = 1;
        gbc.insets = new Insets(0, 0, 5, 5);
        cancel = new JButton("Cancel");
        cancel.addActionListener(new TESaveListener());
        jFrame.add(cancel, gbc);
        jFrame.setVisible(true);       
    }
    
    private class TESaveListener implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent ev) {
            if(ev.getSource() == cancel) {
                jFrame.setVisible(false);
            } else if(ev.getSource() == save) {

                JFileChooser jFileChooser = new JFileChooser("");
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");
                jFileChooser.setFileFilter(filter);
                if(aFp != null) {
                    jFileChooser.setCurrentDirectory(new File(aFp));
                } else {
                    String s = File.separator;
                    jFileChooser.setCurrentDirectory(new File(File.listRoots()[0] + ":" + s + "Users" + System.getProperty("user.name") + s + "Desktop" + s));
                }
                int r = jFileChooser.showSaveDialog(null);
                if(r == JFileChooser.APPROVE_OPTION) {
                    if(!jFileChooser.getSelectedFile().getName().endsWith(".txt")) {
                        jFileChooser.setSelectedFile(new File(jFileChooser.getSelectedFile() + ".txt"));
                    }
                    try {
                        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(jFileChooser.getSelectedFile())));
                        for(int x = 0; x < textEditor.getText().length(); x++) {
                            char[] text = textEditor.getText().toCharArray();
                            if(text[x] != '\n') {
                                writer.print(text[x]);
                            } else {
                                writer.print("\n");
                            }
                        }
                        writer.flush();
                        writer.close();
                        jFrame.setVisible(false);
                        if(teStart) new TEStart();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
                
               /* if(!fileLocation.getText().endsWith("/") && !fileLocation.getText().endsWith("\\")) {
                    fileLocation.setText(fileLocation.getText() + File.separator);
                }
                File f;
                if(saveFile == null) {
                    f = new File(fileLocation.getText(), fileName.getText() + ".txt");
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
                        Prompt p = new Prompt("There is already a file with the name " + f.getName() + " in " + f.getParent(), true, f, jFrame);
                        p.setText(textEditor.getText().toCharArray());
                    }
                }  */
            } else if(ev.getSource() == continueWithoutSaving) {
                if(teStart) {
                    jfR.setVisible(false);
                    new TEStart();
                    jFrame.setVisible(false);
                }
            }
        }
    }
}
