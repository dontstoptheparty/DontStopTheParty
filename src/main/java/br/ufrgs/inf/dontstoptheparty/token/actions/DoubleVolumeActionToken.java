/*
 * DontStopTheParty 2020.
 * Designed by Guilherme Santana, João Pedro Silveira, Renan Magagnin e Wellington M. Espindula.
 */

/*
 * DontStopTheParty 2020.
 * Designed by Guilherme Santana, João Pedro Silveira, Renan Magagnin e Wellington M. Espindula.
 */

package br.ufrgs.inf.dontstoptheparty.token.actions;

import br.ufrgs.inf.dontstoptheparty.song.SongConstants;
import br.ufrgs.inf.dontstoptheparty.song.SongState;
import br.ufrgs.inf.dontstoptheparty.token.Token;

/**
 * Token for duplicate the song state volume
 */
public class DoubleVolumeActionToken implements Token {

    /**
     * Change the song state's volume to its double if its possible.
     * Otherwise, sets it to the default value
     *
     * @param songState state of the song
     */
    @Override
    public void apply(SongState songState) {
        final int newVolume = songState.getVolume() * 2;

        if (newVolume > SongConstants.MAX_VOLUME) {
            songState.setVolume(SongConstants.DEFAULT_VOLUME);
        } else {
            songState.setVolume(newVolume);
        }
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof DoubleVolumeActionToken);
    }
}
