package zad2;
import static java.lang.Thread.sleep;
public class Consumer implements Runnable {

    Buffer b;
    Consumer(Buffer b) {
        this.b=b;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("Konsumer skonsumował " + b.get());
            if(b.blockingQueue.size()>0){
                System.out.println("Obecna kolejka: "+ b.blockingQueue.toString()+"\n");
            }else{
                System.out.println("Bufor pusty\n");
            }
            try {
                sleep((int)(Math.random() * 2000));
            } catch(InterruptedException exc) {exc.printStackTrace(); }
        }
    }

}

