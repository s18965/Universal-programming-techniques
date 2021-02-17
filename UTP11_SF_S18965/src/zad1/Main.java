package zad1;
import java.util.concurrent.*;
public class Main {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        Buffer buffer = new Buffer(5);
        System.out.println("Pojemnosc bufora = " + buffer.pojemnosc+"\n");
        Producer producer = new Producer(buffer);
        Consumer consumer = new Consumer(buffer);
        executor.submit(producer);
        executor.submit(consumer);
        long start = System.currentTimeMillis();
        long end = start + 15*1000;
        while(true)
        {
            if(System.currentTimeMillis()>= end)
            {
                executor.shutdownNow();
                System.exit(0);
            }
        }
    }
}