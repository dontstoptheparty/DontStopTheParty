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
        playerState.setInstrument(this.instrument.getValue());
    }
}
