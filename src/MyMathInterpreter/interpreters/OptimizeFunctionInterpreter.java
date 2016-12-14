package MyMathInterpreter.interpreters;

import MyMath.functions.Function;
import MyMath.functions.realizations.factories.FunctionFactory;
import MyMath.functions.realizations.factories.MVFunctionFactory;
import MyMath.linear.vecnew.VectorDouble;
import MyMath.linear.vecnew.VectorFunction;
import MyMath.optimization.multyDim.Rozenbrok;
import MyMath.optimization.multyDim.Optimizer;
import MyMathInterpreter.Interpreter;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Denis on 22.11.2016.
 */
public class OptimizeFunctionInterpreter implements Interpreter{

    String solution = "Error, this solution is init data";

    public OptimizeFunctionInterpreter() {
        solution = "Error, this solution is init data";
    }

    public List<String> solution(String com) {
        try {
            FunctionFactory functionFactory = new MVFunctionFactory();
            String result = "";
            Function f = functionFactory.buildFunction(com);
            //UnCondTwoDimOpt UCTDO = new UnCondTwoDimOpt(f);
            //result = Double.toString(UCTDO.findMin());
            //UnCondOneDimOpt UCODO = new UnCondOneDimOpt(f);
            //UCODO.defTargetInterval();
            //Interval2d interval = UCODO.getTargetInterval();
            //String toRetTEST = interval.view();
            //return interval.view();
            //return f.toString();
            //return result;
            //UnCondMultiDimOpt UCMDO = new UnCondMultiDimOpt(f);
            //System.out.println("alert");
            //return Double.toString(UCMDO.minimize());
            //return new LinkedList<>();
            Optimizer optimizer = new Rozenbrok();
            Double[] a = new Double[1];
            a[0] = 0.0;
            VectorFunction vf = new VectorFunction();
            VectorDouble prob = new VectorDouble(a);

            VectorDouble min = optimizer.optimize(f);

            System.out.println(min);

            LinkedList<String> res = new LinkedList<>();
            res.add(min.toString());
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            //return "Please, enter function!";
            return new LinkedList<>();
        }
    }

}
