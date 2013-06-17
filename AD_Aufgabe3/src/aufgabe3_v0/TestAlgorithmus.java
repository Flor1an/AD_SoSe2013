package aufgabe3_v0;

import aufgabe3_v0.algorithmus.Algorithmus;
import aufgabe3_v0.tape.Tape;

import java.util.Arrays;
import java.util.Random;

public class TestAlgorithmus {
	
	private static int ANAZAHL_BAENDER = 20;
	private static int ANAZAHL_ZAHLEN = 5000;
	private static int INITIALE_RUNLAENGE = 6;

    /*
    * Main Run
    * */
    public static void main(String args[]){

        // instanz
        Algorithmus alg = new Algorithmus(ANAZAHL_BAENDER);

        // generiere Tape 0
        GenerateTape gt = new GenerateTape();
        //gt.exampleTape(0);
        gt.randomTape(0,ANAZAHL_ZAHLEN);

        // starte
        System.out.println(alg); //Unsortierte Bänder ausgeben (vor dem Sortier Algorithmus)
        alg.debug(true);
        alg.sort(INITIALE_RUNLAENGE); //mit initialer RunLänge
        System.err.println(alg); //Sortierte Bänder ausgeben (nach dem Sortier Algorithmus)

    }
}
