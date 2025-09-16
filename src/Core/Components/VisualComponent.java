package Core.Components;

import java.util.List;

import Core.Math.Geometry.Rectangle2Di;
import Core.Math.Points.Point2i;
import Core.Render.Bitmaps.BitmapARGB;

public class VisualComponent extends StructuredComponent implements Visual {
	private BitmapARGB bitmap;
	private boolean isVisible = true;
	private boolean isAlphaEnabled = false;
	private RenderMode renderMode = RenderMode.InsideParent;
	
	public VisualComponent(Point2i position, Point2i dimensions, List<BaseComponent> children) {
		super(position, dimensions, children);
		this.bitmap = new BitmapARGB(dimensions.x,dimensions.y);
	}
	public VisualComponent(VisualComponent copy) {
		super(copy);
		this.bitmap = new BitmapARGB(copy.bitmap);
		this.isAlphaEnabled = copy.isAlphaEnabled;
		this.isVisible = copy.isVisible;
		this.renderMode = copy.renderMode;
	}

	@Override
	public BitmapARGB getTarget() {
		return this.bitmap;
	}

	@Override
	public boolean isVisible() {
		return this.isVisible;
	}

	@Override
	public boolean isAlphaEnabled() {
		return this.isAlphaEnabled;
	}

	@Override
	public RenderMode getRenderMode() {
		return this.renderMode;
	}
	
	@Override
	public BaseComponent createCopy() {
		return new VisualComponent(this);
	}

}
