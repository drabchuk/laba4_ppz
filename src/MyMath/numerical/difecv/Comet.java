package MyMath.numerical.difecv;

import MyMath.linear.Intervals.Interval;
import MyMath.linear.vecnew.VectorDouble;
import MyMath.linear.vecnew.VectorFunction;

import java.util.List;

/**
 * Created by Denis on 09.12.2016.
 */
public abstract class Comet {

    public abstract List<VectorDouble> trace(VectorFunction grad, VectorDouble start, int steps);
    public abstract List<VectorDouble> trace(
            VectorFunction grad, VectorDouble start, Interval boundaries);
    public abstract List<VectorDouble> backTrace(
            VectorFunction grad, VectorDouble start, Interval boundaries);

}
