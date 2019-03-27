public class PreOrderTraversal<T extends Comparable<T>> extends AvlTreeTraversal<T> {
	public void traverse(AvlNode<T> root) {
		// TODO Auto-generated method stub
		if (root == null)
			return;
		
		System.out.print(root.getValue() + " ");
		traverse(root.getLeftChild());
		traverse(root.getRightChild());		
	}

}
