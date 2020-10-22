package br.ufrgs.inf.dontstoptheparty.player;

import br.ufrgs.inf.dontstoptheparty.enumeration.Note;

public class PlayerState {
    private Note note;
    private int instrument;
    private int volume;
    private int octave;
    private int bpm;
    private int position;

    public PlayerState() {
        this.resetToDefault();
    }

    public void resetToDefault() {
        this.note = null;
        this.instrument = PlayerConstants.DEFAULT_INSTRUMENT;
        this.volume = PlayerConstants.DEFAULT_VOLUME;
        this.bpm = PlayerConstants.DEFAULT_BPM;
        this.octave = PlayerConstants.DEFAULT_OCTAVE;
        this.position = 0;
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
        // TODO VALIDAR O NUMERO (1-128)
        this.instrument = instrument;
    }

    public void incrementInstrument(int instrument) {
        // TODO VALIDAR SE FOR MAIOR QUE 128, VOLTA PRO VALOR DEFAULT
        setInstrument(getInstrument()+instrument);
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getBpm() {
        return bpm;
    }

    public void setBpm(int bpm) {
        this.bpm = bpm;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getOctave() {
        return octave;
    }

    public void setOctave(int octave) {
        // TODO VALIDAR OITAVA
        this.octave = octave;
    }

    public void incrementOctave(int octave){
        // TODO VALIDAR SE FOR MAIOR QUE A OITAVA SUPORTADA, VOLTA PRO VALOR DEFAULT
        setOctave(getOctave()+octave);
    }
}
