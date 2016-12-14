package MyMath.numerical.integrators;

import MyMath.exceptions.DimensionConflictException;
import MyMath.functions.Function;
import MyMath.linear.Intervals.Interval;

/**
 * Created by Denis on 15.11.2016.
 */
public interface IntegratorIf {

    double definiteIntegral(Function f, Interval interval) throws DimensionConflictException;
    double definiteIntegral(Function f, Interval interval, double accuracy) throws DimensionConflictException;

}
