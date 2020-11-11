package br.ufrgs.inf.dontstoptheparty.token;

import br.ufrgs.inf.dontstoptheparty.song.SongState;

public interface Token {
    void apply(SongState songState);
}
