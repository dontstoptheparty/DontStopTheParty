package br.ufrgs.inf.dontstoptheparty.token.actions;

import br.ufrgs.inf.dontstoptheparty.song.SongState;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class SilenceActionTokenTest {

    final SongState songState = new SongState();

    @Test
    public void testForOneTimeSilenceApply() {
        songState.setSilence(false);
        final SilenceActionToken silenceActionToken = new SilenceActionToken();
        silenceActionToken.apply(songState);

        assertTrue(songState.isSilence());
    }

    @Test
    public void testForTwiceSilenceApply() {
        songState.setSilence(false);
        final SilenceActionToken silenceActionToken = new SilenceActionToken();
        silenceActionToken.apply(songState);
        silenceActionToken.apply(songState);

        assertTrue(songState.isSilence());
    }
}