

public class QueueArray1 {
    int length, front, rear;
    int[] queue;

    // 指定された長さの配列を生成するコンストラクタ
    QueueArray1(int len) {
        queue = new int[len];
        length = len;
        front = 0;
        rear = 0;
    }

    // データのエンキュー
    void enqueue(int val) {
        if (rear + 1 == front|(rear==length-1&&front==0)) {
            System.out.println("Queue Overflow");
            System.exit(1);
        }
        queue[rear] = val;
        if(rear==length-1) {
            rear = 0;
        }else{
            rear++;

        }
    }

    // データのデキュー
    int dequeue() {
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
        if(rear==front) return;
        for (int i = front;;) {
            System.out.print(queue[i] + " ");
            if (i == length - 1) {
                i = 0;
            } else {
                i++;
            }
            if (i == rear) break;
        }
        System.out.println();

    }

    // main メソッド
    public static void main(String[] args) {
        QueueArray1 queue = new QueueArray1(10);

        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        queue.enqueue(6);
        queue.display();

        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        queue.display();
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        queue.display();
        System.out.println(queue.dequeue());
        queue.display();
        System.out.println(queue.dequeue());
        queue.display();
    }
}