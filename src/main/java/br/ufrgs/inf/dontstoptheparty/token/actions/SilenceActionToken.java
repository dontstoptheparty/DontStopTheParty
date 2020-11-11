package br.ufrgs.inf.dontstoptheparty.token.actions;

import br.ufrgs.inf.dontstoptheparty.song.SongState;
import br.ufrgs.inf.dontstoptheparty.token.Token;

public class SilenceActionToken implements Token {
    @Override
    public void apply(SongState songState) {
        songState.setSilence(true);
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof SilenceActionToken);
    }
}
