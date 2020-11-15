package br.ufrgs.inf.dontstoptheparty.song;

import br.ufrgs.inf.dontstoptheparty.enumerator.Note;

import java.security.InvalidParameterException;

public class SongState {
    private Note note;
    private int instrument;
    private int volume;
    private int octave;
    private int bpm;
    private boolean silence;

    public SongState() {
        this.resetToDefault();
    }

    public void resetToDefault() {
        this.note = null;
        this.instrument = SongConstants.DEFAULT_INSTRUMENT;
        this.volume = SongConstants.DEFAULT_VOLUME;
        this.octave = SongConstants.DEFAULT_OCTAVE;
        this.bpm = SongConstants.DEFAULT_BPM;
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
        if ((instrument < SongConstants.MIN_INSTRUMENT) || (instrument > SongConstants.MAX_INSTRUMENT)) {
            throw new InvalidParameterException("Instrument " + instrument + " is out of the bound [1-127]");
        }
        this.instrument = instrument;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        if ((volume < SongConstants.MIN_VOLUME) || (volume > SongConstants.MAX_VOLUME)) {
            throw new InvalidParameterException("Volume " + volume + " is out of the bound [1-127]");
        }
        this.volume = volume;
    }

    public int getOctave() {
        return octave;
    }

    public void setOctave(int octave) {
        if ((octave < SongConstants.MIN_OCTAVE) || (octave > SongConstants.MAX_OCTAVE)) {
            throw new InvalidParameterException("Octave " + octave + " is out of the bound [1-9]");
        }
        this.octave = octave;
    }

    public boolean isSilence() {
        return silence;
    }

    public void setSilence(boolean silence) {
        this.silence = silence;
    }

    public int getBpm() {
        return bpm;
    }

    public void setBpm(int bpm) {
        if ((bpm < 0) || (bpm == 0)) {
            throw new InvalidParameterException("BPM " + bpm + " cant be less or equals to zero");
        }
        this.bpm = bpm;
    }
}
