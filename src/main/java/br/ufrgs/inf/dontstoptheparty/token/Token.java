package br.ufrgs.inf.dontstoptheparty.token;

import br.ufrgs.inf.dontstoptheparty.player.PlayerState;

public interface Token {
    void apply(PlayerState playerState);
}
