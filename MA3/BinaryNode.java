//Node in a Binary Tree
public class BinaryNode<T> {

	//underlying value in the node
	private T _value;
	
	//a *pointer* to the left child 
	//In Java, just the left child
	BinaryNode<T> _left_child;
	
	//right
	BinaryNode<T> _right_child;
	
	public BinaryNode()
	{
		_left_child = null;
		_right_child = null;
	}
	
	public BinaryNode(T value)
	{
		_value = value;
	}
	
	public BinaryNode(BinaryNode<T> left, BinaryNode<T> right)
	{
		_left_child = left;
		_right_child = right;
	}
	
	public boolean isLeaf()
	{
		return _left_child == null && _right_child == null;
	}
	
	public BinaryNode<T> getLeftChild()
	{
		return _left_child;
	}
	
	public void setLeftChild(BinaryNode<T> left)
	{
		_left_child = left;
	}
	
	public BinaryNode<T> getRightChild()
	{
		return _right_child;
	}
	
	public void setRightChild(BinaryNode<T> right)
	{
		_right_child = right;
	}
	
	public T getValue()
	{
		return _value;
	}
	
	public void setValue(T value)
	{
		_value = value;
	}	
}
