package MyMath.linear.Intervals;


import MyMath.linear.vecnew.VectorDouble;

/**
 * Created by Denis on 15.11.2016.
 */
public class Interval {

    private int dimension;
    private VectorDouble a;
    private VectorDouble b;

    public Interval(VectorDouble a, VectorDouble b) {
        assert a.getDim() == b.getDim();
        this.dimension = a.getDim();
        for (int i = 0; i < dimension; i++) {
            assert a.get(i) < b.get(i);
        }
        this.a = (VectorDouble) a.copy();
        this.b = (VectorDouble) b.copy();
    }

    public VectorDouble average() {
        Double[] aver = new Double[dimension];
        for (int i = 0; i < dimension; i++) {
            aver[i] = (b.get(i) - a.get(i)) / 2.0;
        }
        return new VectorDouble(aver);
    }

    public boolean within(VectorDouble point) {
        if (point.getDim() == dimension) {
            for (int i = 0; i < dimension; i++) {
                double p = point.get(i);
                if (p < a.get(i) || p > b.get(i)) {
                    return false;
                }
            }
            return true;
        }
        else return false;
    }

    public VectorDouble getA() {
        return a;
    }

    public VectorDouble getB() {
        return b;
    }
}
