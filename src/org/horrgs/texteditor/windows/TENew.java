package org.horrgs.texteditor.windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Horrgs on 2/13/2015.
 */
public class TENew extends JFrame {
    public JButton currentWindow;
    public JButton newWindow;
    private boolean isNewWindow;
    private JTextArea textEditorArea;
    /**
     * This window will come up when you click the button "new"
     * it'll give you the option to do it in a new window or the
     * current editor.
     * @param textEditorArea this is the editor of the area.
     */
    public TENew(JTextArea textEditorArea) {
        this.textEditorArea = textEditorArea;
        setSize(400, 200);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        setResizable(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        currentWindow = new JButton("Current Editor");
        currentWindow.setToolTipText("This will use the current editor and remove all text from it.");
        currentWindow.addActionListener(new TENewListener());
        add(currentWindow, gbc);
        gbc.gridx = 1;
        gbc.insets = new Insets(10, 0, 10 ,10);
        newWindow = new JButton("New Editor");
        newWindow.setToolTipText("This will open a new editor and keep the current one.");
        newWindow.addActionListener(new TENewListener());
        add(newWindow, gbc);
        setVisible(true);
    }
    
    public boolean isNewWindow() {
        return isNewWindow;
    }
    
    private class TENewListener implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent ev) {
            if(ev.getSource() == currentWindow) {
                isNewWindow = false;
                new TEEditor(isNewWindow(), textEditorArea);
                setVisible(false);
            } else if(ev.getSource() == newWindow) {
                isNewWindow = true;
                new TEEditor(isNewWindow(), textEditorArea);
                new TEStart();
                setVisible(false);
            }
        }
    }
}
