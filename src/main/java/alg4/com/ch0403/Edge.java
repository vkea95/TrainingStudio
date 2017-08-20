package alg4.com.ch0403;

/**
 * Created by JianZhang on 8/19/17.
 * 加权无向图的边
 */
public class Edge implements Comparable<Edge> {
    private final int v, w;// use final for the properties
    private final double weight;

    public Edge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public int either() {
        return this.v;
    }

    public int other(int vertex) {
        if (vertex == v) return w;
        else if (vertex == w) return v;
        else throw new RuntimeException("Inconstitute edge");
    }

    public double weight() {
        return weight;
    }

    public int compareTo(Edge that) {
        if (this.weight() == that.weight())
            return 0;
        else if (this.weight() > that.weight())
            return 1;
        else
            return -1;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "v=" + v +
                ", w=" + w +
                ", weight=" + weight +
                '}';
    }
}
