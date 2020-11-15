package br.ufrgs.inf.dontstoptheparty;

import br.ufrgs.inf.dontstoptheparty.ui.TextProcessorUI;
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
                TextProcessorUI textProcessorUI;

                // Verifies if has some input file as argument
                if (args.length == 1) {
                    final String filePath = args[0];
                    textProcessorUI = new TextProcessorUI(filePath);
                } else {
                    textProcessorUI = new TextProcessorUI();
                }
                textProcessorUI.display();

            } catch (MidiUnavailableException e) {
                UIUtils.showErrorDialog(UITextConstants.MIDI_UNAVAILABLE_EXCEPTION);
                e.printStackTrace();
            }
        });
    }
}