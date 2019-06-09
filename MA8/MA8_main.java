import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MA8_main {

	public static void main(String[] args) {
		graphTest();
	}
	
	public static void graphTest()
	{
		//populate graph
		Graph graph = new Graph();
		List<Vertex> vertices = new ArrayList<>();
		
		//generate vertices
		for (int i = 0; i < 4; i ++)
		{
			vertices.add(new Vertex());
		}
		
		/*
		Graph: 0 -> 1 (weight 4)
	           0 -> 3 (weight 20)
		   	   1 -> 2 (weight 4)
		       1 -> 3 (weight 8)
		       2 -> 3 (weight 15)
		 */
		vertices.get(0).addEdge(vertices.get(1), 4);
		vertices.get(0).addEdge(vertices.get(3), 20);
		vertices.get(1).addEdge(vertices.get(2), 4);
		vertices.get(1).addEdge(vertices.get(3), 8);
		vertices.get(2).addEdge(vertices.get(3), 15);
		
		//add vertices to graph
		for(Vertex vertex: vertices)
		{
			graph.addVertex(vertex);
		}
		
		HashMap<Vertex, Integer> distances = graph.computeShortestPath(vertices.get(0));
		System.out.println("Distance from 0 to 0: " + distances.get(vertices.get(0)) + " (expected: 0)");
		System.out.println("Distance from 0 to 1: " + distances.get(vertices.get(1)) + " (expected: 4)");
		System.out.println("Distance from 0 to 2: " + distances.get(vertices.get(2)) + " (expected: 8)");
		System.out.println("Distance from 0 to 3: " + distances.get(vertices.get(3)) + " (expected: 12)");		
	}

}
