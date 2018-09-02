package cci.com.ch15.p05;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class FooBad {
    public ReentrantLock sem1, sem2;
    int TIME_TO_SLEEP = 10000000;
    public FooBad(){

        sem1 = new ReentrantLock();
        sem2 = new ReentrantLock();
        sem1.lock();
        sem2.lock();
    }
    public void first(){
        try{
            System.out.println(" in first");
            Thread.sleep(TIME_TO_SLEEP);
            sem1.unlock();

        }catch (InterruptedException e){
            System.out.println("InterruptedException in first");
        }
    }

    public void second(){
        try  {

            sem1.lock();
            sem1.unlock();
            System.out.println(" in second");
            Thread.sleep(TIME_TO_SLEEP);
            sem2.unlock();
        }catch (InterruptedException e){
            System.out.println("InterruptedException in second");
        }
    }

    public void third(){
        try  {

            sem2.lock();
            sem2.unlock();
            System.out.println(" in third");
            Thread.sleep(TIME_TO_SLEEP);
        }catch (InterruptedException e){
            System.out.println("InterruptedException in third");
        }
    }

}
