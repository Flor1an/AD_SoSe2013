package aufgabe3;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: michaseverin
 * Date: 29.05.13
 * Time: 19:20
 * To change this template use File | Settings | File Templates.
 */
public class Analyse {

    public BufferedWriter fOut;

    private static int ANAZAHL_BAENDER_MIN = 2;
    private static int ANAZAHL_BAENDER_STEP = 2;
    private static int ANAZAHL_BAENDER_MAX = 10;

    private static int ANAZAHL_ZAHLEN_MIN = 10;
    private static int ANAZAHL_ZAHLEN_STEP = 10;
    private static int ANAZAHL_ZAHLEN_MAX = 100;

    private static int INITIALE_RUNLAENGE_MIN = 3;
    private static int INITIALE_RUNLAENGE_STEP = 3;
    private static int INITIALE_RUNLAENGE_MAX = 15;

    public static void main(String[] args){


        Analyse analyse = new Analyse();
        analyse.write("Baender;Zahlen;Run;Nano;Sekunden\n");

        for(int baender=ANAZAHL_BAENDER_MIN; baender<=ANAZAHL_BAENDER_MAX; baender += ANAZAHL_BAENDER_STEP){
            for(int zahlen=ANAZAHL_ZAHLEN_MIN; zahlen<=ANAZAHL_ZAHLEN_MAX; zahlen += ANAZAHL_ZAHLEN_STEP){
                for(int run=INITIALE_RUNLAENGE_MIN; run<=INITIALE_RUNLAENGE_MAX; run += INITIALE_RUNLAENGE_STEP){
                    // instanz
                    Algorithmus alg = new Algorithmus(baender);

                    // generiere Tape 0
                    Tape.randomTape(0, zahlen);

                    // starte
                    Long start = System.nanoTime();
                    alg.sort(run);
                    Long stop = System.nanoTime();

                    // time
                    Long nano = stop - start;
                    Long time = TimeUnit.SECONDS.convert(nano, TimeUnit.NANOSECONDS);
                    analyse.write(baender + ";" + zahlen + ";" + run + ";" + nano + ";" + time + "\n");

                }
            }
        }

        analyse.close();
    }

    Analyse(){
        try {
            fOut = new BufferedWriter( new FileWriter("analyse.csv") );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write(String data){
        try {
            this.fOut.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close(){
        try {
            this.fOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
