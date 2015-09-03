package datastructure.trees;

public class TreeNode {

	public int data;
	public TreeNode left;
	public TreeNode right;
	public TreeNode parent;

	/**
	 * "1(2(4,5(7,)),3(,6))"
	 * 
	 * @param sr
	 * @param parent
	 */
	public TreeNode(StringReader sr, TreeNode parent) {
		if (sr.hasNext()) {
			try {
				if (!sr.hasNext() || !isNumber(sr.peepNextChar().charAt(0))) {
					return;
				}
				data = sr.nextInt();
				if ("(".equals(sr.peepNextChar())) {
					String next = sr.nextChar();
					if ("(".equals(next)) {
						// 看看是否左子数为空
						if (",".equals(sr.peepNextChar()))
							left = null;
						else
							left = new TreeNode(sr, this);

						if (!",".equals(sr.nextChar()))
							System.err.println("怎么不是逗号");

						// 看看是否右子数为空
						if (")".equals(sr.peepNextChar()))
							right = null;
						else
							right = new TreeNode(sr, this);
						if (!")".equals(sr.nextChar()))
							System.err.println("怎么不是右括号");

					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	boolean isNumber(char c) {
		return c <= '9' && c >= '0';
	}

	public void printByMidOrder() {
		if (left != null)
			left.printByMidOrder();
		System.out.print(data + "  ");
		if (right != null)
			right.printByMidOrder();
	}

}
