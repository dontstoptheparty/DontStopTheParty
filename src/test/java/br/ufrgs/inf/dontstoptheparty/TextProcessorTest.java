package br.ufrgs.inf.dontstoptheparty;
import org.junit.Test;
import br.ufrgs.inf.dontstoptheparty.enumerator.Instrument;
import br.ufrgs.inf.dontstoptheparty.enumerator.Note;
import br.ufrgs.inf.dontstoptheparty.token.NoteToken;
import br.ufrgs.inf.dontstoptheparty.token.Token;
import br.ufrgs.inf.dontstoptheparty.token.actions.*;
import br.ufrgs.inf.dontstoptheparty.mediaprocessor.TextProcessor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

public class TextProcessorTest {

    private final TextProcessor testProcessor = new TextProcessor(); 
    @Test
    void testForDefaultValue(){
        String charList = "ABCDEFG!I1i2U3\nu4;5,6789 ?aGz";
        final List<Token> expectedList = new ArrayList<Token>(){{
            //ABCDEFG!I1i2U3\nu4;5,6789 ?aGz
            add(new NoteToken(Note.LA));
            add(new NoteToken(Note.SI));
            add(new NoteToken(Note.DO));
            add(new NoteToken(Note.RE));
            add(new NoteToken(Note.MI));
            add(new NoteToken(Note.FA));
            add(new NoteToken(Note.SOL));

            add(new ChangeInstrumentActionToken(Instrument.AGOGO));
            add(new ChangeInstrumentActionToken(Instrument.HARPSICHORD));
            add(new IncreaseInstrumentActionToken(1));
            add(new ChangeInstrumentActionToken(Instrument.HARPSICHORD));
            add(new IncreaseInstrumentActionToken(2));
            add(new ChangeInstrumentActionToken(Instrument.HARPSICHORD));
            add(new IncreaseInstrumentActionToken(3));
            add(new ChangeInstrumentActionToken(Instrument.TUBULAR_BELLS));
            add(new IncreaseInstrumentActionToken(4));
            add(new ChangeInstrumentActionToken(Instrument.PAN_FLUTE));
            add(new IncreaseInstrumentActionToken(5));
            add(new ChangeInstrumentActionToken(Instrument.CHURCH_ORGAN));
            add(new IncreaseInstrumentActionToken(6));
            add(new IncreaseInstrumentActionToken(7));
            add(new IncreaseInstrumentActionToken(8));
            add(new IncreaseInstrumentActionToken(9));
            add(new DoubleVolumeActionToken());
            add(new IncreaseOctaveActionToken());
            add(new SilenceActionToken());
            add(new NoteToken(Note.SOL));
            add(new NoteToken(Note.SOL));
        }};

        assertNotNull(testProcessor.convert(charList));
        assertEquals(expectedList.size(), testProcessor.convert(charList).size());
//        assertEquals(expectedList.toArray(), testProcessor.convert(charList).toArray());
    }
}
