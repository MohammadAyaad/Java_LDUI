package Core.Math.Points;

public class Point2l {
	public long x;
	public long y;
	public Point2l()
	{
		x = 0;
		y = 0;
	}
	public Point2l(long X,long Y)
	{
		x = X;
		y = Y;
	}
	public Point2l(Point2l copy)
	{
		x = copy.x;
		y = copy.y;
	}
	public void add(Point2l point)
	{
		this.x += point.x;
		this.y += point.y;
	}
	public void add(long num)
	{
		this.x += num;
		this.y += num;
	}
	public void sub(Point2l point)
	{
		this.x -= point.x;
		this.y -= point.y;
	}
	public void sub(long num)
	{
		this.x -= num;
		this.y -= num;
	}
	public void mul(Point2l point)
	{
		this.x *= point.x;
		this.y *= point.y;
	}
	public void mul(long num)
	{
		this.x *= num;
		this.y *= num;
	}
	public void div(Point2l point)
	{
		this.x /= point.x;
		this.y /= point.y;
	}
	public void div(long num)
	{
		this.x /= num;
		this.y /= num;
	}
	public double len(Point2l point)
	{
		return Math.sqrt(((this.x * this.x) - (point.x * point.x)) + ((this.y * this.y) - (point.y * point.y)));
	}
	
}
