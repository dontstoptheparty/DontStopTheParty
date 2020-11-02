package br.ufrgs.inf.dontstoptheparty.jukebox;

import br.ufrgs.inf.dontstoptheparty.player.Player;
import br.ufrgs.inf.dontstoptheparty.token.Token;

import java.util.List;

public class JukeBox implements Runnable {
    private List<Token> tokens;
    private final Player player;

    private final Thread jfuguePlayThread;
    private volatile boolean isRunning;
    private volatile boolean isPaused;
    private volatile int lastPlayedToken;

    public JukeBox(List<Token> tokens, Player player) {
        this.player = player;
        this.isPaused = true;
        this.jfuguePlayThread = new Thread(this);
        this.reload(tokens);
    }

    public void reload(List<Token> tokens) {
        this.tokens = tokens;
        this.reset();
    }

    public void reset() {
        this.player.reset();
    }

    public void start() {
        if (jfuguePlayThread.isAlive()) {
            throw new JukeBoxException("Thread already executing");
        } else {
//            jfuguePlayThread.start();
            isRunning = true;
            isPaused = false;
//            play();
            this.run();
        }
    }

    public void play() {
        if (!jfuguePlayThread.isAlive()) {
            throw new JukeBoxException("Music thread isn't running");
        }
        this.isPaused = false;
    }

    public void pause() {
        this.isPaused = true;
    }

    public void stop() {
        pause();
        lastPlayedToken = 0;
        isRunning = false;
    }

    public void save() {
        this.player.save(tokens);
    }

    @Override
    public void run() {
        int i = 0;

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

        player.close();

    }


}
