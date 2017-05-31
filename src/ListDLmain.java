// ListDLmain.java
// リストの実装をテストするためのクラス
public class ListDLmain {
    public static void main(String[] args) {
        ListDLInt head = new ListDLInt();            // ダミーセルの生成
        ListDLInt elem;

        head.insertNext(new ListDLInt(1));      // セルの先頭への追加
        head.insertNext(new ListDLInt(5));
        head.insertPrev(new ListDLInt(2));      // セルの末尾への追加
        head.display();                      // リストの表示

        elem = (ListDLInt) head.search(5);               // セルを探す
        elem.insertNext(new ListDLInt(3));      // 探したセルの直後にセルを追加
        head.display();

        elem = (ListDLInt) head.search(5);               // セルを探す
        elem.delete();                       // 探したセルを削除
        head.display();
    }
}