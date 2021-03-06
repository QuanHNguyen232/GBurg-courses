import java.util.ArrayList;
import java.util.Stack;
/**
 * AVL Tree
 * @author nguyqu03
 *
 * @param <T>
 */
public class AVL_Tree<T extends Comparable<T>> {
	
	private AVL_Node<T> root;
	/**
	 * Get the root of the tree
	 * @return Node T type
	 */
	public AVL_Node<T> getRoot(){return this.root;}
	
	/**
	 * Insert new data into the AVL Tree
	 * @param data T type
	 */
	public void insert(T data) {
		// empty tree
		if(root == null) {root = new AVL_Node<T>(data);}
		// non-empty tree
		else {
			insertNode(root, data);
		}
		root = balanceNode(root);
	}
	
	// insertion that updates height does not yet implement self-balancing
	private void insertNode(AVL_Node<T> currNode, T data) {
		// move left
		if (data.compareTo(currNode.getData()) <= 0) {
			AVL_Node<T> left = currNode.getLeftNode();
			if (left == null) {
				left = new AVL_Node<T>(data);
				currNode.setLeftNode(left);
			}
			else {
				insertNode(left, data);
			}
			// balance child
			currNode.setLeftNode(balanceNode(left));
		}
		// move right
		else {
			AVL_Node<T> right = currNode.getRightNode();
			if (right == null) {
				right = new AVL_Node<T>(data);
				currNode.setRightNode(right);
			}
			else {
				insertNode(right, data);
			}
			currNode.setRightNode(balanceNode(right));
		}
		// height of currNode may have changed
		currNode.updateHeight();	// updateHeight in lower level nodes -> exit if-else -> updateHeight root
	}
	
	/*
	 * For midterm exam only
	 */
	public void insert_2(T data) {
		// empty tree
		if(root == null) {root = new AVL_Node<T>(data);}
		// non-empty tree
		else {
			insertNode_2(data);
		}
		root = balanceNode(root);
	}
	
	private void insertNode_2(T data) {
		AVL_Node<T> currNode = this.root;
		Stack<AVL_Node<T>> path = new Stack<AVL_Node<T>>();
		// find adding location
		// use stack to store path
		while (currNode != null) {
			path.push(currNode);
			// go LEFT
			if (data.compareTo(currNode.getData()) <= 0) {
				AVL_Node<T> left = currNode.getLeftNode();
				if (left == null) {
					left = new AVL_Node<T>(data);
					currNode.setLeftNode(left);
					left.updateHeight();
					break;
				}
				else {	// continue going down
					currNode = left;
				}
			}
			// go RIGHT
			else {
				AVL_Node<T> right = currNode.getRightNode();
				if (right == null) {
					right = new AVL_Node<T>(data);
					currNode.setRightNode(right);
					right.updateHeight();
					break;
				}
				else {
					currNode = right;
				}
			}
		}
		
		// update height then balanceNode from bottom->top using stack
		while (!path.isEmpty()) {
			AVL_Node<T> parent = path.pop();
			AVL_Node<T> left = parent.getLeftNode();
			AVL_Node<T> right = parent.getRightNode();
			if (left != null) {
				left.updateHeight();
				parent.setLeftNode(balanceNode(left));
			}
			if (right != null) {
				right.updateHeight();
				parent.setRightNode(balanceNode(right));
			}
		}
	}
	
	/*
	 * check for a recent imbalance
	 * perform rotation(s) to fix
	 * return new root of the subtree
	 */
	private AVL_Node<T> balanceNode(AVL_Node<T> node){
		int nodeBal = node.balFactor();
		int childBal;
		
		// no balance issue
		if (Math.abs(nodeBal) < 2) {
			return node;
		}
		// right subtree big; rotate left
		if (nodeBal == 2) {
			AVL_Node<T> child = node.getRightNode();
			childBal = child.balFactor();
			// check subcase (prerotation is required)
			if (childBal == -1) {
				node.setRightNode(child.rotateRight());			
			}
			return node.rotateLeft();
		}
		// left subtree big; rotate right
		else {
			AVL_Node<T> child = node.getLeftNode();
			childBal = child.balFactor();
			// check subcase (prerotation is required)
			if (childBal == 1) {
				node.setLeftNode(child.rotateLeft());			
			}
			return node.rotateRight();
		}
		
	}
	
	/**
	 * Check if data exists in the tree
	 * @param data
	 * @return data if found, else return null
	 */
	public T search(T data){
		AVL_Node<T> node = findNode(data);
		return (node == null) ? null : node.getData();
	}
	
