package aufgabe1;

import java.util.HashMap;
import java.util.UUID;

/**
 * 
 *
 */
public class Benchmark {
	
	
	private static Benchmark instance = null;
	private int anzahl;
	private String algorithmus;
	private int maxTeilsumme;
	private int index1;
	private int index2;
	private long zeitZaehlenInklusive;
	private long zeitZaehlenExklusive;
	private int summeAllerZugriffe;
	
	private Long duration = 0L;
	private HashMap<UUID, Long> measure = new HashMap<UUID, Long>();
	
	private Benchmark(Integer anzahl, String algorithmus){
		this.anzahl = anzahl;
		this.algorithmus = algorithmus;
	}
	
	public static Benchmark resetInstance(Integer anzahl, String algorithmus){
		return instance = new Benchmark(anzahl, algorithmus);
	}

	public static Benchmark getInstance() {
		return instance;
	}
	
	public UUID start(){
		UUID uid = UUID.randomUUID();
		this.measure.put(uid, System.nanoTime());
		return uid;
	}
	
	public void stop(UUID uid){
		if(this.measure.get(uid) != null){
			Long start = this.measure.get(uid);
			//this.measure.put(uid, System.nanoTime() - start);
			this.duration += (System.nanoTime() - start);
		}
	}

	public Long duration(){
		return this.duration;
	}

	public void maxTeilsumme(Integer maxTeilsumme) {
		this.maxTeilsumme = maxTeilsumme;
	}

	public void index1(Integer index1) {
		this.index1 = index1;
	}

	public void index2(Integer index2) {
		this.index2 = index2;
	}

	public void zeitZaehlenInklusive(Long zeitZaehlenInklusive) {
		this.zeitZaehlenInklusive = zeitZaehlenInklusive;
	}
	
	public void zeitZaehlenExklusive(Long zeitZaehlenExklusive) {
		this.zeitZaehlenExklusive = zeitZaehlenExklusive;
	}

	public void summeAllerZugriffe() {
		this.summeAllerZugriffe++;
	}
	
	public String toString(){	
		return this.anzahl + ";" + this.algorithmus + ";" + this.maxTeilsumme + ";" + this.index1 + ";" + this.index2 + ";" + this.zeitZaehlenInklusive + ";" + this.zeitZaehlenExklusive + ";" + this.summeAllerZugriffe + "\n";
	}
}
