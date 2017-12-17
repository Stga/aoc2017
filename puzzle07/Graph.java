import java.util.HashMap;
import java.util.ArrayList;
import java.util.Set;
import java.util.Iterator;

public class Graph {
	private HashMap<String, Node> map;

	public Graph() {
		this.map = new HashMap<String, Node>();
	}

	public void put(String key, Node value) {
		this.map.put(key, value);
	}

	public Node getNode(String key) {
		return this.map.get(key);
	}

	public Set<String> getKeys() {
		return this.map.keySet();
	}

	public boolean containsKey(String key) {
		return map.containsKey(key);
	}

	public void printKeys() {
		Iterator i = map.keySet().iterator();

		while(i.hasNext()) {
			System.out.println(i.next());
		}
	}

	public String getLowestNode() {
		//Start from somewhere in tree based on keySet iterator
		String key = map.keySet().iterator().next();
		Node node = map.get(key);

		return this.findLowestNode(node);
	}

	private String findLowestNode(Node node) {
		ArrayList<Edge> l = node.getAllEdges(); 

		boolean lowest = true;
		Node lowerNode = null;
		for(Edge e : l) {
			if(e.getEndNode().getProgramName() == node.getProgramName()) {
				lowest = false; 
				lowerNode = e.getStartNode();
				break;
			}
		}

		if(lowest) {
			return node.getProgramName();
		} else {
			return this.findLowestNode(lowerNode);
		}
	}
}
