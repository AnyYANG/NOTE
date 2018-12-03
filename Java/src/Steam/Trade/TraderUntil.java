package Steam.Trade;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by  liuyang
 * 2018/10/16    17:01
 * Steam.Trade
 * All Right Reserved by liuyang.
 **/

public class TraderUntil {
    static {
        Trader tom = new Trader("tom", "beijing");
        Trader tim = new Trader("tim", "beijing");
        Trader qim = new Trader("qim", "shenyang");
        Trader eim = new Trader("eim", "shenyang");
        Trader rim = new Trader("rim", "beijing");
        Trader jim = new Trader("jim", "xian");
        Trader best = new Trader("best", "shanghai");
        Trader lol = new Trader("lol", "shanghai");
        traderList = Arrays.asList(rim,eim,qim,tom, tim, jim, best, lol);
        transactions = Arrays.asList(new Transaction(tom, 2001, 800),
                new Transaction(tim, 2001, 700),
                new Transaction(jim, 2001, 100),
                new Transaction(best, 2001, 399),
                new Transaction(lol, 2001, 993),
                new Transaction(tom, 2001, 863),
                new Transaction(eim, 2001, 700),
                new Transaction(jim, 2001, 130),
                new Transaction(rim, 2001, 3949),
                new Transaction(lol, 2001, 993),
                new Transaction(tom, 2001, 86),

                new Transaction(tim, 2002, 600),
                new Transaction(jim, 2002, 500),
                new Transaction(best, 2002, 499),
                new Transaction(lol, 2002, 693),
                new Transaction(tom, 2002, 566),

                new Transaction(tim, 2003, 500),
                new Transaction(jim, 2003, 400),
                new Transaction(best, 2003, 799),
                new Transaction(lol, 2003, 893),
                new Transaction(tom, 2003, 666)
        );
    }

    private static List<Trader> traderList;
    private static List<Transaction> transactions;
    public static List<Trader> getTraderList() {
        return traderList;
    }
    public static List<Transaction> getTransactions() {
        return transactions;
    }


}
