package br.ufrgs.inf.dontstoptheparty.token.actions;

import br.ufrgs.inf.dontstoptheparty.player.PlayerConstants;
import br.ufrgs.inf.dontstoptheparty.player.PlayerState;
import br.ufrgs.inf.dontstoptheparty.token.Token;

public class IncreaseInstrumentActionToken implements Token {
    private final int value;

    public IncreaseInstrumentActionToken(int value) {
        this.value = value;
    }

    @Override
    public void apply(PlayerState playerState) {
        final int newInstrument = playerState.getInstrument() + value;

        if (newInstrument < PlayerConstants.MIN_INSTRUMENT) {
            playerState.setInstrument(PlayerConstants.DEFAULT_INSTRUMENT);
        } else if (newInstrument > PlayerConstants.MAX_INSTRUMENT) {
            final int newInstrumentAdjusted = newInstrument % PlayerConstants.MAX_INSTRUMENT;
            playerState.setInstrument(newInstrumentAdjusted);
        } else {
            playerState.setInstrument(newInstrument);
        }
    }
}
