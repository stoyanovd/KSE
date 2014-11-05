package def.fresh;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created  by dima  on 30.10.14.
 */
public class CoordinateSystem extends JPanel {

	public static final PairDouble zero = new PairDouble(30, 400);
	public static final PairDouble size = new PairDouble(600, 400);
	public static final double SCALE = 200;
	public static final double STEP_FOR_R = 0.01;
	public static final double EPS = 0.0001;

	public CoordinateSystem() {
		setSize(size.getXInt(), size.getYInt());
	}

	public static PairDouble getInOurCoordinateSystem(PairDouble p) {
		return new PairDouble(p.x * SCALE + zero.x, -p.y * SCALE + zero.y);
	}

	public static void drawLinePairs(Graphics graphics, PairDouble from, PairDouble to) {
		PairDouble fromCommon = getInOurCoordinateSystem(from);
		PairDouble toCommon = getInOurCoordinateSystem(to);
		graphics.drawLine(fromCommon.getXInt(), fromCommon.getYInt(), toCommon.getXInt(), toCommon.getYInt());
	}

	public static  void drawMultiLinePairs(Graphics graphics, List<Double> from ,List<Double> to, double r)
	{
		for(int i = 0; i < from.size(); i++)
			drawLinePairs(graphics, new PairDouble(r - STEP_FOR_R, from.get(i)),new PairDouble(r, to.get(i)));
	}

	@Override
	public void paint(Graphics graphics) {
		super.paint(graphics);


		drawCoordinateLines(graphics);
		drawOne(graphics);
		drawMany(graphics);
		Graphics2D graphics2D;
		graphics2D.setS

	}

	public void drawOne(Graphics graphics) {
		PairDouble from = new PairDouble(0, 0);
		PairDouble to;
		for (double r = 0; r <= 3; r += STEP_FOR_R) {
			to = new PairDouble(r, SimpleIterations.getAfterIterationsWithX0(r));
			drawLinePairs(graphics, from, to);
			from = to;
		}
	}

	public void drawMany(Graphics graphics) {
		java.util.List<Double> from = SimpleIterations.getArrayAfterIterationsWithX0(3);
		java.util.List<Double> to;
		int totalCount = 1;
		for (double r = 3 + STEP_FOR_R; r <= 5; r += STEP_FOR_R) {
			to = SimpleIterations.getArrayAfterIterationsWithX0(r);
			int count = 1;
			for (Double f : to) {
				if (Math.abs(f - to.get(0)) > EPS) {
					count++;
				}
			}
			if(count > totalCount)
			{
				for (Double f : to) {
					drawLinePairs(graphics, new PairDouble(r, f), new PairDouble(r, 0));
				}
				totalCount = count;
			}
			drawMultiLinePairs(graphics, from, to, r);
			from = to;
		}
	}

	public void drawCoordinateLines(Graphics graphics) {
		drawLinePairs(graphics, new PairDouble(0, 0), new PairDouble(0, 7));
		drawLinePairs(graphics, new PairDouble(0, 0), new PairDouble(0, -7));
		drawLinePairs(graphics, new PairDouble(0, 0), new PairDouble(7, 0));
		drawLinePairs(graphics, new PairDouble(0, 0), new PairDouble(-7, 0));
	}

}
