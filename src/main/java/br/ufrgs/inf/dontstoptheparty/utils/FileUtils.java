package br.ufrgs.inf.dontstoptheparty.utils;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileUtils {
    private static String CHOOSER_TITLE = "Select a text file";

    public static String readTextFromTextFile(String path) throws IOException {
        final String fileText = Files.readString(Path.of(path));

        return fileText;
    }

    public static String chooseFile() {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt");
        chooser.setDialogTitle(CHOOSER_TITLE);
        chooser.setFileFilter(filter);
        chooser.setAcceptAllFileFilterUsed(false);
        int returnVal = chooser.showOpenDialog(null);

        if(returnVal == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile().getPath();
        } else {
            return null;
        }
    }
}
