package ctc.com.viii.ch07.p02;

public enum Rank {
    Responder(0),
    Manager(1),
    Director(2);
    private int value;

    Rank(int val) {
        this.value = val;
    }

    public int getValue() {
        return value;
    }
}
