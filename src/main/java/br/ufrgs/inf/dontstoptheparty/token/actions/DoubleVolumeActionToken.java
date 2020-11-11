package br.ufrgs.inf.dontstoptheparty.token.actions;

import br.ufrgs.inf.dontstoptheparty.song.SongConstants;
import br.ufrgs.inf.dontstoptheparty.song.SongState;
import br.ufrgs.inf.dontstoptheparty.token.Token;

public class DoubleVolumeActionToken implements Token {
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
