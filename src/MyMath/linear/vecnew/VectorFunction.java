package MyMath.linear.vecnew;

import MyMath.exceptions.DimensionConflictException;
import MyMath.functions.Function;

/**
 * Created by Денис on 09.06.2016.
 */
public class VectorFunction {

    Function[] x;

    public VectorFunction(Function... f) {
        this.x = f;

    }

    public int getDim() {
        return x.length;
    }

    public Function get(int i) throws RuntimeException{
        return x[i];
    }

    public void set(int i, Function val) throws DimensionConflictException {
        if ( (i < 0) || (i >= x.length)) {
            throw new DimensionConflictException();
        }
        x[i] = val;
    }


    public VectorDouble eval() {
        Double[] res = new Double[getDim()];
        for (int i = 0; i < getDim(); i++) {
            res[i] = x[i].eval();
        }
        return new VectorDouble(res);
    }

    public VectorDouble eval(VectorDouble val){
        Double[] res = new Double[getDim()];
        for (int i = 0; i < getDim(); i++) {
            res[i] = x[i].eval(val);
        }
        return new VectorDouble(res);
    }

    public VectorFunction plus(VectorFunction that) throws DimensionConflictException {
        return null;
    }

    public VectorFunction minus(VectorFunction that) throws DimensionConflictException {
        return null;
    }

    public VectorFunction multByConst(Double c) {
        return null;
    }

    public Function norm() {
        return null;
    }

    public VectorFunction copy() {
        return new VectorFunction(x.clone());
    }
}
