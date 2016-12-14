package MyMath.linear.Intervals;


import MyMath.linear.vecnew.VectorDouble;

/**
 * Created by Denis on 15.11.2016.
 */
public class IntervalDividerIterator {

    private int dim;
    private Interval entireInterval;
    private Interval iterator;
    private VectorDouble c;
    private VectorDouble d;

    public IntervalDividerIterator(Interval entireInterval, double step) {
        this.entireInterval = entireInterval;
        VectorDouble a = (VectorDouble) entireInterval.getA();
        VectorDouble b = (VectorDouble) entireInterval.getB();
        this.dim = a.getDim();
        Double[] stamp = new Double[dim];
        for (int i = 0; i < dim; i++) {
            stamp[i] = step;
        }
        VectorDouble stepVec = new VectorDouble(stamp);
        VectorDouble d = (VectorDouble) a.plus(stepVec);
        iterator = new Interval(a, d);
        this.c = iterator.getA();
        this.d = iterator.getB();
    }

    public Interval next() {
        return  null;
    }

}
