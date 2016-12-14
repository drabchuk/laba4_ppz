package MyMath.functions;

import MyMath.functions.realizations.operations.realizations.Var;
import MyMath.exceptions.*;
import MyMath.linear.vecnew.VectorDouble;
import MyMath.linear.vecnew.VectorFunction;

/**
 * Created by Денис on 26.05.2016.
 */
public interface Function {
    double eval();
    double eval(VectorDouble v);
    Function partialDerivative(Var v);
    void setVariable(String name, double val) throws NoSuchVariableException;
    void setVariables(VectorDouble values) throws DimensionConflictException;
    Var[] getVarsArray();
    VectorFunction grad();
    String getInterpretation();
}
