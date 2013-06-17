package aufgabe3;



import java.io.*;
import java.util.Random;

public class Tape {

    RandomAccessFile raf;

    public Tape(String file){
        try {
            this.raf = new RandomAccessFile(new File(file), "rw");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Boolean seek(Integer index){
        Integer pos = index * 4;
        try {
            if(pos <= this.raf.length()){
                this.raf.seek(pos);
                return true;
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Integer read(Integer index){
        try {
            if(this.seek(index + 1) && this.seek(index)){
                return this.raf.readInt();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Integer[] range(Integer start, Integer end){
        Integer number;
        Integer[] rArray = new Integer[end-start+1];
        for(int i=start, j=0; i<=end; i++,j++){
            number = this.read(i);
            if(number instanceof Integer){
                rArray[j] = number;
            }else{
                return null;
            }
        }
        return rArray;
    }

    public Boolean write(Integer index, Integer number){
        try {
            if(this.seek(index)){
                if(number instanceof Integer){
                    this.raf.writeInt(number);
                }else{
                    this.raf.writeUTF("");
                }
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int length(){
        try {
            return (int)(this.raf.length() / 4L);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void reset(){
        try {
            this.raf.setLength(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close(){
        try {
            this.raf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String toString(){
        String s = "[";
        for(int i=0; i<this.length(); i++){
            s += (i==0 ? "" : ", ") + this.read(i);
        }
        return s+"]";
    }
    public static String randomTape(Integer tapeID, int amount){
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

    public static String exampleTape(Integer tapeID){
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
