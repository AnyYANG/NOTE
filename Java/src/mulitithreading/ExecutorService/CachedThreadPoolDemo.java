package mulitithreading.ExecutorService;

/**
 * Created by  liuyang
 * 2018/12/28    20:35
 * mulitithreading.ExecutorService
 * All Right Reserved by liuyang.
 **/

import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 可缓存的线程池，如果线程池长度超过处理需要，可以灵活回收空闲线程，若无可回收，则新建线程
 */
public class CachedThreadPoolDemo {
    //线程计数器
    private CountDownLatch latch = new CountDownLatch(1);


    @Test
    public void demo0() throws InterruptedException {
      Thread thread = new Thread(new printTask("niubi"));
      thread.join();
      thread.start();
      Thread.sleep(10000);
    }
    @Test
    public void demo1() throws InterruptedException {
        Thread thread = new Thread(new printTask("niubi"));
        thread.start();
        latch.await();
    }
    @Test
    public void demo2() {
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            service.submit(new printTask("task" + i));
        }
    }

    /**
     * 打印输出任务
     */
    class printTask implements Runnable {
        printTask(String name) {
            Thread.currentThread().setName("printTask" + name);
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                try {
                    System.out.println(Thread.currentThread().getName() + ":" + i);
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        latch.countDown();
        }
    }
}