	private AVL_Node<T> findNode(T data) {
		AVL_Node<T> currNode = this.root;
		
		while (currNode != null) {
			if (data.compareTo(currNode.getData()) == 0) {
				return currNode;
			}
			else if (data.compareTo(currNode.getData()) < 0) {
				currNode = currNode.getLeftNode();
			}
			else {
				currNode = currNode.getRightNode();
			}
		}
		return currNode;	// if stop while loop => currNode is null
	}
	
	/**
	 * Delete data from tree
	 * @param data (T type)
	 * @return true if data is found and deleted
	 */
	public boolean delete(T data) {
		// case: empty tree
		if (this.root == null) {return false;}
		
		// store the path taken down the tree
		Stack<AVL_Node<T>> path = new Stack<AVL_Node<T>>();
		AVL_Node<T> currNode = this.root;
		AVL_Node<T> parent = null;
		
		// find node or search will fail
		while (currNode != null && currNode.getData().compareTo(data) != 0) {
			path.push(currNode);
			if (data.compareTo(currNode.getData()) < 0) {
				currNode = currNode.getLeftNode();
			}
			else {currNode = currNode.getRightNode();}
			
		}
		
		// case: search failed
		if (currNode == null) {return false;}
		
		// case: leaf
		if (currNode.getLeftNode() == null && currNode.getRightNode() == null) {
			// sub-case: root
			if (currNode == this.root) {
				this.root = null;
				return true;
			}
			// sub-case: non-root
			parent = path.pop();
			if (parent.getLeftNode() == currNode) {
				parent.setLeftNode(null);
			}
			else {parent.setRightNode(null);}
			parent.updateHeight();
			// ancestors to be balanced later
			
		}
		
		// case: 1 LEFT subtree
		else if (currNode.getLeftNode() != null && currNode.getRightNode() == null) {
			// sub-case: root
			if (currNode == this.root) {
				root = currNode.getLeftNode();
				return true;
			}
			// sub-case: non-root (by updating parent)
			parent = path.pop();
			if (parent.getLeftNode() == currNode) {
				parent.setLeftNode(currNode.getLeftNode());
			}
			else {parent.setRightNode(currNode.getLeftNode());}
			parent.updateHeight();
			// ancestors to be balanced later
		}
		
		// case: 1 RIGHT subtree
		else if (currNode.getLeftNode() == null && currNode.getRightNode() != null) {
			// sub-case: root
			if (currNode == this.root) {
				root = currNode.getRightNode();
				return true;
			}
			// sub-case: non-root (by updating parent)
			parent = path.pop();
			if (parent.getLeftNode() == currNode) {
				parent.setLeftNode(currNode.getRightNode());
			}
			else {parent.setRightNode(currNode.getRightNode());}
			parent.updateHeight();
			// ancestors to be balanced later
		}
		
		// case: 2 subtrees
		else {			
			AVL_Node<T> pNode = this.promote(currNode);
			AVL_Node<T> replaceNode = null;
			
			if (pNode == null) {
				replaceNode = currNode.getLeftNode();
				replaceNode.setRightNode(currNode.getRightNode());
				replaceNode = this.balanceNode(replaceNode);
				replaceNode.updateHeight();
				// after this, we can use replaceNode in the following sub-cases
			}
			
			// sub-case: root
			if (currNode == this.root) {
				if (pNode == null) {
					this.root = replaceNode;
				}
				else {
					this.root = pNode;
				}
				return true;
			}
			
			// sub-case: non-root			
			parent = path.pop();
			
			if (parent.getLeftNode() == currNode) {
				if (pNode == null) {
					parent.setLeftNode(replaceNode);
				}
				else {
					parent.setLeftNode(pNode);	
				}
			}
			else {
				if (pNode == null) {
					parent.setRightNode(replaceNode);
				}
				else {
					parent.setRightNode(pNode);	
				}
			}
			parent.updateHeight();
			// ancestors to be balanced later
		}
		
		// update back up through the tree
		while (!path.isEmpty()) {
			currNode = parent;
			parent = path.pop();
			
			if (parent.getLeftNode() == currNode) {
				parent.setLeftNode(this.balanceNode(currNode));
			}
			else {parent.setRightNode(this.balanceNode(currNode));}
			parent.updateHeight();
		}
		this.root = this.balanceNode(this.root);
		return true;
	}
	
