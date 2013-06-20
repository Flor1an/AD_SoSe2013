package aufgabe4_zweiter_versuch;

public class Node {

	public int value;
	public int height;
	public Node leftNode;
	public Node rightNode;

	public Node(int val) {
		this.value = val;
		this.height = 0;
		this.leftNode = null;
		this.rightNode = null;
	}

	public String toString() {
		return String.valueOf(value);
	}
	

	public String toInorderString() {

		if (leftNode == null && rightNode == null) {
			return String.valueOf(value) + "(" + height + ")";
		}
		String l = "";
		try {
			l = leftNode.toInorderString();
		} catch (Exception e) {
		}
		String r = "";
		try {
			r = rightNode.toInorderString();
		} catch (Exception e) {
		}

		return " [" + l + " <" + value + "(" + height + ")" + "> " + r + "] ";
	}

}
