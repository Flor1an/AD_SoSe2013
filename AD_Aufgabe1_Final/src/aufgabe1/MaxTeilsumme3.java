package aufgabe1;

public class MaxTeilsumme3 {

	int algorithmus(final int[] folge) {
		int maxSumme = Integer.MIN_VALUE;
		for (int von = 0; von < folge.length; von++)
			for (int bis = von; bis < folge.length; bis++) { // Summe bilden
				int summe = 0;
				for (int i = von; i <= bis; i++) {
					summe += folge[i];
					Benchmark.getInstance().summeAllerZugriffe();
				}
				if (summe > maxSumme) {
					Benchmark.getInstance().maxTeilsumme(summe);
					Benchmark.getInstance().index1(von);
					Benchmark.getInstance().index2(bis);
				}
				maxSumme = Math.max(summe, maxSumme); // Summe ??berpr??fen, ob
														// gr????er
			}
		return maxSumme;
	}

	int algorithmusPure(final int[] folge) {
		int maxSumme = Integer.MIN_VALUE;
		for (int von = 0; von < folge.length; von++)
			for (int bis = von; bis < folge.length; bis++) { // Summe bilden
				int summe = 0;
				for (int i = von; i <= bis; i++) {
					summe += folge[i];
				}
				maxSumme = Math.max(summe, maxSumme); // Summe ??berpr??fen, ob gr????er
			}
		return maxSumme;
	}
}
