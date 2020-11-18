/*
 * DontStopTheParty 2020.
 * Designed by Guilherme Santana, João Pedro Silveira, Renan Magagnin e Wellington M. Espindula.
 */

package br.ufrgs.inf.dontstoptheparty.token;

import br.ufrgs.inf.dontstoptheparty.enumerator.Note;
import br.ufrgs.inf.dontstoptheparty.song.SongState;

/**
 * Token for change current Note
 */
public class NoteToken implements Token {
    private final Note note;

    public NoteToken(Note note) {
        this.note = note;
    }

    /**
     * Change the song state's note to {@link #note}
     *
     * @param songState state of the song
     */
    @Override
    public void apply(SongState songState) {
        songState.setNote(note);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NoteToken noteToken = (NoteToken) o;
        return note == noteToken.note;
    }
}
