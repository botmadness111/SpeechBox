import java.util.Objects;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Foot implements Runnable {
    private String name;

    final Lock lock = new ReentrantLock();
    private volatile static int flag = 0;

    public Foot(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        while (true) {
            try {
                resolveDeadLock();
                step();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void resolveDeadLock() throws InterruptedException {
        while (true) {
            Boolean isLock = lock.tryLock();
            System.out.println(isLock);

            if (isLock) {
            }

        }
    }

    private void step() throws InterruptedException {
        System.out.println("Step by " + name);

    }
}


public class Main {
    public static void main(String[] args) {
        Foot left = new Foot("left");
        Foot right = new Foot("right");

        Thread thread1 = new Thread(left);
        Thread thread2 = new Thread(right);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}