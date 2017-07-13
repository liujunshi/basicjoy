package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by liujs on 17-7-11.
 */
public class MySyncQueueConditionTest {

    static class PushUtil implements Runnable {
        int start;
        int step;


        @Override
        public void run() {
            for (int i = start; i < start + step; i++) {
                push(i);
            }

        }

        PushUtil(int start, int step) {
            this.start = start;
            this.step = step;
        }

        public void push(Integer v) {
            System.out.println(Thread.currentThread().getName() + "  推： " + v);
            MySyncQueueCondition queue = MySyncQueueCondition.getInstance();
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

        public Integer pull() {
            MySyncQueueCondition queue = MySyncQueueCondition.getInstance();
            Integer v = queue.rpull();
            System.out.println(Thread.currentThread().getName() + " 获得： " + v);
            return v;
        }
    }


    public static void main(String args[]) throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 30; i += 10) {
            executorService.execute(new PushUtil(i,10));
        }
        Thread.sleep(2000);

        for (int i = 0; i < 5; i++) {
            executorService.execute(new PullUtil());
        }
        Thread.sleep(2000);


        for (int i = 1; i < 5; i++) {
            executorService.execute(new PushUtil(i,1));
//            executorService.execute(new PushUtil(i+1));
            Thread.sleep(3000);
        }

    }


}
