/*
 * DontStopTheParty 2020.
 * Designed by Guilherme Santana, João Pedro Silveira, Renan Magagnin e Wellington M. Espindula.
 */

package br.ufrgs.inf.dontstoptheparty.mediaprocessor;

import br.ufrgs.inf.dontstoptheparty.enumerator.Instrument;
import br.ufrgs.inf.dontstoptheparty.enumerator.Note;
import br.ufrgs.inf.dontstoptheparty.token.NoteToken;
import br.ufrgs.inf.dontstoptheparty.token.Token;
import br.ufrgs.inf.dontstoptheparty.token.actions.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TextProcessorTest {

    private final TextProcessor textProcessor = new TextProcessor();
    @Test
    public void testForDefaultValue(){
        String charList = "ABCDEFG!I1i2U3\nu4;5,6789 ?aGz";
        final List<Token> expectedList = new ArrayList<>() {{
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
            add(new ChangeInstrumentActionToken(Instrument.HARPSICHORD));
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

        List<Token> resultList = textProcessor.convert(charList);

        assertNotNull(resultList);
        assertEquals(expectedList.size(), resultList.size());
        assertEquals(expectedList, resultList);
    }


    @Test
    public void testForElseValueAtFirst() {
        String charList = "a";
        final List<Token> expectedList = new ArrayList<>() {{
            add(new SilenceActionToken());
        }};

        List<Token> resultList = textProcessor.convert(charList);

        assertNotNull(resultList);
        assertEquals(expectedList.size(), resultList.size());
        assertEquals(expectedList, resultList);
    }

    @Test
    public void testForSequenceElseValues() {
        String charList = "Aaabb";
        final List<Token> expectedList = new ArrayList<>() {{
            add(new NoteToken(Note.LA));
            add(new NoteToken(Note.LA));
            add(new SilenceActionToken());
            add(new SilenceActionToken());
            add(new SilenceActionToken());
        }};

        List<Token> resultList = textProcessor.convert(charList);

        assertNotNull(resultList);
        assertEquals(expectedList.size(), resultList.size());
        assertEquals(expectedList, resultList);
    }


    @Test
    public void testForAccentedCharacters() {
        String charList = "Aàú";
        final List<Token> expectedList = new ArrayList<>() {{
            add(new NoteToken(Note.LA));
            add(new NoteToken(Note.LA));
            add(new SilenceActionToken());
        }};

        List<Token> resultList = textProcessor.convert(charList);

        assertNotNull(resultList);
        assertEquals(expectedList.size(), resultList.size());
        assertEquals(expectedList, resultList);
    }

    @Test
    public void testForNonAlphabeticalCharacters() {
        String charList = "A絵B文字\uD83D\uDE04C\uD83D\uDE22D\uD83D\uDE33Eππ";
        final List<Token> expectedList = new ArrayList<>() {{
            add(new NoteToken(Note.LA));
            add(new NoteToken(Note.LA));
            add(new NoteToken(Note.SI));
            add(new NoteToken(Note.SI));
            add(new SilenceActionToken());
            add(new SilenceActionToken());
            add(new SilenceActionToken());
            add(new NoteToken(Note.DO));
            add(new NoteToken(Note.DO));
            add(new SilenceActionToken());
            add(new NoteToken(Note.RE));
            add(new NoteToken(Note.RE));
            add(new SilenceActionToken());
            add(new NoteToken(Note.MI));
            add(new NoteToken(Note.MI));
            add(new SilenceActionToken());
        }};

        List<Token> resultList = textProcessor.convert(charList);

        assertNotNull(resultList);
        assertEquals(expectedList.size(), resultList.size());
        assertEquals(expectedList, resultList);
    }

    @Test
    public void testForEmptyString() {
        String charList = "";
        final List<Token> expectedList = new ArrayList<>();

        List<Token> resultList = textProcessor.convert(charList);

        assertNotNull(resultList);
        assertEquals(expectedList.size(), resultList.size());
        assertEquals(expectedList, resultList);
    }

    @Test
    public void testForNullString() {
        String charList = null;
        final List<Token> expectedList = new ArrayList<>();

        List<Token> resultList = textProcessor.convert(charList);

        assertNotNull(resultList);
        assertEquals(expectedList.size(), resultList.size());
        assertEquals(expectedList, resultList);
    }

}
