package br.ufrgs.inf.dontstoptheparty;

import br.ufrgs.inf.dontstoptheparty.ui.Main;
import br.ufrgs.inf.dontstoptheparty.ui.UITextConstants;
import br.ufrgs.inf.dontstoptheparty.ui.UIUtils;

import javax.sound.midi.MidiUnavailableException;
import javax.swing.*;

public class App {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        java.awt.EventQueue.invokeLater(() -> {
            try {
                Main mainWindow;

                // Verifies if has some input file as argument
                if (args.length == 1) {
                    final String filePath = args[0];
                    mainWindow = new Main(filePath);
                } else {
                    mainWindow = new Main();
                }
                mainWindow.display();

            } catch (MidiUnavailableException e) {
                UIUtils.showErrorDialog(UITextConstants.MIDI_UNAVAILABLE_EXCEPTION);
                e.printStackTrace();
            }
        });
    }
}