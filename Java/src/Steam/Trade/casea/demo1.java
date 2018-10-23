package Steam.Trade.casea;

import Steam.Trade.Trader;
import Steam.Trade.TraderUntil;
import Steam.Trade.Transaction;

import java.util.Comparator;
import java.util.List;

/**
 * Created by  liuyang
 * 2018/10/17    15:06
 * Steam.Trade.casea
 * All Right Reserved by liuyang.
 **/

public class demo1 {
    static List<Trader> traders = TraderUntil.getTraderList();
    static List<Transaction> transactions = TraderUntil.getTransactions();

    public  static void main(String args[]){
        filter1();
        filter2();
    }
    //找出所有2001年的交易 并排序
    public static void filter1(){
      transactions.stream().filter(transaction ->transaction.getYear()==2001).sorted(Comparator.comparingInt(Transaction::getValue)).forEach(transaction -> System.out.println(transaction.toString()));
    }
    //找出所有交易员的城市
    public static void filter2(){
        traders.stream().map(trader -> trader.getCity()).distinct().forEach(s -> System.out.println(s));
      }
    //找出所有北京交易员
    public static void filter3(){
        traders.stream().map(trader -> trader.getCity()).distinct().forEach(s -> System.out.println(s));
    }
}
