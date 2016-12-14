package MyMath.optimization.multyDim;

import MyMath.functions.Function;
import MyMath.linear.vecnew.VectorDouble;

/**
 * Created by Денис on 09.06.2016.
 */
public interface Optimizer {

    VectorDouble optimize(Function f);
    VectorDouble optimize(Function f, VectorDouble startingPoint);
    VectorDouble optimize(Function f, VectorDouble startingPoint, Integer requestsCounter);

}
