package Steam.Trade;

/**
 * Created by  liuyang
 * 2018/9/29    11:18
 * Steam.Trade
 * All Right Reserved by liuyang.
 **/

public class Trader {
    private final String name;
    private final String city;
    public Trader(String n, String c){
        this.name = n;
        this.city = c;
    }
    public String getName(){
        return this.name;
    }
    public String getCity() {
        return this.city;
    }
    public String toString(){
        return "Trader:"+this.name + " in " + this.city;
    }
}
