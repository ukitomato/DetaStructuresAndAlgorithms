
import java.io.*;



// ListDL.java
public class ListDL<E>  {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private ListDL prev, next;      // 前と次のセルを指す
    E val;// セルには整数値を格納
    int count = 0;
    boolean dummy = false;

    // コンストラクタ
    <E>ListDL() {
        this.next = this;
        this.prev = this;
        count++;
    }
    ListDL(E val) {
        initLinks();
        this.val = val;
        count++;
    }

    // セルを挿入、削除する操作を共通化するメソッド
    private static void __Insert(ListDL cell, ListDL prev, ListDL next) {
        cell.prev = prev;
        cell.next = next;
        prev.next = cell;
        next.prev = cell;
    }
    private static void __Delete(ListDL prev, ListDL next) {
        prev.next = next;
        next.prev = prev;

    }

    // 前後のセルへのリンクの初期化
    private void initLinks() {
        this.prev = null;
        this.next = null;
    }

    // このセル (this) の次に cell を挿入
    void insertNext(ListDL cell) {
        __Insert(cell, this, this.next);
    }

    // このセル (this) の前に cell を挿入
    void insertPrev(ListDL cell) {
        __Insert(cell, this.prev, this);
    }

    // このセル (this) をリストから削除
    void delete() {
        if (this.prev == null && this.next == null) {
            System.out.println("This cell is not linked");
            System.exit(1);
        }
        __Delete(this.prev,this.next);
        this.initLinks();
        count--;
    }

    // 与えられた整数 i を保持しているセルを探し、そのセルを返す．
    // 見つからなければ null を返す．
    <E>ListDL search(E i) {
        ListDL tmp=this;
        for (int j =0 ;j<count;j++) {
            if (tmp.val == i) {
                return tmp;
            }
            tmp = tmp.next;
        }
        return null;
    }

    // リストの要素を順に出力
    void display() {
        ListDL tmp = this;
        for (int i = 0; i < count; i++) {
            if (!tmp.dummy) System.out.print(tmp.val + " ");
            tmp = tmp.next;
        }
        System.out.println();
    }

    <E>ListDL readFromArray(E[] array) {
        ListDL list = new ListDL();
        int count = 0;
        while (array[count] != null) {
            list.insertNext(new ListDL(array[count]));
        }
        return list;
    }

    String[] writeToStringArray() {
        String[] array = new String[this.count - 1];
        ListDL tmp = this;
        for (int k = 0; k < tmp.count; k++) {
            array[k] = (String)tmp.val;
            tmp = tmp.next;
        }
        return array;
    }


    ListDL readFromFile() throws IOException {
        ListDL list = new ListDL();
        System.out.println("ファイル名を入力");
        String filename = br.readLine();
        File file = new File(filename);
        BufferedReader bf = new BufferedReader(new FileReader(file));
        while (true) {
            String line = bf.readLine();
            if (line == null) break;
            list.insertNext(new ListDL(line));
        }
        bf.close();
        return list;
    }

    void writeToFile(ListDL list) throws IOException {
        System.out.println("ファイル名を入力");
        String filename = br.readLine();
        File file = new File(filename+".txt");
        FileWriter filewriter = new FileWriter(file);
        for (int i = 0; i < list.count; i++) {
            if( list.val != null){
                filewriter.write((String) list.val);
            }
        }
        filewriter.close();
    }

}
