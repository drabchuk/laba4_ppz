package MyMath.numerical.difecv;

import MyMath.linear.Intervals.Interval;
import MyMath.linear.vecnew.VectorDouble;
import MyMath.linear.vecnew.VectorFunction;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Denis on 09.12.2016.
 */
public class ODE1Comet extends Comet {

    private static final double ALPHA = 0.01;
    private static final int MAX_STEPS = 300;

    @Override
    public List<VectorDouble> trace(VectorFunction grad, VectorDouble start, int steps) {
        List<VectorDouble> tr = new LinkedList<>();
        VectorDouble p = start.copy();
        VectorDouble v;
        VectorDouble step;
        tr.add(p);
        Double[] alpha_dump = new Double[start.getDim()];
        for (Double d: alpha_dump) {d = ALPHA;}
        VectorDouble alpha = new VectorDouble(alpha_dump);
        VectorDouble prev_step;
        VectorDouble difference;
        try {
            v = grad.eval(p);
            step = v.normalize().elementaryMult(alpha);
            prev_step = step;
            p = p.plus(step);
            tr.add(p);
            for (int i = 1; i < steps; i++) {
                v = grad.eval(p);
                step = v.normalize().elementaryMult(alpha);
                difference = step.minus(prev_step);
                alpha = alpha.plus(difference);
                prev_step = step;
                p = p.plus(step);
                tr.add(p);
            }
        } catch (ArithmeticException ae) {
            System.out.println("Found stationary point");
        }
        return tr;
    }
    /*@Override
    public List<VectorDouble> trace(VectorFunction grad, VectorDouble start, int steps) {
        List<VectorDouble> tr = new LinkedList<>();
        VectorDouble p = start.copy();
        VectorDouble v;
        VectorDouble tmp;
        tr.add(p);
        try {
            for (int i = 1; i < steps; i++) {
                v = grad.eval(p);

                tmp = v.normalize().multByConst(ALPHA);

                p = p.plus(tmp);
                tr.add(p);
            }
        } catch (ArithmeticException ae) {
            System.out.println("Found stationary point");
        }
        return tr;
    }*/

    /*@Override
    public List<VectorDouble> trace(VectorFunction grad, VectorDouble start, Interval boundaries) {
        List<VectorDouble> tr = new LinkedList<>();
        VectorDouble p = start.copy();
        VectorDouble v;
        VectorDouble tmp;
        tr.add(p);
        try {
            int i = 0;
            while (boundaries.within(p) && i++ < MAX_STEPS){
                v = grad.eval(p);

                //tmp = v.normalize().multByConst(ALPHA);
                tmp = v.multByConst(ALPHA);

                p = p.plus(tmp);
                tr.add(p);
            }
        } catch (ArithmeticException ae) {
            System.out.println("Found stationary point");
        }
        return tr;
    }*/

    @Override
    public List<VectorDouble> trace(VectorFunction grad, VectorDouble start, Interval boundaries) {
        List<VectorDouble> tr = new LinkedList<>();
        VectorDouble p = start.copy();
        VectorDouble v;
        VectorDouble step;
        double velocityReduce = 1.0;
        tr.add(p);
        Double[] alpha_dump = new Double[start.getDim()];
        for (int i = 0; i < start.getDim(); i++) {
            alpha_dump[i] = ALPHA;
        }
        VectorDouble alpha = new VectorDouble(alpha_dump);
        VectorDouble prev_step;
        VectorDouble difference;
        try {
            v = grad.eval(p);
            step = v.elementaryMult(alpha);
            prev_step = v;
            p = p.plus(step);
            tr.add(p);
            int i = 0;
            while (boundaries.within(p) && i++ < MAX_STEPS){
                v = grad.eval(p);
                step = v.elementaryMult(alpha);
                //difference = v.minus(prev_step);
                try {
                    velocityReduce = prev_step.norm() / v.norm();
                    velocityReduce = Math.pow(velocityReduce, 0.2);
                } catch (ArithmeticException ae) {
                    velocityReduce = 1;
                }
                alpha = alpha.multByConst(1.0 / velocityReduce);
                prev_step = v;
                p = p.plus(step);
                tr.add(p);
            }
        } catch (ArithmeticException ae) {
            System.out.println("Found stationary point");
        }
        return tr;
    }

    @Override
    public List<VectorDouble> backTrace(VectorFunction grad, VectorDouble start, Interval boundaries) {
        List<VectorDouble> tr = new LinkedList<>();
        VectorDouble p = start.copy();
        VectorDouble v;
        VectorDouble step;
        double velocityReduce = 1.0;
        tr.add(p);
        Double[] alpha_dump = new Double[start.getDim()];
        for (int i = 0; i < start.getDim(); i++) {
            alpha_dump[i] = ALPHA;
        }
        VectorDouble alpha = new VectorDouble(alpha_dump);
        VectorDouble prev_step;
        VectorDouble difference;
        try {
            v = grad.eval(p).multByConst(-1.0);
            step = v.elementaryMult(alpha);
            prev_step = v;
            p = p.plus(step);
            tr.add(p);
            int i = 0;
            while (boundaries.within(p) && i++ < MAX_STEPS){
                v = grad.eval(p).multByConst(-1.0);
                step = v.elementaryMult(alpha);
                //difference = v.minus(prev_step);
                try {
                    velocityReduce = prev_step.norm() / v.norm();
                    velocityReduce = Math.pow(velocityReduce, 0.2);
                } catch (ArithmeticException ae) {
                    velocityReduce = 1;
                }
                alpha = alpha.multByConst(1.0 / velocityReduce);
                prev_step = v;
                p = p.plus(step);
                tr.add(p);
            }
        } catch (ArithmeticException ae) {
            System.out.println("Found stationary point");
        }
        return tr;
    }
}
