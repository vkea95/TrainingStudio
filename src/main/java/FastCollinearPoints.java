import java.util.Arrays;

/**
 * Created by JianZhang on 1/17/18.
 */
public class FastCollinearPoints {
    public FastCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException();
//        sort the points by the x,y coordinate
        Arrays.sort(points);

    }

    public int numberOfSegments() {
        return 0;
    }  // the number of line segments

    public LineSegment[] segments() {
        return null;
    } // the line segments
}
