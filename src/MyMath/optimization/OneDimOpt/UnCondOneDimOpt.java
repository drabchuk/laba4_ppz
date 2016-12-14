package MyMath.OptimizationMethods.OneDimOpt;


import MyMath.exceptions.NoSuchVariableException;
import MyMath.functions.Function;
import MyMath.linear.Intervals.Interval2d;

/**
 * Created by ����� on 23.02.2016.
 */
public class UnCondOneDimOpt {
    private Function targetFunc;
    private Interval2d targetInterval = null;

    public UnCondOneDimOpt(Function target) {
        this.targetFunc = target;
    }

    public Function getTargetfunc() {
        return targetFunc;
    }

    public void setTargetfunc(Function targetfunc) {
        this.targetFunc = targetfunc;
    }

    private double f(double arg) {
        try {
            targetFunc.setVariable("x", arg);
        } catch (NoSuchVariableException e) {
            e.printStackTrace();
        }
        return targetFunc.eval();
    }

    public Interval2d getTargetInterval() {
        return targetInterval;
    }

    public void setTargetInterval(Interval2d targetInterval) {
        this.targetInterval = targetInterval;
    }

    public void defTargetInterval() {
        targetInterval = defTIbySven(30, 5);
    }

    private Interval2d defTIbySven(double x0, double d) {
        double x1, x2, x3, tmp;
        //x1 = x0;
        //x2 = x1 + d;

        x2 = x0;
        x3 = x2 + d;

        //if(f(x2) > f(x1))
        if(f(x3) > f(x2)) {
            d = -d;
            x1 = x3;
        } else {
            x1 = x2 - d;
        }

        while(true) {
            x3 = x2 + d;
            if (f(x3) > f(x2))
                break;
            x1 = x2;
            x2 = x3;
            d *= 2;
        }

        tmp = (x3 + x2)/2;
        if (f(tmp) >= f(x2)) {
            x3 = tmp;
        } else {
            x1 = x2;
            x2 = tmp;
        }

        if (x1 > x3) {
            tmp = x3;
            x3 = x1;
            x1 = tmp;
        }

        return new Interval2d(x1, x3);
    }
}
