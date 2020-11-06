package br.ufrgs.inf.dontstoptheparty.token.actions;

import br.ufrgs.inf.dontstoptheparty.player.PlayerConstants;
import br.ufrgs.inf.dontstoptheparty.player.PlayerState;
import br.ufrgs.inf.dontstoptheparty.token.Token;

public class IncreaseOctaveActionToken implements Token {
    @Override
    public void apply(PlayerState playerState) {
        final int newOctave = playerState.getOctave() + 1;

        if (newOctave > PlayerConstants.MAX_OCTAVE) {
            playerState.setOctave(PlayerConstants.DEFAULT_OCTAVE);
        } else {
            playerState.setOctave(newOctave);
        }
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof IncreaseOctaveActionToken);
    }
}
