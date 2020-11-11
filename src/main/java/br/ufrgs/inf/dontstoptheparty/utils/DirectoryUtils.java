package br.ufrgs.inf.dontstoptheparty.utils;

import javax.swing.*;

public class DirectoryUtils {
    private static String CHOOSER_TITLE = "Select a directory";

    public static String chooseDirectory() {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle(CHOOSER_TITLE);
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile().getPath();
        } else {
            return null;
        }
    }
}
