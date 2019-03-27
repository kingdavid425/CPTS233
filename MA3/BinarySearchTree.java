import java.util.Vector;

public class BinarySearchTree<T extends Comparable<T>> extends Collection<T> {
//public class BinarySearchTree<T> extends Collection<T> {
	
	//keeps track of the remove direction (left or right)
	private int _remove_counter = 0;
	
	//keeps track of BST size
	private int _size_counter = 0;
	
	protected BinaryNode<T> _root = null;
	
	//removes the largest element from the SUBTREE starting at root
	protected BinaryNode<T> removeLargest(BinaryNode<T> root)
	{
		//NULL tree/empty tree
		if (root == null) return null;
		
		//BASE CASE: root is the largest
		if (root.getRightChild() == null)
		{
			return root.getLeftChild();
		}		
		
		//Recursive 		
		BinaryNode<T> new_right = removeLargest(root.getRightChild());
		
		//reconfigure our right pointer to the returned value
		root.setRightChild(new_right);				
		
		return root;
	}
	
	//removes the smallest element
	protected BinaryNode<T> removeSmallest(BinaryNode<T> root)
	{
		//NULL tree/empty tree
		if (root == null) return null;
		
		//without recursion
		BinaryNode<T> pre = null;
		
		//root is the smallest value
		while (root.getLeftChild() != null)
		{
			pre = root;
			root = root.getLeftChild();
		}
		
		//check if pre is null
		if(pre != null)
			pre.setLeftChild(root.getRightChild());
		
		return pre;
	}
	
	protected BinaryNode<T> findLargest(BinaryNode<T> root)
	{
		while(root != null && root.getRightChild() != null)
			root = root.getRightChild();
		return root;
	}
	
	protected BinaryNode<T> findSmallest(BinaryNode<T> root)
	{
		while(root != null && root.getLeftChild() != null)
			root = root.getLeftChild();
		return root;
	}
	
         
   //check the node
   //if the node is null, replace the node with the value, return root
   //if the mode is not null, compare the value of the node with the item
   //    - if node > item: recurse aEH(root.left, item)
   //    - if node < item: recurse aEH(root.right, item)
   //    - if node == item: return root
	protected BinaryNode<T> addElementHelper(BinaryNode<T> root, T item) {
      //check for null first
		//if null, create new node return pointer to that node
      if(root == null) {
         root = new BinaryNode <T> (item); 
      }
      
      //if not null, compare value, add to correct place
		//you can choose whether to use recursion or not
		//to compare, use this method of the item: 
      //item.compareTo(/*arguments to compare to*/) 
      
      //if(item < root): traverse the left subtree
      if(item.compareTo(root.getValue()) < 0) {
         root._left_child = addElementHelper(root._left_child, item);  
      }
      
      //if(item > root): traverse the right subtree
      if(item.compareTo(root.getValue()) > 0) {
         root._right_child = addElementHelper(root._right_child, item);  
      }
      
      //if(item == root) we just want to return root, so there is no if statement for that case
				
		//always return root because we don't know where the recursion ends
		return root;
	}
	
	protected BinaryNode<T> removeElementHelper(BinaryNode<T> root, T item) {
		//check for null
		//if null, return it.
		if (root == null) {
			return root;
		}
      		
		//we found the item we want to remove (root value == item)
		if (item.compareTo(root.getValue()) == 0) {
      
			//increment removal counter
			_remove_counter++;
         
         //if the root has no children, we just want to set the root = null
         if(root.isLeaf()) {
            root = null;
         //if the root only has a left child we just set the root to be that child
         } else if(root._left_child != null && root._right_child == null) {
            root = root._left_child;
         //if the root only has a right child we just set the root to be that child
         } else if(root._left_child == null && root._right_child != null) {
            root = root._right_child;
         //if the root has two children
			} else if (_remove_counter % 2 == 0) {
   		   //let's assume we are removing from the left when it's an even number
   		   // MA3 TODO
            
            BinaryNode<T> temp = root._left_child;
            //when removing from the left subtree we want to replace the root
            //with the largest (rightmost) child
            
            //traverses left subtree until rightmost child is located
            while(temp.getRightChild().getRightChild() != null) {
               temp = temp.getRightChild();
            }
            
            //set root equal to rightmost child
            root.setValue(temp.getRightChild().getValue());
            
            //delete the rightmost child
            temp.setRightChild(null);    
   	   } else {
   			//remove from the right subtree when it's an odd number
   			// MA3 TODO
            
            BinaryNode<T> temp = root._right_child;
            //when removing from the right subtree we want to replace the root
            //with the smallest (leftmost) child
            
            //traverses right subtree until leftmost child is located
            while(temp.getLeftChild().getLeftChild() != null) {
               temp = temp.getLeftChild();
            }
            
            //set root equal to leftmost child
            root.setValue(temp.getLeftChild().getValue());
            
            //delete the leftmost child
            temp.setLeftChild(null);           
         }
      //item is less than root (item < root)
		} else if (item.compareTo(root.getValue()) < 0) {
			//item is less than root
			//go on finding it in the left subtree
			BinaryNode<T> result = removeElementHelper(root.getLeftChild(), item);
			
			//the recursive call *might* have altered our
			//left child's structure. Inform root of this change
			root.setLeftChild(result);
      //item is greater than root (item > root)
		} else {
			//item is greater than root
			//finding it in the right subtree
			BinaryNode<T> result = removeElementHelper(root.getRightChild(), item);
			root.setRightChild(result);
		}		
		return root;
	}

	public void addElement(T item) {		
		_root = addElementHelper(_root, item);
		_size_counter++;
	}
	
	public void removeElement(T item) {
		_root = removeElementHelper(_root, item);
		_size_counter--;
	}

	@Override
	public boolean isEmpty() {		
		return _root == null;
	}

	@Override
	public int getSize() {		
		return _size_counter;
	}

}
