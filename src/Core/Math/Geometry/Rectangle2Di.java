package Core.Math.Geometry;

import Core.Math.Points.Point2i;

public class Rectangle2Di {
	protected Point2i position;
	protected Point2i size;
	
	public Rectangle2Di()
	{
		this.position = new Point2i();
		this.size = new Point2i();
	}
	
	public Rectangle2Di(Point2i position,Point2i size)
	{
		this.position = (position == null)?new Point2i():position;
		this.size = (size == null)?new Point2i():size;
	}
	public Rectangle2Di(Rectangle2Di copy)
	{
		this.position = new Point2i(copy.position);
		this.size = new Point2i(copy.size);
	}
	public void setPosition(Point2i position)
	{
		this.position = position;
	}
	public void setPosition(int x,int y)
	{
		this.position.x = x;
		this.position.y = y;
	}
	public Point2i getPosition()
	{
		return this.position;
	}
	public void setSize(Point2i size)
	{
		this.size = size;
	}
	public void setSize(int x,int y)
	{
		this.size.x = x;
		this.size.y = y;
	}
	public Point2i getSize()
	{
		return this.size;
	}
	
	public boolean inBounds(int x,int y)
	{
		return (x > this.position.x && x < (this.position.x + this.size.x)) &&
			(y > this.position.y && y < (this.position.y + this.size.y));
	}
	public boolean inBounds(Point2i point)
	{
		return (point.x > this.position.x && point.x < (this.position.x + this.size.x)) &&
				(point.y > this.position.y && point.y < (this.position.y + this.size.y));
	}
	public boolean inBounds(Rectangle2Di rect)
	{
		return (this.position.x < (rect.position.x + rect.size.x) && this.position.x + this.size.x > rect.position.x) &&
				((this.position.y < (rect.position.y + rect.size.y)) && ((this.position.y + this.size.y) > rect.position.y));	
	}
	public boolean collidesWith(Point2i point)
	{
		return (point.x >= this.position.x && point.x <= (this.position.x + this.size.x)) &&
				(point.y >= this.position.y && point.y <= (this.position.y + this.size.y));
	}
	public boolean collidesWith(Rectangle2Di rect)
	{
		return (this.position.x <= (rect.position.x + rect.size.x) && this.position.x + this.size.x >= rect.position.x) &&
				((this.position.y <= (rect.position.y + rect.size.y)) && ((this.position.y + this.size.y) >= rect.position.y));				
	}
	public int getArea()
	{
		return this.size.x * this.size.y;
	}
	public int getPerimeter()
	{
		return (this.size.x + this.size.y) * 2;
	}
	public Rectangle2Di getIntersection(Rectangle2Di rectangle)
	{
		return new Rectangle2Di(
				new Point2i(Math.max(-this.position.x, 0),Math.max (-this.position.y, 0)),
				new Point2i(Math.min((this.position.x + this.size.x),(rectangle.position.x + rectangle.size.x)),
						Math.min((this.position.y + this.size.y),(rectangle.position.y + rectangle.size.y)))
			);
	}
	public float inBorderToInside(Point2i point,float borderThickness)
	{
		float xFactorA = borderThickness/(point.x - this.position.x);
		float xFactorB = borderThickness/(this.position.x + this.size.x - point.x);
		
		float yFactorA = borderThickness/(point.y - this.position.y);
		float yFactorB = borderThickness/(this.position.y + this.size.y - point.y);
		if(((xFactorA < 0) ||
				(xFactorB < 0)) ||
				((yFactorA < 0) ||
				(yFactorB < 0))
				) return 0.0f; // outside
		float xFactor = (xFactorA < xFactorB)?xFactorA:xFactorB;
		float yFactor = (yFactorA < yFactorB)?yFactorA:yFactorB;
		return (xFactor < yFactor)?xFactor:yFactor;
		
		
	}
	/*
	public float inBorderToOutside(Point2i point,float borderThickness)
	{
		
	}
	public float inBorderToMiddle(Point2i point,float borderThickness)
	{
		
	}
	public float inBorderToInsideWithRadius(Point2i point,float borderThickness)
	{
		
	}
	public float inBorderToOutsideWithRadius(Point2i point,float borderThickness)
	{
		
	}
	public float inBorderToMiddleWithRadius(Point2i point,float borderThickness)
	{
		
	}*/
	
}
