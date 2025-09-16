package Core.Components;

import java.util.ArrayList;
import java.util.List;

import Core.Math.Geometry.Rectangle2Di;
import Core.Math.Points.Point2i;


public interface Structured extends BaseComponent {
	public Rectangle2Di getRectangle();
	void setRectangle(Rectangle2Di rect);
	public Point2i getPosition();
}
