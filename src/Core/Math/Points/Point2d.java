package Core.Math.Points;

public class Point2d {
	public double x;
	public double y;
	public Point2d()
	{
		x = 0;
		y = 0;
	}
	public Point2d(double X,double Y)
	{
		x = X;
		y = Y;
	}
	public Point2d(Point2d copy)
	{
		x = copy.x;
		y = copy.y;
	}
	public void add(Point2d point)
	{
		this.x += point.x;
		this.y += point.y;
	}
	public void add(double num)
	{
		this.x += num;
		this.y += num;
	}
	public void sub(Point2d point)
	{
		this.x -= point.x;
		this.y -= point.y;
	}
	public void sub(double num)
	{
		this.x -= num;
		this.y -= num;
	}
	public void mul(Point2d point)
	{
		this.x *= point.x;
		this.y *= point.y;
	}
	public void mul(double num)
	{
		this.x *= num;
		this.y *= num;
	}
	public void div(Point2d point)
	{
		this.x /= point.x;
		this.y /= point.y;
	}
	public void div(double num)
	{
		this.x /= num;
		this.y /= num;
	}
	public double len(Point2d point)
	{
		return Math.sqrt(((this.x * this.x) - (point.x * point.x)) + ((this.y * this.y) - (point.y * point.y)));
	}
}
