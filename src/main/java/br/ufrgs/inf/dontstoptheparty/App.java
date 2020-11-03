package br.ufrgs.inf.dontstoptheparty;

import br.ufrgs.inf.dontstoptheparty.enumerator.Instrument;
import br.ufrgs.inf.dontstoptheparty.enumerator.Note;
import br.ufrgs.inf.dontstoptheparty.jukebox.JukeBox;
import br.ufrgs.inf.dontstoptheparty.player.JavaSoundPlayer;
import br.ufrgs.inf.dontstoptheparty.token.NoteToken;
import br.ufrgs.inf.dontstoptheparty.token.Token;
import br.ufrgs.inf.dontstoptheparty.token.actions.ChangeInstrumentActionToken;
import br.ufrgs.inf.dontstoptheparty.token.actions.DoubleVolumeActionToken;
import br.ufrgs.inf.dontstoptheparty.token.actions.IncreaseInstrumentActionToken;
import br.ufrgs.inf.dontstoptheparty.token.actions.IncreaseOctaveActionToken;

import javax.sound.midi.MidiUnavailableException;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static final int NOTE_ON = 0x90;
    public static final int NOTE_OFF = 0x80;
    public static final String[] NOTE_NAMES = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};

    public static void main(String[] args) {

        final List<Token> tokenList = new ArrayList<>();

        tokenList.add(new DoubleVolumeActionToken());
        tokenList.add(new ChangeInstrumentActionToken(Instrument.ACOUSTIC_PIANO));
        tokenList.add(new NoteToken(Note.DO));
        tokenList.add(new NoteToken(Note.DO));
//        tokenList.add(new SilenceActionToken());
//        tokenList.add(new SilenceActionToken());
//        tokenList.add(new SilenceActionToken());
//        tokenList.add(new SilenceActionToken());
        tokenList.add(new NoteToken(Note.RE));
        tokenList.add(new NoteToken(Note.RE));
        tokenList.add(new NoteToken(Note.MI));
        tokenList.add(new IncreaseInstrumentActionToken(1));
        tokenList.add(new NoteToken(Note.FA));
        tokenList.add(new NoteToken(Note.FA));
        tokenList.add(new NoteToken(Note.SOL));
        tokenList.add(new NoteToken(Note.SOL));
        tokenList.add(new IncreaseOctaveActionToken());
        tokenList.add(new NoteToken(Note.LA));
        tokenList.add(new NoteToken(Note.LA));
        tokenList.add(new NoteToken(Note.SOL));
        tokenList.add(new NoteToken(Note.SI));

        try {
            JavaSoundPlayer javaSoundPlayer = new JavaSoundPlayer();
            JukeBox jukeBox = new JukeBox(tokenList, javaSoundPlayer);

            jukeBox.start();
            Thread.sleep(3000);
            jukeBox.pause();
            Thread.sleep(2000);
            jukeBox.play();

        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        jFuguePlayer.save(tokenList);

//
//        try {
//            Synthesizer midiSynth = MidiSystem.getSynthesizer();
//            midiSynth.open();
//
//            //get and load default instrument and channel lists
//            javax.sound.midi.Instrument[] instr = midiSynth.getDefaultSoundbank().getInstruments();
//            MidiChannel[] mChannels = midiSynth.getChannels();
//
//            Sequence sequence = MidiSystem.getSequence(new File("Test2.mid"));
//
//            int trackNumber = 0;
//            for (Track track :  sequence.getTracks()) {
//                trackNumber++;
//                System.out.println("Track " + trackNumber + ": size = " + track.size());
//                System.out.println();
//                for (int i = 0; i < track.size(); i++) {
//                    MidiEvent event = track.get(i);
//                    System.out.print("@" + event.getTick() + " ");
//                    MidiMessage message = event.getMessage();
//                    if (message instanceof ShortMessage) {
//                        ShortMessage sm = (ShortMessage) message;
//                        System.out.print("Channel: " + sm.getChannel() + " ");
//                        if (sm.getCommand() == NOTE_ON) {
//                            int key = sm.getData1();
//                            int octave = (key / 12) - 1;
//                            int note = key % 12;
//                            String noteName = NOTE_NAMES[note];
//                            int velocity = sm.getData2();
//                            System.out.println("Note on, " + noteName + octave + " key=" + key + " velocity: " + velocity);
//
//                            midiSynth.loadInstrument(instr[0]);//load an instrument
//
//                            mChannels[0].noteOn(key, velocity);//On channel 0, play note number 60 with velocity 100
//                            try {
//                                Thread.sleep(500); // wait time in milliseconds to control duration
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//                        } else if (sm.getCommand() == NOTE_OFF) {
//                            int key = sm.getData1();
//                            int octave = (key / 12) - 1;
//                            int note = key % 12;
//                            String noteName = NOTE_NAMES[note];
//                            int velocity = sm.getData2();
//                            System.out.println("Note off, " + noteName + octave + " key=" + key + " velocity: " + velocity);
//
//                            mChannels[0].noteOff(key, velocity);//On channel 0, play note number 60 with velocity 100
//                        } else {
//                            System.out.println("Command:" + sm.getCommand());
//                        }
//                    } else {
//                        System.out.println("Other message: " + message.getClass());
//                    }
//                }
//
//                System.out.println();
//            }
//        } catch (InvalidMidiDataException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (MidiUnavailableException e) {
//            e.printStackTrace();
//        }


//
//        System.out.println("Attempt for-loop reproducing");
//        JukeBox jukeBox = new JukeBox(tokenList, jFuguePlayer);
//        jukeBox.start();
//
//
//        try {
//            System.out.println("Waiting");
//            Thread.sleep(9500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

//        System.out.println("Attempt playing it all");
//        jFuguePlayer.reset();
//        Player localPlayer = new Player();
//        localPlayer.play(jFuguePlayer.save(tokenList));



        /*FIM EXEMPLO SAVE DO JFUGUE PLAYER*/

//        Pattern p1 = new Pattern("C8 C").setVoice(0).setInstrument("Piano");
//        player.play(p1);

//        p1 = new Pattern("C8q C").setVoice(0).setInstrument("Flute");
//        player.play(p1);
//
//
//        p1 = new Pattern("E3wwd0").setVoice(0).setInstrument("Piano");
//        player.play(p1);
//         */
//
//        /*T: Tempo BPM*/
//        /*:CON(7, 127) VOLUME*/
//        p1 = new Pattern(":CON(7, 40) T[Grave] A B C D :CON(7, 127) T[Pretissimo] A B C D").setVoice(0).setInstrument("Piano");
//        player.play(p1);

        // Playing with loops and patterns
//        for (int i = 0; i < noteList.length; i++){
//            p1 = new Pattern(":CON(7, 127) T[Grave] "+noteList[i]).setVoice(0).setInstrument("Piano");
//            player.play(p1);
//            while (!player.getManagedPlayer().isFinished()) {
//                try {
//                    Thread.sleep((long) 10);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
/*
        try {
            /* Create a new Sythesizer and open it. Most of
             * the methods you will want to use to expand on this
             * example can be found in the Java documentation here:
             * https://docs.oracle.com/javase/7/docs/api/javax/sound/midi/Synthesizer.html
             *//*
            Synthesizer midiSynth = MidiSystem.getSynthesizer();
            midiSynth.open();

            //get and load default instrument and channel lists
            javax.sound.midi.Instrument[] instr = midiSynth.getDefaultSoundbank().getInstruments();
            MidiChannel[] mChannels = midiSynth.getChannels();

            midiSynth.loadInstrument(instr[0]);//load an instrument


            mChannels[0].noteOn(60, 100);//On channel 0, play note number 60 with velocity 100
            try {
                Thread.sleep(3000); // wait time in milliseconds to control duration
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mChannels[0].noteOff(60);//turn of the note


        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }
*/
    }
}