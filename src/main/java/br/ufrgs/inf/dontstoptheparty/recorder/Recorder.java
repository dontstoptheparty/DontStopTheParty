package br.ufrgs.inf.dontstoptheparty.recorder;

import br.ufrgs.inf.dontstoptheparty.player.PlayerState;
import br.ufrgs.inf.dontstoptheparty.token.Token;

import java.io.IOException;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public abstract class Recorder {
    private static String FILE_INIT = "Record__";

    private static String DATA_PATTERN = "MM-dd-yyyy_HH-mm-ss";

    protected final PlayerState playerState;

    public Recorder() {
        this.playerState = new PlayerState();
    }

    public abstract void record(List<Token> tokens, String directory) throws IOException;

    protected String getFilename() {
        final DateFormat df = new SimpleDateFormat(DATA_PATTERN);

        final Date today = Calendar.getInstance().getTime();

        final String formatedData = df.format(today);

        return FILE_INIT + formatedData;
    }
}
