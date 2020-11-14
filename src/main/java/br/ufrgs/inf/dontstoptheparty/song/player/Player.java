package br.ufrgs.inf.dontstoptheparty.song.player;

import br.ufrgs.inf.dontstoptheparty.song.SongState;
import br.ufrgs.inf.dontstoptheparty.token.Token;

public abstract class Player {
    protected final SongState songState;

    public Player() {
        this.songState = new SongState();
    }

    /**
     * Play a {@link br.ufrgs.inf.dontstoptheparty.token.Token}
     *
     * @param token entry token
     */
    public abstract void play(Token token);

    /**
     * Reset player
     */
    public void reset() {
        this.songState.resetToDefault();
    }
}
