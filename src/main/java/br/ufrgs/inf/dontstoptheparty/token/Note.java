package br.ufrgs.inf.dontstoptheparty.token;

public enum Note {
    LA("A"),
    SI("B"),
    DO("C"),
    RE("D"),
    MI("E"),
    FA("F"),
    SOL("G");
    
    private String jfugueRepresentation;

    Note(){}
    
    Note(string jfugueRepresentation){
        this.jfugueRepresentation = jfugueRepresentation;}
    
    public String getJfugueRepresentation()
    {
        return jfugueRepresentation;
    }
}
