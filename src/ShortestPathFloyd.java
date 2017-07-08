
import java.util.Stack;

public class ShortestPathFloyd {
    static final int N = 8;
    static final int M = Integer.MAX_VALUE;
    static int w[][] = {
            {0,24,22,15,M,30,M,29},
            {14,0,24,M,17,M,8,M},
            {M,M,0,M,M,22,11,M},
            {28,M,M,0,6,6,M,M},
            {30,29,5,M,0,26,8,3},
            {M,M,30,14,27,0,M,25},
            {M,M,M,M,19,12,0,M},
            {M,28,M,M,M,9,4,0}
    };

    static int[][] d = new int[N][N];  // 頂点iからjまでの最短距離
    static int[][] p = new int[N][N];  // 頂点iからjまでの最短路でたどる最後の辺<k, j>のk

    static void Floyd() {
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++) {
                d[i][j] = w[i][j];
                p[i][j] = i;
            }
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    int can = (d[i][k] == M || d[k][j] == M) ? M : d[i][k] + d[k][j];//∞には何を足しても∞
                    if (can < d[i][j]) {
                        d[i][j] = can;
                        p[i][j] = p[k][j];
                    }
                }
            }
        }
    }

    static void shortest_path(int m, int n) {
        int x;
        Stack stack = new Stack();
        if (d[m][n] == M)
            System.out.println("There is no path.");
        else {
            x = n;
            stack.push(x);
            while (x != m) {
                x = p[m][x];
                stack.push(x);

            }
            System.out.print(stack.pop()); //矢印の関係で先頭だけ先に表示
            while (!stack.empty()) {
                System.out.print("->" + stack.pop());
            }
            System.out.println("(END)");
        }
    }

    static void array_display(int[][] array) {//配列確認のための配列表示メソッド
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                System.out.print(((array[i][j]==M)?"M":array[i][j]) + " ");
            }
            System.out.println();
        }
    }

    static void wdp_display() {    //w,d,pの配列確認メソッド
        System.out.println("w:");
        array_display(w);
        System.out.println("d:");
        array_display(d);
        System.out.println("p:");
        array_display(p);
    }

    static void display(int m, int n) {    //m->nへのパスと重みを表示
        System.out.print(m + "->" + n + " : ");
        System.out.print("weight=");
        weight_display(m, n);
        System.out.print(", path=");
        shortest_path(m, n);
    }

    static void each_display(int start) {    //引数の位置からの各頂点へのパスと重みを表示
        for (int i = 0; i < N; i++) {
            display(start, i);
        }
    }

    static void weight_display(int m, int n) {//m->nへの重みを表示
        System.out.print((d[m][n]==M)?"M":d[m][n]);
    }

    /**
     * メイン関数．
     *
     * @param args コマンドライン引数
     * @return なし
     */
    public static void main(String[] args) {
        wdp_display();//初期配列状態
        Floyd();// Floyd法による最短路の計算
        wdp_display();//計算後配列
        each_display(1);
        each_display(5);
    }
}
