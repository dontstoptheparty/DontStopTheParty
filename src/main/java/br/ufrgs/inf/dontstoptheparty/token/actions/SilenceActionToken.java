package br.ufrgs.inf.dontstoptheparty.token.actions;

import br.ufrgs.inf.dontstoptheparty.player.PlayerState;
import br.ufrgs.inf.dontstoptheparty.token.Token;

public class SilenceActionToken implements Token {
    @Override
    public void apply(PlayerState playerState) {
        playerState.setSilence(true);
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof SilenceActionToken);
    }
}
