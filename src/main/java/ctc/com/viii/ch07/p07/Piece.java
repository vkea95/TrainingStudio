package ctc.com.viii.ch07.p07;

import java.util.HashMap;
import java.util.Map;

public class Piece {
    // 关注： 1.如何设定边，因为边有形状，有位置，还牵扯到上下左右rotate
    private final static int NUMBER_OF_EDGES = 4;
    private HashMap<Orientation, Edge> edges = new HashMap<>();

    public Piece(Edge[] edges) {
        Orientation[] orientations = Orientation.values();// Enum 类型可以通过values()获取Orientation的list
        for (int i = 0; i < NUMBER_OF_EDGES; i++) {
            edges[i].setParentPiece(this); // dual binding edge, piece
            this.edges.put(orientations[i], edges[i]);//按照顺时针，设定edge
        }
    }

    public void setEdgeAsOrientation(Edge edge, Orientation orientation) {
        //通过计算orientation的偏移量，来获取rotate之后的位置
        Orientation curtOrientation = getOrientation(edge);
        rotateEdgeBy(orientation.ordinal() - curtOrientation.ordinal());// ordinal可以返回int作为index使用？

    }

    private Orientation getOrientation(Edge edge) {
        for (Map.Entry<Orientation, Edge> entry : edges.entrySet()) {
            if (entry.getValue() == edge) {
                return entry.getKey();
            }
        }
        return null;
    }

    public void rotateEdgeBy(int numberRotations) {
        Orientation[] orientations = Orientation.values();
        HashMap<Orientation, Edge> rotatedEdges = new HashMap<>();

        numberRotations = numberRotations % NUMBER_OF_EDGES;
        numberRotations = numberRotations < 0 ? numberRotations + NUMBER_OF_EDGES : numberRotations;

        for (int i = 0; i < NUMBER_OF_EDGES; i++) {
            //计算rotate之后的edge的位置会是多少
            Orientation oldOrientation = orientations[(i - numberRotations + NUMBER_OF_EDGES) % NUMBER_OF_EDGES];
            Orientation newOrientation = orientations[i];
            rotatedEdges.put(newOrientation, edges.get(oldOrientation));
        }
        edges = rotatedEdges;
    }

    /* Check if this piece is a corner piece. */
    public boolean isCorner() {
        Orientation[] orientations = Orientation.values();
        for (int i = 0; i < orientations.length; i++) {
            Shape current = edges.get(orientations[i]).getShape();
            //计算next需要考虑超过Number_OF_EDGES的情况
            Shape next = edges.get(orientations[(i + 1) % NUMBER_OF_EDGES]).getShape();
            if (current == Shape.FLAT && next == Shape.FLAT) {
                return true;
            }
        }
        return false;
    }

    /* Check if this piece has a border edge. */
    public boolean isBorder() {
        Orientation[] orientations = Orientation.values();
        for (int i = 0; i < orientations.length; i++) {
            if (edges.get(orientations[i]).getShape() == Shape.FLAT) {
                return true;
            }
        }
        return false;
    }

    /* Get edge at this orientation. */
    public Edge getEdgeWithOrientation(Orientation orientation) {
        return edges.get(orientation);
    }

    /* Return the edge that matches targetEdge. Returns null if there is no match. */
    //匹配的时候，其实是在看边与边的code是否math
    public Edge getMatchingEdge(Edge targetEdge) {
        //这里找到和目标edge match的edge对象，然后返回过去。
        for (Edge e : edges.values()) {
            if (targetEdge.fitsWith(e)) {
                return e;
            }
        }
        return null;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Orientation[] orientations = Orientation.values();
        for (Orientation o : orientations) {
            sb.append(edges.get(o).toString() + ",");
        }
        return "[" + sb.toString() + "]";
    }
}
