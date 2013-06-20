package aufgabe4;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import aufgabe4.adt.*;
import aufgabe4.helper.*;

public class Starter {
	public static void main(String[] args) {
		
		Random r = new Random();

		Filereader fr = new Filereader("zzahlen.dat");
		List<Integer> eingefuegteZahlen = new ArrayList<>();
		
		Baum t = new Baum();
		//In Baum einfügen:
		for (Integer elem : fr.getEingeleseneFolge()) {
			t.elementEinfuegen(elem);
			eingefuegteZahlen.add(elem);
		}
		
		System.out.println("\n" + t + "\n");
		
		//25 Zahlen aus Baum Löschen
		for (int i = 0; i < 25; i++) {
			Integer zuLoeschen= eingefuegteZahlen.get(r.nextInt(eingefuegteZahlen.size())); 
			
			t.elementLoeschen(zuLoeschen);
			eingefuegteZahlen.remove(zuLoeschen);
		}
//		t.elementLoeschen(157);
//		t.elementLoeschen(74);
		
		System.out.println("\n" + t + "\n");

		
//#################################
//		Baum t = new Baum();
//
//		t.elementEinfuegen(50);
//		t.elementEinfuegen(40);
//t.elementEinfuegen(20);
////		t.elementEinfuegen(70);
//
//t.elementEinfuegen(10);System.out.println("\n" + t);
//t.elementEinfuegen(5);
//
//		System.out.println("\n" + t);

	}

}
