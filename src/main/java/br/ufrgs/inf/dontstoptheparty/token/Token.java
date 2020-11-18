/*
 * DontStopTheParty 2020.
 * Designed by Guilherme Santana, João Pedro Silveira, Renan Magagnin e Wellington M. Espindula.
 */

package br.ufrgs.inf.dontstoptheparty.token;

import br.ufrgs.inf.dontstoptheparty.song.SongState;

/**
 * Abstract internal representation of Notes and Actions
 * Token apply changes in a {@link br.ufrgs.inf.dontstoptheparty.song.SongState} given its action or note change
 */
public interface Token {

    /**
     * Given the SongState, applies changes
     *
     * @param songState state of the song
     */
    void apply(SongState songState);
}
