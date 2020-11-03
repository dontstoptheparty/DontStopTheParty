package br.ufrgs.inf.dontstoptheparty.jukebox;

import br.ufrgs.inf.dontstoptheparty.player.Player;
import br.ufrgs.inf.dontstoptheparty.token.Token;

import java.util.List;

// TODO CREATE AN INTERFACE WITH METHOD COMMENTS
public class JukeBox implements Runnable {
    private List<Token> tokens;
    private final Player player;

    private final Thread playerThread;
    private volatile boolean isRunning;
    private volatile boolean isPaused;
    private volatile int lastPlayedToken;

    public JukeBox(List<Token> tokens, Player player) {
        this.player = player;
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
        pause();
        isRunning = false;
        reset();
    }

    public void save() {
        this.player.save(tokens);
    }

    @Override
    public void run() {
        // TODO IMPROVE THIS IMPLEMENTATION. COULD BE MORE READABLE

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
            }
        }

    }


}
