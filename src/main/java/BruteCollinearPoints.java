import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by JianZhang on 1/16/18.
 */
public class BruteCollinearPoints {
    private int sizeOfSegments;
    private LineSegment[] segmentsArray = null;

    public BruteCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException();
        // bug 1;
        // make sure that data type does not mutate the constructor argument
        checkNull(points);
        Point[] localPoints = Arrays.copyOf(points, points.length);
        checkDuplicate(localPoints);
        countSegments(localPoints);
    }

    private void checkNull(Point[] points) {
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) throw new IllegalArgumentException();
        }
    }

    private void checkDuplicate(Point[] points) {
        Arrays.sort(points);
        for (int i = 1; i < points.length; i++) {
            if (points[i].compareTo(points[i - 1]) == 0) throw new IllegalArgumentException();
        }
    }

    private void countSegments(Point[] points) {
//        Point[] points = this.points;
        ArrayList<LineSegment> rstList = new ArrayList<>();
        Arrays.sort(points);
        int i = 0;
        int j = i + 1;
        int k = j + 1;
        int l = k + 1;
        while (i < points.length) {

//            boolean collinearFlg = false;

            while (j < points.length) {
                while (k < points.length) {
                    if (points[i].slopeTo(points[j]) == points[i].slopeTo(points[k])) {
                        l = points.length - 1;
                        while (l > k) {
                            if (points[i].slopeTo(points[j]) == points[i].slopeTo(points[l])) {
                                rstList.add(new LineSegment(points[i], points[l]));
//                                collinearFlg = true;
                                break;
                            }
                            l--;
                        }
                    }
                    k++;
                }
                j++;
                k = j + 1;
            }
            i++;
            j = i + 1;
            k = j + 1;
        }
        sizeOfSegments = rstList.size();
        segmentsArray = new LineSegment[rstList.size()];
        for (i = 0; i < rstList.size(); i++) {
            segmentsArray[i] = rstList.get(i);

        }
    }

    public int numberOfSegments() {

        return sizeOfSegments;
    }

    public LineSegment[] segments() {

        return Arrays.copyOf(segmentsArray, sizeOfSegments);
    }

    public static void main(String[] args) {
        Point[] points = new Point[4];
        for (int i = 0; i < points.length; i++)
            points[i] = new Point(1, 2);


        BruteCollinearPoints bcp = new BruteCollinearPoints(points);
        LineSegment[] ls = bcp.segments();

        StdOut.println("segmentsArray: " + bcp.segments());
        ArrayList<LineSegment> rstList = new ArrayList<>();

        points = new Point[8];
        for (int i = 0; i < points.length; i++)
            points[i] = new Point(i * 3, i * 9);


        rstList.add(new LineSegment(new Point(1, 2), new Point(2, 3)));
//        LineSegment[] segmentsArray = (LineSegment[]) rstList.toArray();

    }
}
