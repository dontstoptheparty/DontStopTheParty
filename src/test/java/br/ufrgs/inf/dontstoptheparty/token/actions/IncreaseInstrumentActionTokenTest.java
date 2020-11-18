/*
 * DontStopTheParty 2020.
 * Designed by Guilherme Santana, João Pedro Silveira, Renan Magagnin e Wellington M. Espindula.
 */

package br.ufrgs.inf.dontstoptheparty.token.actions;

import br.ufrgs.inf.dontstoptheparty.song.SongConstants;
import br.ufrgs.inf.dontstoptheparty.song.SongState;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IncreaseInstrumentActionTokenTest {
    @Test
    public void incrementOneOnMinInstrument() {
        final SongState songState = new SongState();
        final IncreaseInstrumentActionToken increaseInstrumentActionToken = new IncreaseInstrumentActionToken(1);
        final int newInstrument = SongConstants.MIN_INSTRUMENT + 1;

        songState.setInstrument(SongConstants.MIN_INSTRUMENT);

        increaseInstrumentActionToken.apply(songState);

        assertEquals(newInstrument, songState.getInstrument());
    }

    @Test
    public void incrementTwoOnMinInstrument() {
        final SongState songState = new SongState();
        final IncreaseInstrumentActionToken increaseInstrumentActionToken = new IncreaseInstrumentActionToken(2);
        final int newInstrument = SongConstants.MIN_INSTRUMENT + 2;

        songState.setInstrument(SongConstants.MIN_INSTRUMENT);

        increaseInstrumentActionToken.apply(songState);

        assertEquals(newInstrument, songState.getInstrument());
    }

    @Test
    public void incrementThreeOnMinInstrument() {
        final SongState songState = new SongState();
        final IncreaseInstrumentActionToken increaseInstrumentActionToken = new IncreaseInstrumentActionToken(3);
        final int newInstrument = SongConstants.MIN_INSTRUMENT + 3;

        songState.setInstrument(SongConstants.MIN_INSTRUMENT);

        increaseInstrumentActionToken.apply(songState);

        assertEquals(newInstrument, songState.getInstrument());
    }

    @Test
    public void incrementOneOnMinInstrumentTwoTimes() {
        final SongState songState = new SongState();
        final IncreaseInstrumentActionToken increaseInstrumentActionToken = new IncreaseInstrumentActionToken(1);
        final int newInstrument = SongConstants.MIN_INSTRUMENT + 2;

        songState.setInstrument(SongConstants.MIN_INSTRUMENT);

        increaseInstrumentActionToken.apply(songState);
        increaseInstrumentActionToken.apply(songState);

        assertEquals(newInstrument, songState.getInstrument());
    }

    @Test
    public void increaseOneOnMinInstrumentWhileLessThenMax() {
        final SongState songState = new SongState();
        final IncreaseInstrumentActionToken increaseInstrumentActionToken = new IncreaseInstrumentActionToken(1);
        int newInstrument = SongConstants.MIN_INSTRUMENT + 1;

        songState.setInstrument(SongConstants.MIN_INSTRUMENT);

        while (newInstrument <= SongConstants.MAX_INSTRUMENT) {
            increaseInstrumentActionToken.apply(songState);
            assertEquals(newInstrument, songState.getInstrument());
            newInstrument++;
        }
    }

    @Test
    public void increaseFourOnMinInstrumentWhileLessThenMax() {
        final SongState songState = new SongState();
        final IncreaseInstrumentActionToken increaseInstrumentActionToken = new IncreaseInstrumentActionToken(4);
        int newInstrument = SongConstants.MIN_INSTRUMENT + 4;

        songState.setInstrument(SongConstants.MIN_INSTRUMENT);

        while (newInstrument <= SongConstants.MAX_INSTRUMENT) {
            increaseInstrumentActionToken.apply(songState);
            assertEquals(newInstrument, songState.getInstrument());
            newInstrument = newInstrument + 4;
        }
    }

    @Test
    public void increaseOneOnMinInstrumentUntilResetToDefault() {
        final SongState songState = new SongState();
        final IncreaseInstrumentActionToken increaseInstrumentActionToken = new IncreaseInstrumentActionToken(1);
        int newInstrument = SongConstants.MIN_INSTRUMENT + 1;

        songState.setInstrument(SongConstants.MIN_INSTRUMENT);

        while (newInstrument <= SongConstants.MAX_INSTRUMENT) {
            increaseInstrumentActionToken.apply(songState);
            assertEquals(newInstrument, songState.getInstrument());
            newInstrument++;
        }

        final int newInstrumentAdjusted = newInstrument % SongConstants.MAX_INSTRUMENT;
        increaseInstrumentActionToken.apply(songState);

        assertEquals(newInstrumentAdjusted, songState.getInstrument());
    }

    @Test
    public void increaseFourOnMinInstrumentUntilResetToDefault() {
        final SongState songState = new SongState();
        final IncreaseInstrumentActionToken increaseInstrumentActionToken = new IncreaseInstrumentActionToken(4);
        int newInstrument = SongConstants.MIN_INSTRUMENT + 4;

        songState.setInstrument(SongConstants.MIN_INSTRUMENT);

        while (newInstrument <= SongConstants.MAX_INSTRUMENT) {
            increaseInstrumentActionToken.apply(songState);
            assertEquals(newInstrument, songState.getInstrument());
            newInstrument = newInstrument + 4;
        }

        final int newInstrumentAdjusted = newInstrument % SongConstants.MAX_INSTRUMENT;
        increaseInstrumentActionToken.apply(songState);

        assertEquals(newInstrumentAdjusted, songState.getInstrument());
    }

    @Test
    public void increaseNegativeInstrumentGreaterThenCurrentInstrument() {
        final SongState songState = new SongState();
        final IncreaseInstrumentActionToken increaseInstrumentActionToken = new IncreaseInstrumentActionToken(SongConstants.MIN_INSTRUMENT * 2 * -1);
        final int newInstrument = SongConstants.DEFAULT_INSTRUMENT;

        songState.setInstrument(SongConstants.MIN_INSTRUMENT);
        increaseInstrumentActionToken.apply(songState);
        assertEquals(newInstrument, songState.getInstrument());
    }

}