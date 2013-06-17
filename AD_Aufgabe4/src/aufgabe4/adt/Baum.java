package aufgabe4.adt;

import aufgabe4.helper.*;

/**
 * File: AVLbaum.java
 * 
 * @author Robert ?
 */
public class Baum {
	Integer knotenAnzahl = 0;
	Integer anzahlLinksRotation = 0;
	Integer anzahlRechtsRotation = 0;
	Knoten wurzel; // Zeiger auf die Wurzel des AVL-Baumes
	Knoten z; // Zeiger auf den neuen Knoten,an der Stelle um die rotiert wurde
	Logger log;

	public Baum() {
		wurzel = null;

		log = new Logger("avl.log");
		log.writeToFile("========= Den Baum mit Zahlen Befüllen =========");
	}

	/**
	 * Fügt einem AVL Baum den übergebenen Wert ein und rotiert falls nötig.
	 * 
	 * @param elem
	 *            Wert der eingefügt werden soll.
	 */
	public void elementEinfuegen(int elem) {
		System.out.println("Knoten " + elem + " wird eingefügt...");
		log.writeToFile("Knoten " + elem + " wird eingefügt...");

		// falls der Baum leer ist:
		if (wurzel == null) {
			wurzel = new Knoten(elem, null, null, null, 0);
			return;
		}
		knotenAnzahl++;
		// sonst beginne bei der Wurzel:
		Knoten aktuellerKnoten = wurzel;

		while (true) {

			// Schluessel bereits im Baum enthalten:
			if (elem == aktuellerKnoten.getContent()) {
				System.out.println(">  Der Schluessel " + elem
						+ " ist bereits enthalten!");
				return;
			}

			// Schluessel einfuegen:
			if (elem <= aktuellerKnoten.getContent()) {
				if (aktuellerKnoten.getLinkerSohn() == null) {
					aktuellerKnoten.setLinkerSohn(new Knoten(elem,
							aktuellerKnoten, null, null, 0));
					balanceFaktor(aktuellerKnoten);
					return;
				} else
					aktuellerKnoten = aktuellerKnoten.getLinkerSohn();
			} else {
				if (aktuellerKnoten.getRechterSohn() == null) {
					aktuellerKnoten.setRechterSohn(new Knoten(elem,
							aktuellerKnoten, null, null, 0));
					balanceFaktor(aktuellerKnoten);
					return;
				} else
					aktuellerKnoten = aktuellerKnoten.getRechterSohn();
			}
		}

	}

	/**
	 * Löscht in einem AVL Baum den übergebenen Wert und rotiert falls nötig.
	 * 
	 * @param elem
	 *            Wert der gelöscht werden soll.
	 */
	public void elementLoeschen(int elem) {
		System.out.println("Knoten " + elem + " wird gelöscht...");
		log.writeToFile("Knoten " + elem + " wird gelöscht...");

		Knoten aktuellerKnoten = wurzel;

		while (true) {

			// Schluessel bereits im Baum enthalten:
			if (elem == aktuellerKnoten.getContent()) {

				Knoten tmp = getGroessterVonLinks(aktuellerKnoten);
				aktuellerKnoten.setContent(tmp.getContent());


				balanceFaktor(wurzel);

				return;
			}

			// Schluessel einfuegen:
			if (elem <= aktuellerKnoten.getContent()) {
				if (aktuellerKnoten.getLinkerSohn() == null) {
					// aktuellerKnoten.setLinkerSohn(new Knoten(elem,
					// aktuellerKnoten, null, null, 0));
					// balanceFaktor(aktuellerKnoten);
					return;
				} else
					aktuellerKnoten = aktuellerKnoten.getLinkerSohn();
			} else {
				if (aktuellerKnoten.getRechterSohn() == null) {
					// aktuellerKnoten.setRechterSohn(new Knoten(elem,
					// aktuellerKnoten, null, null, 0));
					// balanceFaktor(aktuellerKnoten);
					return;
				} else
					aktuellerKnoten = aktuellerKnoten.getRechterSohn();
			}
		}
	}

