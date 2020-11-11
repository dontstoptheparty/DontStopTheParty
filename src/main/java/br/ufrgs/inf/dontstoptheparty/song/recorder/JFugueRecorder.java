package br.ufrgs.inf.dontstoptheparty.song.recorder;

import br.ufrgs.inf.dontstoptheparty.token.Token;
import org.jfugue.midi.MidiFileManager;
import org.jfugue.pattern.Pattern;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JFugueRecorder extends Recorder {

    private final org.jfugue.player.Player player;

    public JFugueRecorder() {
        super();
        this.player = new org.jfugue.player.Player();
    }

    @Override
    public void record(List<Token> tokens, String directory) throws IOException {
        final Pattern finalPattern = new Pattern();
        Pattern tempPatter;

        for (Token token : tokens) {
            token.apply(this.songState);
            tempPatter = this.generateSingleNotePattern();
            if (tempPatter != null) {
                finalPattern.add(tempPatter);
                this.cleanNoteAfterPlay();
            }
        }

        final String filename = this.getFilename();
        final File newFile = new File(directory + "/" + filename);
        MidiFileManager.savePatternToMidi(finalPattern, newFile);

        this.songState.resetToDefault();
    }

    private void cleanNoteAfterPlay() {
        this.songState.setNote(null);
    }

    private Pattern generateSingleNotePattern() {
        if (this.songState.isSilence()) {
            this.songState.setSilence(false);
            return new Pattern(this.getSilencePattern());
        } else if (this.songState.getNote() != null) {
            return new Pattern(this.getVolumePattern() +
                    this.getBpmPattern() +
                    this.getInstrumentPattern() +
                    this.getNoteWithOctave());
        } else {
            return null;
        }
    }

    private String getVolumePattern() {
        return ":CON(7, " + this.songState.getVolume() + ") ";
    }

    private String getBpmPattern() {
        return "I" + this.songState.getBpm() + " ";
    }

    private String getInstrumentPattern() {
        return "I" + this.songState.getInstrument() + " ";
    }

    private String getNoteWithOctave() {
        return this.songState.getNote().getStringNotation() + this.songState.getOctave() + " ";
    }

    private String getSilencePattern() {
        return ":CON(7,0) C ";
    }
}
