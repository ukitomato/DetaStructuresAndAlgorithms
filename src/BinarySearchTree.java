// 削除の実装で使用するデータ型
enum Lr {
    L, R
};

public class BinarySearchTree {
    static IntNode root;         // ２分探索木の根（nullで初期化されている）

    // 木構造の表示：２分木の実装のメソッド displayを再利用
    public static void display (IntNode n) {
        if (n == null) return;
        System.out.print(n.val+"(");
        if(n.left==null) System.out.print("null");
        display(n.left);
        System.out.print(",");
        if(n.right==null) System.out.print("null");
        display(n.right);
        System.out.print(")");
    }

    // 最小値の探索
    public static int min_bst() {
        if (root == null) return -1;
        IntNode p = root;
        while (p.left != null) p=p.left;
        return p.val;
    }

    // 値の探索
    public static boolean search_bst(int d) {
        IntNode p = root;
        while (p != null) {
            if (p.val == d) {
                return true;
            } else if (p.val > d) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        return false;
    }

    // 値の挿入
    public static void insert_bst(int d) {
        if (root == null) {
            root = new IntNode(d, null, null);
            return;
        }
        IntNode p = root;
        while (true) {
            if (p.val == d) return;
            if (p.val > d) {
                if (p.left == null) {
                    p.left = new IntNode(d, null, null);
                    return;
                } else {
                    p = p.left;
                }
            } else {
                if (p.right == null) {
                    p.right = new IntNode(d, null, null);
                    return;
                } else {

                    p = p.right;
                }
            }
        }

    }

    // 値の削除
    public static void delete_bst(int d) {
        if (root == null) return;
        IntNode p = root, q = null;
        Lr lr = null;
        while (p != null) {
            if (p.val > d) {
                q=p;
                lr = Lr.L;
                p = p.left;
            } else if (p.val < d) {
                q = p;
                lr = Lr.R;
                p = p.right;
            } else {
                if (p.left == null || p.right == null) {
                    delete_el(p, q, lr);
                    return;
                }
                IntNode r = p.right;
                q = p;
                lr = Lr.R;
                while (r.left != null) {
                    q = r;
                    r = r.left;
                    lr = Lr.L;
                }
                p.val = r.val;
                r.val = d;
                delete_el(r, q, lr);
                return;
            }
        }
        return;
    }

    public static void delete_el(IntNode p, IntNode q, Lr lr) {
        if (p.left == null && p.right == null) {
            if(q==null) root = null;
            else {
                if (lr == Lr.L) q.left = null;
                else q.right = null;
            }
            p = null;
            return;
        }
        if (p.left == null || p.right == null) {
            if (p.left == null) {
                if (q == null) {
                    root = p.right;
                } else {
                    if (lr == Lr.L) q.left = p.right;
                    else q.right = p.right;
                }
            } else {
                if (q == null) {
                    root = p.left;
                } else {
                    if (lr == Lr.L) q.left = p.left;
                    else q.right = p.left;
                }
            }
            p = null;
            return;
        }

    }

    public static void main (String[] args) {
        // 課題の要件を満たすテストを行うためには、main関数で行うテストは自分で書く必要があります。
        System.out.println("最小値："+min_bst());//空の時の最小値の探索
        // 値の挿入
        insert_bst(10);
        insert_bst(15);
        insert_bst(18);
        insert_bst(6);
        insert_bst(12);
        insert_bst(20);
        insert_bst(9);
        display(root);
        System.out.println();

        // その他のメソッドのテスト
        if (search_bst(10))
            System.out.println("Found!");       //全ての要素に探索をする
        else
            System.out.println("Not found!");
        if (search_bst(15))
            System.out.println("Found!");
        else
            System.out.println("Not found!");
        if (search_bst(18))
            System.out.println("Found!");
        else
            System.out.println("Not found!");
        if (search_bst(6))
            System.out.println("Found!");
        else
            System.out.println("Not found!");
        if (search_bst(12))
            System.out.println("Found!");
        else
            System.out.println("Not found!");
        if (search_bst(20))
            System.out.println("Found!");
        else
            System.out.println("Not found!");
        if (search_bst(9))
            System.out.println("Found!");
        else





            System.out.println("Not found!");       //存在しない時
        if (search_bst(14))
            System.out.println("Found!");
        else
            System.out.println("Not found!");
        if (search_bst(7))
            System.out.println("Found!");
        else
            System.out.println("Not found!");
        System.out.println("最小値："+min_bst());

        // 発展課題のテスト
        delete_bst(10);//根を削除
        display(root);
        System.out.println();
    }
}