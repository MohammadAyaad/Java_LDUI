package Core.Components;

import java.util.LinkedList;
import java.util.List;

import Core.Math.Geometry.Rectangle2Di;
import Core.Math.Points.*;

public class StructuredComponent implements Structured {
	private List<BaseComponent> children;
	private BaseComponent parent; 
	private Rectangle2Di bounds;
	public StructuredComponent(Point2i position,Point2i dimensions,List<BaseComponent> children) {
		this.bounds = new Rectangle2Di(position,dimensions);
		this.parent = null;
		this.children = new LinkedList<BaseComponent>();
		if(children != null) {
			for(BaseComponent child : children) {
				this.addChild(child);
			}
		}
	}
	public StructuredComponent(StructuredComponent copy) {
		this.bounds = new Rectangle2Di(copy.bounds);
		copy.parent.addChild(this);
		this.children = new LinkedList<BaseComponent>();
		for(BaseComponent child : copy.children) {
			BaseComponent c = child.createCopy();
			this.addChild(c);
		}
	}
	@Override
	public List<BaseComponent> getChildren() {
		return this.children;
	}
	public void addChild(BaseComponent child) {
		if(child.getParent() != null) child.getParent().removeChild(child);
		child.setParent(this);
		this.children.add(child);
	}
	@Override
	public BaseComponent getParent() {
		return this.parent;
	}

	@Override
	public Rectangle2Di getRectangle() {
		return this.bounds;
	}

	@Override
	public void setRectangle(Rectangle2Di rect) {
		this.bounds = rect;
	}

	@Override
	public Point2i getPosition() {
		return this.bounds.getPosition();
	}
	@Override
	public void setParent(BaseComponent parent) {
		this.parent = parent;
	}
	@Override
	public void removeChild(BaseComponent child) {
		child.setParent(null);
		this.children.remove(child);
	}
	@Override
	public BaseComponent createCopy() {
		return new StructuredComponent(this);
	}
	@Override
	public void EventBegin() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void EventTick(double delta) {
		// TODO Auto-generated method stub
		
	}

}
