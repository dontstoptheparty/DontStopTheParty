package br.ufrgs.inf.dontstoptheparty;

import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello MAVEN!" );

        Player player = new Player();
        Pattern p1;
        String[] noteList = new String[]{
                "C", "C#", "D", "D#", "E", "F", "F#", "G", "A", "A#", "B"
        };

//        Pattern p1 = new Pattern("C8 C").setVoice(0).setInstrument("Piano");
//        player.play(p1);

//        p1 = new Pattern("C8q C").setVoice(0).setInstrument("Flute");
//        player.play(p1);
//
//
//        p1 = new Pattern("E3wwd0").setVoice(0).setInstrument("Piano");
//        player.play(p1);
//         */
//
//        /*T: Tempo BPM*/
//        /*:CON(7, 127) VOLUME*/
//        p1 = new Pattern(":CON(7, 40) T[Grave] A B C D :CON(7, 127) T[Pretissimo] A B C D").setVoice(0).setInstrument("Piano");
//        player.play(p1);

        // Playing with loops and patterns
//        for (int i = 0; i < noteList.length; i++){
//            p1 = new Pattern(":CON(7, 127) T[Grave] "+noteList[i]).setVoice(0).setInstrument("Piano");
//            player.play(p1);
//            while (!player.getManagedPlayer().isFinished()) {
//                try {
//                    Thread.sleep((long) 10);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }

        // Trying to save a MIDI from a Pattern
        p1 = new Pattern(":CON(7, 127) T[Grave] C C# D D# E F F# G G# A A# B").setVoice(0).setInstrument("Piano");
        Pattern p2 = new Pattern(":CON(7, 127) T[Grave] C C# D D# E F F# G G# A A# B").setVoice(0).setInstrument("Guitar");
        Pattern p3 = new Pattern(":CON(7, 127) T[Grave] C C# D D# E F F# G G# A A# B").setVoice(0).setInstrument("Flute");
        Pattern p4 = new Pattern(":CON(7, 127) T[Grave] C C# D D# E F F# G G# A A# B").setVoice(0).setInstrument("Guitar");
        Pattern p5 = new Pattern(":CON(7, 127) T[Grave] C C# D D# E F F# G G# A A# B").setVoice(0).setInstrument("Piano");
        Pattern pTotal = new Pattern(p1, p2, p3, p4, p5);

        player.play(pTotal);
//            while (!player.getManagedPlayer().isFinished()) {
//                try {
//                    Thread.sleep((long) 10);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
////
//        try {
//            MidiFileManager.savePatternToMidi(pTotal, new File("Test.mid"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }
}
