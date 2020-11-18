/*
 * DontStopTheParty 2020.
 * Designed by Guilherme Santana, João Pedro Silveira, Renan Magagnin e Wellington M. Espindula.
 */

/*
 * DontStopTheParty 2020.
 * Designed by Guilherme Santana, João Pedro Silveira, Renan Magagnin e Wellington M. Espindula.
 */

package br.ufrgs.inf.dontstoptheparty.token.actions;

import br.ufrgs.inf.dontstoptheparty.enumerator.Instrument;
import br.ufrgs.inf.dontstoptheparty.song.SongState;
import br.ufrgs.inf.dontstoptheparty.token.Token;

/**
 * Token for Change Instrument
 */
public class ChangeInstrumentActionToken implements Token {
    private final Instrument instrument;

    public ChangeInstrumentActionToken(Instrument instrument) {
        this.instrument = instrument;
    }

    /**
     * Change the song state's instrument to {@link #instrument}
     *
     * @param songState state of the song
     */
    @Override
    public void apply(SongState songState) {
        songState.setInstrument(this.instrument.getGeneralMIDICode());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChangeInstrumentActionToken that = (ChangeInstrumentActionToken) o;
        return instrument == that.instrument;
    }
}
