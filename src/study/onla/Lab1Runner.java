package study.onla;

import MyMath.functions.Function;
import MyMath.functions.realizations.factories.FunctionFactory;
import MyMath.functions.realizations.factories.MVFunctionFactory;
import MyMath.linear.Intervals.Interval;
import MyMath.linear.vecnew.VectorDouble;
import MyMath.linear.vecnew.VectorFunction;
import MyMath.numerical.difecv.PhasePortraitDrawer;

/**
 * Created by Denis on 09.12.2016.
 */
public class Lab1Runner {

    private static final String X_DER = "3*(x+1)*(y-4)";
    private static final String Y_DER = "x^2 - (y^2)";
    private static final String X_DER_VLAD = "(2*x-y)^2-9";
    private static final String Y_DER_VLAD = "9-((x - 2*y)^2)";

    public static void main(String[] args) {
        try {
            FunctionFactory ff = new MVFunctionFactory();
            Function xDer = ff.buildFunction(X_DER);
            Function yDer = ff.buildFunction(Y_DER);
            VectorFunction sytem = new VectorFunction(xDer, yDer);
            //Interval bnd = new Interval(new VectorDouble(-5.0, 3.0)
            //        , new VectorDouble(-3.0, 5.0));
            Interval bnd = new Interval(new VectorDouble(-8.0, -8.0)
                           , new VectorDouble(8.0, 8.0));
            //Interval bnd = new Interval(new VectorDouble(-2.0, 0.0)
            //                , new VectorDouble(0.0, 2.0));
            //Interval bnd = new Interval(new VectorDouble(-1.0, -1.0)
            //                        , new VectorDouble(1.0, 1.0));
            PhasePortraitDrawer drawer = new PhasePortraitDrawer(sytem);
            drawer.draw(bnd, 0.3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
