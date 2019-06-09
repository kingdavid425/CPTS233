import java.util.HashMap;
import java.util.List;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.*;

public class DisjointSet<T> {
	//our underlying data structure is a hash table
	//breakdown:
	//T - item to be stored (Character in our case)
	//SimpleEntry<Integer, T>> - used to store the value in the box (see class notes).  
	//                   A: When the item is a root (set end-point), the int value should 
	//                      be set to a negative value.
	//                      When the item is not a root, the int value should be positive.
	//                   B: When the item is not a root (set end-point), the T value
	//                      should be set to the item's "parent" in the set
	// A really complete example of how to manipulate this data structure can be found in my
	// "find" function.
	HashMap<T, SimpleEntry<Integer, T>> _sets = new HashMap<>();
	
	//recursive solution to path compression. Slower than iterative approach
	private T pathCompressionHelper(T start)
	{
		SimpleEntry<Integer, T> result = _sets.get(start);
		if (result != null && result.getKey() > 0)
		{
			T root = pathCompressionHelper(result.getValue());
			result.setValue(root);
			return root;			
		}
		else
		{
			return start;
		}
	}
	
	//for benchmarking different types of disjoint sets
	public boolean use_path_compression = false;
		
	public void union_with(T first, T second)
	{
		//#1: call find on both items in the set.  This will return a pointer to the root
		//    of each item's set.  From here, you can...
      T a = find(first);
      T b = find(second);
      
      //store the sizes into integers
      int sizeA, sizeB, newSize;
      
      sizeA = _sets.get(a).getKey();
      sizeB = _sets.get(b).getKey();
      
      //if first and second are in the same tree, do nothing
      if(a == b) return;
      
      //else, preform union by size
      newSize = sizeA + sizeB;
      
      //since sizes of roots are negative numbers we use <
      if(sizeA <= sizeB) 
      {
         //parent:
         //       size = sizeA + sizeB
         //       parent remains the same
         //notParent:
         //       size = oldSize * -1
         //       parent = a
         SimpleEntry<Integer, T> parent = new SimpleEntry<Integer, T>(newSize, _sets.get(a).getValue());         
         SimpleEntry<Integer, T> notParent = new SimpleEntry<Integer, T>(-1 * sizeB, a);
         
         _sets.replace(a, parent);
         _sets.replace(b, notParent);                 
      } 
      else 
      {
         //parent:
         //       size = sizeA + sizeB
         //       parent remains the same
         //notParent:
         //       size = oldSize * -1
         //       parent = b
         SimpleEntry<Integer, T> parent = new SimpleEntry<Integer, T>(newSize, _sets.get(b).getValue());
         SimpleEntry<Integer, T> notParent = new SimpleEntry<Integer, T>(-1 * sizeA, b);
         
         _sets.replace(b, parent);
         _sets.replace(a, notParent);      
      }
	}
 	
	public T find(T start)
	{
		//find item in set
		SimpleEntry<Integer, T> result = _sets.get(start);
		
		//Use the result to "move up" the set's tree.
		//We continue as long as the "getValue().getKey()" is positive.
		while (result != null && result.getKey() > 0)
		{
			result = _sets.get(result.getValue());
		}
		
		//performs path compression (ignore for MA #7)
		if (use_path_compression == true)
		{
			//unroll all items along the way
			SimpleEntry<Integer, T> compression_result = _sets.get(start);
			while(compression_result != null && compression_result.getValue() != result.getValue())
			{
				compression_result.setValue(result.getValue());
				compression_result = _sets.get(compression_result.getValue());
			}
		}
		
		//if result is null, we need to add the item to our set
		if (result == null)
		{
			SimpleEntry<Integer, T> temp = new SimpleEntry<Integer, T>(-1, start);
			_sets.put(start, temp);
			
			result = _sets.get(start);
		}
		
		//finally, return a pointer to the item's "root" (send end-point)
		return (result.getValue());			
	}
	
	//Gets all roots in the set
	List<T> getRoots()
	{
		List<T> roots = new ArrayList<>();
		for (SimpleEntry<Integer, T> item : _sets.values())
		{
			if (item.getKey() < 0)
			{
				roots.add(item.getValue());
			}
		}
		return roots;
	}
}
