package aufgabe2;

import aufgabe2.tape.Tape;
import java.io.*;

public class TestTape {
    public static void main(String[] args) throws IOException {

        Tape t0 = new Tape("t0");
        Tape t1 = new Tape("t1");
        Tape t2 = new Tape("t2");
        Tape t3 = new Tape("t3");

        for(int i=0; i<1000; i++){
            t0.write(i, i);
        }

        for(int i=0; i<1000; i++){
            t1.write(i, t0.read(i)+i);
        }

        for(int i=0; i<1000; i++){
            t2.write(i, t1.read(i)+i);
        }

        for(int i=0; i<1000; i++){
            t3.write(i, t2.read(i)+i);
        }

        System.out.println("t0.length = " + t0.length());
        System.out.println("t0[0] = " + t0.read(0));
        System.out.println("t0[999] = " + t0.read(999));

        System.out.println("t3.length = " + t3.length());
        System.out.println("t3[0] = " + t3.read(0));
        System.out.println("t3[999] = " + t3.read(999));

        t1.reset();
        t2.reset();

        t0.close();
        t1.close();
        t2.close();
        t3.close();

    }
}
