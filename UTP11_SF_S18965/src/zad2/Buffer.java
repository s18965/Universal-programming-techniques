package zad2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Buffer {

    BlockingQueue<Integer> blockingQueue;
    int pojemnosc;

    public Buffer(int pojemnosc) {
        this.pojemnosc = pojemnosc;
        blockingQueue = new LinkedBlockingQueue<Integer>(pojemnosc);
    }

    public int get(){
            int skonsumowana=0;
            try{
                int wynik = blockingQueue.take();
                skonsumowana=wynik;
            }catch(InterruptedException inter){
                inter.printStackTrace();
            }
        return skonsumowana;
    }

    public void put(int n){
            while(blockingQueue.size()==pojemnosc){
                System.out.println("Bufor pełny");
            }
            System.out.println("Producent stworzył " + n);
        try {
            blockingQueue.put(n);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
            System.out.println("Obecna kolejka:"+blockingQueue.toString()+"\n");
    }
}
