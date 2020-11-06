package br.ufrgs.inf.dontstoptheparty.token;

import br.ufrgs.inf.dontstoptheparty.enumerator.Note;
import br.ufrgs.inf.dontstoptheparty.player.PlayerState;

public class NoteToken implements Token {
    private final Note note;

    public NoteToken(Note note) {
        this.note = note;
    }

    @Override
    public void apply(PlayerState playerState) {
        playerState.setNote(note);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NoteToken noteToken = (NoteToken) o;
        return note == noteToken.note;
    }
}
