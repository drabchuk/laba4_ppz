package MyMath.optimization.Parametrizators;

import MyMath.functions.realizations.operations.realizations.Var;
import MyMath.linear.vecnew.VectorDouble;

/**
 * Created by Денис on 09.06.2016.
 */
public class LinearParametrizator implements Parametrizer {

    private Double lambda;
    private Var[] vars;
    private VectorDouble point;
    private VectorDouble direction;

    public LinearParametrizator(Var[] vars, VectorDouble point, VectorDouble direction) {
        this.vars = vars;
        this.point = point;
        this.direction = direction;
    }

    @Override
    public void setParams(Double... values) {
        lambda = values[0];
        for (int i = 0; i < vars.length; i++) {
            vars[i].setValue(point.get(i) - direction.get(i) * lambda);
        }
    }

    public void setLambda(Double lambda) {
        this.lambda = lambda;
    }
}
