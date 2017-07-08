import java.util.Random;
import java.util.Scanner;          // テキスト入力を簡単に扱うためのライブラリ

public class Sort {
    static long compare_count = 0; // 比較回数を計測するためのクラス変数

    // 比較回数をリセット
    static void reset () {
        compare_count = 0;
    }

    // i と j を比較
    static int compare(int i, int j, String disp) {
        if(disp.equals("-v")){
        compare_count++;
        System.out.print(i + "と" + j + "を比較：");
        if (i < j) {
            System.out.println(i + "<" + j);
            return -1;
        } else if (i == j) {
            System.out.println(i + "=" + j);
            return 0;
        } else {
            System.out.println(i + ">" + j);
            return 1;
        }
        }else {
            compare_count++;
            if (i < j) return -1;
            else if (i == j) return 0;
            else return 1;
        }
    }

    // 配列の要素の交換
    static void swap(int[] a, int i, int j, String disp) {
        if(disp.equals("-v")) {
            System.out.println(a[i] + "と" + a[j] + "を入れ替える");
            display(a, a.length);
            int tmp = a[i];
            a[i] = a[j];
            a[j] = tmp;
            display(a, a.length);
        }else {
            int tmp = a[i];
            a[i] = a[j];
            a[j] = tmp;
        }
    }

    // 配列の要素を表示
    static void display (int [] a, int n) {
        for (int i = 0; i < n; i++)
            System.out.print(a[i] + " ");
        System.out.println();
    }

    // 選択ソート
    static void selection_sort(int[] a, int n,String disp) {
        for (int i = 0; i < n-1; i++) {
            int min = i;
            for (int j = i+1; j < n; j++) {
                if (compare(a[j],a[min],disp) == -1) min = j;
            }
            swap(a, i, min, disp);
        }
    }

    // 挿入ソート
    static void insertion_sort(int[] a, int n,String disp) {
        for (int i = 1; i < n; i++) {
            int data = a[i];
            int j = i - 1;
            while (j >= 0 && compare(data, a[j], disp) == -1) {
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = data;
        }
    }

    // ヒープソート
    static void sift_down(int[] a, int i, int n,String disp) {
        int snode, sval;
        while ((2 * i + 1) <= n - 1) {
            if (2 * i + 1 == n - 1) {
                snode = 2 * i + 1;
                sval = a[2 * i + 1];
            } else {
                if (compare(a[2 * i + 1], a[2 * i + 2],disp) != -1) {
                    snode = 2 * i + 1;
                    sval = a[2 * i + 1];
                } else {
                    snode = 2 * i + 2;
                    sval = a[2 * i + 2];
                }
            }
            if (compare(a[i], sval,disp)==-1) {
                a[snode] = a[i];
                a[i] = sval;
                i = snode;
            } else return;
        }
        return;
    }

    static void build_heap(int[] a, int n, String disp) {
        for (int i = (int) Math.floor(n / 2) - 1; i >= 0; i--) {
            sift_down(a, i, n,disp);
        }
    }

    static void heap_sort (int[] a, int n,String disp) {
        build_heap(a, n,disp);
        for (int m = n - 1; m > 0; m--) {
            swap(a, 0, m,disp);
            sift_down(a, 0, m, disp);
        }
    }



    // クイックソート
    // partition におけるピボットは, 最後の要素(a[right])とする.
    static int partition(int[] a, int pivot, int left, int right,String disp) {
        swap(a, right, pivot,disp);
        int l = left;
        int r = right-1;
        while (true) {
            while (compare(a[l], a[right],disp) == -1) l++;
            while (l <= r && compare(a[r], a[right],disp) != -1) r--;
            if (l < r) {
                swap(a, l, r, disp);
            } else break;
        }
        swap(a, l, right, disp);
        return l;
    }
    static void quick_sort(int[] a, int left, int right,String disp) {
        if (left < right) {
            int pivot = right;
            int p = partition(a, pivot, left, right,disp);
            quick_sort(a, left, p - 1, disp);
            quick_sort(a, p + 1, right, disp);
        }
    }
    // 待ち行列の配列を表示
    static void display_QueueArray(QueueArray[] b, int n) {
        for (int i = 0; i < 10; i++) {
            System.out.print(i+":");
            b[i].display();
        }
    }

    // 基数ソート
    static void radix_sort(int[] a, int n, int k) {
        QueueArray[] b = new QueueArray[10];
        for (int i = 0; i < 10; i++)
            b[i] = new QueueArray(n + 1);
        int div = 1;
        for (int h = 0; h <= k - 1; h++) {

            for (int i = 0; i < n; i++) {
                int index = a[i] / div % 10;
                b[index].enqueue(a[i]);
            }
            System.out.println((h+1)+"桁目基準待ち行列：");
            display_QueueArray(b, n);

            int num = 0;
            for (int j = 0; j < n; j++) {
                while (b[j].rear!=b[j].front) {
                    a[num] = b[j].dequeue();
                    num++;
                }
            }
            div = div * 10;
            System.out.print((h+1)+"桁目基準整列後配列：");
            display(a,n);
        }

    }


    static void qsort(int[] a, int n,String disp) {
        quick_sort(a, 0, n-1,disp);
    }

    public static void main (String[] args) {
        if (args.length > 0) {
            Scanner scan = new Scanner(System.in);
            int n = Integer.parseInt(args[0]);
            int[] a = new int[n];
            System.out.println(n+"個の整数を入力してください．");
            for (int i = 0; i < n; i++) {
                a[i] = scan.nextInt();           // 整数を入力する
            }
            radix_sort(a, n,Integer.parseInt(args[1]));
            System.out.println("整列結果");
            display(a, n);
        } else {
            int n = 1000;
            int[] a = new int[n];
            int num;
            for (int j = 1; j <= 100; j++) {  // 大きさn (n=1000, 2000, ..., 10000) の配列に対してテスト

                Random rnd = new Random();
                for (int i = 0; i < n - 1; i++) {
                    num = rnd.nextInt(n * 10);// 0〜配列の大きさ*10 -1 のランダムな整数を要素とする
                    a[i] = num;
                }
                long t1 = System.nanoTime();
                qsort(a, n, "nothing");
                long t2 = System.nanoTime();

                System.out.print(j + " ");
                System.out.println(((t2 - t1) / 1000));
            }
        }
    }
}
