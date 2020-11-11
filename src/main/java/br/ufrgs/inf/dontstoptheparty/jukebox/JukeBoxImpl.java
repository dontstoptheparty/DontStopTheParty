package br.ufrgs.inf.dontstoptheparty.jukebox;

import br.ufrgs.inf.dontstoptheparty.player.Player;
import br.ufrgs.inf.dontstoptheparty.recorder.Recorder;
import br.ufrgs.inf.dontstoptheparty.token.Token;

import java.io.IOException;
import java.util.List;

public class JukeBoxImpl extends JukeBox implements Runnable {
    private Thread playerThread;

    private volatile boolean isRunning;
    private volatile boolean isPaused;
    private volatile boolean resetTokens;

    public JukeBoxImpl(List<Token> tokens, Player player, Recorder recorder) {
        super(tokens, player, recorder);
        this.isPaused = true;
        this.isRunning = false;
        this.resetTokens = false;
        this.playerThread = new Thread(this);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void reset() {
        this.isRunning = true;
        this.player.reset();
        this.resetTokens = true;
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

        while (this.isRunning) {
            if (this.resetTokens) {
                i = 0;
                this.resetTokens = false;
            }

            if (i == this.tokens.size()) {
                this.stop();
                this.onFinish();
                break;
            }

            if (!this.isPaused) {
                Token token = this.tokens.get(i);
                this.player.play(token);
                i++;
            } else {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void onFinish() {
        if (this.finishListener != null) {
            this.finishListener.onFinish();
        }
    }

    private void reviveThreadIfNecessary() {
        if (!this.playerThread.isAlive()) {
            this.playerThread = new Thread(this);
            this.playerThread.start();
        }
    }
}
