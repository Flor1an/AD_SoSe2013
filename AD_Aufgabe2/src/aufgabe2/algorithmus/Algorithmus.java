package aufgabe2.algorithmus;

import aufgabe2.tape.Tape;
import java.util.ArrayList;
import java.util.Arrays;

public class Algorithmus {

    /**
    * Instanzvariablen
    * - runLength = i (default start value 3)
    * - tapes = Array mit allen Bändern (default start value 4)
    * */
    private int runLength;
    private Tape[] tapes;
    private Boolean debug;

    /**
    * Konstruktor (Cascade)
    * */
    public Algorithmus(){
        this(3);
    }
    public Algorithmus(int tapesAmount){
        this.debug = false;
        this.tapes = new Tape[tapesAmount*2];
        for(int i=0; i<tapesAmount*2; i++){
            this.tapes[i] = new Tape(""+i);
            this.tapes[i].reset();
        }
    }

    public Boolean debug(Boolean debug){
        return this.debug = debug;
    }

    /**
    * Methode zum starten der Sortierung
    * */
    public void sort(int runLength){
        // DEBUG
        if(this.debug) pl("\n### initial ###", this);

        // Tape Length
        int initialTapeLength = this.tapes[0].length();

        this.sortInitial(runLength);

        // DEBUG
        if(this.debug) pl("\n### sortInitial ###", this);

        // Durchlaufe die Schleife die runLength größer der Bandlänge.



        int inputTapeID = this.tapes.length / 2;
        int inputTapeOffset = 0;

        int outputTapeID = 0;
        int outputTapeOffset = 0;

        while(runLength < initialTapeLength){

            boolean change = false;
            int outputTapeIDoffset = 0;

            for(int horizontal=0; horizontal<initialTapeLength; horizontal += runLength){

                for(int vertical=0; vertical<this.tapes.length / 2; vertical += 2){

                    int inID = inputTapeID + vertical;
                    int inOff = inputTapeOffset;
                    int outID = outputTapeID + outputTapeIDoffset;
                    int outOff = outputTapeOffset;

                    //pl("inID: "+inID+","+(inID+1)+" inOff: "+inOff+" outID: "+outID+" outOff: "+outOff+" run: "+runLength);

                    this.sortRun(inID, inOff, outID, outOff, runLength);

                    
                    outputTapeIDoffset += 1;
                }
                inputTapeOffset += runLength;


                // berechne neues Ausgabeband
                if(change == true){
                    outputTapeIDoffset = 0;
                    outputTapeOffset += runLength * (this.tapes.length / 2);
                }
                change = !change;

            }

            this.tapes[inputTapeID].reset();
            this.tapes[inputTapeID+1].reset();

            // wechsel ein/ausgabebänder
            if(inputTapeID == this.tapes.length / 2){
                inputTapeID = 0;
                inputTapeOffset = 0;

                outputTapeID = this.tapes.length / 2;
                outputTapeOffset = 0;
            }else{
                inputTapeID = this.tapes.length / 2;
                inputTapeOffset = 0;

                outputTapeID = 0;
                outputTapeOffset = 0;
            }

            runLength *= this.tapes.length / 2;

            // DEBUG
            if(this.debug) pl("\n### runLength: " + runLength + " ###", this);


        }
    }

    /**
    * Methode zum sortieren eines Bandes
    * */
    private void sortRun(Integer inputTapeID, Integer inputTapeOffset, Integer outputTapeID, Integer outputTapeOffset, Integer runLength){

        Integer outputTapeIndex = outputTapeOffset;
        Integer inputTapeOneIndex = inputTapeOffset;
        Integer inputTapeTwoIndex = inputTapeOffset;

        Integer tX, tY;
        while(inputTapeOneIndex - inputTapeOffset < runLength && inputTapeTwoIndex - inputTapeOffset < runLength){

            tX = this.tapes[inputTapeID].read(inputTapeOneIndex);
            tY = this.tapes[inputTapeID+1].read(inputTapeTwoIndex);

            if(tX != null && tY != null){
                if(tX < tY){
                    this.tapes[outputTapeID].write(outputTapeIndex, tX);
                    inputTapeOneIndex ++;
                }else{
                    this.tapes[outputTapeID].write(outputTapeIndex, tY);
                    inputTapeTwoIndex ++;
                }
            }else{
                if(tX != null && tY == null){
                    this.tapes[outputTapeID].write(outputTapeIndex, tX);
                    inputTapeOneIndex ++;
                }else if(tX == null && tY != null){
                    this.tapes[outputTapeID].write(outputTapeIndex, tY);
                    inputTapeTwoIndex ++;
                }else{
                    break;
                }
            }
            outputTapeIndex++;
        }

        while(inputTapeOneIndex < inputTapeTwoIndex){
            this.tapes[outputTapeID].write(outputTapeIndex, this.tapes[inputTapeID].read(inputTapeOneIndex));
            inputTapeOneIndex ++;
            outputTapeIndex++;
        }
        while(inputTapeTwoIndex < inputTapeOneIndex){
            this.tapes[outputTapeID].write(outputTapeIndex, this.tapes[inputTapeID+1].read(inputTapeTwoIndex));
            inputTapeTwoIndex ++;
            outputTapeIndex++;
        }
    }

    public void sortInitial(Integer runLength){
        Integer switchTapeOffset = 0;
        Integer outputTapeID = this.tapes.length / 2;
        for(int offset=0; this.tapes[0].length() - 1 > offset; offset+=runLength){
            Integer end;
            if(offset + runLength > this.tapes[0].length()){
                end = this.tapes[0].length() - 1;
            }else{
                end = offset + runLength - 1;
            }
            Integer[] range = this.tapes[0].range(offset, end);

            range = this.bubbleSort(range);
            for(int i=0; i<range.length; i++){
                this.tapes[outputTapeID].write(switchTapeOffset + i, range[i]);
            }

            if(outputTapeID + 1 < this.tapes.length){
                outputTapeID = outputTapeID + 1;
            }else{
                outputTapeID = this.tapes.length / 2;
                switchTapeOffset = switchTapeOffset + runLength;
            }
        }
        this.tapes[0].reset();
    }

    public Integer[] bubbleSort(Integer[] inArray){
        int temp;
        boolean unsorted = true;
        Integer[] array = inArray.clone();
        while (unsorted){
            unsorted = false;
            for (int i=0; i < array.length-1; i++)
                if (array[i] > array[i+1]) {
                    temp = array[i];
                    array[i] = array[i+1];
                    array[i+1] = temp;
                    unsorted = true;
                }
        }
        return array;
    }


    /**
    * Rückgabe aller Bänder als String Repräsentation
    * */
    public String toString(){
        String s = "";
        for(int i=0; i<this.tapes.length; i++){
            s += "Band " + i + ": " + this.tapes[i] + "\n";
        }
        return s;
    }

    /**
    * Helper Methoden zur Ausgabe auf der Konsole
    * */
    private void p(Object ... args){
        for(Object o : args){
            System.out.print(o);
        }
    }
    private void pl(Object ... args){
        for(Object o : args){
            System.out.println(o);
        }
    }
}