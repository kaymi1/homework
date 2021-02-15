package homework10;

class Queue {
    int n;
    boolean valueSet = false;

    synchronized int get() {
        while(!valueSet){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Получено: " + n);
        valueSet = false;
        notify();
        return n;
    }

    synchronized void put(int n) {
        while (valueSet){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.n = n;
        valueSet = true;
        System.out.println("Отпралено: " + n);
        notify();
    }
}

class Producer implements Runnable {
    Queue q;
    Thread t;

    Producer(Queue q) {
        this.q = q;
        t = new Thread(this, "Producer");
    }

    @Override
    public void run() {
        int i = 0;
        while (i < 10) {
            q.put(i++);
        }
    }
}

class Consumer implements Runnable {
    Queue q;
    Thread t;

    Consumer(Queue q) {
        this.q = q;
        t = new Thread(this, "Consumer");
    }

    @Override
    public void run() {
        int i = 0;

        while (i < 10) {
            q.get();
            i++;
        }

    }
}

public class ProducerConsumerMultithreading {
    public static void main(String[] args) {
        Queue q = new Queue();
        Producer p = new Producer(q);
        Consumer c = new Consumer(q);
        p.t.start();
        c.t.start();
    }
}
