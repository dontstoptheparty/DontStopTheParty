package br.ufrgs.inf.dontstoptheparty.player;

import br.ufrgs.inf.dontstoptheparty.token.Token;

import javax.sound.midi.*;
import java.util.List;

public class JavaSoundPlayer extends Player {
    private static final int PLAY_NOTE_DURATION_IN_MILLISECONDS = 800;
    private final Synthesizer midiSynth;
    private final MidiChannel mChannel;
    private final javax.sound.midi.Instrument[] instr;

    public JavaSoundPlayer() throws MidiUnavailableException {
        super();
        this.midiSynth = MidiSystem.getSynthesizer();
        this.instr = midiSynth.getDefaultSoundbank().getInstruments();

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
        }
    }

    private void playNote() {
        int key = getKeyFromPlayerState();
        int volume = getVolumeFromPlayerState();
        Instrument instrument = getInstrumentFromPlayerState();

        midiSynth.loadInstrument(instrument);

        mChannel.noteOn(key, volume);
        rest();
        mChannel.noteOff(key);
    }

    private void playSilence() {
        rest();
    }

    private Instrument getInstrumentFromPlayerState() {
        return instr[playerState.getInstrument() - 1];
    }

    private void rest() {
        try {
            Thread.sleep(PLAY_NOTE_DURATION_IN_MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private int getKeyFromPlayerState() {
        int fundamentalNote = playerState.getNote().getMidiFundamentalValue();
        int octave = playerState.getOctave();

        return fundamentalNote + (12 * octave);
    }

    private int getVolumeFromPlayerState() {
        return playerState.getVolume();
    }

    @Override
    public void save(List<Token> tokens) {
        // TODO IMPROVE HOW SAVING IS WORKING
    }
}
