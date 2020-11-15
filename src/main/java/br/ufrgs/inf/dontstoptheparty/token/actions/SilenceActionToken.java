package br.ufrgs.inf.dontstoptheparty.token.actions;

import br.ufrgs.inf.dontstoptheparty.song.SongState;
import br.ufrgs.inf.dontstoptheparty.token.Token;

/**
 * Token for play a silence
 */
public class SilenceActionToken implements Token {

    /**
     * Change the song state's silence to true
     *
     * @param songState state of the song
     */
    @Override
    public void apply(SongState songState) {
        songState.setSilence(true);
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof SilenceActionToken);
    }
}
