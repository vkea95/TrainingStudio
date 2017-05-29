package ctc.com.viii.ch08.n01;

/**
 * Created by tclresearchamerica on 9/21/16.
 */
public abstract class Card {
    private boolean available = true;
    protected int faceValue;
    private Suit suit;

    public Card(int c, Suit s) {
        this.faceValue = c;
        this.suit = s;
    }

    public abstract int value();

    public Suit suit() {
        return suit;
    }

    public boolean isAvailable() {
        return available;
    }

    public void markUnavailable() {
        available = false;
    }

    public void markAvailable() {
        available = true;
    }
}
