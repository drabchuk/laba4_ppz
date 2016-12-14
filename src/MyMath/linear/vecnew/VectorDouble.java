package MyMath.linear.vecnew;

import MyMath.exceptions.DimensionConflictException;

public class VectorDouble{

    Double[] x;

    public VectorDouble(Double... x){
        this.x = x.clone();
    }

    public int getDim() {
        return x.length;
    }

    public Double get(int i) throws RuntimeException{
        return x[i];
    }

    public void set(int i, Double val) throws DimensionConflictException {
        if ( (i < 0) || (i >= x.length)) {
            throw new DimensionConflictException();
        }
        x[i] = val;
    }

    public VectorDouble plus(VectorDouble that)
            throws DimensionConflictException
    {
        if ( that.getDim() != this.getDim()) {
            throw new DimensionConflictException();
        }

        VectorDouble res = new VectorDouble(x);
        for (int i = 0; i < res.getDim(); i++) {
            Double sum = Double.sum(this.get(i), that.get(i));
            res.set(i, sum);
        }
        return res;
    }

    public VectorDouble minus(VectorDouble that)
            throws DimensionConflictException
    {
        if ( that.getDim() != this.getDim()) {
            throw new DimensionConflictException();
        }

        VectorDouble res = new VectorDouble(x);
        for (int i = 0; i < res.getDim(); i++) {
            Double sum = Double.sum(this.get(i), - that.get(i));
            res.set(i, sum);
        }
        return res;
    }

    public VectorDouble multByConst(Double c){
        VectorDouble res = new VectorDouble(x);
        for (int i = 0; i < res.getDim(); i++) {
            Double temp = this.get(i) * c;
            res.set(i, temp);
        }
        return res;
    }

    public VectorDouble elementaryMult(VectorDouble v){
        VectorDouble res = new VectorDouble(x);
        for (int i = 0; i < res.getDim(); i++) {
            Double temp = this.get(i) * v.get(i);
            res.set(i, temp);
        }
        return res;
    }

    public VectorDouble elementaryDiv(VectorDouble v){
        VectorDouble res = new VectorDouble(x);
        for (int i = 0; i < res.getDim(); i++) {
            Double temp = this.get(i) / v.get(i);
            res.set(i, temp);
        }
        return res;
    }

    public Double norm() {
        double sumSqr = 0.0;
        for (int i = 0; i < x.length; i++) {
            sumSqr += Math.pow(x[i], 2);
        }
        return Math.sqrt(sumSqr);
    }

    public VectorDouble normalize() throws ArithmeticException{
        VectorDouble res = new VectorDouble(x);
        Double norm = this.norm();
        if(norm.equals(0.0)) {
            throw new ArithmeticException("division by zero");
        }
        for (int i = 0; i < res.getDim(); i++) {
            Double temp = this.get(i) / norm;
            res.set(i, temp);
        }
        return res;
    }

    public VectorDouble copy() {
        return new VectorDouble(x.clone());
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("(");
        int dim = this.getDim();
        if(dim > 0) {
            sb.append(this.get(0));
        }
        for (int i = 1; i < dim; i++) {
            sb.append("; ");
            sb.append(this.get(i));
        }
        sb.append(")");
        return sb.toString();
    }
}