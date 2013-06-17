package aufgabe1;

public class MaxTeilsumme2 {

	int algorithmus(final int[] folge) {
		
		int[][] s = new int[folge.length][folge.length];
		// Tabelle f??r Teilsummen: f??r (i,j) gilt
		// s[i][j] == Teilsumme i bis j == folge[i]+folge[i+1]+...+folge[j]
		for (int i = 0; i < folge.length; i++)
			for (int j = 0; j < folge.length; j++) {
				s[i][j] = 0;
			}
		
		int max = Integer.MIN_VALUE;
		s[0][0] = folge[0]; // Teilsumme 0 bis 0
		for (int bis = 1; bis < folge.length; bis++) {
			s[0][bis] = s[0][bis - 1] + folge[bis];
			Benchmark.getInstance().summeAllerZugriffe();
		}
		
		// Teilsummen 0 bis 1, ..., 0 bis n-1
		// auf die vorherige Teilsumme wird das n??chste Element addiert
		for (int von = 1; von < folge.length; von++) {
			for (int bis = von; bis < folge.length; bis++) {
				s[von][bis] = s[von - 1][bis] - folge[von - 1];
				Benchmark.getInstance().summeAllerZugriffe();
			}
		}
		
		// Teilsumme f??ngt um ein Element nach rechts an: um dieses Element
		// kleiner
		// Tabelle s wurde gef??llt; jetzt kann das maximale Element gesucht
		// werden:
		for (int von = 0; von < folge.length; von++) {
			for (int bis = 0; bis < folge.length; bis++) {
				if(s[von][bis] > max){
					Benchmark.getInstance().maxTeilsumme(s[von][bis]);
					Benchmark.getInstance().index1(von);
					Benchmark.getInstance().index2(bis);
				}
				max = Math.max(max, s[von][bis]); // Summe ??berpr??fen, ob gr????er
			}
		}
		if (folge.length == 0)
			return 0; // die Summe der leeren Teilfolge ist 0
		else
			return max;
	}

	int algorithmusPure(final int[] folge) {
		
		int[][] s = new int[folge.length][folge.length];
		// Tabelle f??r Teilsummen: f??r (i,j) gilt
		// s[i][j] == Teilsumme i bis j == folge[i]+folge[i+1]+...+folge[j]
		for (int i = 0; i < folge.length; i++)
			for (int j = 0; j < folge.length; j++) {
				s[i][j] = 0;
			}
		
		int max = Integer.MIN_VALUE;
		s[0][0] = folge[0]; // Teilsumme 0 bis 0
		for (int bis = 1; bis < folge.length; bis++) {
			s[0][bis] = s[0][bis - 1] + folge[bis];
		}
		
		// Teilsummen 0 bis 1, ..., 0 bis n-1
		// auf die vorherige Teilsumme wird das n??chste Element addiert
		for (int von = 1; von < folge.length; von++) {
			for (int bis = von; bis < folge.length; bis++) {
				s[von][bis] = s[von - 1][bis] - folge[von - 1];
			}
		}
		
		// Teilsumme f??ngt um ein Element nach rechts an: um dieses Element
		// kleiner
		// Tabelle s wurde gef??llt; jetzt kann das maximale Element gesucht
		// werden:
		for (int von = 0; von < folge.length; von++) {
			for (int bis = 0; bis < folge.length; bis++) {
				max = Math.max(max, s[von][bis]); // Summe ??berpr??fen, ob gr????er
			}
		}
		if (folge.length == 0)
			return 0; // die Summe der leeren Teilfolge ist 0
		else
			return max;
	}

}
