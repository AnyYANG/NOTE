package Lambda.TestPredicate;

import java.util.List;
import java.util.function.Predicate;

/**
 * Created by Ly on 2018/9/11.

 */
@FunctionalInterface
public interface EmptyFilterObject<T> {

    public boolean test(T t);
}
