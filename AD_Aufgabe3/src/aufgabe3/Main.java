package aufgabe3;

import java.util.concurrent.TimeUnit;

public class Main {
	
	private static int ANAZAHL_BAENDER = 2;
	private static int ANAZAHL_ZAHLEN = 10000;
	private static int INITIALE_RUNLAENGE = 6;

    /*
    * Main Run
    * */
    public static void main(String args[]){

        // instanz
        Algorithmus alg = new Algorithmus(ANAZAHL_BAENDER);
        alg.debug(true);

        // generiere Tape 0
        //Tape.exampleTape(0);
        Tape.randomTape(0, ANAZAHL_ZAHLEN);

        // starte
        alg.sort(INITIALE_RUNLAENGE);
    }
}
