package br.ufrgs.inf.dontstoptheparty;

import br.ufrgs.inf.dontstoptheparty.enumerator.Instrument;
import br.ufrgs.inf.dontstoptheparty.enumerator.Note;
import br.ufrgs.inf.dontstoptheparty.jukebox.JukeBox;
import br.ufrgs.inf.dontstoptheparty.player.JFuguePlayer;
import br.ufrgs.inf.dontstoptheparty.token.NoteToken;
import br.ufrgs.inf.dontstoptheparty.token.Token;
import br.ufrgs.inf.dontstoptheparty.token.actions.*;
import org.jfugue.player.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        final List<Token> tokenList = new ArrayList<>();

        tokenList.add(new DoubleVolumeActionToken());
        tokenList.add(new ChangeInstrumentActionToken(Instrument.ACOUSTIC_PIANO));
        tokenList.add(new NoteToken(Note.DO));
        tokenList.add(new NoteToken(Note.DO));
        tokenList.add(new SilenceActionToken());
        tokenList.add(new SilenceActionToken());
        tokenList.add(new SilenceActionToken());
        tokenList.add(new SilenceActionToken());
        tokenList.add(new NoteToken(Note.RE));
        tokenList.add(new NoteToken(Note.RE));
        tokenList.add(new NoteToken(Note.MI));
        tokenList.add(new IncreaseInstrumentActionToken(1));
        tokenList.add(new NoteToken(Note.FA));
        tokenList.add(new NoteToken(Note.FA));
        tokenList.add(new NoteToken(Note.SOL));
        tokenList.add(new NoteToken(Note.SOL));
        tokenList.add(new IncreaseOctaveActionToken());
        tokenList.add(new NoteToken(Note.LA));
        tokenList.add(new NoteToken(Note.LA));
        tokenList.add(new NoteToken(Note.SOL));
        tokenList.add(new NoteToken(Note.SI));

        JFuguePlayer jFuguePlayer = new JFuguePlayer();

        System.out.println("Attempt for-loop reproducing");
        JukeBox jukeBox = new JukeBox(tokenList, jFuguePlayer);
        jukeBox.start();

        try {
            System.out.println("Waiting");
            Thread.sleep(9500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Attempt playing it all");
        jFuguePlayer.reset();
        Player localPlayer = new Player();
        localPlayer.play(jFuguePlayer.save(tokenList));



        /*FIM EXEMPLO SAVE DO JFUGUE PLAYER*/

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

    }
}
