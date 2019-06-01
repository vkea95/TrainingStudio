package ctc.com.viii.ch07.p07;

public enum Orientation {
    LEFT, TOP, RIGHT, BOTTOM;

    public Orientation getOpposite() {
        switch (this) {// according to the current object, get the opposite object
            case LEFT:
                return RIGHT;
            case RIGHT:
                return LEFT;
            case TOP:
                return BOTTOM;
            case BOTTOM:
                return TOP;
            default:
                return null;
        }
    }
}
