package MyMath.optimization.multyDim;

import static MyMath.constants.Constants.*;

import MyMath.functions.Function;
import MyMath.functions.realizations.operations.realizations.Var;
import MyMath.linear.vecnew.VectorDouble;
import MyMath.linear.vecnew.VectorFunction;
import MyMath.optimization.oneDim.byDirection.DirectionOptimizer;
import MyMath.optimization.oneDim.byDirection.GoldenSectionDirOpt;

public class Rozenbrok implements Optimizer {

    Var[] vars;
    Function f;
    VectorFunction grad;
    DirectionOptimizer directionOptimizer = new GoldenSectionDirOpt();

    @Override
    public VectorDouble optimize(Function f) {
        this.f = f;
        vars = f.getVarsArray();//Det variables array
        grad = f.grad();//Gradient of entire function

        //set start point as 0
        Double[] dump = new Double[f.getVarsArray().length];
        for (int i = 0; i < dump.length; i++) {
            dump[i] = 0.0;
        }
        VectorDouble startingPoint = new VectorDouble(dump);

        VectorDouble approx = methodMainAlgorithm(f, startingPoint);

        return approx;
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

    private VectorDouble methodMainAlgorithm(Function fun, VectorDouble startingPoint) {


        VectorDouble x0 = startingPoint;

        VectorDouble iterationStart = startingPoint;

        VectorDouble approx;

        // first iteration
        int counter = 0;
        do {
            VectorDouble iterationEnd = findParallelTangent(iterationStart);
            VectorDouble iterationDir = iterationEnd.minus(iterationStart);
            approx = directionOptimizer.minimizeByDir(f, iterationStart, iterationDir);
            iterationStart = approx;
            VectorDouble gradA = grad(approx);
            double gradNorm = grad(approx).norm();
            //System.out.println("grad norm: " + gradNorm);
        } while (grad(approx).norm() > STANDARD_ACCURACY && counter++ < 1000);

        return approx;
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
