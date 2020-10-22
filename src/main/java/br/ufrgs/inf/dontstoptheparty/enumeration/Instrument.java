package br.ufrgs.inf.dontstoptheparty.enumeration;

//TODO Implementar instrumentos em ordem de General MIDI
public enum Instrument {
    PIANO(5),
    VIOLINO(3);

    private final int generalMIDICode;

    Instrument(int generalMIDICode) {
        this.generalMIDICode = generalMIDICode;
    }

    public int getGeneralMIDICode() {
        return generalMIDICode;
    }
}

