package aufgabe4_zweiter_versuch;



public class Tree {

	public Node root;
	static Logger log;
	
	public Tree() {
		this.root = null;
		
		log = new Logger("avl.log");
		log.writeToFile("========= Den Baum mit Zahlen Befüllen =========");
	}

	public void insert(int n) {
		this.root = insertLocal(root, n);
	}

	

	public Node remove(int n) {

		 return root= this.remove(this.root, n);

	}

	public int getPreOrderSum(Node k) {
		if ((k.leftNode == null) && (k.rightNode == null))
			return k.value;
		if (k.leftNode == null)
			return k.value + getPreOrderSum(k.rightNode);
		if (k.rightNode == null)
			return k.value + getPreOrderSum(k.leftNode);
		return k.value + getPreOrderSum(k.leftNode) + getPreOrderSum(k.rightNode);
	}

	public int getPostOrderSum(Node k) {
		if ((k.leftNode == null) && (k.rightNode == null))
			return k.value;
		if (k.leftNode == null)
			return k.value + getPostOrderSum(k.rightNode);
		if (k.rightNode == null)
			return k.value + getPostOrderSum(k.leftNode);
		return getPostOrderSum(k.leftNode) + getPostOrderSum(k.rightNode) + k.value;
	}

	public Integer getBalanceFaktor(Node k) {
		return getHeight(k.rightNode) - getHeight(k.leftNode);
	}

	private Node insertLocal(Node k, int n) {

		if (k == null) {
			return new Node(n);
		}
		if (n < k.value) {
			k.leftNode = insertLocal(k.leftNode, n);
			log.writeToFile(n + " wird eingefuegt...");
			//System.out.println(n + " nach " + k + " links eingefuegt");
			if (getHeight(k.leftNode) - getHeight(k.rightNode) == 2) {
				if (getHeight(k.leftNode.leftNode) >= getHeight(k.leftNode.rightNode))
					k = simpleRightRotation(k);
				else
					k = doubleRightRotation(k);
			}
		} else if (n >= k.value) {
			k.rightNode = insertLocal(k.rightNode, n);
			//System.out.println(n + " nach " + k + " rechts eingefuegt");
			log.writeToFile(n + " wird eingefuegt...");
			if (getHeight(k.rightNode) - getHeight(k.leftNode) == 2) {
				if (getHeight(k.rightNode.rightNode) >= getHeight(k.rightNode.leftNode))
					k = simpelLeftRotation(k);
				else
					k = doubleLeftRotation(k);
			}
		}
		k.height = Math.max(getHeight(k.leftNode), getHeight(k.rightNode)) + 1;

		return k;
	}

	private Node linkerAstMax(Node node) {

		Node lam = node.leftNode;

		while (lam.rightNode != null) {
			lam = lam.rightNode;
		}

		return lam;
	}

	private Node remove(Node node, Integer n) {

		if (n == node.value) {
			if (node.leftNode == null && node.rightNode == null) { // links nein rechts nein
				return null;

			} else if (node.leftNode == null && node.rightNode != null) { // links nein rechts ja
				return node.rightNode;

			} else if (node.leftNode != null && node.rightNode == null) { // links ja rechts nein
				return node.leftNode;

			} else { // links ja rechts ja
				
					Node maxNode = this.linkerAstMax(node);
					Integer newValue = maxNode.value;
					this.remove(newValue);
					node.value = newValue;
					return node;

			}
		} else if (n > node.value && node.rightNode != null) {
			node.rightNode= this.remove(node.rightNode, n);
		} else if (n <= node.value && node.leftNode != null) {
			node.leftNode =  this.remove(node.leftNode, n);
		}
//
		if (Math.abs(getHeight(node.leftNode) - getHeight(node.rightNode)) == 2) {
			if (getHeight(node.leftNode.leftNode) >= getHeight(node.leftNode.rightNode))
				node = simpleRightRotation(node);
			else
				node = doubleRightRotation(node);
		}
		return node;
	}

	private static Node simpleRightRotation(Node k) {
		//System.out.println("Rechtsrotation an Node: " + k);
		log.writeToFile("Rechtsrotation an Node: " + k);
		Node neu = k.leftNode;
		k.leftNode = k.leftNode.rightNode;
		neu.rightNode = k;
		k.height = Math.max(getHeight(k.rightNode), getHeight(k.leftNode)) + 1;
		neu.height = Math.max(getHeight(neu.rightNode), getHeight(neu.leftNode)) + 1;

		return neu;
	}

	private static Node simpelLeftRotation(Node k) {
		//System.out.println("Linksrotation an Node: " + k);
		log.writeToFile("Linksrotation an Node: " + k);
		Node neu = k.rightNode;
		k.rightNode = k.rightNode.leftNode;
		neu.leftNode = k;
		k.height = Math.max(getHeight(k.rightNode), getHeight(k.leftNode)) + 1;
		neu.height = Math.max(getHeight(neu.rightNode), getHeight(neu.leftNode)) + 1;

		return neu;
	}

	private static Node doubleLeftRotation(Node k) {
		//System.out.println("Doppelterechtsrotation an Node: " + k);
		log.writeToFile("Doppelterechtsrotation an Node: " + k);
		k.rightNode = simpleRightRotation(k.rightNode);
		return simpelLeftRotation(k);
	}

	private static Node doubleRightRotation(Node k) {
		//System.out.println("Doppeltelinksrotation an Node: " + k);
		log.writeToFile("Doppeltelinksrotation an Node: " + k);
		k.leftNode = simpelLeftRotation(k.leftNode);
		return simpleRightRotation(k);
	}

	private static int getHeight(Node k) {
		if (k == null)
			return -1;
		else
			return k.height;
	}

	public String toString() {
		return root.toInorderString();
	}
	
	public String toInorderString() {
		return root.toInorderString();
	}


}