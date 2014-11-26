package task1;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created  by dima  on 20.11.14.
 */
public class DimasEpsMap {

    public static ArrayList<Double> nearestF = null;
    public static ArrayList<Double> last = null;

    public static int lastSize = 1;
    public static int lastSizeIndex = -1;

    public static final int MAX_R_COUNT = 9;

    public static boolean  findR_s = false;
    public static boolean  findR_ss = false;

    public static boolean  firstGone = true;

    public static boolean contains(double x)
    {
        for (Double aNearestF : nearestF) {
            if (Math.abs(x - aNearestF) < DiagramForTask1.EPS) {
                return true;
            }
        }
        return false;
    }

    public static void add(double x)
    {
        if(lastSizeIndex < MAX_R_COUNT && !contains(x))
            nearestF.add(x);
    }

    private static double f(double r, double x) {
        return r * x * (1 - x);
    }
    private static double dist(double x, double y)
    {
        return Math.abs(x - y);
    }

    public static void tryFindRStars(double r, double x, double xFive)
    {
        if(!firstGone)
            return;
        if(!findR_s && dist(x, 0) > dist(x, (r - 1) / r))
        {
            findR_s = true;
            System.out.println("r* = " + r);
        }
        if(findR_s && !findR_ss && !(dist(xFive, (r - 1) / r) > dist(f(r, xFive), (r - 1) / r) &&
                dist(f(r, xFive), (r - 1) / r) > dist(f(r, f(r, xFive)), (r - 1) / r)))
        {
            findR_ss = true;
            System.out.println("r** = " + r);
        }
    }

    public static void tryFindRNumber(double r)
    {
        if(!firstGone)
            return;
        if (DimasEpsMap.nearestF.size() > DimasEpsMap.lastSize) {
            DimasEpsMap.lastSize = DimasEpsMap.nearestF.size();
            DimasEpsMap.lastSizeIndex++;
            if (DimasEpsMap.lastSizeIndex == 0)
                System.out.println("r*** = r_" + DimasEpsMap.lastSizeIndex + " = " + r);
            else {
                System.out.println("r_" + DimasEpsMap.lastSizeIndex + " = " + r);
            }
        }
    }


}
