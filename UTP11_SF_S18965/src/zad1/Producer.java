package zad1;
import static java.lang.Thread.sleep;

public class Producer implements Runnable {

    Buffer b;

    public Producer(Buffer b) {
        this.b = b;
    }

    @Override
    public void run() {
        while (true) {
            int randomNumber = (int) (Math.random() * 10);
            b.put(randomNumber);
            try {
                sleep((int)(Math.random() * 2000));
            } catch(InterruptedException exc) {exc.printStackTrace(); }

        }
    }
}
