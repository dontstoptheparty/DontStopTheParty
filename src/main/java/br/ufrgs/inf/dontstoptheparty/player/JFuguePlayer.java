package br.ufrgs.inf.dontstoptheparty.player;

import br.ufrgs.inf.dontstoptheparty.token.ActionToken;
import br.ufrgs.inf.dontstoptheparty.token.NoteToken;
import br.ufrgs.inf.dontstoptheparty.token.Token;

import java.util.List;

public class JFuguePlayer extends Player {

    @Override
    protected void play(NoteToken noteToken) {
        //TODO Executar um pattern para uma nota com os dados do state
        String executionPattern = generatePattern(noteToken);
    }

    @Override
    protected void play(ActionToken actionToken) {
        //TODO Altera o state e retorna o pattern da alteração
    }

    public void save(List<Token> tokens) {
        final PlayerState state = new PlayerState();
        String pattern = "";
        //TODO Faz o mesmo processo de exeucação do pattern mas não toca e sim gera MIDI com o retorno do play
        for (Token token : tokens) {
            pattern += generatePattern(token);
        }
    }

    private String generatePattern(Token token) {
        String pattern = "";
        if (token instanceof ActionToken) {
            play((ActionToken) token);
        } else if (token instanceof NoteToken) {
            pattern += generatePattern((NoteToken) token);
            pattern += " ";
        }
        return pattern;
    }

    private String generatePattern(NoteToken noteToken){
        //TODO Implement pattern para uma nota com os dados do state

        return "";
    }
}
