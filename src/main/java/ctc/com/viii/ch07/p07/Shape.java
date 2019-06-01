package ctc.com.viii.ch07.p07;

import java.util.Arrays;

public enum Shape {
    INNER, OUTER, FLAT;

    public Shape getOpposite() {
        switch (this) {
            case INNER:
                return OUTER;
            case OUTER:
                return INNER;
            default:
                return null;
        }
    }
}
