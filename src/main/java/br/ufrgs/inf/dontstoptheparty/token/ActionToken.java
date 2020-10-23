package br.ufrgs.inf.dontstoptheparty.token;

import br.ufrgs.inf.dontstoptheparty.player.PlayerState;

public abstract class ActionToken implements Token {

    @Override
    public void execute(PlayerState playerState) {
        runAction(playerState);
    }

    protected abstract void runAction(PlayerState playerState);
}
