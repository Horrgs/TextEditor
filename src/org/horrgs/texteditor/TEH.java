package org.horrgs.texteditor;

import org.horrgs.texteditor.windows.TEStart;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Horrgs on 2/13/2015.
 */
public class TEH {
    
    /* TODO: Switch C: to File.listRoots()[0] and / to File.separator.
    This will provide flexibility amongst cross platforms.
     */
    public static void main(String[] args) {
        new TEStart();
        //new TEEditor(null);
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
