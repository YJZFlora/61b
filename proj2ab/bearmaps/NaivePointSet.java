package bearmaps;

import java.util.ArrayList;
import java.util.List;

/* NaivePointSet class should be immutable, meaning that you cannot add or or remove points from it. */
public class NaivePointSet implements PointSet  {

    List<Point> points;

    // constructor
    // You can assume points has at least size 1.
    public NaivePointSet(List<Point> points) {
        this.points = points;
    }

    @Override
    //Returns the closest point to the inputted coordinates.
    // This should take θ(N) time where N is the number of points.
    public Point nearest(double x, double y) {
        Point cmp = new Point(x, y);
        Point returnP = points.get(0);
        double closest = Point.distance(points.get(0), cmp);

        for (Point point : points) {
            double distance = Point.distance(cmp, point);
            if (closest > distance) {
                closest = distance;
                returnP = point;
            }
        }
        return returnP;
    }

}
