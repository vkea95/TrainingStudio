package ctc.com.viii.ch07.p08;

public class Piece {
    private Color color;

    public Piece(Color c) {
        this.color = c;
    }

    public void flip() {
        color = color == Color.Black ? Color.White : Color.Black;
    }

    public Color getColor() {
        return color;
    }
}
