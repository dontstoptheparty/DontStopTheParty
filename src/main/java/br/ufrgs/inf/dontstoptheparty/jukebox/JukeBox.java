package br.ufrgs.inf.dontstoptheparty.jukebox;

import br.ufrgs.inf.dontstoptheparty.player.Player;
import br.ufrgs.inf.dontstoptheparty.token.Token;

import java.util.List;

public class JukeBox {
    private List<Token> tokens;
    private final Player player;
    private final JukeBoxRunnable jukeBoxRunnable;

    public JukeBox(List<Token> tokens, Player player) {
        this.player = player;
        this.jukeBoxRunnable = new JukeBoxRunnable(tokens, player);
        this.reload(tokens);
    }

    public void reload(List<Token> tokens) {
        this.tokens = tokens;
        jukeBoxRunnable.setTokens(tokens);
        this.reset();
    }

    public void reset() {
        this.player.reset();
    }

    public void start() {
        Thread jfuguePlayThread = new Thread(jukeBoxRunnable);
        jfuguePlayThread.start();
        play();
    }

    public void play() {
        jukeBoxRunnable.setPaused(false);

    }

    public void pause() {
        jukeBoxRunnable.setPaused(true);
    }

    public void stop() {
        pause();
        jukeBoxRunnable.setLastPlayerToken(0);
    }

    public void record() {
        this.player.save(tokens);
    }

}
