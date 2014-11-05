package def.fresh;

/**
 * Created  by dima  on 30.10.14.
 */
public class PairDouble {
	public double x;
	public double y;


	public PairDouble(double x, double y)
	{
		this.x = x;
		this.y = y;
	}

	public int getXInt()
	{
		return (int)x;
	}
	public int getYInt()
	{
		return (int)y;
	}
}
