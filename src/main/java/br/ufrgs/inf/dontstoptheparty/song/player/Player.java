package br.ufrgs.inf.dontstoptheparty.song.player;

import br.ufrgs.inf.dontstoptheparty.song.SongState;
import br.ufrgs.inf.dontstoptheparty.token.Token;

public abstract class Player {
    protected final SongState songState;

    public Player() {
        this.songState = new SongState();
    }

    public abstract void play(Token token);

    public void reset() {
        this.songState.resetToDefault();
    }
}
