package br.ufrgs.inf.dontstoptheparty.jukebox;

import br.ufrgs.inf.dontstoptheparty.player.Player;
import br.ufrgs.inf.dontstoptheparty.token.Token;

import java.util.List;

public class JukeBox {
    private List<Token> tokens;
    private final Player player;
    private Boolean isPaused;

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

    }

    public void play() {
        this.isPaused = false;
    }

    public void pause() {
        this.isPaused = true;
    }

    public void save() {
        this.player.save(tokens);
    }
}
