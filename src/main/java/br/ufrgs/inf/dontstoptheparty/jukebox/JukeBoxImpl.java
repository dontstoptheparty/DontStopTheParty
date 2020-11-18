/*
 * DontStopTheParty 2020.
 * Designed by Guilherme Santana, João Pedro Silveira, Renan Magagnin e Wellington M. Espindula.
 */

/*
 * DontStopTheParty 2020.
 * Designed by Guilherme Santana, João Pedro Silveira, Renan Magagnin e Wellington M. Espindula.
 */

package br.ufrgs.inf.dontstoptheparty.jukebox;

import br.ufrgs.inf.dontstoptheparty.song.player.JavaSoundPlayer;
import br.ufrgs.inf.dontstoptheparty.song.player.Player;
import br.ufrgs.inf.dontstoptheparty.song.recorder.JFugueRecorder;
import br.ufrgs.inf.dontstoptheparty.song.recorder.Recorder;
import br.ufrgs.inf.dontstoptheparty.token.Token;

import javax.sound.midi.MidiUnavailableException;
import java.io.IOException;
import java.util.List;

public class JukeBoxImpl extends JukeBox implements Runnable {
    private Thread playerThread;

    private volatile boolean isRunning;
    private volatile boolean isPaused;

    public JukeBoxImpl(List<Token> tokens, Player player, Recorder recorder) {
        super(tokens, player, recorder);
        this.isPaused = true;
        this.isRunning = false;
        this.playerThread = new Thread(this);
    }

    public JukeBoxImpl(List<Token> tokens) throws MidiUnavailableException {
        this(tokens, new JavaSoundPlayer(), new JFugueRecorder());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reset() {
        this.isRunning = true;
        this.player.reset();
        this.reviveThreadIfNecessary();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        this.play();
        this.reset();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void play() {
        this.isPaused = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void pause() {
        this.isPaused = true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stop() {
        this.pause();
        this.isRunning = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void record(String directory) throws IOException {
        this.recorder.record(tokens, directory);
    }

    @Override
    public void run() {
        int i = 0;
        onJukeBoxStarted();

        while (this.isRunning) {

            if (i == this.tokens.size()) {
                this.stop();
                break;
            }

            if (!this.isPaused) {
                Token token = this.tokens.get(i);
                this.player.play(token);
                i++;
                onTokenApplied(i);
            } else {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        this.onFinish();
    }

    private void onJukeBoxStarted() {
        if (this.jukeBoxListener != null) {
            this.jukeBoxListener.onStarted();
        }
    }

    private void onFinish() {
        if (this.jukeBoxListener != null) {
            this.jukeBoxListener.onFinish();
        }
    }

    private void onTokenApplied(int position) {
        if (this.jukeBoxListener != null) {
            this.jukeBoxListener.onTokenPlayed(position);
        }
    }

    private void reviveThreadIfNecessary() {
        if (!this.playerThread.isAlive()) {
            this.playerThread = new Thread(this);
            this.playerThread.start();
        }
    }
}
