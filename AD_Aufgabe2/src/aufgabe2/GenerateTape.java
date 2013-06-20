package aufgabe2;

import aufgabe2.tape.Tape;
import java.util.Random;

public class GenerateTape {
	/**
	 * Generates random numbers an stores them on tape with given id
	 * @param tapeID 
	 * @param ammount amount of generated numbers
	 * @return
	 */
    public String randomTape(Integer tapeID,int amount){
        Integer min = 1,
                max = +5000,
                len = amount;

        Tape tape = new Tape(tapeID.toString());
        Random rand = new Random();
        for(int i=0; i<len; i++){
            tape.write(i, rand.nextInt(max-min+1) + min);
        }
        return tape.toString();
    }

    public String exampleTape(Integer tapeID){
        int i = 0;
        Tape tape = new Tape(tapeID.toString());
        for(Integer number : new Integer[]{12, 5, 2, 15, 13, 6, 14, 1, 4, 9, 10, 3, 11, 7, 8}){
            tape.write(i++, number);
        }
        return tape.toString();
    }
}