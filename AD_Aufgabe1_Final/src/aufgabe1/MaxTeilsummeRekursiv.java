package aufgabe1;

public class MaxTeilsummeRekursiv {

	private int rechtesRandMax(final int[] folge, int links, int rechts) {
		// requires 0 <= links <= rechts < folge.length
		// berechnet rechtes Randmaximum in folge zwischen links und rechts
		int bisherMax = Integer.MIN_VALUE;
		int bisherSum = 0;
		for (int i = rechts; i >= links; i--) {
			bisherSum += folge[i];
			Benchmark.getInstance().summeAllerZugriffe();
			bisherMax = Math.max(bisherMax, bisherSum);
		}
		return bisherMax;
	}

	private int linkesRandMax(final int[] folge, int links, int rechts) {
		// requires 0 <= links <= rechts < folge.length
		// berechnet linkes Randmaximum in folge zwischen links und rechts
		int bisherMax = Integer.MIN_VALUE;
		int bisherSum = 0;
		for (int i = links; i <= rechts; i++) {
			bisherSum += folge[i];
			Benchmark.getInstance().summeAllerZugriffe();
			bisherMax = Math.max(bisherMax, bisherSum);
		}
		return bisherMax;
	}

	private int maxTeilsummeRekursiv(final int[] folge, int links, int rechts) {
		// requires 0 <= links <= rechts < folge.length
		// berechnet maximale Teilsumme in folge zwischen links und rechts
		if (links == rechts) { // nur ein Element
			return Math.max(0, folge[links]);
		} else {
			final int mitte = (rechts + links) / 2;
// ggf. zählen?
			final int maxLinks = maxTeilsummeRekursiv(folge, links, mitte);
			final int maxRechts = maxTeilsummeRekursiv(folge, mitte + 1, rechts);
			final int rechtesMax = rechtesRandMax(folge, links, mitte);
			// linke H??lfte
			final int linkesMax = linkesRandMax(folge, mitte + 1, rechts);
			// rechte H??lfte
			int max =  Math.max(maxRechts, Math.max(maxLinks, rechtesMax + linkesMax));
// ggf. zählen?
			Benchmark.getInstance().maxTeilsumme(max);
			return max;
		}
	}

	public int algorithmus(final int[] folge) {
		// berechnet maximale Teilsumme von folge
		return maxTeilsummeRekursiv(folge, 0, folge.length - 1);
	}

	
	
	
	
	


	private int rechtesRandMaxPure(final int[] folge, int links, int rechts) {
		// requires 0 <= links <= rechts < folge.length
		// berechnet rechtes Randmaximum in folge zwischen links und rechts
		int bisherMax = Integer.MIN_VALUE;
		int bisherSum = 0;
		for (int i = rechts; i >= links; i--) {
			bisherSum += folge[i];
			bisherMax = Math.max(bisherMax, bisherSum);
		}
		return bisherMax;
	}

	private int linkesRandMaxPure(final int[] folge, int links, int rechts) {
		// requires 0 <= links <= rechts < folge.length
		// berechnet linkes Randmaximum in folge zwischen links und rechts
		int bisherMax = Integer.MIN_VALUE;
		int bisherSum = 0;
		for (int i = links; i <= rechts; i++) {
			bisherSum += folge[i];
			bisherMax = Math.max(bisherMax, bisherSum);
		}
		return bisherMax;
	}

	private int maxTeilsummeRekursivPure(final int[] folge, int links, int rechts) {
		// requires 0 <= links <= rechts < folge.length
		// berechnet maximale Teilsumme in folge zwischen links und rechts
		if (links == rechts) { // nur ein Element
			return Math.max(0, folge[links]);
		} else {
			final int mitte = (rechts + links) / 2;
			final int maxLinks = maxTeilsummeRekursivPure(folge, links, mitte);
			final int maxRechts = maxTeilsummeRekursivPure(folge, mitte + 1, rechts);
			final int rechtesMax = rechtesRandMaxPure(folge, links, mitte);
			// linke H??lfte
			final int linkesMax = linkesRandMaxPure(folge, mitte + 1, rechts);
			// rechte H??lfte
			int max =  Math.max(maxRechts, Math.max(maxLinks, rechtesMax + linkesMax));
			return max;
		}
	}

	public int algorithmusPure(final int[] folge) {
		// berechnet maximale Teilsumme von folge
		return maxTeilsummeRekursivPure(folge, 0, folge.length - 1);
	}
}
