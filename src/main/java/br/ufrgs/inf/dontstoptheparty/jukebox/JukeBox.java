/*
 * DontStopTheParty 2020.
 * Designed by Guilherme Santana, João Pedro Silveira, Renan Magagnin e Wellington M. Espindula.
 */

/*
 * DontStopTheParty 2020.
 * Designed by Guilherme Santana, João Pedro Silveira, Renan Magagnin e Wellington M. Espindula.
 */

package br.ufrgs.inf.dontstoptheparty.jukebox;

import br.ufrgs.inf.dontstoptheparty.song.player.Player;
import br.ufrgs.inf.dontstoptheparty.song.recorder.Recorder;
import br.ufrgs.inf.dontstoptheparty.token.Token;

import java.io.IOException;
import java.util.List;

public abstract class JukeBox {
    protected final Player player;
    protected final Recorder recorder;

    protected List<Token> tokens;
    protected JukeBoxListener jukeBoxListener;

    public JukeBox(List<Token> tokens, Player player, Recorder recorder) {
        this.player = player;
        this.recorder = recorder;
        this.tokens = tokens;
    }

    /**
     * Reload the music from a new Token's list
     *
     * @param tokens List of tokens from music
     */
    public void reload(List<Token> tokens) {
        this.tokens = tokens;
        this.stop();
    }

    /**
     * Start the music execution
     */
    public abstract void start();

    /**
     * Stop the music execution returning to the start
     */
    public abstract void stop();

    /**
     * Restart execution
     */
    public abstract void reset();

    /**
     * Play music if it was already started and paused
     */
    public abstract void play();

    /**
     * Pause current music execution
     */
    public abstract void pause();

    /**
     * Record the music to a MIDI file
     *
     * @param directory Directory were the file will be saved on
     */
    public abstract void record(String directory) throws IOException;

    /**
     * Sets the JukeBoxListener
     *
     * @param jukeBoxListener Default listener for JukeBox events
     */
    public void setFinishListener(JukeBoxListener jukeBoxListener) {
        this.jukeBoxListener = jukeBoxListener;
    }
}
