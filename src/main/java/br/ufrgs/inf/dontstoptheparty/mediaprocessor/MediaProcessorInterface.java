package br.ufrgs.inf.dontstoptheparty.mediaprocessor;

import br.ufrgs.inf.dontstoptheparty.token.Token;

import java.util.List;

public interface MediaProcessorInterface<T> {
    List<Token> convert(T value);
}
