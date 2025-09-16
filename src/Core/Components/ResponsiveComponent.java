package Core.Components;

import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.List;

import Core.Math.Points.Point2i;

public class ResponsiveComponent extends StructuredComponent implements Responsive {

	public ResponsiveComponent(Point2i position, Point2i dimensions, List<BaseComponent> children) {
		super(position, dimensions, children);
	}
	
	public ResponsiveComponent(ResponsiveComponent copy) {
		super(copy);
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
	
	@Override
	public BaseComponent createCopy() {
		return new ResponsiveComponent(this);
	}
}
