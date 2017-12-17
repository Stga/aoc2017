import java.util.ArrayList;

public class Node {
	final private String programName;
	private int weight;
	private ArrayList<Edge> edges;
	
	public Node(String programName, int weight) {
		this.programName = programName;
		this.weight = weight;
		this.edges = new ArrayList<Edge>();
	}

	public String getProgramName() {
		return this.programName;
	}

	public int getWeight() {
		return this.weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	public void addEdge(Edge edge) {
		this.edges.add(edge);
	}
	
	public Edge getEdge(int index) {
		return this.edges.get(index);
	}
	
	public int getEdgeCount() {
		return this.edges.size();
	}
	
	public ArrayList<Edge> getAllEdges() {
		return this.edges;
	}
}