package aufgabe3_v0;

import aufgabe3_v0.tape.Tape;

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
        //for(Integer number : new Integer[]{12, 5, 2, 15, 13, 6, 14, 1, 4, 9, 10, 3, 11, 7, 8,1,2,3,4,5,6,7,8,9}){
        //for(Integer number : new Integer[]{9,8,7,10,11,12,6,5,4,13,14,15,3,2,1,16,17,18,200,300,400,20,30,40}){
        for(Integer number : new Integer[]{200,300,400,20,30,2,3,4,5,6,7}){
        tape.write(i++, number);
        }
        return tape.toString();
    }
}