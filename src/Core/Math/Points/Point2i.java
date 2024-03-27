package Core.Math.Points;

import java.awt.Point;

public class Point2i {
	public int x;
	public int y;
	public Point2i()
	{
		x = 0;
		y = 0;
	}
	public Point2i(int X,int Y)
	{
		x = X;
		y = Y;
	}
	public Point2i(Point2i copy)
	{
		x = copy.x;
		y = copy.y;
	}
	public void add(Point2i point)
	{
		this.x += point.x;
		this.y += point.y;
	}
	public void add(int num)
	{
		this.x += num;
		this.y += num;
	}
	public void sub(Point2i point)
	{
		this.x -= point.x;
		this.y -= point.y;
	}
	public void sub(int num)
	{
		this.x -= num;
		this.y -= num;
	}
	public void mul(Point2i point)
	{
		this.x *= point.x;
		this.y *= point.y;
	}
	public void mul(int num)
	{
		this.x *= num;
		this.y *= num;
	}
	public void div(Point2i point)
	{
		this.x /= point.x;
		this.y /= point.y;
	}
	public void div(int num)
	{
		this.x /= num;
		this.y /= num;
	}
	public double len(Point2i point)
	{
		return Math.sqrt(((this.x * this.x) - (point.x * point.x)) + ((this.y * this.y) - (point.y * point.y)));
	}
	public static Point2i add(Point2i a,Point2i b)
	{
		return new Point2i(a.x + b.x,a.y + b.y);
	}
	public static Point2i toPoint2i(java.awt.Point p)
	{
		return new Point2i(p.x,p.y);
	}
	@Override
	public String toString()
	{
		return "("+x+","+y+")";
	}
}