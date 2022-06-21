import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		BSTree<Integer> tree = new BSTree<Integer>();

		ArrayList<Integer> arr = new ArrayList<Integer>();

		BSTree<Integer> tree8 = new BSTree<Integer>();
		Integer f = new Integer(-7);
		tree8.insert(0);tree8.insert(-5);tree8.insert(-3);tree8.insert(-10);tree8.insert(-15);tree8.insert(f);
		tree8.insert(5);tree8.insert(10);tree8.insert(3);
		tree8.remove(-5);

		//		System.out.println(tree.inOrder());
		tree.insert(10);
		tree.insert(5);
		tree.insert(20);
		tree.insert(15);
		tree.insert(25);
//		System.out.println(tree.getHead().getLeftNode().getData());


		System.out.println(sumOfLeftLeaves(tree.getHead()));
	}
	static private int sumOfLeftLeaves(BSTNode<Integer> root) {
		/* Write your code here */
		int sum = method(root, 0);

		return sum;
	}
	//	static int sum = 0;
	static private int method(BSTNode<Integer> root, int sum) {
		// check left leaf
		BSTNode<Integer> left = root.getLeftNode();
		BSTNode<Integer> right= root.getRightNode();

		// check left is LEAF
		
		if (left != null) {
			if (left.getLeftNode() == null && left.getRightNode() == null) {
				sum += left.getData();
			}
			else {
				if (left.getLeftNode() != null) return method(left.getLeftNode(), sum);
				if (left.getRightNode() != null) return method(left.getRightNode(), sum);
			}
		}
//		else {
//			return sum;
//		}
		
		
		if (right != null) {
			return method(right, sum);
		}
		
		
		
		return sum;
	}

	public static ArrayList<Integer> name(List<Integer> list, ArrayList<Integer> output) {
		int idx = list.size()/2;
		System.out.println(idx + "-" + list.get(idx) + "\t" + list);
		output.add(list.get(idx));
		int l, r;
		if (idx>0 && idx < list.size()) {
			// Left
			l = 0; r = idx;
			if (l < r) {
				name(list.subList(l, r), output);
			}
			// Right
			l = idx+1; r = list.size();
			if (l < r) {
				name(list.subList(l, r), output);
			}
		}

		return output;
	}

}
