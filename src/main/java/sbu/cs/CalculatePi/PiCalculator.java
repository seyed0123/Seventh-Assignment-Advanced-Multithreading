package sbu.cs.CalculatePi;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.Bidi;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PiCalculator {
    /**
     * Calculate pi and represent it as a BigDecimal object with the given floating point number (digits after .)
     * There are several algorithms designed for calculating pi, it's up to you to decide which one to implement.
     Experiment with different algorithms to find accurate results.

     * You must design a multithreaded program to calculate pi. Creating a thread pool is recommended.
     * Create as many classes and threads as you need.
     * Your code must pass all the test cases provided in the test folder.
     */
        public static class PICal implements Runnable
        {
            private final Long start;
            private final Long finish;
            public static BigDecimal pi;
            public PICal(Long start,Long finish) {
                this.start = start;
                this.finish=finish;
            }
            public static synchronized void addRse(BigDecimal res)
            {
                pi= pi.add(res);
            }
            @Override
            public void run() {
                for(long i = start; i < finish; i++) {

                    BigDecimal firstTerm = BigDecimal.ZERO;
                    BigDecimal secondTerm = BigDecimal.ZERO;

                    BigDecimal first = new BigDecimal(5);
                    first = first.pow((int) (2*i+1));
                    BigDecimal firstCoefficient = BigDecimal.valueOf((2 * i + 1) * Math.pow(-1, i));
                    first = first.multiply(firstCoefficient);

                    firstTerm = (BigDecimal.ONE).divide(first, 10000, RoundingMode.HALF_EVEN);
                    firstTerm = firstTerm.multiply(new BigDecimal(4));

                    BigDecimal second = new BigDecimal(239);
                    second = second.pow((int) (2*i+1));
                    BigDecimal secondCoefficient = BigDecimal.valueOf((2 * i + 1) * Math.pow(-1, i));
                    second = second.multiply(secondCoefficient);

                    secondTerm = (BigDecimal.ONE).divide(second, 10000, RoundingMode.HALF_EVEN);

                    BigDecimal finalTerm = firstTerm.subtract(secondTerm);

                    addRse(finalTerm);
                }
            }
        }
    public String calculate(int floatingPoint)
    {
        PICal.pi=BigDecimal.ZERO;
        long count = 500;
        ExecutorService pool = Executors.newFixedThreadPool(2);
        for(int i = 0; i < 2; i++) {
            pool.execute(new PICal(i*count,(i+1)* count));
        }

        pool.shutdown();

        try {
            pool.awaitTermination(10, TimeUnit.SECONDS);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }

        return PICal.pi.multiply(BigDecimal.valueOf(4)).setScale(floatingPoint, RoundingMode.DOWN).toString();
    }
    public static void main(String[] args) {
        /*
        int floatingPoint = 8;
        BigDecimal base = new BigDecimal("0.1");
        PICal.floatingPoint = base.pow(floatingPoint+1);
        PICal.mc = new MathContext(floatingPoint+1);
        PICal.pi = BigDecimal.ZERO;
        ExecutorService executor = Executors.newCachedThreadPool();
        long power = (long) Math.pow(10 ,6);
        /*Thread thread1 = new Thread(new PICal(0,power));
        Thread thread2= new Thread(new PICal(power,power*2));
        thread2.start();
        thread1.start();
        try {
            thread1.join()
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < 100 && PICal.flag ; i++) {
            executor.execute(new PICal(i*power,(i+1)*power));
        }
        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println((PICal.pi.multiply(BigDecimal.valueOf(4))).setScale(floatingPoint, RoundingMode.FLOOR));*/
    }
}
//3.14159265359