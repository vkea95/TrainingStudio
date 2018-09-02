package cci.com.ch15.p05;

import java.util.concurrent.Semaphore;

public class Foo {
    public Semaphore sem1, sem2;
    int TIME_TO_SLEEP = 5000;
    public Foo(){

        try  {
            sem1 = new Semaphore(1);//only one thread can access at the same time;
            sem2 = new Semaphore(1);//only one thread can access at the same time;
            sem1.acquire();
            sem2.acquire();
        }catch (InterruptedException e){
            System.out.println("InterruptedException in contructor");
        }
    }
    public void first(){
        try{
            System.out.println(" in first");
            Thread.sleep(TIME_TO_SLEEP);
            sem1.release();

        }catch (InterruptedException e){
            System.out.println("InterruptedException in first");
        }
    }

    public void second(){
        try  {

            sem1.acquire();
            sem1.release();
            System.out.println(" in second");
            Thread.sleep(TIME_TO_SLEEP);
            sem2.release();
        }catch (InterruptedException e){
            System.out.println("InterruptedException in second");
        }
    }

    public void third(){
        try  {

            sem2.acquire();
            sem2.release();
            System.out.println(" in third");
            Thread.sleep(TIME_TO_SLEEP);
        }catch (InterruptedException e){
            System.out.println("InterruptedException in third");
        }
    }

}
