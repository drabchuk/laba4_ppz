package MyMath.optimization.multyDim;

import MyMath.functions.Function;
import MyMath.functions.realizations.operations.realizations.Var;
import MyMath.linear.vecnew.VectorDouble;
import MyMath.linear.vecnew.VectorFunction;
import MyMath.optimization.oneDim.byDirection.DirectionOptimizer;
import MyMath.optimization.oneDim.byDirection.GoldenSectionDirOpt;

/**
 * Created by Денис on 10.06.2016.
 */
public class ModifiedParTan implements Optimizer {
    Var[] vars;
    Function f;
    VectorFunction grad;
    DirectionOptimizer directionOptimizer = new GoldenSectionDirOpt();

    @Override
    public VectorDouble optimize(Function f) {
        this.f = f;
        vars = f.getVarsArray();//Det variables array
        grad = f.grad();//Gradient of entire function

        //sey start point as 0
        Double[] dump = new Double[f.getVarsArray().length];
        for (Double d: dump) { d = 0.0; }
        VectorDouble startingPoint = new VectorDouble(dump);

        return null;
    }

    @Override
    public VectorDouble optimize(Function f, VectorDouble startingPoint) {
        return null;
    }

    @Override
    public VectorDouble optimize(Function f, VectorDouble x0, Integer requestsCounter) {
        requestsCounter = 0;
        return null;
    }

    private void methodMainAlgorithm(Function fun, VectorDouble startingPoint) {


        VectorDouble x0 = startingPoint;

        VectorDouble iterationStart = startingPoint;

        // first iteration
        VectorDouble iterationEnd = findParallelTangent(iterationStart);

        VectorDouble iterationDir = iterationEnd.minus(iterationStart);

        VectorDouble approx = directionOptimizer.minimizeByDir(f, iterationEnd, iterationDir);

    }

    private VectorDouble findParallelTangent(VectorDouble point) {

        VectorDouble from = point.copy();
        VectorDouble to = point.copy();
        for (int i = 0; i < vars.length; i++) {
            to = directionOptimizer.minimizeByDir(f, from, grad(from));
            from = to;
        }
        return to;
    }

    private VectorDouble grad(VectorDouble point) {
        setVars(point);
        return grad.eval();
    }

    private void setVars(VectorDouble point) {
        for (int i = 0; i < vars.length; i++) {
            vars[i].setValue(point.get(i));
        }
    }
}
