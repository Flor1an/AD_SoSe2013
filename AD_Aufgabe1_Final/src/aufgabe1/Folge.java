package aufgabe1;

import java.util.Random;

/**
 * @author micha severin, florian kletz 
 *
 */
public class Folge {

	private int[] folge;
	
	Folge(int from, int to, int amount){

		Random r = new Random();
		
		this.folge = new int[amount];

		for (int i = 0; i < amount; i++) {
			int num = r.nextInt(to - from) + from;
			this.folge[i] = num;
		}
		
	}
	
	public void saveFS(String path){
		FileSystem fs = new FileSystem(path);
		fs.write( this.getFolge(" ") );
		fs.close();
	}
	
	public void readFS(String path){
		FileSystem fs = new FileSystem(path);
		String data = fs.read();
		String[] sArray = data.split(" ");
		int[] folge = new int[sArray.length];
		for(int i=0; i<sArray.length; i++){
			folge[i] = Integer.parseInt( sArray[i] );
		}
		this.folge = folge;
	}

	public int[] getFolge() {
		return this.folge;
	}

	public String getFolge(String seperator) {
		String data = "";
		for(int i=0; i<this.folge.length; i++){
			data += this.folge[i] + (this.folge.length-1 > i ? seperator : "");
		}
		return data;
	}

}
