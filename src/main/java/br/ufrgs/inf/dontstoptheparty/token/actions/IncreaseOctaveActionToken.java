package br.ufrgs.inf.dontstoptheparty.token.actions;

import br.ufrgs.inf.dontstoptheparty.song.SongConstants;
import br.ufrgs.inf.dontstoptheparty.song.SongState;
import br.ufrgs.inf.dontstoptheparty.token.Token;

/**
 * Token for increase the song state octave
 */
public class IncreaseOctaveActionToken implements Token {

    /**
     * Increment the song state's octave if possible.
     * Otherwise, changes it to the default value
     *
     * @param songState state of the song
     */
    @Override
    public void apply(SongState songState) {
        final int newOctave = songState.getOctave() + 1;

        if (newOctave > SongConstants.MAX_OCTAVE) {
            songState.setOctave(SongConstants.DEFAULT_OCTAVE);
        } else {
            songState.setOctave(newOctave);
        }
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof IncreaseOctaveActionToken);
    }
}
