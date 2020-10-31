package br.ufrgs.inf.dontstoptheparty.mediaprocessor;

import br.ufrgs.inf.dontstoptheparty.token.NoteToken;
import br.ufrgs.inf.dontstoptheparty.token.Token;
import br.ufrgs.inf.dontstoptheparty.token.actions.ChangeInstrumentActionToken;
import br.ufrgs.inf.dontstoptheparty.token.actions.SilenceActionToken;
import nu.xom.jaxen.expr.DefaultAbsoluteLocationPath;
import br.ufrgs.inf.dontstoptheparty.player.PlayerState;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

private Map<Character,Token> charToTokenMap = new HashMap<Character, Token>(){{
    //NoteTokens
    put("A", new NoteToken(Note.LA));
    put("B", new NoteToken(Note.SI));
    put("C", new NoteToken(Note.DO));
    put("D", new NoteToken(Note.RE));
    put("E", new NoteToken(Note.MI));
    put("F", new NoteToken(Note.FA));
    put("G", new NoteToken(Note.SOL));
    
    //ActionTokens
    put("!", new ChangeInstrumentActionToken(AGOGO));
    put("I", new ChangeInstrumentActionToken(HARPSICHORD));
    put("i", new ChangeInstrumentActionToken(HARPSICHORD));
    put("U", new ChangeInstrumentActionToken(HARPSICHORD));
    put("u", new ChangeInstrumentActionToken(HARPSICHORD));
    put("\n", new ChangeInstrumentActionToken(TUBULAR_BELLS));
    put(";", new ChangeInstrumentActionToken(PAN_FLUTE));
    put(",", new ChangeInstrumentActionToken(CHURCH_ORGAN));
    
    put(" ", new DoubleVolumeActionToken());
    put("?", new IncreaseOctaveActionToken());

    put("1", new IncreaseInstrumentActionTonken(1));
    put("2", new IncreaseInstrumentActionTonken(2));
    put("3", new IncreaseInstrumentActionTonken(3));
    put("4", new IncreaseInstrumentActionTonken(4));
    put("5", new IncreaseInstrumentActionTonken(5));
    put("6", new IncreaseInstrumentActionTonken(6));
    put("7", new IncreaseInstrumentActionTonken(7));
    put("8", new IncreaseInstrumentActionTonken(8));
    put("9", new IncreaseInstrumentActionTonken(9));
}};



public class TextProcessor implements MediaProcessorInterface<String> {
    private Map<Character, Token> charToTokenMap;

    @Override
    public List<Token> convert(String entry) {
        final List<Token> tokenList = new ArrayList<>();
        char previousChar;

        for (char ch: entry.toCharArray()) {
            Token aux = charToTokenMap.get(ch);
            if(aux != null){
                tokenList.add(charToTokenMap.get(ch));
            }
            else{
                switch (previousChar){
                case 'A':
                case 'B':
                case 'C':
                case 'D':
                case 'E':
                case 'F':
                case 'G':
                tokenList.add(charToTokenMap.get(ch));
                break;

                default:
                tokenList.add(new SilenceActionToken());
                }
            }
            previousChar = ch;
        }
        return tokenList;
    }
}

