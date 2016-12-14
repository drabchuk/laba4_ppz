package MyMathInterpreter.interpreters;

import MyMath.functions.Function;
import MyMath.functions.realizations.factories.FunctionFactory;
import MyMath.functions.realizations.factories.MVFunctionFactory;
import MyMath.numerical.integrators.TrapezeIntegrator;
import MyMathInterpreter.Interpreter;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Denis on 23.11.2016.
 */
public class TrapezeMethodInterpreter implements Interpreter {

    FunctionFactory functionFactory = new MVFunctionFactory();
    String solution = "Error, this solution is init data";

    public TrapezeMethodInterpreter() {
        solution = "Error, this solution is init data";
    }

    @Override
    public List<String> solution(String command) throws Exception{
        List<String> res = new LinkedList<>();
        Function f;
        String fInterpretation;
        String fromfInterpretation;
        String toInterpretation;
        //separate
        int integrateIndex = command.indexOf("integrate");
        if(integrateIndex < 0) throw new Exception("missing integrate statement");
        int fromIndex = command.indexOf("from");
        if(integrateIndex < 0) throw new Exception("missing from statement");
        int toIndex = command.indexOf("to");
        if(integrateIndex < 0) throw new Exception("missing to statement");

        fInterpretation = command.substring(integrateIndex + 10, fromIndex - 1);
        fromfInterpretation = command.substring(fromIndex + 5, toIndex - 1);
        toInterpretation = command.substring(toIndex + 3);
        f = functionFactory.buildFunction(fInterpretation);
        //separate

        if (f.getVarsArray().length > 1) {
            throw new Exception("Function is incorrect");
        }

        double a, b, steps = 10000;

        a = Double.valueOf(fromfInterpretation);
        b = Double.valueOf(toInterpretation);
        if (b <= a) throw new Exception("b <= a");
        TrapezeIntegrator trapezeIntegrator = new TrapezeIntegrator(f);
        double integral = trapezeIntegrator.Integrate(a, b, steps);

        res.add("Integrate function by trapeze method.");
        res.add("f(x) = " + f.getInterpretation());
        res.add("Initial parameters: ");
        res.add("a = " + a + "; b = " + b);
        res.add("RESULT: ");
        res.add("integral = " + Double.toString(integral));

        return res;
    }

}
