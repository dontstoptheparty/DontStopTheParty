package br.ufrgs.inf.dontstoptheparty.token.actions;

import br.ufrgs.inf.dontstoptheparty.player.PlayerConstants;
import br.ufrgs.inf.dontstoptheparty.player.PlayerState;
import br.ufrgs.inf.dontstoptheparty.token.Token;

public class DoubleVolumeActionToken implements Token {
    @Override
    public void apply(PlayerState playerState) {
        final int newVolume = playerState.getVolume() * 2;

        if (newVolume > PlayerConstants.MAX_VOLUME) {
            playerState.setVolume(PlayerConstants.DEFAULT_VOLUME);
        } else {
            playerState.setVolume(newVolume);
        }
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof DoubleVolumeActionToken);
    }
}
