package aufgabe4_zweiter_versuch;






public class Starter {
public static void main(String[] args) {
	
	
	
	Tree a1 = new Tree();
	//Einfügen
	a1.insert(50);
	a1.insert(30);
	a1.insert(70);
	
	a1.insert(25);
	a1.insert(35);
	
	a1.insert(65);
	a1.insert(75);
	
	a1.insert(60);
	
	System.out.println(a1);
	
	//Löschen
	a1.remove(25);
	a1.remove(35);

	System.out.println(a1);

}
}
