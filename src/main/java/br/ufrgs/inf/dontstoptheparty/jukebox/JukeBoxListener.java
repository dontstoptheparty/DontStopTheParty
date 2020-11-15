package br.ufrgs.inf.dontstoptheparty.jukebox;

public interface JukeBoxListener {
    /**
     * Called when JukeBox started its execution
     */
    void onStarted();

    /**
     * Called when a token is applied by JukeBox
     *
     * @param position position of new Token that will be played
     */
    void onTokenPlayed(int position);

    /**
     * Called when it was finished
     */
    void onFinish();
}
