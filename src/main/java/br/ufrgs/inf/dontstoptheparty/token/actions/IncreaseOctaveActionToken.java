package br.ufrgs.inf.dontstoptheparty.token.actions;

import br.ufrgs.inf.dontstoptheparty.song.SongConstants;
import br.ufrgs.inf.dontstoptheparty.song.SongState;
import br.ufrgs.inf.dontstoptheparty.token.Token;

public class IncreaseOctaveActionToken implements Token {
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
