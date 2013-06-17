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
        gt.randomTape(0);

        // starte den Zossen
        System.out.println(alg.toString());
        alg.debug(false);
        alg.sort(3);
        System.err.println(alg.toString());
    }
}
