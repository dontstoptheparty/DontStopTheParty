package br.ufrgs.inf.dontstoptheparty.jukebox;

import br.ufrgs.inf.dontstoptheparty.player.Player;
import br.ufrgs.inf.dontstoptheparty.token.Token;

import java.util.List;

public class JukeBoxRunnable implements Runnable {

    private final Player player;
    private List<Token> tokens;
    private volatile boolean isPaused;
    private volatile int lastPlayerToken;

    public JukeBoxRunnable(List<Token> tokens, Player player) {
        this.player = player;
        this.tokens = tokens;
        this.isPaused = true;
        this.lastPlayerToken = 0;
    }


    @Override
    public void run() {
        // TODO Improve this for implementation
        for (int i = 0; (i < tokens.size() && !isPaused); i++) {
            Token token = tokens.get(i);
            player.play(token);

            lastPlayerToken = i;
        }
    }

    public List<Token> getTokens() {
        return tokens;
    }

    public void setTokens(List<Token> tokens) {
        this.tokens = tokens;
    }

    public Player getPlayer() {
        return player;
    }

    public boolean isPaused() {
        return isPaused;
    }

    public void setPaused(boolean paused) {
        isPaused = paused;
    }

    public int getLastPlayerToken() {
        return lastPlayerToken;
    }

    public void setLastPlayerToken(int lastPlayerToken) {
        this.lastPlayerToken = lastPlayerToken;
    }
}
