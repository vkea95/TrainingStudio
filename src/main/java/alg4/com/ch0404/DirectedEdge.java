package alg4.com.ch0404;

/**
 * Created by JianZhang on 8/19/17.
 */
public class DirectedEdge {
    private final int from;
    private final int to;
    private final double weight;

    public DirectedEdge(int from, int to, double weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;

    }

    public int from() {
        return from;
    }

    public int to() {
        return to;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "DirectedEdge{" +
                "from=" + from +
                ", to=" + to +
                ", weight=" + weight +
                '}';
    }
}
