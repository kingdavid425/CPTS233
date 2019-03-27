import java.util.Stack;

public class MA3_main {

	public static void main(String[] args) {
      /*
		//Build a temporary tree for demo
		BinaryNode<String> root = new BinaryNode<>("A");
		
		BinaryNode<String> node = new BinaryNode<>("B");
		node.setLeftChild(new BinaryNode<String>("D"));
		node.setRightChild(new BinaryNode<String>("E"));
		
		root.setLeftChild(node);
		
		node = new BinaryNode<>("C");
		node.setRightChild(new BinaryNode<String>("F"));
		
		root.setRightChild(node);
		
		//root is the root of the tree.
		//let's try traversal now!
      */
		
		BinarySearchTree<String> bst = new BinarySearchTree<>();
		bst.addElement("C");
		bst.addElement("B"); //E, A, D, F
      bst.addElement("E");
      bst.addElement("A");
      bst.addElement("D");
      bst.addElement("F");
      
      BinaryNode<String> root = bst._root;
      
      System.out.println("After addElement:");
      
		System.out.println("------------");
		System.out.println("Pre-order Travesal");
		preorder(root);
		System.out.println();
		
		System.out.println("------------");
		System.out.println("Post-order Travesal");
		postorder(root);
		System.out.println();

		System.out.println("------------");
		System.out.println("In-order Travesal");
		inorder(root);
		System.out.println();
            
      bst.removeElement("C");
      //bst.removeElement("C");
		bst.removeElement("B"); //E, A, D, F
      //bst.removeElement("E");
      //bst.removeElement("C");
      //bst.removeElement("A");
      //bst.removeElement("D");
      bst.removeElement("F");
      
      root = bst._root;
      
      System.out.println("After removeElement:");
      
		System.out.println("------------");
		System.out.println("Pre-order Travesal");
		preorder(root);
		System.out.println();
		
		System.out.println("------------");
		System.out.println("Post-order Travesal");
		postorder(root);
		System.out.println();

		System.out.println("------------");
		System.out.println("In-order Travesal");
		inorder(root);
		System.out.println();
   }
	
	public static void preorder(BinaryNode<String> root)
	{
		if (root == null) return;
		
		//operations we perform on a certain node
		
		Stack<BinaryNode<String>> stack = new Stack<>();
		stack.push(root);
		
		while(!stack.isEmpty())
		{
			root = stack.pop();	
			System.out.println(root.getValue());
			
			if (root.getRightChild() != null)
				stack.push(root.getRightChild());
			if (root.getLeftChild() != null)
				stack.push(root.getLeftChild());			
		}
		
		//preorder(root.getLeftChild());
		//preorder(root.getRightChild());
		
	}
	
	public static void postorder(BinaryNode<String> root)
	{
		if (root == null) return;
		
		postorder(root.getLeftChild());
		postorder(root.getRightChild());
		System.out.println(root.getValue());
	}
	
	public static void inorder(BinaryNode<String> root)
	{
		if (root == null) return;
		
		inorder(root.getLeftChild());
		System.out.println(root.getValue());
		inorder(root.getRightChild());
	}
}
