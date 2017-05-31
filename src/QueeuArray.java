

public class QueeuArray {
    int length, front, rear;
    Node[] queue;

    // 指定された長さの配列を生成するコンストラクタ
    QueeuArray(int len) {
        queue = new Node[len];
        length = len;
        front = 0;
        rear = 0;
    }

    // データのエンキュー
    void enqueue(Node str) {
        if (rear + 1 == front|(rear==length-1&&front==0)) {
            System.out.println("Queue Overflow");
            System.exit(1);
        }
        queue[rear] = str;
        if(rear==length-1) {
            rear = 0;
        }else{
            rear++;

        }
    }

    // データのデキュー
    Node dequeue() {
        if (rear == front) {
            System.out.println("Queue Underflow");
            System.exit(1);
        }
        int tmp = front;
        if(front==length-1) {
            front = 0;
        }else{
            front++;
        }
        return queue[tmp];
    }

    // キューの要素の表示
    void display() {
        for (int i = front;;) {
            System.out.print(queue[i].val + " ");
            if (i == length - 1) {
                i = 0;
            } else {
                i++;
            }
            if (i == rear) break;
        }
        System.out.println();

    }
}