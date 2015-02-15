package org.horrgs.texteditor;

import org.horrgs.texteditor.windows.TEEditor;
import org.horrgs.texteditor.windows.TENew;

import java.io.*;

/**
 * Created by Horrgs on 2/13/2015.
 */
public class TEH {
    
    /* TODO: Switch C: to File.listRoots()[0] and / to File.separator.
    This will provide flexibility amongst cross platforms.
     */
    public static void main(String[] args) {
        //new TEStart();
        new TEEditor(null, null);
        File f = new File("C:\\Users\\Horrgs\\Desktop\\Programs\\MySQL.txt");
        System.out.println("Absolute File " + f.getAbsoluteFile());
        System.out.println("Absolute Path " + f.getAbsolutePath());
        System.out.println("Parent " + f.getParent());
        System.out.println("Parent File " + f.getParentFile());
        System.out.println("Path " + f.getPath());
        System.out.println("Name " + f.getName());
        
    }

    /**
     @author martinus
     */

    public static int countLines(String filename) throws IOException {
        InputStream is = new BufferedInputStream(new FileInputStream(filename));
        try {
            byte[] c = new byte[1024];
            int count = 0;
            int readChars = 0;
            boolean empty = true;
            while ((readChars = is.read(c)) != -1) {
                empty = false;
                for (int i = 0; i < readChars; ++i) {
                    if (c[i] == '\n') {
                        ++count;
                    }
                }
            }
            return (count == 0 && !empty) ? 1 : count;
        } finally {
            is.close();
        }
    }
}
