/**
 * BinaryTreeNode represents a node in a binary tree with a left and
 * right child.
 */
public class BinaryTreeNode<T>{
  protected T element;
  protected BinaryTreeNode<T> left, right;

  /**
   * Creates a new tree node with the specified data.
   *
   * @param obj the element that will become a part of the new tree node
  */
  public BinaryTreeNode(T obj) {
    element = obj;
    left = null;
    right = null;
  }

  /**
   * Returns the number of non-null children of this node.
   *
   * @return the integer number of non-null children of this node
   */
  public int numChildren(){
    int children = 0;

    if (left != null){
      children = 1 + left.numChildren();
    }
    if (right != null){
      children = children + 1 + right.numChildren();
    }

    return children;
  }

  /**
   * Return the element at this node.
   *
   * @return the element stored at this node
   */
  public T getElement(){
    return element;
  }

  /**
   * Return the right child of this node.
   *
   * @return the right child of this node
   */
  public BinaryTreeNode<T> getRight(){
    return right;
  }

  /**
   * Sets the right child of this node.
   *
   * @param node the right child of this node
   */
  public void setRight(BinaryTreeNode<T> node){
    right = node;
  }

  /**
   * Return the left child of this node.
   *
   * @return the left child of the node
   */
  public BinaryTreeNode<T> getLeft(){
    return left;
  }

  /**
   * Sets the left child of this node.
   *
   * @param node the left child of this node
   */
  public void setLeft(BinaryTreeNode<T> node){
    left = node;
  }

  public String toString(){
    return ""+element;
  }

  public void print(){
    if (right != null){
      right.recPrint(true, "");
    }
    System.out.println(this);
    if (left != null){
      left.recPrint(false, "");
    }
  }

  private void recPrint(boolean isRight, String indent) {
    if (right != null){
      right.recPrint(true, indent + (isRight ? "        " : " |      "));
    }
    System.out.print(indent);
    if (isRight) {
      System.out.print(" /");
    } else {
      System.out.print(" \\");
    }
    System.out.println("----- "+this);
    if (left != null) {
      left.recPrint(false, indent + (isRight ? " |      " : "        "));
    }
  }
}
