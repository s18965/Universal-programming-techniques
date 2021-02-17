package zad1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {

    int pojemnosc;
    List<Integer> list;
    ReentrantLock lock =new ReentrantLock();
    Condition produktPobierany=lock.newCondition();
    Condition produktProdukowany=lock.newCondition();


    public Buffer(int pojemnosc) {
            this.pojemnosc = pojemnosc;
            list = new ArrayList<>();
    }

    public int get(){
        lock.lock();
        try{
            while(list.size()==0){
                try {
                    produktPobierany.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            int skonsumowana=list.get(0);
            list.remove(list.get(0));
            produktProdukowany.signal();
            return skonsumowana;
        }finally {
            lock.unlock();
        }
    }

    public void put(int n){
        lock.lock();
        try {
            while(list.size()==pojemnosc){
                System.out.println("Bufor pełny");
                try {
                    produktProdukowany.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Producent stworzył " + n);
            list.add(n);
            System.out.println("Obecna kolejka:" + list.toString()+ "\n");
            produktPobierany.signal();
        }finally {
            lock.unlock();
        }
    }
}
