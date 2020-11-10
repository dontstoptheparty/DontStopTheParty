package br.ufrgs.inf.dontstoptheparty;

import br.ufrgs.inf.dontstoptheparty.enumerator.Instrument;
import br.ufrgs.inf.dontstoptheparty.enumerator.Note;
import br.ufrgs.inf.dontstoptheparty.jukebox.JukeBox;
import br.ufrgs.inf.dontstoptheparty.mediaprocessor.TextProcessor;
import br.ufrgs.inf.dontstoptheparty.player.JavaSoundPlayer;
import br.ufrgs.inf.dontstoptheparty.recorder.JFugueRecorder;
import br.ufrgs.inf.dontstoptheparty.token.NoteToken;
import br.ufrgs.inf.dontstoptheparty.token.Token;
import br.ufrgs.inf.dontstoptheparty.token.actions.ChangeInstrumentActionToken;
import br.ufrgs.inf.dontstoptheparty.token.actions.DoubleVolumeActionToken;
import br.ufrgs.inf.dontstoptheparty.token.actions.IncreaseInstrumentActionToken;
import br.ufrgs.inf.dontstoptheparty.token.actions.IncreaseOctaveActionToken;
import br.ufrgs.inf.dontstoptheparty.ui.MainWindow;

import javax.sound.midi.MidiUnavailableException;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

//        final List<Token> tokenList = new ArrayList<>();
//
//        tokenList.add(new DoubleVolumeActionToken());
//        tokenList.add(new ChangeInstrumentActionToken(Instrument.ACOUSTIC_PIANO));
//        tokenList.add(new NoteToken(Note.DO));
//        tokenList.add(new NoteToken(Note.DO));
////        tokenList.add(new SilenceActionToken());
////        tokenList.add(new SilenceActionToken());
////        tokenList.add(new SilenceActionToken());
////        tokenList.add(new SilenceActionToken());
//        tokenList.add(new NoteToken(Note.RE));
//        tokenList.add(new NoteToken(Note.RE));
//        tokenList.add(new NoteToken(Note.MI));
//        tokenList.add(new IncreaseInstrumentActionToken(24));
//        tokenList.add(new NoteToken(Note.FA));
//        tokenList.add(new NoteToken(Note.FA));
//        tokenList.add(new NoteToken(Note.SOL));
//        tokenList.add(new NoteToken(Note.SOL));
//        tokenList.add(new IncreaseOctaveActionToken());
//        tokenList.add(new NoteToken(Note.LA));
//        tokenList.add(new NoteToken(Note.LA));
//        tokenList.add(new NoteToken(Note.SOL));
//        tokenList.add(new NoteToken(Note.SI));
//
//        try {
//            JavaSoundPlayer javaSoundPlayer = new JavaSoundPlayer();
//            JFugueRecorder jFugueRecorder = new JFugueRecorder();
//            JukeBox jukeBox = new JukeBox(tokenList, javaSoundPlayer, jFugueRecorder);
//
//            jukeBox.start();
//
//        } catch (MidiUnavailableException e) {
//            e.printStackTrace();
//        }
//
////        jFuguePlayer.save(tokenList);
//
//
//        // TODO HERE WE CAN START THE "INTEGRATION TESTS"
//        System.out.println("Write the song: ");
//        String inputSong = "aA?BCDEFGAbABBBCCCDD!3BBGD4,AB;CD?EFG";
//        TextProcessor textProcessor = new TextProcessor();
//        List<Token> tokens = textProcessor.convert(inputSong);
//
//        try {
//            JavaSoundPlayer player = new JavaSoundPlayer();
//            JFugueRecorder recorder = new JFugueRecorder();
//            JukeBox jukeBox = new JukeBox(tokens, player, recorder);
//
//            jukeBox.start();
//        } catch (MidiUnavailableException e) {
//            e.printStackTrace();
//        }


        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
         
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }
}