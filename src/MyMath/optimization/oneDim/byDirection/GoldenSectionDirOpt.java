package MyMath.optimization.oneDim.byDirection;

import MyMath.functions.Function;
import MyMath.linear.vecnew.VectorDouble;
import MyMath.optimization.Parametrizators.LinearParametrizator;
import MyMath.optimization.oneDim.optimizer.GoldenSection;
import MyMath.optimization.oneDim.optimizer.OneDimOptimizer;
import static MyMath.constants.Constants.*;

/**
 * Created by Денис on 09.06.2016.
 */
public class GoldenSectionDirOpt implements DirectionOptimizer {

    @Override
    public VectorDouble minimizeByDir(Function f, VectorDouble x0, VectorDouble direction) {
        double lambda;
        LinearParametrizator parametrizator = new LinearParametrizator(
                f.getVarsArray()
                , x0
                , direction
        );

        // 2.1
        // find lambda
        // min f(x0 - grad * lambda)
        OneDimOptimizer optimizer = new GoldenSection(f, parametrizator);
        lambda = optimizer.findMinimum(STANDARD_ACCURACY);
        VectorDouble minPoint = x0.plus(direction.multByConst(-lambda));

        return minPoint;
    }
}
