package br.ufrgs.inf.dontstoptheparty;

import br.ufrgs.inf.dontstoptheparty.ui.Main;

import javax.sound.midi.MidiUnavailableException;
import javax.swing.*;

public class App {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
         
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Main().display();
                } catch (MidiUnavailableException e) {
//                    UIUtils.showErrorDialog(MIDI_UNAVAILABLE_EXCEPTION);
                    e.printStackTrace();
                }
            }
        });
    }
}