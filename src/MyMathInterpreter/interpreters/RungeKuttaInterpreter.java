package MyMathInterpreter.interpreters;

import MyMath.functions.Function;
import MyMath.functions.realizations.factories.FunctionFactory;
import MyMath.functions.realizations.factories.MVFunctionFactory;
import MyMath.numerical.diferentiator.RungeKuttaSolver;
import MyMathInterpreter.Interpreter;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Denis on 22.11.2016.
 */
public class RungeKuttaInterpreter implements Interpreter {

    FunctionFactory functionFactory = new MVFunctionFactory();
    String solution = "Error, this solution is init data";

    public RungeKuttaInterpreter() {
        solution = "Error, this solution is init data";
    }

    @Override
    public List<String> solution(String command) throws Exception{
        List<String> res = new LinkedList<>();
        Function f;
        String fInterpretation;
        String fromfInterpretation;
        String toInterpretation;
        String overInterpretation;
        //separate
        int integrateIndex = command.indexOf("cauchy problem");
        if(integrateIndex < 0) throw new Exception("missing cauchy problem statement");
        int fromIndex = command.indexOf("from");
        if(integrateIndex < 0) throw new Exception("missing from statement");
        int toIndex = command.indexOf("to");
        if(integrateIndex < 0) throw new Exception("missing to statement");
        int overIndex = command.indexOf("over");
        if(integrateIndex < 0) throw new Exception("missing over statement");

        fInterpretation = command.substring(integrateIndex + 15, fromIndex - 1);
        fromfInterpretation = command.substring(fromIndex + 5, toIndex - 1);
        toInterpretation = command.substring(toIndex + 3, overIndex - 1);
        overInterpretation = command.substring(overIndex + 5);
        f = functionFactory.buildFunction(fInterpretation);
        //separate

        if (f.getVarsArray().length > 2) {
            throw new Exception("Function is incorrect");
        }

        double a, b, fa;

        a = Double.valueOf(fromfInterpretation);
        b = Double.valueOf(toInterpretation);
        fa = Double.valueOf(overInterpretation);
        if (b <= a) throw new Exception("b <= a");
        RungeKuttaSolver solver = new RungeKuttaSolver(f);
        double result = solver.solve(a, b, fa);
        res.add("Solve differential equation by Runge-Kutta method.");
        res.add("y' = " + f.getInterpretation());
        res.add("Initial parameters: ");
        res.add("a = " + a + "; b = " + b);
        res.add("y(a) = " + fa);
        res.add("RESULT: ");
        res.add("y(b) = " + Double.toString(result));
        return res;
    }
}
