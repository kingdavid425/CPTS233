
public class Heap<T extends Comparable<T>> extends Queue<T> 
{
	
	private Vector<T> _items = new Vector<>(10);
	
	private void buildHeap()
	{
		for (int i = _items.getSize() / 2 - 1; i >= 0; i --)
		{
			adjustHeap(i);
		}
	}
	
	//MA #5 TODO: Implement!
	//Percolates the item specified at by index down into its proper location within a heap.
	//Used for dequeue operations and array to heap conversions
	private void adjustHeap(int index)
	{     
      //index of the specified node and its left and right subtrees
	   int smallest = index;
      int left = (2 * index) + 1;
      int right = (2 * index) + 2;
      
      //used when swapping the nodes; only one is needed but I added an extra one for readability
      T newSmallest, oldSmallest;
      
      //checks to makes sure left node exists to avoid IndexOutOfBoundsException
      if(left < _items.getSize()) {
         //if leftValue < smallestValue
         if(_items.getElementAt(left).compareTo(_items.getElementAt(smallest)) < 0) {
            smallest = left;
         }
      }
      
      //checks to makes sure right node exists to avoid IndexOutOfBoundsException
      if(right < _items.getSize()) {
         //if rightValue < smallestValue
         if(_items.getElementAt(right).compareTo(_items.getElementAt(smallest)) < 0) {
            smallest = right;
         }
      }
      
      //where the swap occurs, if necessary
      if(smallest != index) {  
         oldSmallest = _items.getElementAt(index);
         
         if(smallest == left) { //swapLeft
            newSmallest = _items.getElementAt(left);          
            _items.setElementAt(oldSmallest, left);
         } else {               //swapRight
            newSmallest = _items.getElementAt(right);                        
            _items.setElementAt(oldSmallest, right);
         }
                
         _items.setElementAt(newSmallest, index);
         
         //recursive call
         adjustHeap(smallest);  
      }     
	}
	
	public Heap()
	{
		
	}
	
	public Heap(Vector<T> unsorted)
	{
		for (int i = 0; i < unsorted.getSize(); i ++)
		{
			_items.push(unsorted.getElementAt(i));
		}
		buildHeap();
	}

	@Override
	public T getFirst() {
		// TODO Auto-generated method stub
		return _items.getElementAt(0);
	}

	@Override
	public void enqueue(T item) {
		// TODO Auto-generated method stub
		
		//Adds a new item to the heap
		//calculate positions
		//NOTE: in this MA, we STARTED AT 0!!!!!
		int current_position = _items.getSize();
		int parent_position = (current_position - 1) /2;
		
		//insert element (note: may get erased if we hit the WHILE loop)
		//for simplicity purpose, in this MA, we are using the "swapping" method
		//i.e, insert the item first, then swap it all the way up
		_items.addElement(item);
		
		//get parent element if it exists
		T parent = null;
		if (parent_position >= 0)
		{
			parent = _items.getElementAt(parent_position);
		}
		
		if (parent != null)
		{
			//bubble up
			while (current_position > 0 && item.compareTo(parent) < 0)
			{
				_items.setElementAt(parent, current_position);
				current_position = parent_position;
				parent_position = (current_position - 1) / 2;
				if (parent_position >= 0)
				{
					parent = _items.getElementAt(parent_position);
				}
			}
			
			//after finding the correct location, we can finally place our item
			_items.setElementAt(item, current_position);
		}
	}	

	@Override
	public T dequeue() {
		// TODO Auto-generated method stub
		int last_position = _items.getSize() - 1;
		T last_item = _items.getElementAt(last_position);
		T top = _items.getElementAt(0);
		
		_items.setElementAt(last_item, 0);
		_items.removeElementAt(last_position);
		
		//percolate down after placing the last element in the root for simplicity purpose
		adjustHeap(0);
		return top;
	}

	@Override
	public void addElement(T item) {
		// TODO Auto-generated method stub
		enqueue(item);
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return _items.getSize() == 0;
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return _items.getSize();
	}

}
