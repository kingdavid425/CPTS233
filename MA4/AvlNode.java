// Basic node stored in AVL trees

class AvlNode<T extends Comparable<T>>
{
    // Constructors
	AvlNode()
	{
		
	}
	
    AvlNode(T v)
    {
        this(v, null, null);
    }

    AvlNode(T v, AvlNode<T> lt, AvlNode<T> rt)
    {
        value  = v;
        left   = lt;
        right  = rt;
        height = 0;
    }

    private T value;             	    // The data in the node
    private AvlNode<T>    left;         // Left child
    private AvlNode<T>    right;        // Right child
    private int           height = 0;       // Height
    
    //As a habit, we are going to keep using getter/setter functions instead of accessing the property directly
    public void setValue(T v)
    {
    	value = v;
    }
    
    public T getValue()
    {
    	return value;
    }
    
    public void setLeftChild(AvlNode<T> l)
    {
    	left = l;
    }
    
    public AvlNode<T> getLeftChild()
    {
    	return left;
    }
    
    public void setRightChild(AvlNode<T> r)
    {
    	right = r;
    }
    
    public AvlNode<T> getRightChild()
    {
    	return right;
    }    
    
    public void setHeight(int h)
    {
    	height = h;
    }
    
    public int getHeight()
    {
    	return height;
    }
    
    //Balance factor that helps determines if the current node is balanced
    public int getBalanceFactor()
    {
    	int left_height = (left == null) ? -1 : left.getHeight();
    	int right_height = (right == null) ? -1 : right.getHeight();
    	
    	return right_height - left_height;
    }
    
}