package br.ufrgs.inf.dontstoptheparty.token.actions;

import br.ufrgs.inf.dontstoptheparty.enumerator.Instrument;
import br.ufrgs.inf.dontstoptheparty.player.PlayerState;
import br.ufrgs.inf.dontstoptheparty.token.Token;

public class ChangeInstrumentActionToken implements Token {
    private final Instrument instrument;

    public ChangeInstrumentActionToken(Instrument instrument) {
        this.instrument = instrument;
    }

    @Override
    public void apply(PlayerState playerState) {
        playerState.setInstrument(this.instrument.getGeneralMIDICode());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChangeInstrumentActionToken that = (ChangeInstrumentActionToken) o;
        return instrument == that.instrument;
    }
}
