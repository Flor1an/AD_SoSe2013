package aufgabe2;

import aufgabe2.tape.Tape;
import java.util.Random;

public class GenerateTape {
    public String randomTape(Integer tapeID){
        Integer min = 1,
                max = +1000000,
                len = 100000;

        Tape tape = new Tape("C:\\Users\\Florian\\Dropbox\\Eclipse Workspace\\AD_Aufgabe2\\files\\"+tapeID);
        Random rand = new Random();
        for(int i=0; i<len; i++){
            tape.write(i, rand.nextInt(max-min+1) + min);
        }
        return tape.toString();
    }

    public String exampleTape(Integer tapeID){
        int i = 0;
        Tape tape = new Tape("C:\\Users\\Florian\\Dropbox\\Eclipse Workspace\\AD_Aufgabe2\\files\\"+tapeID);
        for(Integer number : new Integer[]{0,200,12, 5, 2, 15, 13, 6, 14, 1, 4, 9, 10, 3, 11, 7, 8,300}){
            tape.write(i++, number);
        }
        return tape.toString();
    }
}
