package br.ufrgs.inf.dontstoptheparty.song;

import br.ufrgs.inf.dontstoptheparty.enumerator.Note;

public class SongState {
    private Note note;
    private int instrument;
    private int volume;
    private int octave;
    private boolean silence;

    public SongState() {
        this.resetToDefault();
    }

    public void resetToDefault() {
        this.note = null;
        this.instrument = SongConstants.DEFAULT_INSTRUMENT;
        this.volume = SongConstants.DEFAULT_VOLUME;
        this.octave = SongConstants.DEFAULT_OCTAVE;
        this.silence = false;
    }

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }

    public int getInstrument() {
        return instrument;
    }

    public void setInstrument(int instrument) {
        this.instrument = instrument;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getOctave() {
        return octave;
    }

    public void setOctave(int octave) {
        this.octave = octave;
    }

    public boolean isSilence() {
        return silence;
    }

    public void setSilence(boolean silence) {
        this.silence = silence;
    }
}
