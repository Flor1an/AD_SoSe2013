package aufgabe3_v0.tape;



import java.io.*;

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

}
