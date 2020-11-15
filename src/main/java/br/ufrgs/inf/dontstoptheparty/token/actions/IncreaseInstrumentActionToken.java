package br.ufrgs.inf.dontstoptheparty.token.actions;

import br.ufrgs.inf.dontstoptheparty.song.SongConstants;
import br.ufrgs.inf.dontstoptheparty.song.SongState;
import br.ufrgs.inf.dontstoptheparty.token.Token;


/**
 * Token for increase the song state volume
 */
public class IncreaseInstrumentActionToken implements Token {
    private final int value;

    public IncreaseInstrumentActionToken(int value) {
        this.value = value;
    }

    /**
     * Change the song state's volume to {@link #value}.
     *
     * @param songState state of the song
     */
    @Override
    public void apply(SongState songState) {
        final int newInstrument = songState.getInstrument() + value;

        if (newInstrument < SongConstants.MIN_INSTRUMENT) {
            songState.setInstrument(SongConstants.DEFAULT_INSTRUMENT);
        } else if (newInstrument > SongConstants.MAX_INSTRUMENT) {
            final int newInstrumentAdjusted = newInstrument % SongConstants.MAX_INSTRUMENT;
            songState.setInstrument(newInstrumentAdjusted);
        } else {
            songState.setInstrument(newInstrument);
        }
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof IncreaseInstrumentActionToken);
    }
}
