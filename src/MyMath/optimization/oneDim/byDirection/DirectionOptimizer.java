package MyMath.optimization.oneDim.byDirection;

import MyMath.functions.Function;
import MyMath.linear.vecnew.VectorDouble;

/**
 * Created by Денис on 09.06.2016.
 */
public interface DirectionOptimizer {

    VectorDouble minimizeByDir(
            Function f
            , VectorDouble point
            , VectorDouble direction
    );

}
