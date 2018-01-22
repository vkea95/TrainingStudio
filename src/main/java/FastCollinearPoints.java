import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.ArrayList;

/**
 * Created by JianZhang on 1/17/18.
 */
public class FastCollinearPoints {

    private LineSegment[] segments = null;
    private int numberOfSegments = 0;

    public FastCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException();
        checkNull(points);
        Point[] localPoints = Arrays.copyOf(points, points.length);
        checkDuplicate(localPoints);
//        sort the points by the x,y coordinate
        count(localPoints);
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

    private void count(Point[] points) {
        ArrayList<LineSegment> rstList = new ArrayList<>();
        Arrays.sort(points);
        for (int i = 0; i < points.length; i++) {
            Arrays.sort(points, points[i].slopeOrder());
            // after sorting, points[i] will be the first one
            int j = 1;
            while (j < points.length - 1) {
                boolean nonDuplicateFLg = true;
                int length = 1;
                while (j < points.length - 1) {
                    if (points[0].compareTo(points[j]) < 0 && points[0].slopeTo(points[j]) == points[0].slopeTo(points[j + 1])) {
                        length++;
                        j++;
                        // remove the duplicated elements
                    } else if (points[0].compareTo(points[j]) > 0) {
                        double slopeJ = points[0].slopeTo(points[j]);

                        if (slopeJ == points[0].slopeTo(points[j + 1]) || (j > 1 && slopeJ == points[0].slopeTo(points[j - 1]))) {
                            nonDuplicateFLg = false;
                            while ((j < points.length) && slopeJ == points[0].slopeTo(points[j])) {
                                j++;
                            }
                            break;
                        }else {
                            break;
                        }

                    } else {
                        break;
                    }
                }
                if (nonDuplicateFLg && length >= 3) {
//                    boolean duplicateFlg = false;
                    rstList.add(new LineSegment(points[0], points[j]));
                }

                j++;

            }

            // recovery from the sorted order by x,y
            Arrays.sort(points);
        }
        numberOfSegments = rstList.size();
        segments = new LineSegment[rstList.size()];
        for (int i = 0; i < rstList.size(); i++) {
            segments[i] = rstList.get(i);

        }
    }

    public int numberOfSegments() {
        return numberOfSegments;
    }

    public LineSegment[] segments() {
        return Arrays.copyOf(segments, numberOfSegments);
    }

    public static void main(String[] args) {
//        ArrayList<String> list = new ArrayList<>();
//        while (!StdIn.isEmpty()) {
//            String ss = StdIn.readString();
//            for (String str : ss.split(" ")) {
//                list.add(str);
//            }
//        }
        Point[] points = new Point[8];
//        for (int i = 0; i < points.length; i++)
//            points[i] = new Point(2 * i, 2 * i);
//        points[0] = new Point(10000, 0);
//        points[1] = new Point(0, 10000);
//        points[2] = new Point(3000, 7000);
//        points[3] = new Point(7000, 3000);
//        points[4] = new Point(20000, 21000);
//        points[5] = new Point(3000, 4000);
//        points[6] = new Point(14000, 150000);
//        points[7] = new Point(6000, 7000);


        FastCollinearPoints bcp = new FastCollinearPoints(points);
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
