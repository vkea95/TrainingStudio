package ctc.com.viii.ch08.n01.blackjack;

import ctc.com.viii.ch08.n01.Hand;

import java.util.List;

/**
 * Created by tclresearchamerica on 9/22/16.
 */
public class BlackJackHand extends Hand<BlackJackCard> {

    public int score() {
        List<Integer> scores = possibleScored();
        int maxUnder = Integer.MIN_VALUE;
        int minOver = Integer.MAX_VALUE;

        for (int score : scores) {
            if (score > 21 && score < minOver) {
                minOver = score;
            } else if (score <= 21 && score > maxUnder) {
                maxUnder = score;
            }
        }
        return maxUnder == Integer.MIN_VALUE ? minOver : maxUnder;
    }

    /*
    re   a list of all possible scores this hand could have(evaluationg each ace as both 1 and 11)
     */
    private List<Integer> possibleScored() {
        return null;
    }

    public boolean busted() {
        return score() > 21;
    }

    public boolean is21() {
        return score() == 21;
    }

    public boolean isBLackJack() {
        return true;
    }

}
