package br.ufrgs.inf.dontstoptheparty.player;

import br.ufrgs.inf.dontstoptheparty.token.Token;

public abstract class Player {
    protected final PlayerState playerState;

    public Player() {
        this.playerState = new PlayerState();
    }

    public abstract void play(Token token);

    public void reset() {
        this.playerState.resetToDefault();
    }
}
