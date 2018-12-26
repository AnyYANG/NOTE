package bingfa;

import javafx.concurrent.Task;
import org.junit.jupiter.api.Test;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by  liuyang
 * 2018/12/26    14:44
 * bingfa
 * All Right Reserved by liuyang.
 **/

public class CallableDemo {
    @Test
    public void testdemo() {
        ThreadDemo td = new ThreadDemo();
        FutureTask<Long> result = new FutureTask<>(td);
        new Thread(result).start();
        while (!result.isDone()) {  //判断线程是否执行完成。
            System.out.println("waite result" + System.currentTimeMillis());
        }
        try {
            Long sum = result.get();  //FutureTask 可用于 闭锁 类似于CountDownLatch的作用，在所有的线程没有执行完成之后这里是不会执行的
            System.out.println(sum);
            System.out.println("------------------------------------");
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    class ThreadDemo implements Callable<Long> {

        @Override
        public Long call() throws Exception {
            long sum = 0;
            for (int i = 0; i < 100000; i++) {
                sum += i;
                System.out.println(sum);
            }
            return sum;
        }
    }
}
