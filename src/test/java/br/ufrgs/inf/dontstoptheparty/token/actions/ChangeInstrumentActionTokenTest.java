package br.ufrgs.inf.dontstoptheparty.token.actions;

import br.ufrgs.inf.dontstoptheparty.enumerator.Instrument;
import br.ufrgs.inf.dontstoptheparty.song.SongState;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ChangeInstrumentActionTokenTest {
    @Test
    public void changeInstrumentToAgogo() {
        final SongState songState = new SongState();
        final Instrument instrument = Instrument.AGOGO;
        final ChangeInstrumentActionToken changeInstrumentActionToken = new ChangeInstrumentActionToken(instrument);

        changeInstrumentActionToken.apply(songState);

        assertEquals(instrument.getGeneralMIDICode(), songState.getInstrument());
    }

    @Test
    public void changeInstrumentToAcousticPiano() {
        final SongState songState = new SongState();
        final Instrument instrument = Instrument.ACOUSTIC_PIANO;
        final ChangeInstrumentActionToken changeInstrumentActionToken = new ChangeInstrumentActionToken(instrument);

        changeInstrumentActionToken.apply(songState);

        assertEquals(instrument.getGeneralMIDICode(), songState.getInstrument());
    }

    @Test
    public void changeInstrumentToChurchOrgan() {
        final SongState songState = new SongState();
        final Instrument instrument = Instrument.CHURCH_ORGAN;
        final ChangeInstrumentActionToken changeInstrumentActionToken = new ChangeInstrumentActionToken(instrument);

        changeInstrumentActionToken.apply(songState);

        assertEquals(instrument.getGeneralMIDICode(), songState.getInstrument());
    }

    @Test
    public void changeInstrumentToHarpchord() {
        final SongState songState = new SongState();
        final Instrument instrument = Instrument.HARPSICHORD;
        final ChangeInstrumentActionToken changeInstrumentActionToken = new ChangeInstrumentActionToken(instrument);

        changeInstrumentActionToken.apply(songState);

        assertEquals(instrument.getGeneralMIDICode(), songState.getInstrument());
    }

    @Test
    public void changeInstrumentToPanFlute() {
        final SongState songState = new SongState();
        final Instrument instrument = Instrument.PAN_FLUTE;
        final ChangeInstrumentActionToken changeInstrumentActionToken = new ChangeInstrumentActionToken(instrument);

        changeInstrumentActionToken.apply(songState);

        assertEquals(instrument.getGeneralMIDICode(), songState.getInstrument());
    }

    @Test
    public void changeInstrumentToTubularBells() {
        final SongState songState = new SongState();
        final Instrument instrument = Instrument.TUBULAR_BELLS;
        final ChangeInstrumentActionToken changeInstrumentActionToken = new ChangeInstrumentActionToken(instrument);

        changeInstrumentActionToken.apply(songState);

        assertEquals(instrument.getGeneralMIDICode(), songState.getInstrument());
    }
}