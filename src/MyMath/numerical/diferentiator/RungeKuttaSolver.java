package MyMath.numerical.diferentiator;

import MyMath.exceptions.NoSuchVariableException;
import MyMath.functions.Function;
import MyMath.functions.realizations.operations.realizations.Var;

/**
 * Created by Denis on 22.11.2016.
 */
public class RungeKuttaSolver {
    Function f;

    public RungeKuttaSolver(Function f) throws Exception{
        this.f = f;
        Var[] vars = f.getVarsArray();
    }

    public double solve(double a, double b, double fa) throws Exception{
        double x = a, y = fa;
        double k1, k2, k3, k4;
        double h = 0.01, h2 = h/2.0, h6 = h/6.0;
        while(x < b) {
            k1 = f(x, y);
            x += h2;
            k2 = f(x, y + h2 * k1);
            k3 = f(x, y + h2 * k2);
            x += h2;
            k4 = f(x, y + h * k3);
            y += h * (k1 + 2.0 * k2 + 2.0 * k3 + k4) / 6.0;
        }
        return y;
    }

    private double f(double x, double y) throws NoSuchVariableException{
        f.setVariable("x", x);
        f.setVariable("y", y);
        return f.eval();
    }

}
