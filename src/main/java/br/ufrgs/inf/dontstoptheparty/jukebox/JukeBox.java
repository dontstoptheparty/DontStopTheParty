package br.ufrgs.inf.dontstoptheparty.jukebox;

import br.ufrgs.inf.dontstoptheparty.player.Player;
import br.ufrgs.inf.dontstoptheparty.recorder.Recorder;
import br.ufrgs.inf.dontstoptheparty.token.Token;

import java.util.List;

public class JukeBox implements Runnable {
    private List<Token> tokens;
    private final Player player;
    private final Recorder recorder;

    private final Thread playerThread;
    private volatile boolean isRunning;
    private volatile boolean isPaused;
    private volatile int lastPlayedToken;

    public JukeBox(List<Token> tokens, Player player, Recorder recorder) {
        this.player = player;
        this.recorder = recorder;
        this.isPaused = true;
        this.playerThread = new Thread(this);
        this.reload(tokens);
    }

    public void reload(List<Token> tokens) {
        this.tokens = tokens;
        this.reset();
    }

    public void reset() {
        this.player.reset();
        this.lastPlayedToken = 0;
    }

    public void start() {
        if (playerThread.isAlive()) {
            throw new JukeBoxException("Thread already executing");
        } else {
            lastPlayedToken = 0;
            playerThread.start();
            isRunning = true;
            play();
        }
    }

    public void play() {
        if (!playerThread.isAlive()) {
            throw new JukeBoxException("Music thread isn't running");
        }
        this.isPaused = false;
    }

    public void pause() {
        this.isPaused = true;
    }

    public void stop() {
        isRunning = false;
        pause();
        reset();
    }

    public void record() {
        this.recorder.record(tokens);
    }

    @Override
    public void run() {
        int i = lastPlayedToken;

        while (isRunning) {
            if (!isPaused) {
                Token token = tokens.get(i);
                player.play(token);

                lastPlayedToken = i;
                i++;

                if (i == tokens.size()) {
                    stop();
                }
            } else {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }


}
