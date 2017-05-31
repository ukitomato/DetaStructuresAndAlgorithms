// BinarySearchTree2

public class BinarySearchTree2 {

    // 木機構の表示：２分木の実装のメソッド displayを再利用
    public static void display (Node n) {
        if (n == null) {
            System.out.print("null");
            return;
        }

        System.out.print(n.val+"(");

        display(n.left);
        System.out.print(",");

        display(n.right);
        System.out.print(")");
    }

    // 最小値の探索
    public static int min_bst(IntNode2 p) {
        if (p == null) return -1;
        IntNode2 q = p;
        while (q.left != null) q=q.left;
        return q.val;
    }

    // 値の探索
    public static boolean search_bst(IntNode2 p, int d) {
        IntNode2 q = p;
        while (q != null) {
            if (q.val == d) {
                return true;
            } else if (q.val > d) {
                q = q.left;
            } else {
                q = q.right;
            }
        }
        return false;
    }


    // 値の挿入
    public static IntNode2 insert_bst(IntNode2 p, int d) {
        if (p == null) {
            p = new IntNode2(d, null, null);
            return p;
        }
        IntNode2 q = p;
        while (true) {
            if (q.val == d) return q;
            if (q.val > d) {
                if (q.left == null) {
                    q.left = new IntNode2(d, null, null);
                    return q;
                } else {
                    q = q.left;
                }
            } else {
                if (q.right == null) {
                    q.right = new IntNode2(d, null, null);
                    return q;
                } else {

                    q = q.right;
                }
            }
        }

    }

    // 最小値の削除
    public static IntNode2 delete_min_bst (IntNode2 p) {}

    // 値の削除
    public static IntNode2 delete_bst(IntNode2 p, int d) {}

    public static void main (String[] args) {
        // 値の挿入
        IntNode2 t = null;
        t = insert_bst(t, 10);
        t = insert_bst(t, 15);
    }


}