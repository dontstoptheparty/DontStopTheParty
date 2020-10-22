package br.ufrgs.inf.dontstoptheparty.mediaprocessor;

import br.ufrgs.inf.dontstoptheparty.token.Token;

import java.util.ArrayList;
import java.util.List;

public class TextProcessor implements MediaProcessorInterface<String> {
    @Override
    public List<Token> convert(String entry) {
        final List<Token> tokenList = new ArrayList<>();
        char previousChar ;

        for (char ch: entry.toCharArray()) {
            //TODO Implementar regras de coversão
            previousChar = ch;
        }

        return tokenList;
    }
}
