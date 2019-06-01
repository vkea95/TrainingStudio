package ctc.com.viii.ch07.p08;

public class Player {

    private Color color;

    public Player(Color c) {
        this.color = c;
    }

    public Color getColor() {
        return color;
    }

    public int getScore() {
        return Game.getInstance().getBoard().getScoreForColor(color);
    }

    public boolean playPiece(int row, int column) {
        return Game.getInstance().getBoard().placeColor(row, column, color);
    }

}
