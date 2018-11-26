class BinaryTreeNode {
    int data;
    BinaryTreeNode leftNode = null, rightNode = null;

    public void setBinaryTreeNode(int data) {
        this.data = data;
    }

    public void setLeftNode(BinaryTreeNode leftNode) {
        this.leftNode = leftNode;
    }

    public void setRightNode(BinaryTreeNode rightNode) {
        this.rightNode = rightNode;
    }
}

class BinaryTree {
    BinaryTreeNode[] btn;
    BinaryTreeNode rooNode;
    int NodeSize;

    public BinaryTree(int[] arrayNode) {
        NodeSize = arrayNode.length;
        btn = new BinaryTreeNode[NodeSize];

        //把arrayNode元素转化为节点
        for (int i = 0; i < NodeSize; i++) {
            btn[i] = new BinaryTreeNode();
            btn[i].setBinaryTreeNode(arrayNode[i]);
            if (i == 0) {
                rooNode = btn[i];
            }
        }
        //把二叉树的左右子树节点补全
        for (int j = 0; j <= (NodeSize - 2) / 2; j++) {
            btn[j].setLeftNode(btn[2 * j + 1]);
            btn[j].setRightNode(btn[2 * j + 2]);
        }
    }

    //递归方法前序遍历
    void preOrder(BinaryTreeNode btn) {
        BinaryTreeNode root = btn;
        if (root != null) {
            printNode(root);
            inOrder(root.leftNode);
            inOrder(root.rightNode);
        }
    }

    //递归方法中序遍历
    void inOrder(BinaryTreeNode btn) {
        BinaryTreeNode root = btn;

        if (root != null) {
            inOrder(root.leftNode);
            printNode(root);
            inOrder(root.rightNode);
        }
    }


    //递归方法中序遍历
    void inOrder(BinaryTreeNode btn  ,Integer level  ,Integer chekclevel ) {
        BinaryTreeNode root = btn;
        int nodeLevel =  ( null == level )?1:level+1;

        if (root != null) {
            inOrder(root.leftNode ,nodeLevel, chekclevel);
            //printNode(root);
            if(nodeLevel ==chekclevel){
                printNode(root);
            }
            inOrder(root.rightNode, nodeLevel ,chekclevel);
        }
    }




    //递归方法后序遍历
    void postOrder(BinaryTreeNode btn) {
        BinaryTreeNode root = btn;

        if (root != null) {
            postOrder(root.leftNode);
            postOrder(root.rightNode);
            printNode(root);
        }
    }

    //打印节点信息
    static void printNode(BinaryTreeNode btn) {
        int a = btn.data;
        System.out.println(a);
    }
}

public class Tree {
    public static void main(String[] args) {
        int[] arrayNode = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        BinaryTree bt = new BinaryTree(arrayNode);
        System.out.println("inOrder:");
        //bt.inOrder(bt.rooNode);

        bt.inOrder(bt.rooNode ,null , 3);

     //   System.out.println("preOrder:");
/*        bt.preOrder(bt.rooNode);
        System.out.println("postOrder:");
        bt.postOrder(bt.rooNode);*/
    }
}