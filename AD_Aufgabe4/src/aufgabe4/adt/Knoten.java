package aufgabe4.adt;

public class Knoten {
	private Integer content;
	private Knoten linkerSohn;
	private Knoten rechterSohn;
	private Knoten vaterKnoten;
	private Integer balanceFaktor;

	public Knoten(int content, Knoten vaterKnoten, Knoten linkerSohn,
			Knoten rechnerSohn, Integer balanceFaktor) {
		this.setContent(content);
		this.setVaterKnoten(vaterKnoten);
		this.setLinkerSohn(linkerSohn);
		this.setRechterSohn(rechnerSohn);
		this.setBalanceFaktor(balanceFaktor);
	}

	public Integer getContent() {
		return content;
	}

	public void setContent(Integer content) {
		this.content = content;
	}

	public Knoten getLinkerSohn() {
		return linkerSohn;
	}

	public void setLinkerSohn(Knoten linkerSohn) {
		this.linkerSohn = linkerSohn;
	}

	public Knoten getRechterSohn() {
		return rechterSohn;
	}

	public void setRechterSohn(Knoten rechterSohn) {
		this.rechterSohn = rechterSohn;
	}

	public Knoten getVaterKnoten() {
		return vaterKnoten;
	}

	public void setVaterKnoten(Knoten vaterKnoten) {
		this.vaterKnoten = vaterKnoten;
	}

	public Integer getBalanceFaktor() {
		return balanceFaktor;
	}

	public void setBalanceFaktor(int balanceFaktor) {
		this.balanceFaktor = balanceFaktor;
	}

	public String toString() {
		return " [" + getLinkerSohn() + "<" + getContent() + ">" 	+ getRechterSohn() + "] ";	
	}
	
	public String toInOrderString() {
		String l="";
		try {
			l=getLinkerSohn().toInOrderString();
		} catch (Exception e) {
			// TODO: handle exception
		}
		String r="";
		try {
			r=getRechterSohn().toInOrderString();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return l + " " + getContent() + " " + r ;	
	}
}