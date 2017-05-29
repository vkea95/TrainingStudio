package ctc.com.viii.ch08.n01;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tclresearchamerica on 9/22/16.
 */
public class Hand<T extends Card> {

    protected List<T> cards = new ArrayList<T>();

    public int score() {
        int score = 0;
        for (T card : cards) {
            score += card.value();
        }
        return score;
    }

    public void addCard(T card) {
        cards.add(card);
    }
}
