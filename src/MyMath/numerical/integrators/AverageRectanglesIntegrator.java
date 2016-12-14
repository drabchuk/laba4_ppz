package MyMath.numerical.integrators;

import MyMath.exceptions.DimensionConflictException;
import MyMath.functions.Function;
import MyMath.linear.Intervals.Interval;

/**
 * Created by Denis on 15.11.2016.
 */
public class AverageRectanglesIntegrator implements IntegratorIf {

    private final double STANDARD_STEP = 0.1;

    @Override
    public double definiteIntegral(Function f, Interval interval) throws DimensionConflictException {
        return 0;
    }

    @Override
    public double definiteIntegral(Function f, Interval interval, double accuracy) throws DimensionConflictException {
        return 0;
    }

    private double elementaryCellApprox(Function f, Interval interval) {
        return f.eval(interval.average());
    }

}
