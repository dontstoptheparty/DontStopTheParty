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
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IncreaseOctaveActionTokenTest {
    @Test
    public void increaseMinOctaveOneTime() {
        final SongState songState = new SongState();
        final IncreaseOctaveActionToken increaseOctaveActionToken = new IncreaseOctaveActionToken();
        final int newOctave = SongConstants.MIN_OCTAVE + 1;

        songState.setOctave(SongConstants.MIN_OCTAVE);

        increaseOctaveActionToken.apply(songState);

        assertEquals(newOctave, songState.getOctave());
    }

    @Test
    public void increaseMinOctaveTwoTimes() {
        final SongState songState = new SongState();
        final IncreaseOctaveActionToken increaseOctaveActionToken = new IncreaseOctaveActionToken();
        final int newOctave = SongConstants.MIN_OCTAVE + 2;

        songState.setOctave(SongConstants.MIN_OCTAVE);

        increaseOctaveActionToken.apply(songState);
        increaseOctaveActionToken.apply(songState);

        assertEquals(newOctave, songState.getOctave());
    }

    @Test
    public void increaseMinOctaveThreeTimes() {
        final SongState songState = new SongState();
        final IncreaseOctaveActionToken increaseOctaveActionToken = new IncreaseOctaveActionToken();
        final int newOctave = SongConstants.MIN_OCTAVE + 3;

        songState.setOctave(SongConstants.MIN_OCTAVE);

        increaseOctaveActionToken.apply(songState);
        increaseOctaveActionToken.apply(songState);
        increaseOctaveActionToken.apply(songState);

        assertEquals(newOctave, songState.getOctave());
    }

    @Test
    public void doubleMinVolumeWhileLessThenMax() {
        final SongState songState = new SongState();
        final IncreaseOctaveActionToken increaseOctaveActionToken = new IncreaseOctaveActionToken();
        int newOctave = SongConstants.MIN_OCTAVE + 1;

        songState.setOctave(SongConstants.MIN_OCTAVE);

        while (newOctave <= SongConstants.MIN_OCTAVE) {
            increaseOctaveActionToken.apply(songState);
            assertEquals(newOctave, songState.getOctave());
            newOctave = newOctave + 1;
        }
    }

    @Test
    public void doubleMinVolumeUntilResetToDefault() {
        final SongState songState = new SongState();
        final IncreaseOctaveActionToken increaseOctaveActionToken = new IncreaseOctaveActionToken();
        int newOctave = SongConstants.MIN_OCTAVE + 1;

        songState.setOctave(SongConstants.MIN_OCTAVE);

        while (newOctave <= SongConstants.MAX_OCTAVE) {
            increaseOctaveActionToken.apply(songState);
            assertEquals(newOctave, songState.getOctave());
            newOctave = newOctave + 1;
        }

        increaseOctaveActionToken.apply(songState);
        assertEquals(SongConstants.DEFAULT_OCTAVE, songState.getOctave());
    }
}