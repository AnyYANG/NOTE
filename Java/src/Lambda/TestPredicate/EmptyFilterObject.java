package Lambda.TestPredicate;

import java.util.function.Predicate;

/**
 * Created by Ly on 2018/9/11.
 */
public class EmptyFilterObject<T> implements Predicate<T> {
    @Override
    public boolean test(T t) {
        if(t==null){
            return  false;
        }
        return true;
    }
}
