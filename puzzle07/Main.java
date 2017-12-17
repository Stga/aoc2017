import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.Iterator;
import java.util.ArrayList; 

public class Main {
	public static void main(String[] args) {
		try {
			Graph graph = new Graph();
			BufferedReader br = new BufferedReader(new FileReader("input.txt"));
			String line;


			while((line=br.readLine()) != null) {
				final String[] splitString = line.split("->");

				final String lowerProgramName = splitString[0].split(" ")[0];

				final String s = splitString[0].split(" ")[1];
				final int weight = Integer.parseInt(s.substring(1, s.length()-1));
				
				//If the graph contains bottom program key it has been inserted
				//already with a 0 weight in order to create edges, update it,
				//otherwise create a new Node with its given name/weight.
				if(graph.containsKey(lowerProgramName)) {
					graph.getNode(lowerProgramName).setWeight(weight);
				} else {
					Node node = new Node(lowerProgramName, weight);
					graph.put(lowerProgramName, node);
				}

				Node lowerNode = graph.getNode(lowerProgramName);

				//If the lower program is holding any other programs above it,
				//create and edge if they exist in the graph, otherwise create
				//these with a weight of zero and add the edge.
				if(splitString.length > 1) {
					String[] higherPrograms = splitString[1].split(",");
					
					for(String higherProgram : higherPrograms) {
						String s2 = higherProgram.substring(1, higherProgram.length());

						Node higherNode = null;

						if(graph.containsKey(s2)) {
							higherNode = graph.getNode(s2);
						} else {
							higherNode = new Node(s2, 0);
							graph.put(s2, higherNode);
						}

						Edge edge = new Edge(graph.getNode(lowerProgramName), higherNode);
						
						lowerNode.addEdge(edge);
						higherNode.addEdge(edge);
					}
				}
			}

			String lowestNodeName = graph.getLowestNode();
			System.out.println(lowestNodeName);

		} catch(IOException e) {
			System.out.println(e);
		}
	}
}
