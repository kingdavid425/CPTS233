public class InOrderTraversal<T extends Comparable<T>> extends AvlTreeTraversal<T> {
	public void traverse(AvlNode<T> root) {
      if(root == null)
         return;
      
      traverse(root.getLeftChild());
      System.out.print(root.getValue() + " ");
      traverse(root.getRightChild());
   }
}