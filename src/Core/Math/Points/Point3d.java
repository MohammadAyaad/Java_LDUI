package Core.Math.Points;

public class Point3d {
	public double x;
	public double y;
	public double z;
	public Point3d()
	{
		x = 0;
		y = 0;
		z = 0;
	}
	public Point3d(double X,double Y,double Z)
	{
		x = X;
		y = Y;
		z = Z;
	}
	public Point3d(Point3d copy)
	{
		x = copy.x;
		y = copy.y;
		z = copy.z;
	}
	public void add(Point3d point)
	{
		this.x += point.x;
		this.y += point.y;
		this.z += point.z;
	}
	public void add(double num)
	{
		this.x += num;
		this.y += num;
		this.z += num;
	}
	public void sub(Point3d point)
	{
		this.x -= point.x;
		this.y -= point.y;
		this.z -= point.z;
	}
	public void sub(double num)
	{
		this.x -= num;
		this.y -= num;
		this.z -= num;
	}
	public void mul(Point3d point)
	{
		this.x *= point.x;
		this.y *= point.y;
		this.z *= point.z;
	}
	public void mul(double num)
	{
		this.x *= num;
		this.y *= num;
		this.z *= num;
	}
	public void div(Point3d point)
	{
		this.x /= point.x;
		this.y /= point.y;
		this.z /= point.z;
	}
	public void div(double num)
	{
		this.x /= num;
		this.y /= num;
		this.z /= num;
	}
	public double len(Point3d point)
	{
		return Math.sqrt(((this.x * this.x) - (point.x * point.x)) + ((this.y * this.y) - (point.y * point.y)) + ((this.z * this.z) - (point.z * point.z)));
	}
	
}
