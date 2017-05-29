package leetcode.com.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tclresearchamerica on 7/28/16.
 */
public class UndirectedGraphNode {
    public int label;
    public List<UndirectedGraphNode> neighbors;

    public UndirectedGraphNode(int x) {
        label = x;
        neighbors = new ArrayList<>();
    }
}
