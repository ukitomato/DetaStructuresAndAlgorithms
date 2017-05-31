// List.java
public class List {
    static List head;       // 先頭セルを格納する変数
    List next;              // セルのポインタ部
    int data;               // セルのデータ部

    // 新しいセルの挿入（挿入位置はセル p の直後）
    static void insert_cell(List p, int d) {
        List new_cell = new List();
        new_cell.data = d;
        new_cell.next = p.next;
        p.next = new_cell;
    }

    // 新しいセルの挿入（挿入位置はリストの先頭）
    static void insert_cell_top(int d) {
        List new_cell = new List();
        new_cell.data = d;
        new_cell.next = head;
        head = new_cell;
    }

    // セルの削除（削除されるのはセル p の直後のセル）
    static void delete_cell(List p) {
        p.next = p.next.next;
    }

    // セルの削除（削除されるのはリストの先頭セル）
    static void delete_cell_top() {
        head = head.next;
    }

    // リストの要素の表示
    static void display() {
        List tmp = head;
        while (tmp != null) {
            System.out.print(tmp.data+" ");
            tmp = tmp.next;
        }
        System.out.println();
    }

    // main メソッド
    public static void main(String[] args) {
        insert_cell_top(1);
        insert_cell(head, 3);
        insert_cell(head, 2);
        display();

        delete_cell(head);
        display();

        delete_cell_top();
        display();
    }
}
