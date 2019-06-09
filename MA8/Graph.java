import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;

public class Graph {
	HashMap<Integer, Vertex> _vertices = new HashMap<>();
	
	public void addVertex(Vertex vertex)
	{
		_vertices.put(vertex.getId(), vertex);
	}
	
	//MA #8 TODO: IMPLEMENT!
	public HashMap<Vertex, Integer> computeShortestPath(Vertex start)
	{		
		HashMap<Vertex, Integer> distances = new HashMap<>(); //holds known distances	
		PriorityQueue<Vertex> dijkstra_queue = new PriorityQueue<>(new PathWeightComparator()); //underlying heap     
      List<Vertex> visited = new ArrayList<Vertex>(); //keeps track of vertices we have already examined
      
      int weight; //pathWeight; added for readability
      Vertex current;
      
      //      _id   , pathWeight
      HashMap<Vertex, Integer> currentEdges; //holds the vertices and their pathweights that are attached to current
      
      //make sure that the starting vertex is in the graph
      if(!start.equals(null)) {     
         //start with a vertex
         //push on starting vertex
         distances.put(start, 0);
         start.setPathWeight(0);
         dijkstra_queue.add(start);
           
         //while queue not empty
         while(!dijkstra_queue.isEmpty()) {
            current = dijkstra_queue.poll();   //pop next vertex
            currentEdges = current.getEdges(); //get the edges attached to the vertex
            
            current.setPathWeight(distances.get(current)); //set currents pathweight according to
                                                           //the value in the distances table
                        
            for(Vertex v : currentEdges.keySet()) {
               weight = current.getPathWeight() + currentEdges.get(v);
               
               if(distances.containsKey(v)) {                              
                  if(distances.get(v) > weight) {
                     distances.remove(v);
                     distances.put(v, weight);
                  }  
               } else distances.put(v, weight); //!distances.containsKey(v)  
                                  
               //if we have not examined one of the edges we are looking at, we want to add it to
               //the queue to examine in turn
               if(!visited.contains(v)) dijkstra_queue.add(v);          
            }                      
            visited.add(current); //we have now done what we want with the current vertex, we do not want to 
                                  //examine it again and so we had it to the visited list
         }
      }		
		return distances;
	}
}