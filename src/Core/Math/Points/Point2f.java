package Core.Math.Points;

public class Point2f {
	public float x;
	public float y;
	public Point2f()
	{
		x = 0;
		y = 0;
	}
	public Point2f(float X,float Y)
	{
		x = X;
		y = Y;
	}
	public Point2f(Point2f copy)
	{
		x = copy.x;
		y = copy.y;
	}
	public void add(Point2f point)
	{
		this.x += point.x;
		this.y += point.y;
	}
	public void add(float num)
	{
		this.x += num;
		this.y += num;
	}
	public void sub(Point2f point)
	{
		this.x -= point.x;
		this.y -= point.y;
	}
	public void sub(float num)
	{
		this.x -= num;
		this.y -= num;
	}
	public void mul(Point2f point)
	{
		this.x *= point.x;
		this.y *= point.y;
	}
	public void mul(float num)
	{
		this.x *= num;
		this.y *= num;
	}
	public void div(Point2f point)
	{
		this.x /= point.x;
		this.y /= point.y;
	}
	public void div(float num)
	{
		this.x /= num;
		this.y /= num;
	}
	public double len(Point2f point)
	{
		return Math.sqrt(((this.x * this.x) - (point.x * point.x)) + ((this.y * this.y) - (point.y * point.y)));
	}
	
}
