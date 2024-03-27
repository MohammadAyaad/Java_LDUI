package Core.Components.BaseComponent.StructureComponent;

import java.util.ArrayList;
import java.util.List;

import Core.Components.InitStatus;
import Core.Components.PositionMode;
import Core.Components.BaseComponent.BaseComponent;
import Core.Math.Geometry.Rectangle2Di;
import Core.Math.Points.Point2i;


public class StructureComponent extends BaseComponent {
	public PositionMode positionMode;
	protected Rectangle2Di bounds;
	
	public StructureComponent(Point2i position,Point2i size)
	{
		this.bounds = new Rectangle2Di(position,size);
		this.parent = null;
		this.children = new ArrayList();
		this.positionMode = PositionMode.RelativeToParent;
		this.status = this.initialize();
	}
	public StructureComponent(Rectangle2Di rectangle)
	{
		this.bounds = new Rectangle2Di(rectangle);
		this.parent = null;
		this.children = new ArrayList();
		this.positionMode = PositionMode.RelativeToParent;
		this.status = this.initialize();
	}
	
	
	public Point2i getPosition()
	{
		switch(this.positionMode)
		{
		case RelativeToParent:
			if(this.parent != null && (this.parent instanceof StructureComponent)) return Point2i.add(this.bounds.getPosition(), ((StructureComponent)parent).bounds.getPosition());
			else return this.bounds.getPosition();
		case Absolute:
			return this.bounds.getPosition();
		default:
			return this.bounds.getPosition();
		}
	}
	public Point2i getAbsolutePosition()
	{
		switch(this.positionMode)
		{
		case RelativeToParent:
			if(this.parent != null && (this.parent instanceof StructureComponent)) return Point2i.add(this.bounds.getPosition(), ((StructureComponent)parent).bounds.getPosition());
			else return this.bounds.getPosition();
		case Absolute:
			return this.bounds.getPosition();
		default:
			return this.bounds.getPosition();
		}
	}
	public Point2i getRelativePosition() {
		return this.bounds.getPosition();
	}
	
	public Rectangle2Di getBounds()
	{
		return this.bounds;
	}
	
	
	public InitStatus initialize()
	{
		return InitStatus.OK;
	}
	
}
