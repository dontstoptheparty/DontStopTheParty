package br.ufrgs.inf.dontstoptheparty;

import br.ufrgs.inf.dontstoptheparty.jukebox.JukeBoxImpl;
import br.ufrgs.inf.dontstoptheparty.mediaprocessor.TextProcessor;
import br.ufrgs.inf.dontstoptheparty.song.player.JavaSoundPlayer;
import br.ufrgs.inf.dontstoptheparty.song.recorder.JFugueRecorder;
import br.ufrgs.inf.dontstoptheparty.ui.Main;

import javax.sound.midi.MidiUnavailableException;
import javax.swing.*;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
         
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                final JavaSoundPlayer player;
                try {
                    player = new JavaSoundPlayer();
                    final JFugueRecorder recorder = new JFugueRecorder();
                    final JukeBoxImpl jukeBox = new JukeBoxImpl(new ArrayList<>(), player, recorder);
                    final TextProcessor textProcessor = new TextProcessor();
                    new Main(jukeBox, textProcessor).display();
                } catch (MidiUnavailableException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}