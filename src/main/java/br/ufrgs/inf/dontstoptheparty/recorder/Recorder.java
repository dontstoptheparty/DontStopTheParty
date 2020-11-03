package br.ufrgs.inf.dontstoptheparty.recorder;

import br.ufrgs.inf.dontstoptheparty.player.PlayerState;
import br.ufrgs.inf.dontstoptheparty.token.Token;

import java.util.List;

public abstract class Recorder {
    protected final PlayerState playerState;

    public Recorder() {
        this.playerState = new PlayerState();
    }

    public abstract void record(List<Token> tokens);

}
