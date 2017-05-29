package ctc.com.viii.ch08.n02.dispatchCall;

/**
 * Created by tclresearchamerica on 9/23/16.
 */
public abstract class Person {
    int level;
    boolean isFree;

    public Person(int level) {
        this.level = level;
        this.isFree = false;
    }

    public boolean isFree() {
        return isFree;
    }
}
