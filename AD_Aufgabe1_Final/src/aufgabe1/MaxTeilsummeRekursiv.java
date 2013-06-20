package aufgabe1;

public class MaxTeilsummeRekursiv {

	private int[] rechtesRandMax(final int[] folge, int links, int rechts) {
		// requires 0 <= links <= rechts < folge.length
		// berechnet rechtes Randmaximum in folge zwischen links und rechts
		int i1 = -1, i2 = rechts;

		int bisherMax = Integer.MIN_VALUE;
		int bisherSum = 0;

		for (int i = rechts; i >= links; i--) {
			bisherSum += folge[i];
			Benchmark.getInstance().summeAllerZugriffe();

			if (bisherSum > bisherMax) {
				i1 = i;
			}

			bisherMax = Math.max(bisherMax, bisherSum);

		}

		return new int[] { bisherMax, i1, i2 };
	}

	private int[] linkesRandMax(final int[] folge, int links, int rechts) {
		// requires 0 <= links <= rechts < folge.length
		// berechnet linkes Randmaximum in folge zwischen links und rechts
		int i1 = links, i2 = -1;

		int bisherMax = Integer.MIN_VALUE;
		int bisherSum = 0;
		for (int i = links; i <= rechts; i++) {
			bisherSum += folge[i];
			Benchmark.getInstance().summeAllerZugriffe();

			if (bisherSum > bisherMax) {
				i2 = i;
			}

			bisherMax = Math.max(bisherMax, bisherSum);

		}
		return new int[] { bisherMax, i1, i2 };
	}

	private int[] maxTeilsummeRekursiv(final int[] folge, int links, int rechts) {
		// requires 0 <= links <= rechts < folge.length
		// berechnet maximale Teilsumme in folge zwischen links und rechts
		if (links == rechts) { // nur ein Element
			return new int[] { folge[links], links, links };
		} else {
			final int mitte = (rechts + links) / 2;
			final int[] maxLinks = maxTeilsummeRekursiv(folge, links, mitte);
			final int[] maxRechts = maxTeilsummeRekursiv(folge, mitte + 1, rechts);
			final int[] rechtesMax = rechtesRandMax(folge, links, mitte);
			final int[] linkesMax = linkesRandMax(folge, mitte + 1, rechts);

			if (maxRechts[0] > maxLinks[0]) {
				if (maxRechts[0] > (rechtesMax[0] + linkesMax[0])) {
					Benchmark.getInstance().maxTeilsumme(maxRechts[0]);
					Benchmark.getInstance().index1(maxRechts[1]);
					Benchmark.getInstance().index2(maxRechts[2]);
					return maxRechts;
				} else {
					Benchmark.getInstance().maxTeilsumme(rechtesMax[0] + linkesMax[0]);
					Benchmark.getInstance().index1(rechtesMax[1]);
					Benchmark.getInstance().index2(linkesMax[2]);
					return new int[] { rechtesMax[0] + linkesMax[0], rechtesMax[1], linkesMax[2] };
				}
			} else if (maxLinks[0] > (rechtesMax[0] + linkesMax[0])) {
				Benchmark.getInstance().maxTeilsumme(maxLinks[0]);
				Benchmark.getInstance().index1(maxLinks[1]);
				Benchmark.getInstance().index2(maxLinks[2]);
				return maxLinks;
			} else {
				Benchmark.getInstance().maxTeilsumme(rechtesMax[0] + linkesMax[0]);
				Benchmark.getInstance().index1(rechtesMax[1]);
				Benchmark.getInstance().index2(linkesMax[2]);
				return new int[] { rechtesMax[0] + linkesMax[0], rechtesMax[1],	linkesMax[2] };
			}

		}
	}

	public int[] algorithmus(final int[] folge) {
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
			final int maxRechts = maxTeilsummeRekursivPure(folge, mitte + 1,
					rechts);
			final int rechtesMax = rechtesRandMaxPure(folge, links, mitte);
			// linke Haelfte
			final int linkesMax = linkesRandMaxPure(folge, mitte + 1, rechts);
			// rechte Haelfte
			int max = Math.max(maxRechts, Math.max(maxLinks, rechtesMax + linkesMax));
			return max;
		}
	}

	public int algorithmusPure(final int[] folge) {
		// berechnet maximale Teilsumme von folge
		return maxTeilsummeRekursivPure(folge, 0, folge.length - 1);
	}
}
