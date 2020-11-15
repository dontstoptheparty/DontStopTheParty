package br.ufrgs.inf.dontstoptheparty;

import br.ufrgs.inf.dontstoptheparty.ui.TextMainUI;
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
                TextMainUI textMainUI;

                // Verifies if has some input file as argument
                if (args.length == 1) {
                    final String filePath = args[0];
                    textMainUI = new TextMainUI(filePath);
                } else {
                    textMainUI = new TextMainUI();
                }
                textMainUI.display();

            } catch (MidiUnavailableException e) {
                UIUtils.showErrorDialog(UITextConstants.MIDI_UNAVAILABLE_EXCEPTION);
                e.printStackTrace();
            }
        });
    }
}