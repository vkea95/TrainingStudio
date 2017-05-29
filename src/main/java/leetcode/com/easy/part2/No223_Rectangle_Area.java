package leetcode.com.easy.part2;

/**
 * Created by tclresearchamerica on 4/14/16.
 * Location:
 * https://leetcode.com/problems/rectangle-area/
 * *********************************************
 * Description:
 * Find the total area covered by two rectilinear rectangles in a 2D plane.
 * <p>
 * Each rectangle is defined by its bottom left corner and top right corner as shown in the figure.
 * **********************************************
 * Analysis
 * <p>
 * This problem can be converted as a overlap internal problem. On the x-axis, there are (A,C) and (E,G); on the y-axis,
 * there are (F,H) and (B,D). If they do not have overlap, the total area is the sum of 2 rectangle areas.
 * If they have overlap, the total area should minus the overlap area.
 * **********************************************
 * Hints:
 * 坐标的问题,还是可以从分解后的坐标入手,分别尝试分析进行解读
 *
 */
public class No223_Rectangle_Area {

    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int totalArea = (G - E) * (H - F) + (C - A) * (D - B);
        if (A >= G || C <= E) return totalArea;
        if (B >= H || D <= F) return totalArea;

        int rightSide = Math.min(C, G);
        //bug1: min -> max
        int leftSide = Math.max(A, E);
        int topSide = Math.min(H, D);
        //bug2: min -> max
        int bottomSide = Math.max(F, B);
        int overlapArea = (rightSide - leftSide) * (topSide - bottomSide);
        return totalArea - overlapArea;
    }
}
