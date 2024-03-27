package Core.Graphics.Color;

public class Color4f {
	public float red;
	public float green;
	public float blue;
	public float alpha;
	public Color4f()
	{
		this.red = 0;
		this.green = 0;
		this.blue = 0;
		this.alpha = 0;
	}
	public Color4f(float alpha,float red,float green,float blue)
	{
		this.red = red;
		this.green = green;
		this.blue = blue;
		this.alpha = alpha;
	}
	public void set(float alpha,float red,float green,float blue)
	{
		this.red = red;
		this.green = green;
		this.blue = blue;
		this.alpha = alpha;
	}
}