	public Knoten getGroessterVonLinks(Knoten k) {
		Knoten aktuellerKnoten;
		if (k.getLinkerSohn() != null) {
			aktuellerKnoten = k.getLinkerSohn();
		} else {
			aktuellerKnoten = k.getRechterSohn();
		}

		while (true) {
			try {
				if (aktuellerKnoten.getRechterSohn() == null) {
					Knoten tmp = aktuellerKnoten;
					
					if(aktuellerKnoten.getContent() == aktuellerKnoten.getVaterKnoten().getRechterSohn().getContent()){//rechts
						System.out.println("r " +aktuellerKnoten.getContent() + "  +  " + aktuellerKnoten.getVaterKnoten().getRechterSohn().getContent() );
			
						try {
							aktuellerKnoten.getVaterKnoten().setRechterSohn(aktuellerKnoten.getRechterSohn());
						} catch (Exception e) {
							aktuellerKnoten.getVaterKnoten().setRechterSohn(null);
						}
						
						return tmp;
					} else if (aktuellerKnoten.getContent() == aktuellerKnoten.getVaterKnoten().getLinkerSohn().getContent()){//links
						System.out.println("l " +aktuellerKnoten.getContent() + "  +  " + aktuellerKnoten.getVaterKnoten().getLinkerSohn().getContent() );
						try {
							aktuellerKnoten.getVaterKnoten().setLinkerSohn(aktuellerKnoten.getLinkerSohn());
						} catch (Exception e) {
							aktuellerKnoten.getVaterKnoten().setLinkerSohn(null);
						}
						
						return tmp;
					}
					
					//return aktuellerKnoten;
				} else {
					aktuellerKnoten = aktuellerKnoten.getRechterSohn();
				}
			} catch (Exception e) {
				if(k.getLinkerSohn()==null && k.getRechterSohn()==null){
					if(k.getVaterKnoten().getRechterSohn()==k){
						System.err.println("asd");
						k.getVaterKnoten().setRechterSohn(null);
						return k;
					}else if(k.getVaterKnoten().getLinkerSohn()==k){
						System.err.println("bla");
						k.getVaterKnoten().setLinkerSohn(null);
						return k;
					}
					
				}
			}
		}
	}

