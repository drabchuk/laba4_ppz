package MyMath.numerical.integrators;

import MyMath.exceptions.NoSuchVariableException;
import MyMath.functions.Function;

/**
 * Created by Denis on 15.11.2016.
 */
public class TrapezeIntegrator {

    private Function f;

    public TrapezeIntegrator(Function f) {
        this.f = f;
    }


    public double Integrate(double from, double to, double steps) {
        double step = (to - from) / steps;
        double a = from;
        double b = from + step;
        double fa = f(a);
        double fb = f(b);
        double sum = 0.0;

         do {
            sum += (fb + fa) / 2.0;
            a = b;
            fa = fb;
            b = a + step;
            fb = f(b);
        } while(b < to);

        return sum * step;
    }

    private double f(double x) {
        try {
            f.setVariable("x", x);
        } catch (NoSuchVariableException e) {
            e.printStackTrace();
        }
        return f.eval();
    }

}
