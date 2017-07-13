package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by liujs on 17-7-11.
 */
public class MySyncQueueTest {

    static class PushUtil implements Runnable {
        int start;

        @Override
        public void run() {
            for (int i = start; i < start + 10; i++) {
                push(i);
            }

        }

        PushUtil(int start) {
            this.start = start;
        }

        public void push(Integer v) {
            System.out.println(Thread.currentThread().getName() + "  推： " + v);
            MySyncQueueSync queue = MySyncQueueSync.getInstance();
            queue.lpush(v);
        }

    }


    static class PullUtil implements Runnable {

        @Override
        public void run() {
            while (true) {
                pull();
            }
        }

        public int pull() {
            MySyncQueueSync queue = MySyncQueueSync.getInstance();
            int v = queue.rpull();
            System.out.println(Thread.currentThread().getName() + " 获得： " + v);
            return queue.rpull();
        }
    }


    public static void main(String args[]) throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 30; i += 10) {
            executorService.execute(new PushUtil(i));
        }
        Thread.sleep(2000);

        for (int i = 0; i < 5; i++) {
            executorService.execute(new PullUtil());
        }
        Thread.sleep(5000);

        for (int i = 30; i < 60; i += 10) {
            executorService.execute(new PushUtil(i));
        }

    }


}
