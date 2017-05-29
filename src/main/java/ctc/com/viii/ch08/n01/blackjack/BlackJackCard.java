package ctc.com.viii.ch08.n01.blackjack;

import ctc.com.viii.ch08.n01.Card;
import ctc.com.viii.ch08.n01.Suit;

/**
 * Created by tclresearchamerica on 9/22/16.
 */
public class BlackJackCard extends Card {

    public BlackJackCard(int c, Suit s) {
        super(c, s);
    }

    @Override
    public int value() {
        if (isAce()) return 1;
        else if (faceValue >= 11 && faceValue <= 13) return 10;
        else return faceValue;
    }

    public int minValue() {
        if (isAce()) return 1;
        else return value();
    }


    public int maxValue() {
        if (isAce()) return 11;
        else return value();
    }

    public boolean isAce() {
        return faceValue == 1;
    }

    public boolean isFaceCard() {
        return faceValue >= 11 && faceValue <= 13;
    }
}
