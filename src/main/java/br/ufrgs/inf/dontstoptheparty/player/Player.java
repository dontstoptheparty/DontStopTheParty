package br.ufrgs.inf.dontstoptheparty.player;

import br.ufrgs.inf.dontstoptheparty.token.ActionToken;
import br.ufrgs.inf.dontstoptheparty.token.NoteToken;
import br.ufrgs.inf.dontstoptheparty.token.Token;

import java.util.List;

public abstract class Player {
    protected final PlayerState playerState;

    public Player() {
        this.playerState = new PlayerState();
    }

    public void play(Token token) {
        if (token instanceof NoteToken) {
            play((NoteToken) token);
        } else if (token instanceof ActionToken) {
            play((ActionToken) token);
        }
    }

    protected abstract void play(NoteToken noteToken);

    protected abstract void play(ActionToken actionToken);

    public abstract void save(List<Token> tokens);

    public void reset() {
        this.playerState.resetToDefault();
    }
}
