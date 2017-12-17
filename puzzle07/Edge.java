public class Edge {
	private final Node startNode;
	private final Node endNode;
	
	public Edge(Node startNode, Node endNode) {
		this.startNode = startNode;
		this.endNode = endNode;
	}
	
	public Node getStartNode() {
		return this.startNode;
	}
	
	public Node getEndNode() {
		return this.endNode;
	}
}