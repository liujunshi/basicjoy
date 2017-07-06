package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by liujs on 17-7-7.
 */
public class StartJob implements Runnable {


    @Override
    public void run() {
        long start = System.currentTimeMillis();
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + "任务完开始  start：" + start);
        IDGenerator idGenerator = IDGenerator.getInstance(threadName);
        System.out.println(threadName + "获得生成器  生成器名称：" + idGenerator.getName());

        for (int i = 0; i < 100; i++) {
            int id = idGenerator.getNextId();
            System.out.println(threadName + " getid ：" + id);
        }
        long end = System.currentTimeMillis();
        System.out.println(threadName + "任务完成 耗时：" + (end - start));
    }


    public static void main(String args[]) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        StartJob startJob = new StartJob();
        executorService.execute(startJob);
        executorService.execute(new StartJob());
        executorService.execute(new StartJob());
        executorService.execute(new StartJob());
        executorService.execute(new StartJob());

        executorService.shutdown();

    }

}
