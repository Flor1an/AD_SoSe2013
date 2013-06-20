package aufgabe2;

import aufgabe2.algorithmus.Algorithmus;
import aufgabe2.tape.Tape;

import java.util.Arrays;
import java.util.Random;

public class TestAlgorithmus {

    /*
    * Main Run
    * */
    public static void main(String args[]){

        // instanz
        Algorithmus alg = new Algorithmus(2);

        // generiere Tape 0
        GenerateTape gt = new GenerateTape();
        //gt.exampleTape(0);
        gt.randomTape(0,1000);

        // starte
        System.out.println(alg); //Unsortierte Bänder ausgeben (vor dem Sortier Algorithmus)
        alg.debug(false);
        alg.sort(3); //mit initialer RunLänge
        System.err.println(alg); //Sortierte Bänder ausgeben (nach dem Sortier Algorithmus)

    }
}
