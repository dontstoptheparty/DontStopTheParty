package br.ufrgs.inf.dontstoptheparty.jukebox;

import br.ufrgs.inf.dontstoptheparty.player.Player;
import br.ufrgs.inf.dontstoptheparty.token.Token;

import java.util.List;

public class JukeBox implements Runnable {
    private List<Token> tokens;
    private final Player player;
    private boolean isPaused;
    private int lastPlayerToken;

    public JukeBox(List<Token> tokens, Player player) {
        this.player = player;
        this.isPaused = true;
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
        // TODO Montar a segunda thread de execução
        Thread jfuguePlayThread = new Thread(this);
        jfuguePlayThread.start();
        play();
    }

    public void play() {
        this.isPaused = false;

    }

    public void pause() {
        this.isPaused = true;
    }

    public void stop() {
        pause();
        lastPlayerToken = 0;
    }

    public void save() {
        this.player.save(tokens);
    }

    @Override
    public void run() {

        for (int i = 0; (i < tokens.size() && !isPaused); i++) {
            Token token = tokens.get(i);
            player.play(token);

            lastPlayerToken = i;
        }

        stop();

    }
}
