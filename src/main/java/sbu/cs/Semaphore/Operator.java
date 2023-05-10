package sbu.cs.Semaphore;

import java.time.LocalDateTime;
import java.util.concurrent.Semaphore;

public class Operator extends Thread {
    private Semaphore sem;
    public Operator(String name,Semaphore sem) {
        super(name);
        this.sem=sem;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++)
        {
            try {
                sem.acquire();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Resource.accessResource();// critical section - a Maximum of 2 operators can access the resource concurrently
            System.out.println(this.getName()+" accessed resource in "+ LocalDateTime.now());
            sem.release();
            try {
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
