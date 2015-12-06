package util;

import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;

/**
 * Helper class for UI
 * @author apoorva
 *
 */
public class ShapeUtils {

	public static Shape radiusShape(int points, int... radii)
	 {
		Polygon polygon = new Polygon();

		for (int i = 0; i < points; i++)
		{
			double radians = Math.toRadians(i * 360 / points);
			int radius = radii[i % radii.length];

			double x = Math.cos(radians) * radius;
			double y = Math.sin(radians) * radius;

			polygon.addPoint((int)x, (int)y);
		}

		Rectangle bounds = polygon.getBounds();
		polygon.translate(-bounds.x, -bounds.y);

		return polygon;
	}

}
