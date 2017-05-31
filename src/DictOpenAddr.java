import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

// DictOpenAddr.java
public class DictOpenAddr {
    DictData[] H;       // 辞書の配列
    int B;              // 配列の大きさ

    // コンストラクタ
    DictOpenAddr(int len) {
        H = new DictData[len];
        B = len;
        for (int i = 0; i < B; i++) {
            H[i] = new DictData();
            H[i].state = State.EMPTY;
        }
    }
    // ハッシュ関数
    int h(int d, int count) {
        return (d + count) % B;
    }
    // データ d を辞書に挿入
    void insert_hash(int d) {
        if(search_hash(d)) return;
        int count = 0;
        int hash = h(d, count);
        int init_hash = h(d, 0);
        do {
            if (H[hash].state == State.EMPTY || H[hash].state == State.DELETED) {
                H[hash].name = d;
                H[hash].state = State.OCCUPIED;
                return;
            }
            count++;
            hash = h(d, count);
        } while (init_hash != hash);
        System.out.println("Dict is full! : " + d);
    }
    // データ d が辞書内に含まれるかを探索（戻り値はboolean型でも可）
    boolean search_hash(int d) {
        int count = 0;
        int hash = h(d, count);
        int init_hash = hash;
        do {
            if (H[hash].state == State.OCCUPIED) {
                if (H[hash].name == d) return true;
            } else if (H[hash].state == State.EMPTY) {
                return false;
            }
            count++;
            hash = h(d, count);
        } while (hash != init_hash);
        return false;
    }
    // データ d を辞書から削除
    void delete_hash(int d) {
        if (!search_hash(d)) {
            System.out.println(d + "is not in this dict!");
            return;
        }
        int count = 0;
        int hash = h(d, count);
        int init_hash = hash;
        do {
            if (H[hash].state == State.OCCUPIED) {
                if (H[hash].name == d) {
                    H[hash].state = State.DELETED;
                    return;
                }
            }
            count++;
            hash = h(d, count);
        } while (hash != init_hash);
    }
    // 配列要素の表示
    void display() {
        for (int i=0;i<B;i++) {
            if (H[i].state==State.OCCUPIED) System.out.print(H[i].name+" ");
            if (H[i].state==State.EMPTY) System.out.print("e ");
            if (H[i].state==State.DELETED) System.out.print("d ");
        }
        System.out.println();
    }

    // mainメソッド
    public static void main(String[] args) throws IOException {

        DictOpenAddr dict = new DictOpenAddr(10000);
        long[] time = new long[10];
        try {
            BufferedReader br1 = new BufferedReader(new FileReader("dict-sample.txt"));
            while (true) {

                String str = br1.readLine();
                if (str == null) break;
                int num = Integer.parseInt(str);
                dict.insert_hash(num);
            }
            br1.close();
            BufferedReader br2 = new BufferedReader(new FileReader("dict-sample.txt"));

            for (int i = 0; i < time.length; i++) {
                int count = 0;
                long amount = 0;
                while (count <= 1000) {
                    String str = br2.readLine();
                    if (str == null) break;
                    int num = Integer.parseInt(str);
                    long t1 = System.nanoTime();
                    dict.search_hash(num);
                    long t2 = System.nanoTime();
                    amount += t2 - t1;
                    count++;
                }
                time[i] = amount;

            }
            br2.close();
            long average_amount = 0;
            for (int i = 0; i < time.length; i++) {
                System.out.println(i + ":" + time[i]);
                average_amount += time[i];
            }
            long average = average_amount / time.length;
            System.out.println("Average:" + average);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}