package aufgabe4;

import java.awt.EventQueue;

import aufgabe4.adt.*;
import aufgabe4.helper.*;

public class Starter {
	public static void main(String[] args) {
		
//		Filereader fr = new Filereader("zzahlen.dat");
//		Baum t = new Baum();
//		for (Integer elem : fr.getEingeleseneFolge()) {
//			t.elementEinfuegen(elem);
//		}
//		System.out.println(t);
		
		
		
		 Baum avl = new Baum();

//		 avl.elementEinfuegen(50);
//		 avl.elementEinfuegen(70);
//		 avl.elementEinfuegen(30);
//		 avl.elementEinfuegen(60);
//		 avl.elementEinfuegen(40);
//		 avl.elementEinfuegen(80);
//		 avl.elementEinfuegen(20);
		 
//		 avl.elementEinfuegen(5);
		 //avl.elementEinfuegen(15);
//		 avl.elementEinfuegen(35);
		 
		// avl.elementEinfuegen(25);
//		 avl.elementEinfuegen(35);
		 
//		 avl.elementEinfuegen(15);
//		 avl.elementEinfuegen(12);
//		 avl.elementEinfuegen(16);
		 
//		 
//		 
//		 avl.elementEinfuegen(5);
//		 avl.elementEinfuegen(15);
//		 
//		 
//		  avl.elementEinfuegen(2);
//		 avl.elementEinfuegen(6);
//		 
//		 avl.elementEinfuegen(12);
//		 avl.elementEinfuegen(17);
		 
		 avl.elementEinfuegen(50);
		 avl.elementEinfuegen(60);
		 
		 avl.elementEinfuegen(30);
		 avl.elementEinfuegen(20);
		 avl.elementEinfuegen(40);
		 
		 System.out.println("\n" + avl);
		 avl.elementLoeschen(60);
//		 avl.elementLoeschen(20);
//		 avl.elementLoeschen(40);
//		 avl.elementLoeschen(30);
//		 
//		 System.out.println("\n" + avl);
//		 avl.elementLoeschen(25);
		 
		 System.out.println("\n" + avl);

	

		//draw(t.getWurzel());
	

	}

//	public static void draw(Knoten k) {
//		try {
//			System.err.println("\t"+k.getContent());
//			System.err.println();
//			
//			draw(k.getLinkerSohn());
//			draw(k.getRechterSohn());
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//	}
}
