package aufgabe1;

import java.util.UUID;

/**
 * 
 *
 */
public class Main {
	public static void main(String[] args) {
		
		FileSystem csv = new FileSystem("maxteilsummeTest.csv");
		csv.write("Anzahl;Algorithmus;maxTeilsumme;Index1;Index2;Zeit (inkl. Zaehlen);Zeit (exkl. Zaehlen);Summe aller Zugriffe\n");
		
		for(int i=100; i<=1000; i=i+100){
			
			/* Konfiguration */
			String dat = "folge.dat";
			int von = -100,
				bis = +100,
				anzahl = i;
			
			/* Folge */
			Folge f = new Folge(von, bis, anzahl);
			f.saveFS(dat);
			f.readFS(dat);
			
			UUID uid;
			Long zeitZaehlenExklusive;
			
			/* MaxTeilsumme3 */
			MaxTeilsumme3 maxTeilsumme3 = new MaxTeilsumme3();
				/* exklusive */
				Benchmark.resetInstance(anzahl, "MaxTeilsumme3");
				uid = Benchmark.getInstance().start();
				maxTeilsumme3.algorithmusPure(f.getFolge());
				Benchmark.getInstance().stop(uid);
				zeitZaehlenExklusive = Benchmark.getInstance().duration();
			
				/* inklusive */
				Benchmark.resetInstance(anzahl, "MaxTeilsumme3");
				uid = Benchmark.getInstance().start();
				maxTeilsumme3.algorithmus(f.getFolge());
				Benchmark.getInstance().stop(uid);
				Benchmark.getInstance().zeitZaehlenExklusive( zeitZaehlenExklusive );
				Benchmark.getInstance().zeitZaehlenInklusive( Benchmark.getInstance().duration() );
				
				/* save results */
				csv.write(Benchmark.getInstance().toString());
			
			/* MaxTeilsumme2 */
			MaxTeilsumme2 maxTeilsumme2 = new MaxTeilsumme2();
				/* exklusive */
				Benchmark.resetInstance(anzahl, "MaxTeilsumme2");
				uid = Benchmark.getInstance().start();
				maxTeilsumme2.algorithmusPure(f.getFolge());
				Benchmark.getInstance().stop(uid);
				zeitZaehlenExklusive = Benchmark.getInstance().duration();

				/* inklusive */
				Benchmark.resetInstance(anzahl, "MaxTeilsumme2");
				uid = Benchmark.getInstance().start();
				maxTeilsumme2.algorithmus(f.getFolge());
				Benchmark.getInstance().stop(uid);
				Benchmark.getInstance().zeitZaehlenExklusive( zeitZaehlenExklusive );
				Benchmark.getInstance().zeitZaehlenInklusive( Benchmark.getInstance().duration() );

				/* save results */
				csv.write(Benchmark.getInstance().toString());
				
			/* MaxTeilsummeRekursiv */
			MaxTeilsummeRekursiv maxTeilsummeRekursiv = new MaxTeilsummeRekursiv();
				/* exklusive */
				Benchmark.resetInstance(anzahl, "MaxTeilsummeRekursiv");
				uid = Benchmark.getInstance().start();
				maxTeilsummeRekursiv.algorithmusPure(f.getFolge());
				Benchmark.getInstance().stop(uid);
				zeitZaehlenExklusive = Benchmark.getInstance().duration();

				/* inklusive */
				Benchmark.resetInstance(anzahl, "MaxTeilsummeRekursiv");
				uid = Benchmark.getInstance().start();
				maxTeilsummeRekursiv.algorithmus(f.getFolge());
				Benchmark.getInstance().stop(uid);
				Benchmark.getInstance().zeitZaehlenExklusive( zeitZaehlenExklusive );
				Benchmark.getInstance().zeitZaehlenInklusive( Benchmark.getInstance().duration() );

				/* save results */
				csv.write(Benchmark.getInstance().toString());
			
			/* MaxTeilsumme1 */
				MaxTeilsumme1 maxTeilsumme1 = new MaxTeilsumme1();
				/* exklusive */
				Benchmark.resetInstance(anzahl, "MaxTeilsumme1");
				uid = Benchmark.getInstance().start();
				maxTeilsumme1.algorithmusPure(f.getFolge());
				Benchmark.getInstance().stop(uid);
				zeitZaehlenExklusive = Benchmark.getInstance().duration();

				/* inklusive */
				Benchmark.resetInstance(anzahl, "MaxTeilsumme1");
				uid = Benchmark.getInstance().start();
				maxTeilsumme1.algorithmus(f.getFolge());
				Benchmark.getInstance().stop(uid);
				Benchmark.getInstance().zeitZaehlenExklusive( zeitZaehlenExklusive );
				Benchmark.getInstance().zeitZaehlenInklusive( Benchmark.getInstance().duration() );

				/* save results */
				csv.write(Benchmark.getInstance().toString());
		}
			
		/* Close csv */
		csv.close();
	}
}
