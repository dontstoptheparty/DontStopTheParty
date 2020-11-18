/*
 * DontStopTheParty 2020.
 * Designed by Guilherme Santana, João Pedro Silveira, Renan Magagnin e Wellington M. Espindula.
 */

/*
 * DontStopTheParty 2020.
 * Designed by Guilherme Santana, João Pedro Silveira, Renan Magagnin e Wellington M. Espindula.
 */

package br.ufrgs.inf.dontstoptheparty.enumerator;

public enum Note {
    LA("A", 5),
    SI("B", 6),
    DO("C", 0),
    RE("D", 1),
    MI("E", 2),
    FA("F", 3),
    SOL("G", 4);

    private final String stringNotation;
    private final int midiFundamentalValue;

    Note(String stringNotation, int midiFundamentalValue) {
        this.stringNotation = stringNotation;
        this.midiFundamentalValue = midiFundamentalValue;
    }

    public String getStringNotation() {
        return stringNotation;
    }

    public int getMidiFundamentalValue() {
        return midiFundamentalValue;
    }
}
