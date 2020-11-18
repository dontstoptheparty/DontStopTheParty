/*
 * DontStopTheParty 2020.
 * Designed by Guilherme Santana, João Pedro Silveira, Renan Magagnin e Wellington M. Espindula.
 */

package br.ufrgs.inf.dontstoptheparty.token.actions;

import br.ufrgs.inf.dontstoptheparty.song.SongConstants;
import br.ufrgs.inf.dontstoptheparty.song.SongState;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DoubleVolumeActionTokenTest {
    @Test
    public void doubleMinVolumeOneTime() {
        final SongState songState = new SongState();
        final DoubleVolumeActionToken doubleVolumeActionToken = new DoubleVolumeActionToken();
        final int newVolume = SongConstants.MIN_VOLUME * 2;

        songState.setVolume(SongConstants.MIN_VOLUME);

        doubleVolumeActionToken.apply(songState);

        assertEquals(newVolume, songState.getVolume());
    }

    @Test
    public void doubleMinVolumeTwoTimes() {
        final SongState songState = new SongState();
        final DoubleVolumeActionToken doubleVolumeActionToken = new DoubleVolumeActionToken();
        final int newVolume = (int) (SongConstants.MIN_VOLUME * Math.pow(2, 2));

        songState.setVolume(SongConstants.MIN_VOLUME);

        doubleVolumeActionToken.apply(songState);
        doubleVolumeActionToken.apply(songState);

        assertEquals(newVolume, songState.getVolume());
    }

    @Test
    public void doubleMinVolumeThreeTimes() {
        final SongState songState = new SongState();
        final DoubleVolumeActionToken doubleVolumeActionToken = new DoubleVolumeActionToken();
        final int newVolume = (int) (SongConstants.MIN_VOLUME * Math.pow(2, 3));

        songState.setVolume(SongConstants.MIN_VOLUME);

        doubleVolumeActionToken.apply(songState);
        doubleVolumeActionToken.apply(songState);
        doubleVolumeActionToken.apply(songState);

        assertEquals(newVolume, songState.getVolume());
    }

    @Test
    public void doubleMinVolumeWhileLessThenMax() {
        final SongState songState = new SongState();
        final DoubleVolumeActionToken doubleVolumeActionToken = new DoubleVolumeActionToken();
        int newVolume = SongConstants.MIN_VOLUME * 2;
        songState.setVolume(SongConstants.MIN_VOLUME);

        while (newVolume <= SongConstants.MAX_VOLUME) {
            doubleVolumeActionToken.apply(songState);
            assertEquals(newVolume, songState.getVolume());
            newVolume = newVolume * 2;
        }
    }

    @Test
    public void doubleMinVolumeUntilResetToDefault() {
        final SongState songState = new SongState();
        final DoubleVolumeActionToken doubleVolumeActionToken = new DoubleVolumeActionToken();
        int volume = SongConstants.MIN_VOLUME * 2;
        songState.setVolume(SongConstants.MIN_VOLUME);

        while (volume <= SongConstants.MAX_VOLUME) {
            doubleVolumeActionToken.apply(songState);
            assertEquals(volume, songState.getVolume());
            volume = volume * 2;
        }

        doubleVolumeActionToken.apply(songState);
        assertEquals(SongConstants.DEFAULT_VOLUME, songState.getVolume());
    }
}