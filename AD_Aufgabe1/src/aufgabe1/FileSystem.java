package aufgabe1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * @author micha severin, florian kletz 
 *
 */
public class FileSystem {

	private File file;
	private BufferedWriter bw;
	
	FileSystem(String path){
		this.file = new File(path);
	}
	
	private BufferedReader bufferedReader(){
		BufferedReader br = null;
		try{
			br = new BufferedReader( new FileReader( this.file.getAbsolutePath() ) );
		}catch(Exception e){
			System.err.println("Initialize Error: " + e.getMessage());
		}
		return br;
	}
	
	private BufferedWriter bufferedWriter(){
		BufferedWriter bw = null;
		try{
			bw = new BufferedWriter( new FileWriter( this.file.getAbsolutePath() ) );
		}catch(Exception e){
			System.err.println("Initialize Error: " + e.getMessage());
		}
		return bw;
	}
	
	public Boolean exists(){
		return this.file.exists();
	}
	
	public String read(){
		String data = "";
		BufferedReader br = this.bufferedReader();
		try{
			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null){
				data += sCurrentLine;
			}
			br.close();
		}catch(Exception e){
			System.err.println("Read Error: " + e.getMessage());			
		}
		return data;
	}
	
	public void write(String data){
		if(this.bw == null){
			this.bw = this.bufferedWriter();
		}
		try{
			this.bw.write(data);
		}catch(Exception e){
			System.err.println("Write Error: " + e.getMessage());			
		}
	}
	
	public void close(){
		try{
			this.bw.close();
			this.bw = null;
		}catch(Exception e){
			System.err.println("Close Error: " + e.getMessage());				
		}
	}
	
}
