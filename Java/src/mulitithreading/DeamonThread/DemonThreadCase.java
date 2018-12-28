package mulitithreading.DeamonThread;

/**
 * Created by  liuyang
 * 2018/12/28    18:21
 * 守护进程的简单demo
 * mulitithreading.DeamonThread
 * All Right Reserved by liuyang.
 **/

/**
 * 守护线程ChildThread 会在mainThread启动后一直输出数字，直到mainThread线程结束了。ChlidThread停止输出
 */
public class DemonThreadCase {

    public static void main(String args[]) {
        Thread mainThread = new Thread(new Runnable() {
            @Override
            public void run() {
                Thread childThread = new Thread(new ChildThread(Thread.currentThread()));

                 //设置chlidThread 为mainThread的守护线程。
                childThread.setDaemon(true);
                childThread.start();
                for (int i = 0; i < 20; i++) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("主线程：" + i);
                }
                System.out.println("主线程要结束了");
            }
        });
        mainThread.start();

    }


    /**
     * 这是一个守护线程类
     */
    static class ChildThread implements Runnable {
        //获取主线程
        Thread mainThread;
        ChildThread(Thread thread) {
            this.mainThread = thread;
        }
        @Override
        public void run() {
            int i = 1;
            //这里无论是true  或者mainThread.isAlive 都是可以停止的。只不过区别是守护线程检测不到主进程主动停止的。
            //而mainThread.isAlive(); 是主动中断的。检测到主进程关闭后，主动中断守护进程的。
            while (mainThread.isAlive()) {
                System.out.println("守护线程在执行: " + i);
                i++;
            }
            System.out.println("检测到主进程结束，子线程关闭中...");
        }
    }
}
