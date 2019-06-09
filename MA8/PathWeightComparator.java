import java.util.Comparator;

public class PathWeightComparator implements Comparator<Vertex>{

	@Override
	public int compare(Vertex v1, Vertex v2) {
		return v1.getPathWeight() - v2.getPathWeight(); 
	} 
}
