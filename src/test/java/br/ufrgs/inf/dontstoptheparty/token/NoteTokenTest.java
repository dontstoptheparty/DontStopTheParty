/*
 * DontStopTheParty 2020.
 * Designed by Guilherme Santana, João Pedro Silveira, Renan Magagnin e Wellington M. Espindula.
 */

/*
 * DontStopTheParty 2020.
 * Designed by Guilherme Santana, João Pedro Silveira, Renan Magagnin e Wellington M. Espindula.
 */

package br.ufrgs.inf.dontstoptheparty.token;

import br.ufrgs.inf.dontstoptheparty.enumerator.Note;
import br.ufrgs.inf.dontstoptheparty.song.SongState;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class NoteTokenTest {
    @Test
    public void changeNoteToDO() {
        final SongState songState = new SongState();
        final Note note = Note.DO;
        final NoteToken noteToken = new NoteToken(note);

        noteToken.apply(songState);

        assertEquals(note.getMidiFundamentalValue(), songState.getNote().getMidiFundamentalValue());
    }

    @Test
    public void changeNoteToFA() {
        final SongState songState = new SongState();
        final Note note = Note.FA;
        final NoteToken noteToken = new NoteToken(note);

        noteToken.apply(songState);

        assertEquals(note.getMidiFundamentalValue(), songState.getNote().getMidiFundamentalValue());
    }

    @Test
    public void changeNoteToLA() {
        final SongState songState = new SongState();
        final Note note = Note.LA;
        final NoteToken noteToken = new NoteToken(note);

        noteToken.apply(songState);

        assertEquals(note.getMidiFundamentalValue(), songState.getNote().getMidiFundamentalValue());
    }

    @Test
    public void changeNoteToMI() {
        final SongState songState = new SongState();
        final Note note = Note.MI;
        final NoteToken noteToken = new NoteToken(note);

        noteToken.apply(songState);

        assertEquals(note.getMidiFundamentalValue(), songState.getNote().getMidiFundamentalValue());
    }

    @Test
    public void changeNoteToRE() {
        final SongState songState = new SongState();
        final Note note = Note.RE;
        final NoteToken noteToken = new NoteToken(note);

        noteToken.apply(songState);

        assertEquals(note.getMidiFundamentalValue(), songState.getNote().getMidiFundamentalValue());
    }

    @Test
    public void changeNoteToSI() {
        final SongState songState = new SongState();
        final Note note = Note.SI;
        final NoteToken noteToken = new NoteToken(note);

        noteToken.apply(songState);

        assertEquals(note.getMidiFundamentalValue(), songState.getNote().getMidiFundamentalValue());
    }

    @Test
    public void changeNoteToSOL() {
        final SongState songState = new SongState();
        final Note note = Note.SOL;
        final NoteToken noteToken = new NoteToken(note);

        noteToken.apply(songState);

        assertEquals(note.getMidiFundamentalValue(), songState.getNote().getMidiFundamentalValue());
    }

    @Test
    public void changeNoteToNull() {
        final SongState songState = new SongState();
        final NoteToken noteToken = new NoteToken(null);

        noteToken.apply(songState);

        assertNull(songState.getNote());
    }
}
