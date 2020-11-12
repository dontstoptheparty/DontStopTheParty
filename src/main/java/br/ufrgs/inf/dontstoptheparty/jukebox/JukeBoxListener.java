package br.ufrgs.inf.dontstoptheparty.jukebox;

import br.ufrgs.inf.dontstoptheparty.token.Token;

import java.util.List;

public interface JukeBoxListener {
    /**
     * Called when JukeBox started its execution
     */
    void onStarted();

    /**
     * Called when a token is applied by JukeBox
     *
     * @param tokens   List of Tokens that is playing
     * @param position position of new Token that will be played
     */
    void onTokenPlayed(List<Token> tokens, int position);

    /**
     * Called when it was finished
     */
    void onFinish();
}
