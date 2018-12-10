package bingfa.reeentrantlock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by  liuyang
 * 2018/12/6    20:51
 * bingfa
 * All Right Reserved by liuyang.
 **/

public class RLock {
    protected static Lock lock = new ReentrantLock();
    protected static Condition condition=lock.newCondition();
}
