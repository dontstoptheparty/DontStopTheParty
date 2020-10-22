package br.ufrgs.inf.dontstoptheparty.token;

import br.ufrgs.inf.dontstoptheparty.enumeration.Note;
import br.ufrgs.inf.dontstoptheparty.player.PlayerState;

public class NoteToken implements Token {
    private final Note note;

    public NoteToken(Note note) {
        this.note = note;
    }

    @Override
    public void execute(PlayerState jukeboxState) {
        jukeboxState.setNote(note);
    }
}
