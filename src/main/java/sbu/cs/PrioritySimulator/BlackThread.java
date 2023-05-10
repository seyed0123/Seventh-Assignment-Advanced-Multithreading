package sbu.cs.PrioritySimulator;

import java.util.concurrent.CountDownLatch;

public class BlackThread extends ColorThread {

    private static final String MESSAGE = "hi blues, hi whites!";

    public BlackThread(CountDownLatch latch) {
        super(latch);
    }

    void printMessage() {
        super.printMessage(new Message(this.getClass().getName(), getMessage()));
    }

    @Override
    String getMessage() {
        return MESSAGE;
    }

    @Override
    public void run() {
        printMessage();
    }
}
