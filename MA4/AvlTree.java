public class AvlTree<T extends Comparable<T>> extends Collection<T> 
{
	//keeps track of the remove direction (left or right)
	private int _remove_counter = 0;
	
	//keeps track of BST size
	private int _size_counter = 0;
	
	protected AvlNode<T> _root = null;
	
	//removes the largest element from the subtree starting at root
	protected AvlNode<T> removeLargest(AvlNode<T> root)
	{
		if (root == null)
			return root;
		
		//BASE CASE: root is largest (has no right node)
		if (root.getRightChild() == null)
		{
			AvlNode<T> left = root.getLeftChild();
			//return left node to parent
			return left;
		}
		else
		{
			//RECURSIVE CASE:
			AvlNode<T> new_right = removeLargest(root.getRightChild());
			root.setRightChild(new_right);
			return root;
		}
	}
	
	//removes the smallest element in the subtree starting at root
	//Updated: DIFFERENT from MA3. Now written with recursion too
	protected AvlNode<T> removeSmallest(AvlNode<T> root)
	{
		if (root == null)
			return root;
		
		//BASE CASE: root is smallest (has no left node)
		if (root.getLeftChild() == null)
		{
			AvlNode<T> right = root.getRightChild();
			//return right node to parent
			return right;
		}
		else
		{
			//RECURSIVE CASE:
			AvlNode<T> new_left = removeSmallest(root.getLeftChild());
			root.setLeftChild(new_left);
			return root;
		}
	}
	
	//MA4 TODO: Implement me THIRD!
	//The purpose of this function is to figure out where the imbalance occurs within root (left or right)
	//and return the result of the appropriate rotation (left or right) on root's child.
	protected AvlNode<T> balance(AvlNode<T> root)
	{
		//check for null roots first
		if (root == null)
			return root;
      
      //imbalance occurs within right subtree    
      if(root.getBalanceFactor() > 1)
         root = rotateLeft(root);
      //imbalance occurs within left subtree
      else if(root.getBalanceFactor() < -1)
         root = rotateRight(root);
      
      return root;
	}
   
   //go back through and fix the height of the nodes rotated
	//MA4 TODO: Implement me SECOND!
	protected AvlNode<T> rotateLeft(AvlNode<T> root)
	{
		//check for null roots
		if (root == null)
			return root;
		
      //1. Let CurrentRoot = the original root   
      AvlNode<T> current_root = root;
      
		//2. Let NewRoot = CurrentRoot's right child
		AvlNode<T> new_root = root.getRightChild();
      
      //3. Set CurrentRoot's right child = NewRoot's left child
      current_root.setRightChild(new_root.getLeftChild());
      
      //4. Set NewRoot's left child = CurrentRoot
      new_root.setLeftChild(current_root);
		
		//MA4 TODO: rotate left and return "new_root"
		return new_root;
	}
   
	//MA4 TODO: Implement me FIRST!
	protected AvlNode<T> rotateRight(AvlNode<T> root)
	{
		//check for null roots
		if (root == null)
			return root;
      
      //1. Let CurrentRoot = the original root   
      AvlNode<T> current_root = root;
		
		//2. Let NewRoot = CurrentRoot's left child
		AvlNode<T> new_root = root.getLeftChild();
      
      //3. Set CurrentRoot's left child = NewRoot's right child
      current_root.setLeftChild(new_root.getRightChild());
      
      //4. Set NewRoot's right child = CurrentRoot
      new_root.setRightChild(current_root);
		
		//MA4 TODO: rotate right and return "new_root"
		return new_root;
	}
	
	protected AvlNode<T> setHeight(AvlNode<T> root)
	{
		//check for null roots
		if (root == null)
			return root;
		
		AvlNode<T> left = root.getLeftChild();
		AvlNode<T> right = root.getRightChild();
		
		//start at 1 because we assume null trees have height of -1
		int height = 1;
		
		//add in larger of left or right heights
		int left_height = (left == null) ? -1 : left.getHeight();
		int right_height = (right == null) ? -1 : right.getHeight();
		
		height += (left_height < right_height) ? right_height: left_height;
		
		//reassign new height to root
		root.setHeight(height);
		
		//check to see if balance factor is out of balance
		int balance_factor = root.getBalanceFactor();
		if (balance_factor < -1 || balance_factor > 1)
		{
			return balance(root);
		}
		return root;
	}
	
	protected AvlNode<T> addElementHelper(AvlNode<T> root, T item)
	{
		//BASE CASE: create new node
		if (root == null)
		{
			root = new AvlNode<T>();
			root.setValue(item);
			return root;
		}
		
		//else, use the same helper as in BST
		//RECURSIVE CASE: figure out which child to call
		//is "item" larger than us?
		if (item.compareTo(root.getValue()) > 0){
			AvlNode<T> right_child = root.getRightChild();
			AvlNode<T> result = addElementHelper(right_child, item);
			root.setRightChild(result);
		}
		else if (item.compareTo(root.getValue()) < 0)
		{
			//same as above, except switching from right to left
			AvlNode<T> left_child = root.getLeftChild();
			AvlNode<T> result = addElementHelper(left_child, item);
			root.setLeftChild(result);
		}
		else
		{
			//duplicates, do nothing
			return root;
		}
		
		//For BST, this process would end
		//return root;
		
		//For AVL though, we need to return a balanced node
		return setHeight(root);
	}
	
	protected AvlNode<T> removeElementHelper(AvlNode<T> root, T item)
	{
		//use the same helper as in BST
		//null check
		if (root == null)
			return null;
		
		//three possibilities:
		// we found the item (root value == item)
		// item is less than root (item < root)
		// item is greater than root (item > root)
		if (item.equals(root.getValue()))
		{
			//increment removal counter
			_remove_counter++;
			
			//we found the item
			//do we remove from the left or right?
			if (_remove_counter % 2 == 0)
			{
				//check to see if left is not null
				if (root.getLeftChild() != null)
				{
					//left subtree remove
					//we need the largest from the left side
					AvlNode<T> largest = findLargest(root.getLeftChild());
					
					//take the largest's value, put inside root
					root.setValue(largest.getValue());
					
					//having gotten the value, we can now remove the node from the tree
					AvlNode<T> result = removeLargest(root.getLeftChild());
					root.setLeftChild(result);
					return setHeight(root);
				}
				else
				{
					//else, delete the current root, return the result
					return setHeight(removeSmallest(root));
				}
			}
			else
			{
				//right subtree remove
				if (root.getRightChild() != null)
				{
					AvlNode<T> smallest = findSmallest(root.getRightChild());
					
					root.setValue(smallest.getValue());
					
					AvlNode<T> result = removeSmallest(root.getRightChild());
					root.setRightChild(result);
					return setHeight(root);
				}
				else
				{
					return setHeight(removeLargest(root));
				}
			}				
		}
		else if (item.compareTo(root.getValue()) < 0)
		{
			//item is less than root
			AvlNode<T> result = removeElementHelper(
					root.getLeftChild(), //send along our left child
					item				 //and the same item
					);
			root.setLeftChild(result);
		}
		else
		{
			//item is greater than root
			AvlNode<T> result = removeElementHelper(
					root.getRightChild(),
					item
					);
			root.setRightChild(result);
		}
		
		//similar to addElementHelper, need to balance the root		
		return setHeight(root);
	}
	
	protected AvlNode<T> findLargest(AvlNode<T> root)
	{
		while(root != null && root.getRightChild() != null)
			root = root.getRightChild();
		return root;
	}
	
	protected AvlNode<T> findSmallest(AvlNode<T> root)
	{
		while(root != null && root.getLeftChild() != null)
			root = root.getLeftChild();
		return root;
	}
	
	//provide traverse method for the AVL tree
	public void traverse(AvlTreeTraversal<T> traversal)
	{
		traversal.traverse(_root);
	}

	@Override
	public void addElement(T item) {
		// TODO Auto-generated method stub
		_root = addElementHelper(_root, item);
		_size_counter++;
	}
	
	public void removeElement(T item) {
		_root = removeElementHelper(_root, item);
		_size_counter--;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return _root == null;
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return _size_counter;
	}
	
}
