package aufgabe4.helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Filereader {
	File file;
	List<Integer> folge = new ArrayList<>();

	public Filereader(String path) {
		File f = new File(path);
		this.file = f;
		readFile();
	}

	private void readFile() {
		try {
			FileReader fr = new FileReader(file);
	        BufferedReader br = new BufferedReader(fr);
	        String[] text = br.readLine().split(" ").clone();
			for (String elem : text) {
				folge.add(Integer.valueOf(elem));
			} ;

			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Integer> getEingeleseneFolge(){
		return folge;
	}
}
