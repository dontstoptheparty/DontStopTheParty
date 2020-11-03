package br.ufrgs.inf.dontstoptheparty.enumerator;

public enum Instrument {
    ACOUSTIC_PIANO(1),
    HARPSICHORD(7),
    TUBULAR_BELLS(15),
    PAN_FLUTE(76),
    CHURCH_ORGAN(20),
    AGOGO(114);

    private final int generalMIDICode;

    Instrument(int value) {
        this.generalMIDICode = value;
    }

    public int getGeneralMIDICode() {
        return generalMIDICode;
    }
}

