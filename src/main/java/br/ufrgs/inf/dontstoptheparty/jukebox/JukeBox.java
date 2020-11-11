package br.ufrgs.inf.dontstoptheparty.jukebox;

import br.ufrgs.inf.dontstoptheparty.player.Player;
import br.ufrgs.inf.dontstoptheparty.recorder.Recorder;
import br.ufrgs.inf.dontstoptheparty.token.Token;

import java.util.List;

public class JukeBox implements Runnable {
    private final Player player;
    private final Recorder recorder;

    private List<Token> tokens;
    private FinishListener finishListener;
    private Thread playerThread;

    private volatile boolean isRunning;
    private volatile boolean isPaused;
    private volatile boolean resetTokens;

    public JukeBox(List<Token> tokens, Player player, Recorder recorder) {
        this.player = player;
        this.recorder = recorder;
        this.isPaused = true;
        this.isRunning = false;
        this.resetTokens = false;
        this.playerThread = new Thread(this);
        this.reload(tokens);
    }

    public void setFinishCallback(FinishListener finishMethod) {
        this.finishListener = finishMethod;
    }

    public void reload(List<Token> tokens) {
        this.tokens = tokens;
        this.reset();
    }

    public void reset() {
        this.isRunning = true;
        this.player.reset();
        this.resetTokens = true;
        this.reviveThreadIfNecessary();
    }

    public void start() {
        this.play();
        this.reset();
    }

    public void play() {
        this.isPaused = false;
    }

    public void pause() {
        this.isPaused = true;
    }

    public void stop() {
        this.pause();
        this.isRunning = false;
    }

    public void record() {
        this.recorder.record(tokens);
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
        if(!this.playerThread.isAlive()) {
            this.playerThread = new Thread(this);
            this.playerThread.start();
        }
    }
}
