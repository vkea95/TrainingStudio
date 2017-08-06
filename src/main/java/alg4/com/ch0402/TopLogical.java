package alg4.com.ch0402;

/**
 * Created by JianZhang on 8/6/17.
 */
public class TopLogical {

    private Iterable<Integer> order;//顶点的拓扑顺序


    public TopLogical(Digraph G) {
        DirectedCycle cycleFinder = new DirectedCycle(G);//---> 要先check是否有cycle,这是前提条件
        if (!cycleFinder.hasCycle()) {
            DepthFirstOrder depthFirstOrder = new DepthFirstOrder(G);

            order = depthFirstOrder.reversePostOrder();//---> 逆后序是拓扑顺序
        }
    }

    public Iterable<Integer> order() {
        return order;
    }

    public boolean isDAG() {
        return order != null;
    }
}
