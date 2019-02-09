class LinearHashTable<K, V> extends HashTableBase<K, V>
{
	
	//determines whether or not we need to resize
	//to turn off resize, just always return false
    protected boolean needsResize()
    {
    	//linear probing seems to get worse after a load factor of about 70%
		if (_number_of_elements > (0.7 * _primes[_local_prime_index]))
		{
			return true;
		}
		return false;
    }
    
    //called to check to see if we need to resize
    protected void resizeCheck()
    {
    	//Right now, resize when load factor > .7; it might be worth it to experiment with 
		//this value for different kinds of hashtables
		if (needsResize())
		{
			_local_prime_index++;

			HasherBase<K> hasher = _hasher;
			LinearHashTable<K, V> new_hash = new LinearHashTable<K, V>(hasher, _primes[_local_prime_index]);
			
			for (HashItem<K, V> item: _items)
			{
				if (item.isEmpty() == false)
				{
					//add to new hash table
					new_hash.addElement(item.getKey(), item.getValue());
				}
			}
			
			_items = new_hash._items;
		}
    }
    
    public LinearHashTable()
    {
    	super();
    }
    
    public LinearHashTable(HasherBase<K> hasher)
    {
    	super(hasher);
    }
    
    public LinearHashTable(HasherBase<K> hasher, int number_of_elements)
    {
    	super(hasher, number_of_elements);
    }
    
    //copy constructor
    public LinearHashTable(LinearHashTable<K, V> other)
    {
    	super(other);
    }
    
    //concrete implementation for parent's addElement method
    public void addElement(K key, V value)
    {
    	//check for size restrictions
    	resizeCheck();
    	
    	//calculate initial hash based on key
    	int hash = super.getHash(key);
    	
    	//MA #1 TODO: find empty slot to insert (update hash variable as necessary)
    	
    	//loops starting at original hash value and increments 
    	//by 1 positively until an empty hash is found
    	while (!_items.elementAt(hash).isEmpty()) {			 
    		if (hash > _number_of_elements) {				
    			hash = 0;									
    		} else {										
    			hash++;										
    		}   		
    	}
        
    	//adds element to hash table
    	_items.elementAt(hash).setKey(key);
    	_items.elementAt(hash).setValue(value);
    	_items.elementAt(hash).setIsEmpty(false);
    	    	
    	//remember how many things we are presently storing
    	//Hint: do we always increase the size whenever this function is called?
    	_number_of_elements++;
    }
    
    //removes supplied key from hash table
    public void removeElement(K key)
    {
    	//calculate hash
    	int hash = super.getHash(key);
    	
    	//MA #1 TODO: find slot to remove. Remember to check for infinite loop!
    	
    	//verifies that the element to be removed is in the set (to avoid
    	//an infinite loop) and then locates the element by incrementing 
    	//the hash value by one until finding the correct key
    	if(super.getKeys().contains(key)) {						
    		while (_items.elementAt(hash).getKey() != key) {	
        		if (hash > _number_of_elements) {
        			hash = 0;
        		} else {
        			hash++;
        		}  
    		} 
    		
    		//ALSO: Use lazy deletion.
    		_items.elementAt(hash).setIsEmpty(true);
    		
    		//decrease HashTable size
        	_number_of_elements--;
    		
    	} else {
    		System.out.println("Key \"" + key + "\" not found.");
    	}
    }
    
    //returns true if the key is contained in the hash table
    public boolean containsElement(K key)
    {
    	int hash = super.getHash(key);
    	HashItem<K, V> slot = _items.elementAt(hash);
    	
    	//left incomplete to avoid hints :)
    	return false;
    }
    
    //returns the item pointed to by key
    public V getElement(K key)
    {
    	int hash = super.getHash(key);
    	HashItem<K, V> slot = _items.elementAt(hash);
    	
    	//left incomplete to avoid hints :)
    	return null;
    }
}