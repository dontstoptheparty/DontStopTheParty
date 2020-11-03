package br.ufrgs.inf.dontstoptheparty.player;

import br.ufrgs.inf.dontstoptheparty.token.Token;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;
import java.util.List;

public class JavaSoundPlayer extends Player {
    private static final int PLAY_NOTE_DURATION_IN_MILLISECONDS = 800;

    private final Synthesizer midiSynth;
    private final MidiChannel mChannel;

    public JavaSoundPlayer() throws MidiUnavailableException {
        super();
        this.midiSynth = MidiSystem.getSynthesizer();
        this.midiSynth.loadAllInstruments(midiSynth.getDefaultSoundbank());

        midiSynth.open();
        this.mChannel = midiSynth.getChannels()[0];
    }

    @Override
    public void play(Token token) {
        token.apply(this.playerState);

        if (playerState.isSilence()) {
            playSilence();
            playerState.setSilence(false);
        } else if (playerState.getNote() != null) {
            playNote();
            cleanNoteAfterPlay();
        }
    }

    private void playNote() {
        int key = getKeyFromPlayerState();
        int volume = getVolumeFromPlayerState();
        changeInstrument();

        mChannel.noteOn(key, volume);
        rest();
        mChannel.noteOff(key);
    }

    private void changeInstrument() {
        int instrument = playerState.getInstrument();
        mChannel.programChange(instrument);
    }

    private int getKeyFromPlayerState() {
        int fundamentalNote = playerState.getNote().getMidiFundamentalValue();
        int octave = playerState.getOctave();

        return fundamentalNote + (12 * octave);
    }

    private int getVolumeFromPlayerState() {
        return playerState.getVolume();
    }

    private void playSilence() {
        rest();
    }

    private void cleanNoteAfterPlay() {
        this.playerState.setNote(null);
    }


    private void rest() {
        try {
            Thread.sleep(PLAY_NOTE_DURATION_IN_MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(List<Token> tokens) {
        // TODO IMPROVE HOW SAVING IS WORKING
    }
}
