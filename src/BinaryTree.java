
// BinaryTree.java
public class BinaryTree {
    // 行きがけ順のなぞり
    public static void preorder (Node n) {
        if (n == null) return;
        System.out.print(n.val+" ");
        preorder(n.left);
        preorder(n.right);
    }

    // 通りがけ順のなぞり
    public static void inorder (Node n) {
        if (n == null) return;
        inorder(n.left);
        System.out.print(n.val+" ");
        inorder(n.right);
    }

    // 帰りがけ順のなぞり
    public static void postorder (Node n) {
        if (n == null) return;
        postorder(n.left);
        postorder(n.right);
        System.out.print(n.val+" ");
    }

    // 木構造の表示
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

    public static void substitute(Node n, Node[] array, int boxes) {
        if(n == null) return;

        array[boxes] = n;
        substitute(n.right, array, (boxes * 2) + 2);
        substitute(n.left, array, (boxes * 2) + 1);

    }

    public static void display3 (Node n) {
        Node[] boxes = array(n);
        substitute(n, boxes, 0);
        for (int p = 0; p < boxes.length; p++) {
            System.out.print("--");
        }
        System.out.println();
        int array_num = 0;
        int center = 1;
        int floor = height(n);
        for(int i=0;i<height(n)-1;i++) {
            for (int j = 0; j < (int)Math.pow(2,i); j++) {
                for(int l=0;l<((height(n)/(i+1)));l++) {
                    System.out.print("　");
                }
                if(boxes[array_num]==null) System.out.print("　");
                else System.out.print(boxes[array_num].val);
                if (center == array_num) {
                    for(int h=0;h<floor-2;h++) {
                        System.out.print("　");
                    }
                    floor--;
                    center += center + 2;
                }
                array_num++;
            }
            System.out.println();
        }
        while (array_num < boxes.length) {
            if(boxes[array_num]==null) System.out.print(" ");
            else System.out.print(boxes[array_num].val);
            System.out.print(" ");
            array_num++;
        }
        System.out.println();
        for (int p = 0; p < boxes.length; p++) {
            System.out.print("--");
        }
        System.out.println();
    }


    // 幅優先探索
    public static void breadth_first_search (Node n) {
        QueeuArray queue = new QueeuArray(100); // 課題２で実装した待ち行列
        queue.enqueue(n);
        while (!is_empty(queue)) {
            Node node = queue.dequeue();
            System.out.print(node.val+" ");
            if (node.left != null) queue.enqueue(node.left);
            if (node.right != null) queue.enqueue(node.right);
        }
    }
    public static boolean is_empty (QueeuArray array) {
        if (array.rear == array.front) {
            return true;
        } else {
            return false;
        }
    }

    public static Node[] array(Node n) {
        int boxes = 0;
        for (int i = 0; i < height(n); i++) {
            boxes = boxes + (int)Math.pow(2, i);
        }
        Node[] array = new Node[boxes];
        return array;
    }



    // 木の高さ：ただし、教科書の定義の高さ + 1とする。nullの高さが0, 根のみの木が高さ１
    public static int height (Node n) {
        int lh = 0, rh = 0;
        if (n == null) return 0;
        lh = height(n.left) + 1;
        rh = height(n.right) + 1;
        if (lh > rh) {
            return lh;
        } else {
            return rh;
        }
    }

    public static void main (String[] args) {
        // 課題の要件を満たすテストを行うためには、main関数で行うテストは自分で書く必要があります。
        // 木構造の構築
        Node i = new Node("I", null, null);
        Node h = new Node("H", null, null);
        Node g = new Node("G", null, null);
        Node d = new Node("D", null, null);
        Node e = new Node("E", null, i);
        Node f = new Node("F", h, g);
        Node c = new Node("C", d, e);
        Node b = new Node("B", f, null);
        Node a = new Node("A", c, b);

        // 各メソッドのテスト
        preorder(a);
        System.out.println();
        postorder(a);
        System.out.println();
        inorder(a);
        System.out.println();
        breadth_first_search(a);
        System.out.println();
        System.out.println(height(a));
        display3(a);
        System.out.println();

    }
}