package br.ufrgs.inf.dontstoptheparty.token;

import br.ufrgs.inf.dontstoptheparty.player.PlayerState;

public class NoteToken implements Token {
    private final Note note;

    public NoteToken(Note note) {
        this.note = note;
    }

    @Override
    public void execute(PlayerState playerState) {
        playerState.setNote(note);
    }
}
