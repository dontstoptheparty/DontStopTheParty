package br.ufrgs.inf.dontstoptheparty.player;

import br.ufrgs.inf.dontstoptheparty.token.Token;
import org.jfugue.pattern.Pattern;

import java.util.List;

public abstract class Player {
    protected final PlayerState playerState;

    public Player() {
        this.playerState = new PlayerState();
    }

    public abstract void play(Token token);

    public abstract Pattern save(List<Token> tokens);

    public void reset() {
        this.playerState.resetToDefault();
    }
}
