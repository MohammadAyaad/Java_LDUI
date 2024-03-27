package Core.Graphics.Color;

public class Color3f {
	public float red;
	public float green;
	public float blue;
	public Color3f()
	{
		this.red = 0;
		this.green = 0;
		this.blue = 0;
	}
	public Color3f(float red,float green,float blue)
	{
		this.red = red;
		this.green = green;
		this.blue = blue;
	}
	public void set(float red,float green,float blue)
	{
		this.red = red;
		this.green = green;
		this.blue = blue;
	}
}
