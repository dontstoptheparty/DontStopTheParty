/*
 * DontStopTheParty 2020.
 * Designed by Guilherme Santana, João Pedro Silveira, Renan Magagnin e Wellington M. Espindula.
 */

package br.ufrgs.inf.dontstoptheparty.mediaprocessor;

import br.ufrgs.inf.dontstoptheparty.token.Token;

import java.util.List;

public interface MediaProcessorInterface<T> {
    /**
     * Process a media and converts it into a list of Token's
     *
     * @param value Media entry
     * @return list of Token's from the media
     */
    List<Token> convert(T value);
}
