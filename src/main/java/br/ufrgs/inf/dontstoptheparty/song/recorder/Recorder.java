package br.ufrgs.inf.dontstoptheparty.song.recorder;

import br.ufrgs.inf.dontstoptheparty.song.SongState;
import br.ufrgs.inf.dontstoptheparty.token.Token;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public abstract class Recorder {
    private static String FILE_INIT = "Record__";

    private static String DATA_PATTERN = "MM-dd-yyyy_HH-mm-ss";

    protected final SongState songState;

    public Recorder() {
        this.songState = new SongState();
    }

    /**
     * Give a list of Token's, record it into a MIDI file
     *
     * @param tokens    entry Token's
     * @param directory directory the file will be saved on
     * @throws IOException
     */
    public abstract void record(List<Token> tokens, String directory) throws IOException;

    protected String getFilename() {
        final DateFormat df = new SimpleDateFormat(DATA_PATTERN);

        final Date today = Calendar.getInstance().getTime();

        final String formatedData = df.format(today);

        return FILE_INIT + formatedData;
    }
}
