package MyMath.optimization.multyDim;

import MyMath.functions.Function;
import MyMath.functions.realizations.factories.FunctionFactory;
import MyMath.functions.realizations.factories.MVFunctionFactory;
import MyMath.functions.realizations.operations.realizations.Var;

import java.util.Scanner;

/**
 * Created by Denis on 14.12.2016.
 */
public class RozenbrokRunner {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        FunctionFactory ff = new MVFunctionFactory();
        try {
            while(true) {
                System.out.println("enter function: ");
                String fun_inter = scanner.nextLine();
                if (fun_inter.equals("exit")) {
                    break;
                }
                Function f = ff.buildFunction(fun_inter);
                System.out.println(f.getInterpretation());
                Optimizer optimizer = new Rozenbrok();
                optimizer.optimize(f);
                Var[] var_arr = f.getVarsArray();
                for (Var v : var_arr) {
                    System.out.printf("%s = %.2f \n", v.getName(), v.eval());
                }
                System.out.println();
//            System.out.println(v.toString());
                System.out.printf("f(min) = %.2f \n", f.eval());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
