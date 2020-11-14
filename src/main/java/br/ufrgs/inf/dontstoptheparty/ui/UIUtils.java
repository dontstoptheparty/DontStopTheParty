package br.ufrgs.inf.dontstoptheparty.ui;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import static br.ufrgs.inf.dontstoptheparty.ui.UITextConstants.*;

public class UIUtils {

    /**
     * Given a title, the file filter and the selection mode,
     * creates a File Chooser Windows and returns the absolute path for the file/directory
     *
     * @param chooserTitle      Chooser Windows Title
     * @param fileFilter        filter the files accepted
     * @param fileSelectionMode selection mode (file or directory)
     * @return Absolute path to the file/directory
     */
    private static String choose(String chooserTitle, FileFilter fileFilter, int fileSelectionMode) {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle(chooserTitle);
        chooser.setFileFilter(fileFilter);
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.setFileSelectionMode(fileSelectionMode);
        int returnVal = chooser.showOpenDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile().getPath();
        } else {
            return null;
        }
    }

    /**
     * Opens a windows chooser to chose a file and returns it's path
     *
     * @return file chosen path
     */
    public static String chooseFile() {
        return choose(CHOOSER_FILE_TITLE,
                new FileNameExtensionFilter("Text files", "txt"),
                JFileChooser.FILES_ONLY);
    }

    /**
     * Opens a windows chooser to chose a directory and returns it's path
     *
     * @return directory chosen path
     */
    public static String chooseDirectory() {
        return choose(CHOOSER_DIRECTORY_TITLE, null, JFileChooser.DIRECTORIES_ONLY);
    }

    /**
     * Show a dialog with a message
     *
     * @param message message to be shown
     */
    public static void showMessageDialog(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    /**
     * Show an error dialog with an error message
     *
     * @param message error message to be shown
     */
    public static void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(null, message, DEFAULT_ERROR_TITLE_DIALOG, JOptionPane.ERROR_MESSAGE);
    }
}
