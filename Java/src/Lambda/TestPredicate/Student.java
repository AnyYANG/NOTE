package Lambda.TestPredicate;

/**
 * Created by Ly on 2018/9/11.
 */
public class Student {
    int score;
    String name;

    public Student(String name, int score) {
        this.score = score;
        this.name =name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
