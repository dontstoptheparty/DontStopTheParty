package br.ufrgs.inf.dontstoptheparty.player;

import br.ufrgs.inf.dontstoptheparty.token.Token;
import org.jfugue.midi.MidiFileManager;
import org.jfugue.pattern.Pattern;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JFuguePlayer extends Player {

    private final org.jfugue.player.Player player;

    public JFuguePlayer() {
        super();
        this.player = new org.jfugue.player.Player();
    }

    @Override
    public void play(Token token) {
        token.apply(this.playerState);
        this.playSingleNotePattern();
    }

    @Override
    public Pattern save(List<Token> tokens) {
        final Pattern finalPattern = new Pattern();
        Pattern tempPatter;

        for (Token token : tokens) {
            token.apply(this.playerState);
            tempPatter = this.generateSingleNotePattern();
            if (tempPatter != null) {
                finalPattern.add(tempPatter);
                this.cleanNoteAfterPlay();
            }
        }

        try {
            MidiFileManager.savePatternToMidi(finalPattern, new File("Test2.mid"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return finalPattern;
    }

    private void playSingleNotePattern() {
        final Pattern pattern = this.generateSingleNotePattern();
        if (pattern != null) {
            player.play(pattern);
            this.cleanNoteAfterPlay();
        }
    }

    private void cleanNoteAfterPlay() {
        this.playerState.setNote(null);
    }

    private Pattern generateSingleNotePattern() {
        if (this.playerState.isSilence()) {
            this.playerState.setSilence(false);
            return new Pattern(this.getSilencePattern());
        } else if (this.playerState.getNote() != null) {
            return new Pattern(this.getVolumePattern() +
                    this.getInstrumentPattern() +
                    this.getNoteWithOctave());
        } else {
            return null;
        }
    }

    private String getVolumePattern() {
        return ":CON(7, "+this.playerState.getVolume()+") ";
    }

    private String getInstrumentPattern() {
        return "I"+this.playerState.getInstrument()+" ";
    }

    private String getNoteWithOctave() {
        return this.playerState.getNote().getValue() + this.playerState.getOctave()+ " ";
    }

    private String getSilencePattern() {
        return ":CON(7,0) C ";
    }
}
