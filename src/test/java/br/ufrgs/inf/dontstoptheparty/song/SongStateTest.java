/*
 * DontStopTheParty 2020.
 * Designed by Guilherme Santana, João Pedro Silveira, Renan Magagnin e Wellington M. Espindula.
 */

/*
 * DontStopTheParty 2020.
 * Designed by Guilherme Santana, João Pedro Silveira, Renan Magagnin e Wellington M. Espindula.
 */

package br.ufrgs.inf.dontstoptheparty.song;

import br.ufrgs.inf.dontstoptheparty.enumerator.Note;
import org.junit.Test;

import java.security.InvalidParameterException;

import static org.junit.Assert.*;

public class SongStateTest {

    final SongState songState = new SongState();

    @Test
    public void testForDefaultInstrumentSet() {
        final int instrument = 5;
        songState.setInstrument(instrument);
        assertEquals(instrument, songState.getInstrument());
    }

    @Test
    public void testForLimitInstrumentSet() {
        final int instrumentMinLimit = 1;
        songState.setInstrument(instrumentMinLimit);
        assertEquals(instrumentMinLimit, songState.getInstrument());

        final int instrumentMaxLimit = 127;
        songState.setInstrument(instrumentMaxLimit);
        assertEquals(instrumentMaxLimit, songState.getInstrument());
    }

    @Test(expected = InvalidParameterException.class)
    public void testForInvalidInstrumentSet() {
        final int instrumentBelowLimit = -1;
        songState.setInstrument(instrumentBelowLimit);
        assertNotEquals(instrumentBelowLimit, songState.getInstrument());

        final int instrumentAboveLimit = 188;
        songState.setInstrument(instrumentAboveLimit);
        assertNotEquals(instrumentAboveLimit, songState.getInstrument());
    }

    @Test(expected = InvalidParameterException.class)
    public void testForZeroInstrumentSet() {
        final int instrumentZero = 0;
        songState.setInstrument(instrumentZero);
        assertNotEquals(instrumentZero, songState.getInstrument());
    }

    @Test
    public void testForDefaultVolumeSet() {
        final int volume = 5;
        songState.setVolume(volume);
        assertEquals(volume, songState.getVolume());
    }

    @Test
    public void testForLimitVolumeSet() {
        final int volumeMinLimit = 1;
        songState.setVolume(volumeMinLimit);
        assertEquals(volumeMinLimit, songState.getVolume());

        final int volumeMaxLimit = 127;
        songState.setVolume(volumeMaxLimit);
        assertEquals(volumeMaxLimit, songState.getVolume());
    }

    @Test(expected = InvalidParameterException.class)
    public void testForInvalidVolumeSet() {
        final int volumeBelowLimit = -1;
        songState.setVolume(volumeBelowLimit);
        assertNotEquals(volumeBelowLimit, songState.getVolume());

        final int volumeAboveLimit = 188;
        songState.setVolume(volumeAboveLimit);
        assertNotEquals(volumeAboveLimit, songState.getVolume());
    }

    @Test(expected = InvalidParameterException.class)
    public void testForZeroVolumeSet() {
        final int volumeZero = 0;
        songState.setVolume(volumeZero);
        assertNotEquals(volumeZero, songState.getVolume());
    }


    @Test
    public void testForDefaultOctaveSet() {
        final int octave = 5;
        songState.setOctave(octave);
        assertEquals(octave, songState.getOctave());
    }

    @Test
    public void testForLimitOctaveSet() {
        final int octaveMinLimit = 1;
        songState.setOctave(octaveMinLimit);
        assertEquals(octaveMinLimit, songState.getOctave());

        final int octaveMaxLimit = 9;
        songState.setOctave(octaveMaxLimit);
        assertEquals(octaveMaxLimit, songState.getOctave());
    }

    @Test(expected = InvalidParameterException.class)
    public void testForInvalidOctaveSet() {
        final int octaveBelowLimit = -1;
        songState.setOctave(octaveBelowLimit);
        assertNotEquals(octaveBelowLimit, songState.getOctave());

        final int octaveAboveLimit = 12;
        songState.setOctave(octaveAboveLimit);
        assertNotEquals(octaveAboveLimit, songState.getOctave());
    }

    @Test(expected = InvalidParameterException.class)
    public void testForZeroOctaveSet() {
        final int volumeZero = 0;
        songState.setOctave(volumeZero);
        assertNotEquals(volumeZero, songState.getOctave());
    }

    @Test
    public void testForSilenceSet() {
        songState.setSilence(true);
        assertTrue(songState.isSilence());

        songState.setSilence(false);
        assertFalse(songState.isSilence());
    }

    @Test
    public void testForDefaultBpmSet() {
        int defaultBpm = 120;
        songState.setBpm(defaultBpm);
        assertEquals(defaultBpm, songState.getBpm());

        defaultBpm = 150;
        songState.setBpm(defaultBpm);
        assertEquals(defaultBpm, songState.getBpm());
    }

    @Test(expected = InvalidParameterException.class)
    public void testForInvalidBpmSet() {
        int defaultBpm = -1;
        songState.setBpm(defaultBpm);
        assertNotEquals(defaultBpm, songState.getBpm());

        defaultBpm = 0;
        songState.setBpm(defaultBpm);
        assertNotEquals(defaultBpm, songState.getBpm());
    }

    @Test
    public void testForLimitBpmSet() {
        final int limitBpm = 1;
        songState.setBpm(limitBpm);
        assertEquals(limitBpm, songState.getBpm());
    }

    @Test
    public void testForNoteSet() {
        Note defaultNote = Note.MI;
        songState.setNote(defaultNote);
        assertEquals(defaultNote, songState.getNote());

        Note newNote = Note.SOL;
        songState.setNote(newNote);
        assertEquals(newNote, songState.getNote());
    }

    @Test
    public void testForResetValues() {
        songState.resetToDefault();
        assertNull(songState.getNote());
        assertEquals(SongConstants.DEFAULT_INSTRUMENT, songState.getInstrument());
        assertEquals(SongConstants.DEFAULT_VOLUME, songState.getVolume());
        assertEquals(SongConstants.DEFAULT_OCTAVE, songState.getOctave());
        assertEquals(SongConstants.DEFAULT_BPM, songState.getBpm());
        assertFalse(songState.isSilence());
    }


}