package br.ufrgs.inf.dontstoptheparty.song.player;

import br.ufrgs.inf.dontstoptheparty.token.Token;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

public class JavaSoundPlayer extends Player {
    private static final int DEFAULT_NOTE_DURATION_IN_MILLISECONDS = 800;

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
        token.apply(this.songState);

        if (songState.isSilence()) {
            playSilence();
            songState.setSilence(false);
        } else if (songState.getNote() != null) {
            playNote();
            cleanNoteAfterPlay();
        }
    }

    private void playNote() {
        int key = getKeyFromPlayerState();
        int volume = getVolumeFromPlayerState();
        long noteDurationInMillis = getNoteDurationInMillis();
        changeInstrument();

        mChannel.noteOn(key, volume);
        rest(noteDurationInMillis);
        mChannel.noteOff(key);
    }

    private void changeInstrument() {
        int instrument = songState.getInstrument();
        mChannel.programChange(instrument);
    }

    private int getKeyFromPlayerState() {
        int fundamentalNote = songState.getNote().getMidiFundamentalValue();
        int octave = songState.getOctave();

        return fundamentalNote + (12 * octave);
    }

    private int getVolumeFromPlayerState() {
        return songState.getVolume();
    }

    private long getNoteDurationInMillis() {
        return calculateNoteDurationMillisecond(songState.getBpm());
    }

    private void playSilence() {
        rest();
    }

    private void cleanNoteAfterPlay() {
        this.songState.setNote(null);
    }

    private void rest() {
        rest(DEFAULT_NOTE_DURATION_IN_MILLISECONDS);
    }

    private void rest(long timeInMillis) {
        try {
            Thread.sleep(timeInMillis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Given a frequency in BPM (beats per minute) calculate note duration in milliseconds
     *
     * @param bpm Frequency in BPM
     * @return noteDuration
     */
    private long calculateNoteDurationMillisecond(int bpm) {
        return (60 * 1000) / bpm;
    }
}
