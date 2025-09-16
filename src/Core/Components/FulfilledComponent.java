package Core.Components;

import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.List;

import Core.Math.Points.Point2i;
import Core.Render.Bitmaps.BitmapARGB;

public class FulfilledComponent extends StructuredComponent implements Responsive, Visual {
	private BitmapARGB bitmap;
	private boolean isVisible = true;
	private boolean isAlphaEnabled = false;
	private RenderMode renderMode = RenderMode.InsideParent;
	
	public FulfilledComponent(Point2i position, Point2i dimensions, List<BaseComponent> children) {
		super(position, dimensions, children);
		this.bitmap = new BitmapARGB(dimensions.x,dimensions.y);
	}
	
	public FulfilledComponent(FulfilledComponent copy) {
		super(copy);
		this.bitmap = new BitmapARGB(copy.bitmap);
		this.isAlphaEnabled = copy.isAlphaEnabled;
		this.isVisible = copy.isVisible;
		this.renderMode = copy.renderMode;
	}
	
	@Override
	public BaseComponent createCopy() {
		return new FulfilledComponent(this);
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
	public void mouseDragged(MouseEvent e) {}

	@Override
	public void mouseMoved(MouseEvent e) {}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void focusGained(FocusEvent e) {}

	@Override
	public void focusLost(FocusEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}
}
