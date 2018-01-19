/**
 * Created by JianZhang on 1/16/18.
 */
public class BruteCollinearPoints {
    private int sizeOfSegments;
    private LineSegment[] segmentsArray = null;

    public BruteCollinearPoints(Point[] points) {
        if (points == null || points.length != 4) throw new IllegalArgumentException();

        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                if (points[i].compareTo(points[j]) == 0) throw new IllegalArgumentException();
            }
        }
        Point minPoint = null;
        Point maxPoint = null;
        if (points[0].compareTo(points[1]) < 0) {
            minPoint = points[0];
            maxPoint = points[1];
        } else {
            minPoint = points[1];
            maxPoint = points[0];

        }

        if (points[2].compareTo(minPoint) < 0) {
            minPoint = points[2];
        } else if (points[2].compareTo(maxPoint) > 0) {
            maxPoint = points[2];
        }
        if (points[3].compareTo(minPoint) < 0) {
            minPoint = points[3];
        } else if (points[3].compareTo(maxPoint) > 0) {
            maxPoint = points[3];
        }
        if (points[0].slopeTo(points[1]) == points[0].slopeTo(points[2]) && points[0].slopeTo(points[1]) == points[0].slopeTo(points[3])) {
            segmentsArray = new LineSegment[1];
            segmentsArray[0] = new LineSegment(minPoint, maxPoint);
            sizeOfSegments = 1;
        }


    }


    public int numberOfSegments() {
        return sizeOfSegments;
    }

    public LineSegment[] segments() {
        return segmentsArray;
    }
}
