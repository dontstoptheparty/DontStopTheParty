package br.ufrgs.inf.dontstoptheparty.player;

import br.ufrgs.inf.dontstoptheparty.enumerator.Instrument;

public class PlayerConstants {
    public static final int DEFAULT_INSTRUMENT = Instrument.ACOUSTIC_PIANO.getGeneralMIDICode();
    public static final int DEFAULT_OCTAVE = 3;
    public static final int DEFAULT_VOLUME = 63;
    public static final int MAX_VOLUME = 127;
    public static final int MIN_INSTRUMENT = 1;
    public static final int MAX_INSTRUMENT = 127;
    public static final int MAX_OCTAVE = 9;
}
