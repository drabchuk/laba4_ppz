package MyMath.functions.realizations.factories;

import MyMath.functions.Function;

/**
 * Created by Денис on 09.06.2016.
 */
public abstract class FunctionFactory {

    public abstract Function buildFunction(String interpretation) throws Exception;

}
