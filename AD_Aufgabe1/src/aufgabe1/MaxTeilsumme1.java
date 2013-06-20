package aufgabe1;

public class MaxTeilsumme1 {
	
	int algorithmus(final int[] folge) {
		
		int von = 0;
		int bisherMax = 0;
		int randMax = 0;
		
		for (int i = 0; i < folge.length; i++) {
			randMax = Math.max(0, randMax + folge[i]);
			Benchmark.getInstance().summeAllerZugriffe();
			if(randMax > bisherMax){
				Benchmark.getInstance().index1(von);
				Benchmark.getInstance().index2(i);
				bisherMax = randMax;
			}
			von = randMax == 0 ? von = i+1 : von;
		};
		Benchmark.getInstance().maxTeilsumme(bisherMax);
		return bisherMax;
	}
	
	int algorithmusPure(final int[] folge) {
		
		int bisherMax = 0;
		int randMax = 0;
		
		for (int i = 0; i < folge.length; i++) {
			randMax = Math.max(0, randMax + folge[i]);
			bisherMax = Math.max(bisherMax, randMax);
		};
		return bisherMax;
	}
	
}
