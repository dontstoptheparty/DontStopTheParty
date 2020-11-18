/*
 * DontStopTheParty 2020.
 * Designed by Guilherme Santana, João Pedro Silveira, Renan Magagnin e Wellington M. Espindula.
 */

/*
 * DontStopTheParty 2020.
 * Designed by Guilherme Santana, João Pedro Silveira, Renan Magagnin e Wellington M. Espindula.
 */

package br.ufrgs.inf.dontstoptheparty.utils;

import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileUtils {

    /**
     * Read the content from a text (.txt) file
     *
     * @param path File path
     * @return return the content of the file. Otherwise, return null;
     * @throws IOException Input Exception if have file can't be opened
     */
    public static String readTextFromTextFile(String path) throws IOException {
        if (isATextFile(path)) {
            return Files.readString(Path.of(path));
        } else {
            return null;
        }
    }

    /**
     * Checks if a file is a Text File
     *
     * @param path File path
     * @return if a file is a text file (.txt)
     */
    public static boolean isATextFile(String path) {
        FileFilter filenameFilter = new FileNameExtensionFilter("Text files", "txt");
        return filenameFilter.accept(new File(path));
    }
}
