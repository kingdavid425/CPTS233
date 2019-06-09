import java.util.Comparator;
import java.util.HashMap;
import java.util.Objects;

public class Vertex {
	private int _id;
	private static int _id_counter = 0;
	
	HashMap<Vertex, Integer> _edges = new HashMap<>();
	
	//cheater method for tracking path weight
	int _path_weight = 0;
	
	public Vertex()
	{
		_id_counter ++;
		_id = _id_counter;
	}
	
	public Vertex(int id)
	{
		if (id >= _id_counter)
		{
			_id_counter = id + 1;			
		}
		_id = id;
	}
	
	public Vertex(Vertex v)
	{
		_id = v.getId();
		_edges = v.getEdges();
		_path_weight = v.getPathWeight();
	}
	
	public int getPathWeight()
	{
		return _path_weight;
	}
	
	public void setPathWeight(int weight)
	{
		_path_weight = weight;
	}
	
	public int getId()
	{
		return _id;
	}
	
	public void addEdge(Vertex vertex, int weight)
	{
		_edges.put(vertex, weight);
	}
	
	public int getEdgeWeight(Vertex edge)
	{
		return _edges.get(edge);
	}
	
	public HashMap<Vertex, Integer> getEdges()
	{
		return _edges;
	}
	
	public void removeEdge(Vertex vertex)
	{
		_edges.remove(vertex);
	}
	
	//override the equals and hashCode function for hash map purposes
	//so that the Vertices are ONLY identified by their Ids
	//and only use Ids for hashing
	@Override
	public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof Vertex)) {
            return false;
        }

        Vertex v = (Vertex)o;

        return this.getId() == v.getId();
    }
	
	@Override
	public int hashCode() {
		return Objects.hash(this.getId());
	}
}

