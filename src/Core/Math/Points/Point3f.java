package Core.Math.Points;

public class Point3f {
	public float x;
	public float y;
	public float z;
	public Point3f()
	{
		x = 0;
		y = 0;
		z = 0;
	}
	public Point3f(float X,float Y,float Z)
	{
		x = X;
		y = Y;
		z = Z;
	}
	public Point3f(Point3f copy)
	{
		x = copy.x;
		y = copy.y;
		z = copy.z;
	}
	public void add(Point3f point)
	{
		this.x += point.x;
		this.y += point.y;
		this.z += point.z;
	}
	public void add(float num)
	{
		this.x += num;
		this.y += num;
		this.z += num;
	}
	public void sub(Point3f point)
	{
		this.x -= point.x;
		this.y -= point.y;
		this.z -= point.z;
	}
	public void sub(float num)
	{
		this.x -= num;
		this.y -= num;
		this.z -= num;
	}
	public void mul(Point3f point)
	{
		this.x *= point.x;
		this.y *= point.y;
		this.z *= point.z;
	}
	public void mul(float num)
	{
		this.x *= num;
		this.y *= num;
		this.z *= num;
	}
	public void div(Point3f point)
	{
		this.x /= point.x;
		this.y /= point.y;
		this.z /= point.z;
	}
	public void div(float num)
	{
		this.x /= num;
		this.y /= num;
		this.z /= num;
	}
	public double len(Point3f point)
	{
		return Math.sqrt(((this.x * this.x) - (point.x * point.x)) + ((this.y * this.y) - (point.y * point.y)) + ((this.z * this.z) - (point.z * point.z)));
	}
	public boolean Get2DProjection(float FOV,float near,Point2f point)
	{
		if(z > near)
		{
			point.x = (float)((this.x/(this.z * Math.sin(FOV))));
			point.y = (float)((this.y/(this.z * Math.sin(FOV))));
			return true;
		}
		else
		{
			return false;
		}
	}
	
}
