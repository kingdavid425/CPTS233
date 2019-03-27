import java.util.List;

//This class implements both the Vector and Stack functionalities. 
public class Vector<T> extends Array<T> {

	//readjusts the vector to a new size
	private void adjustSize(int new_size)
	{
		//is the new size within bounds?
		if (new_size < 0)
		{
			throw new IndexOutOfBoundsException("new_size is less than zero");
		}
		
		int i = 0;
		
		T[] new_array = (T[]) new Object[new_size];
		
		//take the smaller of the two vector sizes, used for the loop below
		int smaller_size = (new_size < _number_of_items)
				? new_size          //if new_size is smaller
			    : _number_of_items; //if our size is smaller
		
		for(i = 0; i < smaller_size; i++)
		{
			new_array[i] = _items[i];
		}
		
		//reassign values to new vector
		_items = new_array;
		_number_of_items = smaller_size;
		_max_size = new_size;
	}
	
	protected void checkCapacity(int requested_size)
	{
		//is our array too small?
		if(requested_size >= _max_size)
		{
			//find a value of max_size that will be large enough to accommodate our requested size
			int new_size = _max_size;
			
			//find a value that is at least twice as large as what we currently have
			while (requested_size >= new_size)
			{
				//Doubling the size
				new_size = (new_size * 2) + 1;				
			}
			
			//call the actual adjust method above
			adjustSize(new_size);
		}
	}
	
	public Vector(int starting_size)
	{
		super(starting_size);
	}
	
	public Vector(List<T> values)
	{
		super(values);
	}
	
	public void addElement(T item)
	{
		checkCapacity(_number_of_items + 1);
		push(item);
	}
	
	public void setElementAt(T item, int index)
	{
		checkCapacity(index);
		super.setElementAt(item, index);
	}
	
	public void addElementAt(T item, int index)
	{
		//first check to see if index is within bounds
		checkCapacity(index);
		
		//then check if it's going to be too big
		checkCapacity(_number_of_items + 1);
		
		super.addElementAt(item, index);
		
	}
	
	public void setSize(int size)
	{
		checkCapacity(size);
		super.setSize(size);
	}
	
	//Stack functions
	public void push(T item)
	{
		this.setElementAt(item, _number_of_items);
	}
	
	//return the "top"-most element in the stack
	public T getTop()
	{
		return getElementAt(_number_of_items - 1); 
	}
	
	public T pop()
	{		
		_number_of_items --;
		return getTop();
	}
}
