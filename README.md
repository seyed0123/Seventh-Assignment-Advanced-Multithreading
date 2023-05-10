1- for the first problem, I used the Machin PI formula, I created two threads that calculate a part of the pi series.
this formula used the arc-tan Tyler series to calculate the pi number.
in each thread calculate this Tyler series![img.png](img.png) and store them in the `PICal.pi` variable and at the end of the program return it.

2-for this problem used the `CountDownLatch` object to count a number of threads of each color and wait for them to execute entirely and then run another group of threads.
latch object must be set in each thread and this part `latch.countDown()` must be added when the message prints to take in its calculation. 

3- for this problem create a Semaphore object and passed it into every thread and use it like a lock, using this function `sem.acquire()` to get access to resources and using `sem.release()` to release resources for other threads.