	/* find the rightmost left descendant
	 * promote that node to root of subtree
	 * rebalance at new root. New root may change
	 * return new root of subtree
	 */
	private AVL_Node<T> promote(AVL_Node<T> node){
		AVL_Node<T> dNode = node.getLeftNode();
		AVL_Node<T> pNode;
		
		if (dNode.getRightNode() == null) {
			return null;
		}
		
		// case: left child has right subtree
		Stack<AVL_Node<T>> dPath = new Stack<AVL_Node<T>>();
		
			// useful references
			// SHOULD BE IN LOOP
//			AVL_Node<T> dParent = dNode;
//			dPath.push(dNode);
//			dNode = dParent.getRightNode();
			
		// travel down to the furthest right
		while (dNode.getRightNode() != null) {
			dPath.push(dNode);
			dNode = dNode.getRightNode();
		}	// by the end, dNode is the rightmost node
		
		// Pop to get Parent
		AVL_Node<T> dParent = dPath.pop();

		// remove dNode from its original location
		// works for case dNode has/doesnot have left subtree
		dParent.setRightNode(dNode.getLeftNode());
		dParent.updateHeight();

		// save reference to dNode, for promotion 
		pNode = dNode;
		
		// travel back up path, balancing as we go
		while (!dPath.isEmpty()) {
			dNode = dParent;
			dParent = dPath.pop();

			// know dNode is dParent's right child
			// balance at dNode and save the return i.e. new root of the subtree as dParent's right child
			dParent.setRightNode(balanceNode(dNode));
			dParent.updateHeight();
		}	// by the end of loop, 

		// rebalance at last dParent
		dParent = this.balanceNode(dParent);
		
		// rebind node's left child appropriately
		node.setLeftNode(dParent);

		// pluck out node and replace with pNode
		pNode.setLeftNode(node.getLeftNode());
		pNode.setRightNode(node.getRightNode());
		node.setLeftNode(null);
		node.setRightNode(null);

		// rebalance and return
		pNode = this.balanceNode(pNode);
		pNode.updateHeight();
		
		return pNode;
	}
	
	/**
	 * Get ArrayList of data in specific depth in order
	 * @param depth
	 * @return ArrayList of data
	 */
	public ArrayList<T> depthInOrder(int depth) {
		ArrayList<T> result = new ArrayList<T>();
		
		goToDepth(result, this.root, depth, 0);
		
		return result;
	}
	
	private void goToDepth(ArrayList<T> arr, AVL_Node<T> currNode, int target, int currDepth){
		if (target == currDepth) {
			arr.add(currNode.getData());
		}
		else {
			AVL_Node<T> left = currNode.getLeftNode();
			AVL_Node<T> right = currNode.getRightNode();
			if (left != null) {
				goToDepth(arr, left, target, currDepth+1);
			}
			if (right != null) {
				goToDepth(arr, right, target, currDepth+1);
			}
		}
	}
	
	/**
	 * Get the path as a Stack of nodes' data of shortest downward from startVal to endVal
	 * @param startVal (T type)
	 * @param endVal  (T type)
	 * @return Stack of data
	 */
	public Stack<T> findPath(T startVal, T endVal){
		AVL_Node<T> currNode = this.findNode(startVal);
		
		if (currNode == null) {return new Stack<T>();}
		
		Stack<T> stack = new Stack<T>();		
		// loop from currNode to end
		while (currNode != null) {
			T currData = currNode.getData();
			
			// Case: next data == data in stack (e.g. below) -> pop the top
			if (!stack.isEmpty() && currData.compareTo(stack.peek()) == 0) {
				stack.pop();
			}
			stack.push(currData);
			
			// return if found Node that has endVal (currNode's data == endVal)
			if (currNode.getData().compareTo(endVal) == 0) {
				return stack;
			}
			// case: endVal is on left of currNode
			else if (endVal.compareTo(currNode.getData()) < 0) {
				currNode = currNode.getLeftNode();
			}
			else {currNode = currNode.getRightNode();}
		}
		// return empty Stack if cannot find endVal
		return new Stack<T>();
	}
	
	/**
	 * Get the list of values in ordered
	 * @return ArrayList of ordered values
	 */
	public ArrayList<T> inOrder() {
		if (this.root == null) return null;
		else {
			// ArrayList container for ordered elements
			ArrayList<T> datum = new ArrayList<T>();
			return getSubTree(root, datum);
		}
	}

	private ArrayList<T> getSubTree(AVL_Node<T> node, ArrayList<T> sublist){
		// add left, add parent, then add right
		// add leftNode to sublist
		if (node.getLeftNode() != null) {
			getSubTree(node.getLeftNode(), sublist);
		}
		// add currNode to sublist
		sublist.add(node.getData());
		// add rightNode to sublist
		if (node.getRightNode() != null) {
			getSubTree(node.getRightNode(), sublist);
		}

		return sublist;
	}
}