	public void deleteGroessterVonLinks(Knoten k) {
		Knoten aktuellerKnoten;
		if (k.getLinkerSohn() != null) {
			aktuellerKnoten = k.getLinkerSohn();
		} else {
			aktuellerKnoten = k.getRechterSohn();
		}
		
		while(true){
			try {//rechter
				if (aktuellerKnoten.getContent()==(int)Double.POSITIVE_INFINITY){
					aktuellerKnoten.getVaterKnoten().setRechterSohn(null);
					System.out.println("### rechts sucsess");
					return;
				}
				aktuellerKnoten = aktuellerKnoten.getRechterSohn();
			} catch (Exception e) {
				// TODO: handle exception
				break;
			}
			
						
		}
		
		while(true){
			try {//linker
				if (aktuellerKnoten.getContent()==(int)Double.POSITIVE_INFINITY){
					aktuellerKnoten.getVaterKnoten().setLinkerSohn(null);
					System.out.println("### links sucsess");
					return;
				}
				aktuellerKnoten = aktuellerKnoten.getLinkerSohn();
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
		

//		while (true) {
//			try {
//				aktuellerKnoten = aktuellerKnoten.getRechterSohn();
//				if (aktuellerKnoten.getContent() == (int) Double.POSITIVE_INFINITY) {
//					try {
//						aktuellerKnoten.getVaterKnoten().setRechterSohn(
//								aktuellerKnoten.getLinkerSohn());
//						return;
//					} catch (Exception e) {
//						aktuellerKnoten.getVaterKnoten().setRechterSohn(null);
//						return;
//					}
//				}
//			} catch (Exception e) {
//				System.err.println(aktuellerKnoten);
//
//				break;
//			}
//		}
//
//		while (true) {
//			try {
//				aktuellerKnoten = aktuellerKnoten.getLinkerSohn();
//				if (aktuellerKnoten.getContent() == (int) Double.POSITIVE_INFINITY) {
//					aktuellerKnoten.getVaterKnoten().setLinkerSohn(null);
//					return;
//				}
//			} catch (Exception e) {
//				System.err.println(aktuellerKnoten);
//				break;
//			}
//		}

		// Knoten aktuellerKnoten;
		// if(k.getLinkerSohn()!=null){
		// aktuellerKnoten = k.getLinkerSohn();
		// }else{
		// aktuellerKnoten=k.getRechterSohn();
		//
		// }
		// while(true){
		// if (aktuellerKnoten.getRechterSohn() != null){
		// if(aktuellerKnoten.getRechterSohn().getRechterSohn()==null){
		// aktuellerKnoten.setRechterSohn(null);
		// return;
		// }else{
		// aktuellerKnoten = aktuellerKnoten.getRechterSohn();
		// }}else{
		// try {
		// k.setLinkerSohn(k.getLinkerSohn().getLinkerSohn());
		// return;
		// } catch (Exception e) {
		// k.setRechterSohn(null);
		// return;
		// }
		//
		// }
		// }
	}

	/**
	 * Berechnet die Balance Faktoren rekusiv.
	 * 
	 * @param knoten
	 */
	private void balanceFaktor(Knoten knoten) {

		// falls ich in der Wurzel bin:
		if (knoten == wurzel) {
			// Balancewerte anpassen:(vereinfacht)
			knoten.setBalanceFaktor(Tiefe(knoten.getLinkerSohn()) - (Tiefe(knoten.getRechterSohn())));
			// eventuell Rotieren um die AVL-Bedingung widerherzustellen:
			if (Math.abs(knoten.getBalanceFaktor()) >= 2)
				rotiere(knoten);
			return;
		}

		// Balancewerte anpassen:(vereinfacht)
		knoten.setBalanceFaktor((Tiefe(knoten.getRechterSohn()) - Tiefe(knoten
				.getLinkerSohn())));

		// eventuell Rotieren um die AVL-Bedingung widerherzustellen:
		if (Math.abs(knoten.getBalanceFaktor()) >= 2) {
			rotiere(knoten);
			knoten = z;
		}

		// rekursiver Aufruf von upin für k.v:
		try {
			balanceFaktor(knoten.getVaterKnoten());
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	/**
	 * Prüft auf mögliche Rotation basierend auf den Balance Faktoren (wenn
	 * dieser => 2)
	 * 
	 * @param knoten
	 *            Knoten von dem die Überprüfung ausgeht.
	 */
	public void rotiere(Knoten knoten) {

		Integer b = knoten.getBalanceFaktor();

		Integer bR = 0;
		Integer bL = 0;

		try {
			bR = knoten.getRechterSohn().getBalanceFaktor();
		} catch (Exception e) {
			// TODO: handle exception
			bR=Integer.MIN_VALUE;
		}

		try {
			bL = knoten.getLinkerSohn().getBalanceFaktor();
		} catch (Exception e) {
			// TODO: handle exception
			bL=Integer.MIN_VALUE;
		}

		if (Math.abs(b) == 2) {
			if (bR == 1|| bL == Integer.MIN_VALUE) { // bL ist richtig !
				linksRotation(knoten);
				return;
			}
			if (bR == -1) {
				doppelLinksrotation(knoten);
				return;
			}
			if (bL == -1 || bR == Integer.MIN_VALUE) { //bR ist richtig !
				rechtsRotation(knoten);
				return;
			}
			if (bL == 1) {
				doppelRechtsrotation(knoten);
				return;
			}
		}

	}

	/**
	 * Rechtsrotation
	 * 
	 * @param knoten
	 *            Knoten bei dem der Balance Faktor = 2 ist.
	 */
	private void rechtsRotation(Knoten knoten) {
		/*----------------------------------------
		 	v
		    |
		    k                    v  
		   /                     | 
		  l          =>          l
		 /                      / \
		ll                     ll   k
		 ----------------------------------------*/
		System.out.println("Rechtsrotation bei Wert: " + knoten.getContent());
		log.writeToFile("Rechtsrotation bei Wert: " + knoten.getContent());

		anzahlRechtsRotation++;
		Boolean linkerSohn = null;
		try {

			if (knoten.getContent() == knoten.getVaterKnoten().getLinkerSohn()
					.getContent()) { // LINKS
				linkerSohn = true;
			} else if (knoten.getContent() == knoten.getVaterKnoten()
					.getRechterSohn().getContent()) {
				linkerSohn = false;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		Knoten v = knoten.getVaterKnoten();

		Knoten k = knoten;

		Knoten l = knoten.getLinkerSohn();
		Knoten ll = knoten.getLinkerSohn().getLinkerSohn();

		try {
			k.setLinkerSohn(k.getLinkerSohn().getRechterSohn());
		} catch (Exception e) {
			k.setLinkerSohn(null);
		}

		// k.setRechterSohn(null);
		k.setVaterKnoten(l);

		l.setLinkerSohn(ll);
		l.setRechterSohn(k);
		l.setVaterKnoten(v);

		try {
			if (linkerSohn) {
				v.setLinkerSohn(l);
			} else {
				v.setRechterSohn(l);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		try {
			if (k.getVaterKnoten().getVaterKnoten() == null)
				wurzel = k.getVaterKnoten();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	/**
	 * Linksrotation
	 * 
	 * @param knoten
	 *            Knoten bei dem der Balance Faktor = 2 ist.
	 */
	private void linksRotation(Knoten knoten) {
		/*----------------------------------------
		 v
		/ \
		 k                        v
		  \                      / \
		   r          =>          r
		    \                    / \
		     rr                 k   rr
		 ----------------------------------------*/
		System.out.println("Linksrotation bei Wert: " + knoten.getContent());
		log.writeToFile("Linksrotation bei Wert: " + knoten.getContent());
		anzahlLinksRotation++;
		Boolean linkerSohn = null;
		try {
			if (knoten.getContent() == knoten.getVaterKnoten().getLinkerSohn()
					.getContent()) { // LINKS
				linkerSohn = true;
			} else if (knoten.getContent() == knoten.getVaterKnoten()
					.getRechterSohn().getContent()) {
				linkerSohn = false;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		Knoten v = knoten.getVaterKnoten();

		Knoten k = knoten;
		Knoten r = knoten.getRechterSohn();
		Knoten rr = knoten.getRechterSohn().getRechterSohn();
		try {
			k.setRechterSohn(k.getRechterSohn().getLinkerSohn());
		} catch (Exception e) {
			k.setRechterSohn(null);
		}

		// k.setLinkerSohn(null);
		k.setVaterKnoten(r);
		k.setBalanceFaktor(0);

		r.setRechterSohn(rr);
		r.setLinkerSohn(k);
		r.setVaterKnoten(v);

		r.setBalanceFaktor(0);

		try {
			if (linkerSohn) {
				v.setLinkerSohn(r);
			} else {
				v.setRechterSohn(r);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		try {
			if (k.getVaterKnoten().getVaterKnoten() == null)
				wurzel = k.getVaterKnoten();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	/**
	 * Doppelte Rechtsrotation (Ruft die Rechtsrotation intern auf)
	 * 
	 * @param knoten
	 *            Knoten bei dem der Balance Faktor = 2 ist.
	 */
	private void doppelRechtsrotation(Knoten knoten) {
		/*---------------------------------------
		 v                      v
		 |                      |
		 k                      k
		/                      /   
		l            =>        r  
		\     				 /
		 r					l
		 ---------------------------------------*/
		System.out.println("Doppel Rechtsrotation bei Wert: "
				+ knoten.getContent());
		log.writeToFile("Doppel Rechtsrotation bei Wert: "
				+ knoten.getContent());

		Knoten v = knoten.getVaterKnoten();

		Knoten k = knoten;

		Knoten l = knoten.getLinkerSohn();
		Knoten r = knoten.getLinkerSohn().getRechterSohn();

		k.setLinkerSohn(r);
		r.setLinkerSohn(l);
		l.setRechterSohn(null);

		rechtsRotation(k); // übergabe an "normale" Rechtsrotation

	}

	/**
	 * Doppelte Linksrotation (Ruft die Linksrotation intern auf)
	 * 
	 * @param knoten
	 *            Knoten bei dem der Balance Faktor = 2 ist.
	 */
	private void doppelLinksrotation(Knoten knoten) {
		/*---------------------------------------
		 v                      v
		 |                      |
		 k                      k
		  \                      \   
		   r            =>        l  
		  /     				   \
		 l					        r
		 ---------------------------------------*/
		System.out.println("Doppel Linksrotation bei Wert: "
				+ knoten.getContent());
		log.writeToFile("Doppel Linksrotation bei Wert: " + knoten.getContent());

		Knoten v = knoten.getVaterKnoten();

		Knoten k = knoten;

		Knoten r = knoten.getRechterSohn();
		Knoten l = knoten.getRechterSohn().getLinkerSohn();

		r.setLinkerSohn(null);
		l.setRechterSohn(r);
		k.setRechterSohn(l);

		linksRotation(k); // übergabe an "normale" Linksrotation

	}

	/**
	 * Berechnet rekusiv die Tiefe
	 * 
	 * @param knoten
	 *            Knoten für den die Tiefe berechnet werden soll
	 * @return gibt die Tiefe zurueck
	 */
	private int Tiefe(Knoten knoten) {
		if (knoten == null)
			return 0;

		Knoten l = knoten.getLinkerSohn();
		Knoten r = knoten.getRechterSohn();

		return 1 + (int) Math.max(Tiefe(l), Tiefe(r));
	}

	public void preorder() {
		System.out.print("preorder:");
		preorder(wurzel);
		System.out.println("");
	}

	public void preorder(Knoten knoten) {
		if (knoten != null) {
			System.out.print(knoten.getContent() + " ");
			preorder(knoten.getLinkerSohn());
			preorder(knoten.getRechterSohn());
		}
	}

	public void inorder() {
		System.out.print("inorder:");
		inorder(wurzel);
		System.out.println("");
	}

	public void inorder(Knoten knoten) {
		if (knoten != null) {
			inorder(knoten.getLinkerSohn());
			System.out.print(knoten.getContent() + " ");
			inorder(knoten.getRechterSohn());
		}
	}

	public void postorder() {
		System.out.print("postorder:");
		postorder(wurzel);
		System.out.println("");
	}

	public void postorder(Knoten knoten) {
		if (knoten != null) {
			postorder(knoten.getLinkerSohn());
			postorder(knoten.getRechterSohn());
			System.out.print(knoten.getContent() + " ");
		}
	}

	public String toString() {
		return wurzel.toString();
	}

	public Knoten getWurzel() {
		return wurzel;
	}

	public int getKnotenAnzahl() {
		return knotenAnzahl;
	}

	/**
	 * Berechnet für einen Teilbaum die Summe der Elemente
	 * 
	 * @param k
	 *            Knoten ab dem die Teilsumme berechnet werden soll
	 * @return
	 */
	public int summeTeilbaum(Knoten k) {
		int summe = 0;
		try {
			summe += summeTeilbaum(k.getLinkerSohn());
			summe += summeTeilbaum(k.getRechterSohn());
			return summe += k.getContent();
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * Führt eine Postorder Addition durch
	 * 
	 * @return
	 */
	public int summePost() {
		// SummePost als Postorder Addition: Implementieren Sie eine Methode,
		// die die Summe der Schlüssel in einem gegebenen AVL-Baum in
		// Postorderweise (Summe Linker Teilbaum + Summe Rechter Teilbaum +
		// Knoten) bestimmt.
		return summeTeilbaum(getWurzel().getLinkerSohn())
				+ summeTeilbaum(getWurzel().getRechterSohn())
				+ getKnotenAnzahl();
	}

	/**
	 * Führt eine Preorder Addition durch
	 * 
	 * @return
	 */
	public int summePre() {
		// SummePre als Preorder Addition: Implementieren Sie eine Methode, die
		// die Summe der Schlüssel in einem gegebenen AVL-Baum in Preorderweise
		// (Knoten + Summe Linker Teilbaum + Summe Rechter Teilbaum) bestimmt.
		return getKnotenAnzahl() + summeTeilbaum(getWurzel().getLinkerSohn())
				+ summeTeilbaum(getWurzel().getRechterSohn());
	}
}