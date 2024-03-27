package Core.Components.BaseComponent.StructureComponent.VisualComponent;

import Core.Components.RenderMode;
import Core.Components.BaseComponent.StructureComponent.StructureComponent;
import Core.Math.Geometry.Rectangle2Di;
import Core.Math.Points.Point2i;
import Core.Render.Bitmaps.BitmapARGB;

public abstract class VisualComponent extends StructureComponent{
	public boolean visible = true;
	protected BitmapARGB target = null;
	public boolean enableAlpha = false;
	public RenderMode renderMode;
	
	public VisualComponent(Point2i position, Point2i size) {
		super(position, size);
		this.renderMode = RenderMode.InsideParent;
	}
	public BitmapARGB getTarget()
	{
		return this.target;
	}
	
	public abstract void paintTarget(Rectangle2Di rectangle);
	
	
}
