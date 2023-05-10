package sbu.cs.PrioritySimulator;

import java.util.concurrent.CountDownLatch;

public abstract class ColorThread extends Thread {
    private CountDownLatch latch;
    public ColorThread(CountDownLatch latch)
    {
        super();
        this.latch=latch;
    }
    void printMessage(Message message) {
        System.out.printf("[x] %s. thread_name: %s%n", message.toString(), currentThread().getName());
        Runner.addToList(message);
        latch.countDown();
    }

    abstract String getMessage();
}
