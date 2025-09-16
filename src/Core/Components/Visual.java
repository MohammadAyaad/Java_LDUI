package Core.Components;

import Core.Math.Geometry.Rectangle2Di;
import Core.Math.Points.Point2i;
import Core.Render.Bitmaps.BitmapARGB;

public interface Visual extends Structured {
	public BitmapARGB getTarget();
	public boolean isVisible();
	public boolean isAlphaEnabled();
	public RenderMode getRenderMode();
}
