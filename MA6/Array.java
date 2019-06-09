import java.util.List;

public class Array<T> extends Indexed<T> {
	
	//items will handle the actual storage of our data items
	protected T[] _items;
	
	//used to store the max size of our array
	protected int _max_size;
	
	//tracks the ACTUAL number of items in our array
	protected int _number_of_items;
	
	//place holder default constructor
	public Array()
	{
		
	}
	
	//constructor with single input parameter.
	//need to initialize using () to call this
	public Array(int max_size)
	{
		_max_size = max_size;
		_number_of_items = 0;
		_items = (T[])new Object[_max_size]; //force a generic type array. This is the way to do it in Java. "new T[]" would throw errors.
	}
	
	//construct from an existing List
	public Array(List<T> values)
	{
		_max_size = values.size();
		_number_of_items = 0;
		_items = (T[])new Object[_max_size];
		
		for(T item: values)
		{
			addElement(item);
		}
	}
	
	@Override
	public T getElementAt(int index) {
		// TODO Auto-generated method stub
		if (index < 0 || index >= _number_of_items)
		{
			throw new IndexOutOfBoundsException();	
		}
		
		return _items[index];
	}

	@Override
	public void setElementAt(T item, int index) {
		// TODO Auto-generated method stub
		if (index < 0 || index >= _max_size)
		{
			System.out.println("Index out of range.");
			throw new IndexOutOfBoundsException();			
		}
		
		_items[index] = item;
		
		//if this index is larger than our currently tracked _number_of_items,
		//replace with specified index
		if (_number_of_items <= index)
		{
			_number_of_items = index + 1;
		}
	}

	@Override
	public void addElementAt(T item, int index) {
		// TODO Auto-generated method stub
		
		//make sure that we're not full and that the index is within bounds
		if (index >= _max_size)
		{
			throw new IndexOutOfBoundsException();	
		}
		if (_number_of_items == _max_size)
		{
			try {
				throw new Exception("Array is at max size");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//shift every item to the right
		//worst case is index == 0
		//best case is index == number of items
		for (int i = _number_of_items - 1; i >= index; i --)
		{
			setElementAt(_items[i], i + 1);
		}
		
		//now that we have a spot for our item, add it to the array
		setElementAt(item, index);
	}

	@Override
	public void removeElementAt(int index) {
		// TODO Auto-generated method stub
		
		//make sure that we are in bound
		if (index < 0 || index >= _number_of_items)
		{
			System.out.println("Index out of range.");
			throw new IndexOutOfBoundsException();			
		}
		
		//shift everything left
		for (int i = index; i < _number_of_items - 1; i ++)
		{
			_items[i] = _items[i+1];
		}
		
		//do the last shift if we have room
		if (_number_of_items + 1 < _max_size)
		{
			_items[_number_of_items] = _items[_number_of_items + 1];
		}
		
		//decrement the number of items in our array
		_number_of_items --;
	}

	@Override
	public void addElement(T item) {
		// TODO Auto-generated method stub
		addElementAt(item, _number_of_items);
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return _number_of_items == 0;
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return _number_of_items;
	}
	
	//The setSize method for Arrays won't actually change the underlying size of the array.
	//Instead, it merely readjusts the number of items being tracked in the array.		
	public void setSize(int size)
	{
		//check for exceptions
		if (size < 0 || size >= _max_size)
		{
			System.out.println("Invalid size.");
			throw new IndexOutOfBoundsException();			
		}
	}

}
