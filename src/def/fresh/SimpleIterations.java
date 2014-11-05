package def.fresh;

import java.util.ArrayList;
import java.util.List;

/**
 * Created  by dima  on 30.10.14.
 */
public class SimpleIterations {


	public static final int NUMBER_OF_ITERATIONS = 1000;
	public static final double OUR_X0 = 0.2;
	public static final int BIFURCATIONS_NUMBER = 32;

	public static double getAfterIterations(double x0, double r)
	{
		double x = x0;
		for(int i = 0;  i < NUMBER_OF_ITERATIONS; i++)
			x = getNext(x, r);
		return x;
	}

	public static double getAfterIterationsWithX0(double r)
	{
		return getAfterIterations(OUR_X0, r);
	}

	public static double f(double x, double r) {
		return r * x * (1 - x);
	}

	public static double getNext(double cur, double r) {
		return f(cur, r);
	}

	public static final List<Double> getArrayAfterIterationsWithX0(double r)
	{
		List<Double> list = new ArrayList<>();
		double x = getAfterIterationsWithX0(r);
		for(int i = 0; i < BIFURCATIONS_NUMBER; i++)
		{
			x = getNext(x ,r);
			list.add(x);
		}
		return list;
	}
}
