package ctc.com.viii.ch08.n01;

/**
 * Created by tclresearchamerica on 9/21/16.
 */
public enum Suit {
    Club(0), Diamond(1), Heart(2), Spade(3);
    private int value;

    Suit(int v) {
        value = v;
    }

    public int getValue() {
        return value;
    }

    public static Suit getSuitFromValue(int value) {
        return Club;
    }
}
