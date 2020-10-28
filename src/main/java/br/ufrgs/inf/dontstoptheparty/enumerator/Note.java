package br.ufrgs.inf.dontstoptheparty.enumerator;

public enum Note {
    LA("A"),
    SI("B"),
    DO("C"),
    RE("D"),
    MI("E"),
    FA("F"),
    SOL("G");
    
    private String value;

    Note(String value){
        this.value = value;
    }
    
    public String getValue()
    {
        return value;
    }
}
